package xyz.brassgoggledcoders.patchouliprovider;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public abstract class PatchouliBookProvider implements DataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    protected final PackOutput.PathProvider datapackProvider;
    protected final PackOutput.PathProvider assetsProvider;

    private final String locale;
    private final String modid;

    public PatchouliBookProvider(PackOutput packOutput, String modid, String locale) {
        this.datapackProvider = packOutput.createPathProvider(PackOutput.Target.DATA_PACK, "patchouli_books");
        this.assetsProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "patchouli_books");
        this.modid = modid;
        this.locale = locale;
    }

    /**
     * Performs this provider's action.
     *
     * @param cache the cache
     * @return the completable future
     */
    @Override
    public CompletableFuture<?> run(@Nonnull CachedOutput cache) {
        List<CompletableFuture<?>> list = new ArrayList<>();
        this.addBooks(book -> {
            if(!book.getUseResourcePack()) {
                LOGGER.error("Book {} is not using the resource pack. As of Patchouli 1.20 books that aren't located in '.minecraft/patchouli_books' must specify 'use_resource_pack:true'. ", book.getId());
                LOGGER.error("Please consider setting 'useResourcePack' to 'true' by calling 'setUseResourcePack(true)' on the BookBuilder.");
            }
            list.add(saveBook(cache, book.toJson(), book.getId()));
            for (CategoryBuilder category : book.getCategories()) {
                list.add(saveCategory(cache, category.toJson(), book.getId(), category.getId(), book.getUseResourcePack()));
                for (EntryBuilder entry : category.getEntries()) {
                    list.add(saveEntry(cache, entry.toJson(), book.getId(), entry.getId(), book.getUseResourcePack()));
                }
            }
        });
        return CompletableFuture.allOf(list.toArray((p_253414_) -> {
            return new CompletableFuture[p_253414_];
        }));
    }

    protected abstract void addBooks(Consumer<BookBuilder> consumer);

    private CompletableFuture<?> saveEntry(CachedOutput cache, JsonObject json, ResourceLocation bookId, ResourceLocation id, boolean useResourcePack) {
        String pathSuffix = bookId.getPath() + "/" + locale + "/entries/" + id.getPath();
        PackOutput.PathProvider provider = useResourcePack ? assetsProvider : datapackProvider;
        return DataProvider.saveStable(cache, json, provider.json(new ResourceLocation(bookId.getNamespace(), pathSuffix)));
    }

    private CompletableFuture<?> saveCategory(CachedOutput cache, JsonObject json, ResourceLocation bookId, ResourceLocation id, boolean useResourcePack) {
        String pathSuffix = bookId.getPath() + "/" + locale + "/categories/" + id.getPath();
        PackOutput.PathProvider provider = useResourcePack ? assetsProvider : datapackProvider;
        return DataProvider.saveStable(cache, json, provider.json(new ResourceLocation(bookId.getNamespace(), pathSuffix)));
    }

    private CompletableFuture<?> saveBook(CachedOutput cache, JsonObject json, ResourceLocation bookId) {
        //The book json needs to remain in 'data', see: https://vazkiimods.github.io/Patchouli/docs/upgrading/upgrade-guide-117
        String pathSuffix = bookId.getPath() + "/book";
        return DataProvider.saveStable(cache, json, datapackProvider.json(new ResourceLocation(bookId.getNamespace(), pathSuffix)));
    }

    public BookBuilder createBookBuilder(String id, String name, String landingText) {
        return new BookBuilder(modid, id, name, landingText);
    }

    /**
     * Gets a name for this provider, to use in logging.
     */
    @Nonnull
    @Override
    public String getName() {
        return "Patchouli Book Provider";
    }
}

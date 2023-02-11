package xyz.brassgoggledcoders.patchouliprovider;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public abstract class PatchouliBookProvider implements DataProvider {
    private static final Logger LOGGER = LogManager.getLogger();

    private final PackOutput packOutput;
    private final String locale;
    private final String modid;

    public PatchouliBookProvider(PackOutput packOutput, String modid, String locale) {
        this.packOutput = packOutput;
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
        addBooks(book -> {
            saveBook(cache, book.toJson(), book.getId());
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
        Path mainOutput = packOutput.getOutputFolder();
        String pathSuffix = makeBookPath(bookId, useResourcePack) + "/" + locale + "/entries/" + id.getPath() + ".json";
        Path outputPath = mainOutput.resolve(pathSuffix);
        return DataProvider.saveStable(cache, json, outputPath);
    }

    private CompletableFuture<?> saveCategory(CachedOutput cache, JsonObject json, ResourceLocation bookId, ResourceLocation id, boolean useResourcePack) {
        Path mainOutput = packOutput.getOutputFolder();
        String pathSuffix = makeBookPath(bookId, useResourcePack) + "/" + locale + "/categories/" + id.getPath() + ".json";
        Path outputPath = mainOutput.resolve(pathSuffix);
        return DataProvider.saveStable(cache, json, outputPath);
    }

    private CompletableFuture<?> saveBook(CachedOutput cache, JsonObject json, ResourceLocation bookId) {
        Path mainOutput = packOutput.getOutputFolder();
        //The book json needs to remain in 'data', see: https://vazkiimods.github.io/Patchouli/docs/upgrading/upgrade-guide-117
        String pathSuffix = makeBookPath(bookId, false) + "/book.json";
        Path outputPath = mainOutput.resolve(pathSuffix);
        return DataProvider.saveStable(cache, json, outputPath);
    }

    public BookBuilder createBookBuilder(String id, String name, String landingText) {
        return new BookBuilder(modid, id, name, landingText);
    }

    private String makeBookPath(ResourceLocation bookId, boolean useResourcePack) {
        String location = useResourcePack ? "assets/" : "data/";
        return location + bookId.getNamespace() + "/patchouli_books/" + bookId.getPath();
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

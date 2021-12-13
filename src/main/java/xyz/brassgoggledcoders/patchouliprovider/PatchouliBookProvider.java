package xyz.brassgoggledcoders.patchouliprovider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

public abstract class PatchouliBookProvider implements DataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    private final DataGenerator generator;
    private final String locale;
    private final String modid;

    public PatchouliBookProvider(DataGenerator gen, String modid, String locale) {
        this.generator = gen;
        this.modid = modid;
        this.locale = locale;
    }

    /**
     * Performs this provider's action.
     *
     * @param cache the cache
     */
    @Override
    public void run(@Nonnull HashCache cache) {
        addBooks(book -> {
            saveBook(cache, book.toJson(), book.getId());
            for (CategoryBuilder category : book.getCategories()) {
                saveCategory(cache, category.toJson(), book.getId(), category.getId(), book.getUseResourcePack());
                for (EntryBuilder entry : category.getEntries()) {
                    saveEntry(cache, entry.toJson(), book.getId(), entry.getId(), book.getUseResourcePack());
                }
            }
        });
    }

    protected abstract void addBooks(Consumer<BookBuilder> consumer);

    private void saveEntry(HashCache cache, JsonObject json, ResourceLocation bookId, ResourceLocation id, boolean useResourcePack) {
        Path mainOutput = generator.getOutputFolder();
        String pathSuffix = makeBookPath(bookId, useResourcePack) + "/" + locale + "/entries/" + id.getPath() + ".json";
        Path outputPath = mainOutput.resolve(pathSuffix);
        try {
            DataProvider.save(GSON, cache, json, outputPath);
        } catch (IOException e) {
            LOGGER.error("Couldn't save entry to {}", outputPath, e);
        }
    }

    private void saveCategory(HashCache cache, JsonObject json, ResourceLocation bookId, ResourceLocation id, boolean useResourcePack) {
        Path mainOutput = generator.getOutputFolder();
        String pathSuffix = makeBookPath(bookId, useResourcePack) + "/" + locale + "/categories/" + id.getPath() + ".json";
        Path outputPath = mainOutput.resolve(pathSuffix);
        try {
            DataProvider.save(GSON, cache, json, outputPath);
        } catch (IOException e) {
            LOGGER.error("Couldn't save category to {}", outputPath, e);
        }
    }

    private void saveBook(HashCache cache, JsonObject json, ResourceLocation bookId) {
        Path mainOutput = generator.getOutputFolder();
        //The book json needs to remain in 'data', see: https://vazkiimods.github.io/Patchouli/docs/upgrading/upgrade-guide-117
        String pathSuffix = makeBookPath(bookId, false) + "/book.json";
        Path outputPath = mainOutput.resolve(pathSuffix);
        try {
            DataProvider.save(GSON, cache, json, outputPath);
        } catch (IOException e) {
            LOGGER.error("Couldn't save book to {}", outputPath, e);
        }
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

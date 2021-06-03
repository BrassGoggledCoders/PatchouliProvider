package xyz.brassgoggledcoders.patchouliprovider.page;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.AbstractPageBuilder;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

import java.util.ArrayList;
import java.util.List;

public class RelationsPageBuilder extends AbstractPageBuilder<RelationsPageBuilder> {
    private final List<ResourceLocation> entries = new ArrayList<>();
    private String title;
    private String text;

    public RelationsPageBuilder(EntryBuilder entryBuilder) {
        super("relations", entryBuilder);
    }

    @Override
    protected void serialize(JsonObject json) {
        JsonArray entries = new JsonArray();
        for (ResourceLocation entry : this.entries) {
            entries.add(entry.toString());
        }
        json.add("entries", entries);

        if (title != null) {
            json.addProperty("title", title);
        }
        if (text != null) {
            json.addProperty("text", text);
        }
    }

    public RelationsPageBuilder addEntry(ResourceLocation entry) {
        entries.add(entry);
        return this;
    }

    public RelationsPageBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public RelationsPageBuilder setText(String text) {
        this.text = text;
        return this;
    }
}

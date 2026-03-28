package xyz.brassgoggledcoders.patchouliprovider.page;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.Identifier;
import xyz.brassgoggledcoders.patchouliprovider.AbstractPageBuilder;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

import java.util.ArrayList;
import java.util.List;

public class ImagePageBuilder extends AbstractPageBuilder<ImagePageBuilder> {
    private final List<Identifier> images = new ArrayList<>();
    private String title;
    private Boolean border;
    private String text;

    public ImagePageBuilder(Identifier image, EntryBuilder parent) {
        super("patchouli:image", parent);
        this.images.add(image);
    }

    @Override
    protected void serialize(JsonObject json) {
        JsonArray images = new JsonArray();
        for (Identifier image : this.images) {
            images.add(image.toString());
        }
        json.add("images", images);
        if (title != null) {
            json.addProperty("title", title);
        }
        if (border != null) {
            json.addProperty("border", border);
        }
        if (text != null) {
            json.addProperty("text", text);
        }
    }

    public ImagePageBuilder addImage(Identifier image) {
        images.add(image);
        return this;
    }

    public ImagePageBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ImagePageBuilder setBorder(Boolean border) {
        this.border = border;
        return this;
    }

    public ImagePageBuilder setText(String text) {
        this.text = text;
        return this;
    }
}

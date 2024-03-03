package xyz.brassgoggledcoders.patchouliprovider.page;

import com.google.gson.JsonObject;
import xyz.brassgoggledcoders.patchouliprovider.AbstractPageBuilder;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class LinkPageBuilder extends AbstractPageBuilder<LinkPageBuilder> {
    private final String url;
    private final String linkText;
    // Link page support the same attributes as Text pages, see: https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types#link-pages-patchoulilink
    private String text;
    private String title;

    public LinkPageBuilder(String url, String linkText, EntryBuilder entryBuilder) {
        super("patchouli:link", entryBuilder);
        this.url = url;
        this.linkText = linkText;
    }

    public LinkPageBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public LinkPageBuilder setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    protected void serialize(JsonObject json) {
        json.addProperty("url", url);
        json.addProperty("link_text", linkText);
        if (title != null) {
            json.addProperty("text", title);
        }
        if (title != null) {
            json.addProperty("title", title);
        }
    }
}

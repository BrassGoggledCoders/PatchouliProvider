package xyz.brassgoggledcoders.patchouliprovider.page;

import com.google.gson.JsonObject;
import xyz.brassgoggledcoders.patchouliprovider.AbstractPageBuilder;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class LinkPageBuilder extends AbstractPageBuilder<LinkPageBuilder> {
    private final String url;
    private final String linkText;

    public LinkPageBuilder(String url, String linkText, EntryBuilder entryBuilder) {
        super("patchouli:link", entryBuilder);
        this.url = url;
        this.linkText = linkText;
    }

    @Override
    protected void serialize(JsonObject json) {
        json.addProperty("url", url);
        json.addProperty("link_text", linkText);
    }
}

package xyz.brassgoggledcoders.patchouliprovider.page;

import com.google.gson.JsonObject;
import xyz.brassgoggledcoders.patchouliprovider.AbstractPageBuilder;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class EmptyPageBuilder extends AbstractPageBuilder<EmptyPageBuilder> {
    private final boolean drawFiller;

    public EmptyPageBuilder(boolean drawFiller, EntryBuilder entryBuilder) {
        super("patchouli:empty", entryBuilder);
        this.drawFiller = drawFiller;
    }

    @Override
    protected void serialize(JsonObject json) {
        json.addProperty("draw_filler", drawFiller);
    }
}

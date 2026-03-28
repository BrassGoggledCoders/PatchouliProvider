package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.Identifier;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class SmokingPageBuilder extends RecipePageBuilder<SmokingPageBuilder> {
    public SmokingPageBuilder(Identifier recipe, EntryBuilder entryBuilder) {
        super("patchouli:smoking", recipe, entryBuilder);
    }
}

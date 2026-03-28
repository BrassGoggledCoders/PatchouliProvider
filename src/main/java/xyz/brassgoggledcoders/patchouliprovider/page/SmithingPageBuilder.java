package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.Identifier;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class SmithingPageBuilder extends RecipePageBuilder<SmithingPageBuilder> {
    public SmithingPageBuilder(Identifier recipe, EntryBuilder entryBuilder) {
        super("patchouli:smithing", recipe, entryBuilder);
    }
}

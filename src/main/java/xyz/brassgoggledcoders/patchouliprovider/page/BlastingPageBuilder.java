package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.Identifier;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class BlastingPageBuilder extends RecipePageBuilder<BlastingPageBuilder> {
    public BlastingPageBuilder(Identifier recipe, EntryBuilder entryBuilder) {
        super("patchouli:blasting", recipe, entryBuilder);
    }
}

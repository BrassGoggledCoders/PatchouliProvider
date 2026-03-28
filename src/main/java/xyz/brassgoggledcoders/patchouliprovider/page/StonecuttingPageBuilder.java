package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.Identifier;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class StonecuttingPageBuilder extends RecipePageBuilder<StonecuttingPageBuilder> {
    public StonecuttingPageBuilder(Identifier recipe, EntryBuilder entryBuilder) {
        super("patchouli:stonecutting", recipe, entryBuilder);
    }
}

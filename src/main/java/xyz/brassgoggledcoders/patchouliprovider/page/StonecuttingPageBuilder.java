package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class StonecuttingPageBuilder extends RecipePageBuilder<StonecuttingPageBuilder> {
    public StonecuttingPageBuilder(ResourceLocation recipe, EntryBuilder entryBuilder) {
        super("patchouli:stonecutting", recipe, entryBuilder);
    }
}

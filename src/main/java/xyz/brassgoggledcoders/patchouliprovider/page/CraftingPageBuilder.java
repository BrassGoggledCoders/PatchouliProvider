package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class CraftingPageBuilder extends RecipePageBuilder<CraftingPageBuilder> {
    public CraftingPageBuilder(ResourceLocation recipe, EntryBuilder entryBuilder) {
        super("patchouli:crafting", recipe, entryBuilder);
    }
}

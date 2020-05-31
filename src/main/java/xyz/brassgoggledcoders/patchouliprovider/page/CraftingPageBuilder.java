package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class CraftingPageBuilder extends RecipePageBuilder<CraftingPageBuilder> {
    public CraftingPageBuilder(ResourceLocation recipe, EntryBuilder entryBuilder) {
        super("crafting", recipe, entryBuilder);
    }
}

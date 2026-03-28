package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.Identifier;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class CraftingPageBuilder extends RecipePageBuilder<CraftingPageBuilder> {
    public CraftingPageBuilder(Identifier recipe, EntryBuilder entryBuilder) {
        super("patchouli:crafting", recipe, entryBuilder);
    }
}

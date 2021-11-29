package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class SmeltingPageBuilder extends RecipePageBuilder<SmeltingPageBuilder> {
    public SmeltingPageBuilder(ResourceLocation recipe, EntryBuilder entryBuilder) {
        super("patchouli:smelting", recipe, entryBuilder);
    }
}

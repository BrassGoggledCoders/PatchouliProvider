package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class SmeltingPageBuilder extends RecipePageBuilder<SmeltingPageBuilder> {
    public SmeltingPageBuilder(ResourceLocation recipe, EntryBuilder entryBuilder) {
        super("smelting", recipe, entryBuilder);
    }
}

package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class CampfirePageBuilder extends RecipePageBuilder<CampfirePageBuilder> {
    public CampfirePageBuilder(ResourceLocation recipe, EntryBuilder entryBuilder) {
        super("patchouli:campfire", recipe, entryBuilder);
    }
}

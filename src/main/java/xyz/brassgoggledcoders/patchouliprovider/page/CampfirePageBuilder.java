package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.Identifier;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class CampfirePageBuilder extends RecipePageBuilder<CampfirePageBuilder> {
    public CampfirePageBuilder(Identifier recipe, EntryBuilder entryBuilder) {
        super("patchouli:campfire", recipe, entryBuilder);
    }
}

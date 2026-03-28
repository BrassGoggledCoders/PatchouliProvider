package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.resources.Identifier;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class SmeltingPageBuilder extends RecipePageBuilder<SmeltingPageBuilder> {
    public SmeltingPageBuilder(Identifier recipe, EntryBuilder entryBuilder) {
        super("patchouli:smelting", recipe, entryBuilder);
    }
}

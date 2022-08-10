package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class SmithingPageBuilder extends RecipePageBuilder<SmithingPageBuilder> {
	public SmithingPageBuilder(ResourceLocation recipe, EntryBuilder entryBuilder) {
		super("patchouli:smithing", recipe, entryBuilder);
	}
}
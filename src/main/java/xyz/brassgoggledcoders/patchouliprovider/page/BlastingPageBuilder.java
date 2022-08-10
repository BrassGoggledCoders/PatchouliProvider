package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class BlastingPageBuilder extends RecipePageBuilder<BlastingPageBuilder> {
	public BlastingPageBuilder(ResourceLocation recipe, EntryBuilder entryBuilder) {
		super("patchouli:blasting", recipe, entryBuilder);
	}
}
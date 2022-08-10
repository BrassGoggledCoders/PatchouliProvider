package xyz.brassgoggledcoders.patchouliprovider.page;

import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

public class SmokingPageBuilder extends RecipePageBuilder<SmokingPageBuilder> {
	public SmokingPageBuilder(ResourceLocation recipe, EntryBuilder entryBuilder) {
		super("patchouli:smoking", recipe, entryBuilder);
	}
}
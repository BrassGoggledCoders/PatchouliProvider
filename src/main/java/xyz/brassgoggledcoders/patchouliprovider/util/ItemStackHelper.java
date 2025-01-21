package xyz.brassgoggledcoders.patchouliprovider.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;

public class ItemStackHelper {
	private static final Gson GSON = new GsonBuilder().create();

	public static String serializeStack(ItemStack stack, HolderLookup.Provider registries) {
		return new ItemInput(stack.getItemHolder(), stack.getComponentsPatch())
				.serialize(registries) + (stack.getCount() == 1 ? "" :("#" + stack.getCount()));
	}
}

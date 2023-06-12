package xyz.brassgoggledcoders.patchouliprovider.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.item.ItemStack;

public class ItemStackHelper {
	private static final Gson GSON = new GsonBuilder().create();

	public static String serializeStack(ItemStack stack) {
		StringBuilder builder = new StringBuilder();
		builder.append(BuiltInRegistries.ITEM.getKey(stack.getItem()));

		int count = stack.getCount();
		if (count > 1) {
			builder.append("#");
			builder.append(count);
		}

		if (stack.hasTag()) {
			Dynamic<?> dyn = new Dynamic<>(NbtOps.INSTANCE, stack.getTag());
			JsonElement j = dyn.convert(JsonOps.INSTANCE).getValue();
			builder.append(GSON.toJson(j));
		}

		return builder.toString();
	}
}

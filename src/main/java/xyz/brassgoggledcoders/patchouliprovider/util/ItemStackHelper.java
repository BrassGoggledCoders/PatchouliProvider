package xyz.brassgoggledcoders.patchouliprovider.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.item.ItemStack;

public class ItemStackHelper {
	private static final Gson GSON = new GsonBuilder().create();

	public static String serializeStack(ItemStack stack, HolderLookup.Provider provider) {
		StringBuilder builder = new StringBuilder();
		builder.append(BuiltInRegistries.ITEM.getKey(stack.getItem()));

		int count = stack.getCount();
		if (count > 1) {
			builder.append("#");
			builder.append(count);
		}

		if (!stack.getComponentsPatch().isEmpty()) {
			DataComponentPatch data = stack.getComponentsPatch();
			DataComponentPatch.CODEC.encodeStart(provider.createSerializationContext(JsonOps.INSTANCE), data).result()
					.ifPresent(e -> builder.append(e));
		}

		return builder.toString();
	}

	public static JsonElement stackToJson(ItemStack stack, HolderLookup.Provider provider) {
		JsonObject ret = new JsonObject();
		ret.addProperty("item", BuiltInRegistries.ITEM.getKey(stack.getItem()).toString());
		if (stack.getCount() != 1) {
			ret.addProperty("count", stack.getCount());
		}
		if (!stack.getComponentsPatch().isEmpty()) {
			DataComponentPatch data = stack.getComponentsPatch();
			DataComponentPatch.CODEC.encodeStart(provider.createSerializationContext(JsonOps.INSTANCE), data).result().ifPresent(e -> ret.add("components", e));
		}
		return ret;
	}
}

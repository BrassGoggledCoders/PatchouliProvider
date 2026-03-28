package xyz.brassgoggledcoders.patchouliprovider.util;

import com.mojang.serialization.DynamicOps;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemStackHelper {
	public static String serializeStack(ItemStackTemplate stackTemplate, HolderLookup.Provider registries) {
		return serialize(stackTemplate, registries) + (stackTemplate.count() == 1 ? "" :("#" + stackTemplate.count()));
	}

	private static String serialize(ItemStackTemplate stack, HolderLookup.Provider levelRegistry) {
		StringBuilder stringbuilder = new StringBuilder(getItemName(stack.item()));
		String s = serializeComponents(stack.components(), levelRegistry);
		if (!s.isEmpty()) {
			stringbuilder.append('[');
			stringbuilder.append(s);
			stringbuilder.append(']');
		}

		return stringbuilder.toString();
	}

	private static String serializeComponents(DataComponentPatch components, HolderLookup.Provider levelRegistries) {
		DynamicOps<Tag> dynamicops = levelRegistries.createSerializationContext(NbtOps.INSTANCE);
		return components.entrySet().stream().flatMap(p_465851_ -> {
			DataComponentType<?> datacomponenttype = p_465851_.getKey();
			Identifier identifier = BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(datacomponenttype);
			if (identifier == null) {
				return Stream.empty();
			} else {
				Optional<?> optional = p_465851_.getValue();
				if (optional.isPresent()) {
					TypedDataComponent<?> typeddatacomponent = TypedDataComponent.createUnchecked(datacomponenttype, optional.get());
					return typeddatacomponent.encodeValue(dynamicops).result().stream().map(p_465849_ -> identifier + "=" + p_465849_);
				} else {
					return Stream.of("!" + identifier);
				}
			}
		}).collect(Collectors.joining(String.valueOf(',')));
	}

	private static String getItemName(Holder<Item> item) {
		return item.unwrapKey().<Object>map(ResourceKey::identifier).orElseGet(() -> "unknown[" + item + "]").toString();
	}
}

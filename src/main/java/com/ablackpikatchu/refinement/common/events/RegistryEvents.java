package com.ablackpikatchu.refinement.common.events;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.anotation.registries.RegisterBlock;
import com.ablackpikatchu.refinement.core.anotation.registries.RegisterItem;
import com.ablackpikatchu.refinement.core.util.ReflectionsUtils;
import com.ablackpikatchu.refinement.core.util.lists.ClassLists;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD)
public class RegistryEvents {

	private RegistryEvents() {
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		ReflectionsUtils.getFieldsAnnotatedWith(
				ClassLists.REGISTRIES_CLASSES, RegisterItem.class)
				.forEach(field -> {
					try {
						if (field.isAccessible() && field.get(field.getDeclaringClass()) instanceof Item) {
							Item item = (Item) field.get(field.getDeclaringClass());
							item = item.setRegistryName(new ResourceLocation(MOD_ID,
									field.getAnnotation(RegisterItem.class).registryName()));
							if (item != Items.AIR)
								event.getRegistry().register(item);
						}
					} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
					}
				});
	}

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		ReflectionsUtils.getFieldsAnnotatedWith(
				ClassLists.REGISTRIES_CLASSES, RegisterBlock.class)
				.forEach(field -> {
					try {
						if (field.isAccessible() && field.get(field.getDeclaringClass()) instanceof Block) {
							Block block = (Block) field.get(field.getDeclaringClass());
							block.setRegistryName(new ResourceLocation(MOD_ID,
									field.getAnnotation(RegisterBlock.class).registryName()));
							if (block != Blocks.AIR)
								event.getRegistry().register(block);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
					}
				});
	}
}

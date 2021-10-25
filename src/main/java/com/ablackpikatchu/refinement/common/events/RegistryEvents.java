package com.ablackpikatchu.refinement.common.events;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.objectweb.asm.Type;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.anotation.registries.HoldsRegstries;
import com.ablackpikatchu.refinement.core.anotation.registries.RegisterBlock;
import com.ablackpikatchu.refinement.core.anotation.registries.RegisterItem;
import com.ablackpikatchu.refinement.core.util.ReflectionsUtils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forgespi.language.ModFileScanData;

@HoldsRegstries
@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD)
public class RegistryEvents {

	private static final ArrayList<Class<?>> REGISTRY_CLASSES = new ArrayList<>();

	public static void init() {
		final List<ModFileScanData.AnnotationData> annotations = ModList.get().getAllScanData().stream()
				.map(ModFileScanData::getAnnotations).flatMap(Collection::stream)
				.filter(a -> a.getAnnotationType().equals(Type.getType(HoldsRegstries.class)))
				.collect(Collectors.toList());

		annotations.stream().filter(a -> Type.getType(HoldsRegstries.class).equals(a.getAnnotationType()))
				.filter(a -> a.getTargetType() == ElementType.TYPE).forEach(data -> {
					try {
						REGISTRY_CLASSES.add(Class.forName(data.getClassType().getClassName(), false,
								RegistryEvents.class.getClassLoader()));
					} catch (ClassNotFoundException e) {
					}
				});
	}

	private RegistryEvents() {
	}

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		ReflectionsUtils.getFieldsAnnotatedWith(REGISTRY_CLASSES, RegisterItem.class).forEach(field -> {
			try {
				if (field.isAccessible() && field.get(field.getDeclaringClass()) instanceof Item) {
					Item item = (Item) field.get(field.getDeclaringClass());
					item = item.setRegistryName(
							new ResourceLocation(field.getDeclaringClass().getAnnotation(HoldsRegstries.class).modId(),
									field.getAnnotation(RegisterItem.class).registryName()));
					event.getRegistry().register(item);
				}
			} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
			}
		});
	}

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		ReflectionsUtils.getFieldsAnnotatedWith(REGISTRY_CLASSES, RegisterBlock.class).forEach(field -> {
			try {
				if (field.isAccessible() && field.get(field.getDeclaringClass()) instanceof Block) {
					Block block = (Block) field.get(field.getDeclaringClass());
					block.setRegistryName(
							new ResourceLocation(field.getDeclaringClass().getAnnotation(HoldsRegstries.class).modId(),
									field.getAnnotation(RegisterBlock.class).registryName()));
					event.getRegistry().register(block);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
			}
		});
	}
}

package com.ablackpikatchu.refinement.core.util.lists;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class ItemLists {

	public static Item[] MOLDS = { ItemInit.GEM_MOLD.get(), ItemInit.INGOT_MOLD.get(), ItemInit.COGWHEEL_MOLD.get() };

	public static boolean isItemInTheList(Item[] items, Item item) {
		for (Item listItem : items) {
			if (listItem == item)
				return true;
		}
		return false;
	}

	public static boolean isMold(Item item) {
		return isItemInTheList(MOLDS, item);
	}

	public static Item[] DYES = { Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.CYAN_DYE, Items.GRAY_DYE,
			Items.GREEN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE, Items.LIME_DYE, Items.MAGENTA_DYE,
			Items.ORANGE_DYE, Items.PINK_DYE, Items.PURPLE_DYE, Items.RED_DYE, Items.WHITE_DYE, Items.YELLOW_DYE };

	public static Item[] LOGS = { Items.OAK_LOG, Items.ACACIA_LOG, Items.BIRCH_LOG, Items.DARK_OAK_LOG,
			Items.JUNGLE_LOG, Items.SPRUCE_LOG };

	public static Item[] NON_WOOD_STAIRS = { Items.ANDESITE_STAIRS, Items.BLACKSTONE_STAIRS, Items.BRICK_STAIRS,
			Items.COBBLESTONE_STAIRS, Items.DARK_PRISMARINE_STAIRS, Items.DIORITE_STAIRS, Items.END_STONE_BRICK_STAIRS,
			Items.GRANITE_STAIRS, Items.MOSSY_COBBLESTONE_STAIRS, Items.MOSSY_STONE_BRICK_STAIRS,
			Items.NETHER_BRICK_STAIRS, Items.POLISHED_ANDESITE_STAIRS, Items.POLISHED_BLACKSTONE_BRICK_STAIRS,
			Items.POLISHED_BLACKSTONE_STAIRS, Items.POLISHED_DIORITE_STAIRS, Items.POLISHED_GRANITE_STAIRS,
			Items.PRISMARINE_BRICK_STAIRS, Items.PRISMARINE_STAIRS, Items.PURPUR_STAIRS, Items.QUARTZ_STAIRS,
			Items.RED_NETHER_BRICK_STAIRS, Items.RED_SANDSTONE_STAIRS, Items.SANDSTONE_STAIRS,
			Items.SMOOTH_QUARTZ_STAIRS, Items.SMOOTH_RED_SANDSTONE_STAIRS, Items.SMOOTH_SANDSTONE_STAIRS,
			Items.STONE_BRICK_STAIRS, Items.STONE_STAIRS };

	public static Item[] NON_WOOD_SLABS = { Items.ANDESITE_SLAB, Items.BLACKSTONE_SLAB, Items.BRICK_SLAB,
			Items.COBBLESTONE_SLAB, Items.DARK_PRISMARINE_SLAB, Items.DIORITE_SLAB, Items.END_STONE_BRICK_SLAB,
			Items.GRANITE_STAIRS, Items.MOSSY_COBBLESTONE_STAIRS, Items.MOSSY_STONE_BRICK_STAIRS,
			Items.NETHER_BRICK_SLAB, Items.POLISHED_ANDESITE_SLAB, Items.POLISHED_BLACKSTONE_BRICK_SLAB,
			Items.POLISHED_BLACKSTONE_SLAB, Items.POLISHED_DIORITE_SLAB, Items.POLISHED_GRANITE_SLAB,
			Items.PRISMARINE_BRICK_SLAB, Items.PRISMARINE_SLAB, Items.PURPUR_SLAB, Items.QUARTZ_SLAB,
			Items.RED_NETHER_BRICK_SLAB, Items.RED_SANDSTONE_SLAB, Items.SANDSTONE_SLAB, Items.SMOOTH_QUARTZ_SLAB,
			Items.SMOOTH_RED_SANDSTONE_SLAB, Items.SMOOTH_SANDSTONE_SLAB, Items.STONE_BRICK_SLAB, Items.STONE_SLAB };

}

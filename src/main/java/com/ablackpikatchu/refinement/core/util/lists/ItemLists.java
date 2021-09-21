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

	public static Item[] NON_WOOD_STAIRS = { Items.BRICK_STAIRS, Items.ANDESITE_STAIRS };
	
	public static Item[] NON_WOOD_SLABS = { Items.BRICK_SLAB, Items.ANDESITE_SLAB };

}

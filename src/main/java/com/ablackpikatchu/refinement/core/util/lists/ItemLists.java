package com.ablackpikatchu.refinement.core.util.lists;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.Item;

public class ItemLists {

	public static Item[] MOLDS = { ItemInit.GEM_MOLD.get(), ItemInit.INGOT_MOLD.get(), ItemInit.COGWHEEL_MOLD.get() };
	
	public static boolean isItemInTheList(Item[] items, Item item) {
		for (Item listItem : items) {
			if (listItem == item) return true;
		}
		return false;
	}
	
	public static boolean isMold(Item item) {
		return isItemInTheList(MOLDS, item);
	}

}

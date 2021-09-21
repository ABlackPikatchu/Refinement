package com.ablackpikatchu.refinement.core.util.helper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PlayerHelper {

	public static boolean hasItem(PlayerEntity player, Item item) {
		for (int i = 0; i <= player.inventory.getContainerSize(); ++i) {
			if (player.inventory.getItem(i).getItem() == item)
				return true;
		}
		return false;
	}

	public static ItemStack getFirstStackMatchingItem(PlayerEntity player, Item item) {
		if (hasItem(player, item)) {
			for (int i = 0; i <= player.inventory.getContainerSize(); ++i) {
				if (player.inventory.getItem(i).getItem() == item)
					return player.inventory.getItem(i);
			}
		} else
			return ItemStack.EMPTY;
		return ItemStack.EMPTY;
	}

}
package com.ablackpikatchu.refinement.core.util.helper;

import net.minecraft.item.ItemStack;

public class ItemStackHelper {
	
	public static boolean areStacksTheSame(ItemStack source, ItemStack destination) {
		if (source.getItem() == destination.getItem()) {
			if (source.hasTag() || destination.hasTag())
				return source.getOrCreateTag().equals(destination.getOrCreateTag());
			return true;
		}
		return false;
	}

}

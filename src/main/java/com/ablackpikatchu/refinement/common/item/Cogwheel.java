package com.ablackpikatchu.refinement.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Cogwheel extends Item{

	public Cogwheel(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return this.getDefaultInstance();
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
}

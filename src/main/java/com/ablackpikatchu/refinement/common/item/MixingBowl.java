package com.ablackpikatchu.refinement.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MixingBowl extends Item {

	public MixingBowl(Properties properties) {
		super(properties);

	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		final ItemStack copy = itemStack.copy();
		copy.setDamageValue(copy.getDamageValue() + 1);
		if (copy.getDamageValue() >= copy.getMaxDamage())
			copy.shrink(1);
		return copy;
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
}

package com.ablackpikatchu.refinement.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RefinedCoal extends Item{
	
	public RefinedCoal(Properties properties) {
		super(properties);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 800;
	}

}

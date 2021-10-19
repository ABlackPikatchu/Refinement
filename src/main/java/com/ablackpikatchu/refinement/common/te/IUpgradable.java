package com.ablackpikatchu.refinement.common.te;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;

public interface IUpgradable {
	
	default ItemStack executeDispenserBehaviour(IBlockSource dispenser, ItemStack usedStack) {
		return usedStack;
	}

}

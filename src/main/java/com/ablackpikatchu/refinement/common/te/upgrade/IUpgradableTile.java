package com.ablackpikatchu.refinement.common.te.upgrade;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;

public interface IUpgradableTile {
	
	default ItemStack executeDispenserBehaviour(IBlockSource dispenser, ItemStack usedStack) {
		return usedStack;
	}
	
	default int getSlotForUpgrade(Upgrade upgrade) {
		return -1;
	}

}

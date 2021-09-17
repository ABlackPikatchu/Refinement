package com.ablackpikatchu.refinement.common.slot.itemspecific;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;

public class SpeedUpgradeSlot extends Slot {

	public SpeedUpgradeSlot(IInventory inventory, int index, int xPos, int yPos) {
		super(inventory, index, xPos, yPos);
	}
	
	public SpeedUpgradeSlot(LockableLootTileEntity inventory, int index, int xPos, int yPos) {
		super((IInventory) inventory, index, xPos, yPos);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		if (stack.getItem() == ItemInit.SPEED_UPGRADE.get())
			return true;
		else
			return false;
	}

}

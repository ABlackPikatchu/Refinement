package com.ablackpikatchu.refinement.common.slot.itemspecific;

import com.ablackpikatchu.refinement.common.te.upgrade.Upgrade;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;

public class UpgradeSlot extends Slot {

	private final Upgrade upgrade;

	public UpgradeSlot(IInventory inventory, int index, int xPos, int yPos, Upgrade upgrade) {
		super(inventory, index, xPos, yPos);
		this.upgrade = upgrade;
	}

	public UpgradeSlot(LockableLootTileEntity inventory, int index, int xPos, int yPos, Upgrade upgrade) {
		super((IInventory) inventory, index, xPos, yPos);
		this.upgrade = upgrade;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		if (stack.getItem() == this.upgrade.getAsItem())
			return true;
		else
			return false;
	}

}

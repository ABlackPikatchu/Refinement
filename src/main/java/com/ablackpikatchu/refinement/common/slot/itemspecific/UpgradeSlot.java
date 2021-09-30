package com.ablackpikatchu.refinement.common.slot.itemspecific;

import com.ablackpikatchu.refinement.core.util.enums.Upgrades;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;

public class UpgradeSlot extends Slot {

	private final Upgrades upgrade;

	public UpgradeSlot(IInventory inventory, int index, int xPos, int yPos, Upgrades upgrade) {
		super(inventory, index, xPos, yPos);
		this.upgrade = upgrade;
	}

	public UpgradeSlot(LockableLootTileEntity inventory, int index, int xPos, int yPos, Upgrades upgrade) {
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

package com.ablackpikatchu.refinement.common.inventory.slot.itemspecific;

import com.ablackpikatchu.refinement.common.te.upgrade.Upgrade;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;

public class UpgradeSlot extends Slot {

	private final Upgrade upgrade;
	private final boolean active;

	public UpgradeSlot(IInventory inventory, int index, int xPos, int yPos, Upgrade upgrade) {
		super(inventory, index, xPos, yPos);
		this.upgrade = upgrade;
		this.active = true;
	}

	public UpgradeSlot(IInventory inventory, int index, int xPos, int yPos, Upgrade upgrade, boolean active) {
		super(inventory, index, xPos, yPos);
		this.upgrade = upgrade;
		this.active = active;
	}

	public UpgradeSlot(LockableLootTileEntity inventory, int index, int xPos, int yPos, Upgrade upgrade) {
		super(inventory, index, xPos, yPos);
		this.upgrade = upgrade;
		this.active = true;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		if (!active)
			return false;
		if (stack.getItem() == this.upgrade.getAsItem())
			return true;
		else
			return false;
	}

	@Override
	public boolean isActive() { return active; }

}

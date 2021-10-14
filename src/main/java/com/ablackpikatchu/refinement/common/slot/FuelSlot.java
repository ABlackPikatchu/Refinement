package com.ablackpikatchu.refinement.common.slot;

import com.ablackpikatchu.refinement.common.te.MachineTileEntity;
import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class FuelSlot extends Slot {

	public final MachineTileEntity machine;

	public FuelSlot(MachineTileEntity machine, int pIndex, int pX, int pY) {
		super((IInventory) machine, pIndex, pX, pY);
		this.machine = machine;
	}

	@Override
	public boolean mayPlace(ItemStack pStack) {
		return pStack.getItem() == ItemInit.REFINED_CARBON_INGOT.get();
	}

}

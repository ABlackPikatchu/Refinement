package com.ablackpikatchu.refinement.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemReservedSlot extends Slot {

	private final Item item;

	public ItemReservedSlot(IInventory pContainer, int pIndex, int pX, int pY, Item item) {
		super(pContainer, pIndex, pX, pY);
		this.item = item;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		if (stack.getItem() == this.item)
			return true;
		else
			return false;
	}

}

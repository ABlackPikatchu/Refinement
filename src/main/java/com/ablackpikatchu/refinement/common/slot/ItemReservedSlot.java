package com.ablackpikatchu.refinement.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemReservedSlot extends Slot {
	
	private Item item;

	public ItemReservedSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_, Item item) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		this.item = item;
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		if (stack.getItem() == this.item) return true;
		else return false;
	}

}

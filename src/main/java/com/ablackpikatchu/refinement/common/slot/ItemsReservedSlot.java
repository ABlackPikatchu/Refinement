package com.ablackpikatchu.refinement.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemsReservedSlot extends Slot {
	
	private Item[] items;

	public ItemsReservedSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_, Item[] items) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		this.items = items;	
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		for (Item item : this.items) {
			if (stack.getItem() == item) return true;
		}
		return false;
	}

}

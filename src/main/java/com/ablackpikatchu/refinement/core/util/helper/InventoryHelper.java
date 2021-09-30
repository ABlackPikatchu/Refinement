package com.ablackpikatchu.refinement.core.util.helper;

import javax.annotation.Nullable;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

public class InventoryHelper {

	public static ItemStack tryMoveInItem(@Nullable IInventory pSource, IInventory pDestination, ItemStack pStack,
			int pSlot, @Nullable Direction pDirection) { 
		ItemStack itemstack = pDestination.getItem(pSlot);
		if (canPlaceItemInContainer(pDestination, pStack, pSlot, pDirection)) {
			if (itemstack.isEmpty()) {
				ItemStack copy = pStack.copy();
				copy.setCount(1);
				pDestination.setItem(pSlot, copy);
				pStack.shrink(1);
			} else if (canMergeItems(itemstack, pStack)) {
				int i = pStack.getMaxStackSize() - itemstack.getCount();
				int j = Math.min(pStack.getCount(), i);
				pStack.shrink(j);
				itemstack.grow(j);
			}
			pDestination.setChanged();
			pSource.setChanged();
		}

		return pStack;
	}

	private static boolean canMergeItems(ItemStack p_145894_0_, ItemStack p_145894_1_) {
		if (p_145894_0_.getItem() != p_145894_1_.getItem()) {
			return false;
		} else if (p_145894_0_.getDamageValue() != p_145894_1_.getDamageValue()) {
			return false;
		} else if (p_145894_0_.getCount() > p_145894_0_.getMaxStackSize()) {
			return false;
		} else {
			return ItemStack.tagMatches(p_145894_0_, p_145894_1_);
		}
	}

	public static boolean canPlaceItemInContainer(IInventory container, ItemStack stack, int slot,
			@Nullable Direction side) {
		if (!container.canPlaceItem(slot, stack)) {
			return false;
		} else {
			return !(container instanceof ISidedInventory)
					|| ((ISidedInventory) container).canPlaceItemThroughFace(slot, stack, side);
		}
	}

}

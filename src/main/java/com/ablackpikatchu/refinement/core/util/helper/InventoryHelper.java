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
			} else
				mergeOneByOne(itemstack, pStack);
			pDestination.setChanged();
			pSource.setChanged();
		}

		return pStack;
	}
	
	/*
	public static ItemStack tryMoveInItem(@Nullable IInventory pSource, IInventory pDestination, ItemStack pStack,
			int pSlot, @Nullable Direction pDirection, int maxStackForDestination) {
		ItemStack itemstack = pDestination.getItem(pSlot);
		if (canPlaceItemInContainer((IInventory) pDestination, pStack, pSlot, pDirection)) {
			if (itemstack.isEmpty()) {
				ItemStack copy = pStack.copy();
				copy.setCount(1);
				pDestination.setItem(pSlot, copy);
				pStack.shrink(1);
			} else if (canMergeItems(itemstack, pStack, maxStackForDestination)) {
				int i = maxStackForDestination - itemstack.getCount();
				int j = Math.min(pStack.getCount(), i);
				pStack.shrink(j);
				itemstack.grow(j);
			} else
				mergeOneByOne(itemstack, pStack, maxStackForDestination);
			pDestination.setChanged();
			pSource.setChanged();
		}

		return pStack;
	}
	
	public static boolean canMergeItems(ItemStack destination, ItemStack source, int maxStackForDestination) {
		if (destination.getItem() != source.getItem()) {
			return false;
		} else if (destination.getDamageValue() != source.getDamageValue()) {
			return false;
		} else if (destination.getCount() > maxStackForDestination) {
			return false;
		} else {
			return ItemStack.tagMatches(destination, source);
		}
	}

	public static void mergeOneByOne(ItemStack destination, ItemStack source, int maxStackForDestination) {
		if (destination.getItem() != source.getItem())
			return;
		for (int i = destination.getCount(); i <= maxStackForDestination; ++i) {
			if (destination.getCount() + 1 <= maxStackForDestination) {
				source.shrink(1);
				destination.grow(1);
			}
		}
	}
	*/
	
	public static boolean canMergeItems(ItemStack destination, ItemStack source) {
		if (destination.getItem() != source.getItem()) {
			return false;
		} else if (destination.getDamageValue() != source.getDamageValue()) {
			return false;
		} else if (destination.getCount() > destination.getMaxStackSize()) {
			return false;
		} else {
			return ItemStack.tagMatches(destination, source);
		}
	}

	public static void mergeOneByOne(ItemStack destination, ItemStack source) {
		if (destination.getItem() != source.getItem())
			return;
		for (int i = destination.getCount(); i <= destination.getMaxStackSize(); ++i) {
			if (destination.getCount() + 1 <= destination.getMaxStackSize()) {
				source.shrink(1);
				destination.grow(1);
			}
		}
	}

	public static boolean canPlaceItemInContainer(IInventory container, ItemStack stack, int slot,
			@Nullable Direction side) {
		if (!container.canPlaceItem(slot, stack)) {
			return false;
		} else {
			return (container instanceof ISidedInventory)
					|| ((ISidedInventory) container).canPlaceItemThroughFace(slot, stack, side);
		}
	}

}

package com.ablackpikatchu.refinement.core.util.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.Property;
import net.minecraft.tileentity.TileEntity;

import net.minecraftforge.common.util.Constants;

public class TileEntityHelper {

	/**
	 * Updates the tile entity
	 * @param te the tile entity to update
	 */
	public static void updateTE(TileEntity te) {
		te.setChanged();
		te.getLevel().sendBlockUpdated(te.getBlockPos(), te.getBlockState(), te.getBlockState(),
				Constants.BlockFlags.BLOCK_UPDATE);
	}

	public static <T extends Comparable<T>, V extends T> void setStateProperty(TileEntity te, Property<T> property,
			V value) {
		te.getLevel().setBlockAndUpdate(te.getBlockPos(), te.getBlockState().setValue(property, value));
	}

	public static boolean canPlaceItemInStack(ItemStack stack, ItemStack stackToPlace) {
		if (stack.isEmpty())
			return true;
		else if (stack.getItem() == stackToPlace.getItem()
				&& stack.getCount() <= (stack.getMaxStackSize() - stackToPlace.getCount()))
			return true;
		else if (stack.getItem() == Items.AIR)
			return true;
		else
			return false;
	}
}

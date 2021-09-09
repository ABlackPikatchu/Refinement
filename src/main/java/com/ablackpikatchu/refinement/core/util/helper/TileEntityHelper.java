package com.ablackpikatchu.refinement.core.util.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityHelper {

	public static void updateTE(TileEntity te) {
		te.setChanged();
		te.getLevel().sendBlockUpdated(te.getBlockPos(), te.getBlockState(), te.getBlockState(),
				Constants.BlockFlags.BLOCK_UPDATE);
	}

	public static boolean canPlaceItemInStack(ItemStack stack, ItemStack stackToPlace) {
		if (stack == ItemStack.EMPTY)
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

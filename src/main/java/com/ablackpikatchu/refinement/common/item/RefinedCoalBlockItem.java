package com.ablackpikatchu.refinement.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RefinedCoalBlockItem extends Item{

	public RefinedCoalBlockItem(Block block, Properties properties) {
		super(properties);
	}


	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 8000;
	}

}

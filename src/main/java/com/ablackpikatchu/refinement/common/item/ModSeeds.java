package com.ablackpikatchu.refinement.common.item;

import com.ablackpikatchu.refinement.core.config.CommonConfig;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ModSeeds extends BlockItem {

	public ModSeeds(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public void fillItemCategory(ItemGroup tabs, NonNullList<ItemStack> items) {
		if (CommonConfig.CROPS_ENABLED.get())
			super.fillItemCategory(tabs, items);
	}

}

package com.ablackpikatchu.refinement.common.item.blockitem;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementMaterialsGroup;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RefinedCoalBlockItem extends BlockItem {

	public RefinedCoalBlockItem() {
		super(BlockInit.REFINED_CARBON_BLOCK.get(), new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS));
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 8000;
	}

}

package com.ablackpikatchu.refinement.resourcecrops.common;

import com.ablackpikatchu.refinement.core.config.CommonConfig;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ModEssence extends Item {

	public ModEssence(Properties properties) {
		super(properties);
	}
	
	@Override
	public void fillItemCategory(ItemGroup tabs, NonNullList<ItemStack> items) {
		if (CommonConfig.CROPS_ENABLED.get())
			super.fillItemCategory(tabs, items);
	}

}

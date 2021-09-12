package com.ablackpikatchu.refinement.core.itemgroup;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RefinementFoodGroup extends ItemGroup{
	
	public static final RefinementFoodGroup REFINEMENT_FOOD = new RefinementFoodGroup(ItemGroup.TABS.length, "refinement_food");

	public RefinementFoodGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemInit.MINERS_STEW.get());
	}

}

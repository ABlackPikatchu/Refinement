package com.ablackpikatchu.refinement.core.itemgroup;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RefinementItemGroup extends ItemGroup {

	public static final RefinementItemGroup REFINEMENT = new RefinementItemGroup(ItemGroup.TABS.length, "refinement");

	public RefinementItemGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemInit.REFINED_DIAMOND.get());
	}
	
	public String getName() {
		return "refinement";
	}

}

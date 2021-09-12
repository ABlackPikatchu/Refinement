package com.ablackpikatchu.refinement.core.itemgroup;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RefinementMaterialsGroup extends ItemGroup{
	
	public static final RefinementMaterialsGroup REFINEMENT_MATERIALS = new RefinementMaterialsGroup(ItemGroup.TABS.length, "refinement_materials");

	public RefinementMaterialsGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemInit.GRIT.get());
	}

}

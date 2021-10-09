package com.ablackpikatchu.refinement.core.itemgroup;

import com.ablackpikatchu.refinement.resourcecrops.core.CropInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RefinementResourceCropsGroup extends ItemGroup {

	public static RefinementResourceCropsGroup REFINEMENT_RESOURCE_CROPS = new RefinementResourceCropsGroup(
			ItemGroup.TABS.length, "refinement_resource_crops");

	public RefinementResourceCropsGroup(int p_i1853_1_, String p_i1853_2_) {
		super(p_i1853_1_, p_i1853_2_);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(CropInit.IRON_SEEDS.get());
	}

}

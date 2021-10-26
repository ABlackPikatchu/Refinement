package com.ablackpikatchu.refinement.core.itemgroup;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RefinementArmorGroup extends ItemGroup{
	
	public static final RefinementArmorGroup REFINEMENT_ARMOR = new RefinementArmorGroup(ItemGroup.TABS.length, "refinement_armor");

	public RefinementArmorGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemInit.Armor.REFINED_IRON_HELMET);
	}

}

package com.ablackpikatchu.refinement.core.itemgroup;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RefinementToolsWeaponsGroup extends ItemGroup{
	
	public static final RefinementToolsWeaponsGroup REFINEMENT_TOOLS_WEAPONS = new RefinementToolsWeaponsGroup(ItemGroup.TABS.length, "refinement_tools_weapons");

	public RefinementToolsWeaponsGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemInit.GRIT_PAPER.get());
	}

}

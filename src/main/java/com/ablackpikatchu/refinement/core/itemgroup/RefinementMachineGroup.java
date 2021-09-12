package com.ablackpikatchu.refinement.core.itemgroup;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RefinementMachineGroup extends ItemGroup{
	
	public static final RefinementMachineGroup REFINEMENT_MACHINE = new RefinementMachineGroup(ItemGroup.TABS.length, "refinement_machine");

	public RefinementMachineGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemInit.MACHINE_PARTS.get());
	}

}

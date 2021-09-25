package com.ablackpikatchu.refinement.core.itemgroup;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RefinementItemGroup extends ItemGroup {

	public static final RefinementItemGroup REFINEMENT = new RefinementItemGroup(ItemGroup.TABS.length, "refinement");
	public static final RefinementFoodGroup REFINEMENT_FOOD = RefinementFoodGroup.REFINEMENT_FOOD;
	public static final RefinementArmorGroup REFINEMENT_ARMOUR = RefinementArmorGroup.REFINEMENT_ARMOR;
	public static final RefinementToolsWeaponsGroup REFINEMENT_TOOLS_WEAPONS = RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS;
	public static final RefinementMachineGroup REFINEMENT_MACHINE = RefinementMachineGroup.REFINEMENT_MACHINE;
	public static final RefinementMaterialsGroup REFINEMENT_MATERIALS = RefinementMaterialsGroup.REFINEMENT_MATERIALS;
	public static final RefinementResourceCropsGroup REFINEMENT_RESOURCE_CROPS = RefinementResourceCropsGroup.REFINEMENT_RESOURCE_CROPS;

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

package com.ablackpikatchu.refinement.common.te.upgrade;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.Item;

public enum Upgrade {

	SPEED(new String[] {
			"Alloy Smelter", "Grinder"
	}), AUTO_EJECT, AUTO_IMPORT, ENERGY_ABILITY(new String[] {
			"Alloy Smelter", "Grinder"
	});

	public final String[] compatibleMachines;

	private Upgrade(String[] compatibleMachines) {
		this.compatibleMachines = compatibleMachines;
	}

	/**
	 * private Upgrade(Block[] compatibleMachines) { ArrayList<String>
	 * compatibleMachinesString = new ArrayList<>(); for (Block machine :
	 * compatibleMachines) compatibleMachinesString.add(getBlockName(machine));
	 * this.compatibleMachines = compatibleMachinesString.toArray(new String[] {});
	 * }
	 */

	private Upgrade() {
		this.compatibleMachines = new String[] {};
	}

	public Item getAsItem() {
		switch (this) {
		case SPEED:
			return ItemInit.SPEED_UPGRADE.get();
		case AUTO_EJECT:
			return ItemInit.AUTO_EJECT_UPGRADE.get();
		case AUTO_IMPORT:
			return ItemInit.AUTO_IMPORT_UPGRADE.get();
		case ENERGY_ABILITY:
			return ItemInit.ENERGY_ABILITY_UPGRADE.get();
		}
		return null;
	}

}

package com.ablackpikatchu.refinement.core.util.enums;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.Item;

public enum Upgrades {

	SPEED(ItemInit.SPEED_UPGRADE.get()), 
	AUTO_EJECT(ItemInit.AUTO_EJECT_UPGRADE.get()),
	AUTO_IMPORT(ItemInit.AUTO_IMPORT_UPGRADE.get());

	Item upgrade;

	private Upgrades(Item upgrade) {
		this.upgrade = upgrade;
	}

	public Item getAsItem() {
		return this.upgrade;
	}

}

package com.ablackpikatchu.refinement.common.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GluttonyBracelet extends Item {

	public GluttonyBracelet(Properties p_i48487_1_) {
		super(p_i48487_1_);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return false;
	}

}

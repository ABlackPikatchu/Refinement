package com.ablackpikatchu.refinement.common.item;

import java.util.List;

import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ArmorUpgrader extends Item {
	
	public static String rolled = "rolled";
	public static String type = "type";
	public static String potionType = "potion";
	public static String abilityType = "ability";

	public ArmorUpgrader(Properties p_i48487_1_) {
		super(p_i48487_1_);
	}
	
	public String getType(ItemStack stack) {
		return NBTHelper.getString(stack, ArmorUpgrader.type);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltip,
			ITooltipFlag p_77624_4_) {
		if (getType(stack) != "") tooltip.add(new StringTextComponent("Upgrade Type: \u00A76" + getType(stack)));
	}

}

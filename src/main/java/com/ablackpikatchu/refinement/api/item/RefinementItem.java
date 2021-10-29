package com.ablackpikatchu.refinement.api.item;

import java.util.List;

import com.ablackpikatchu.refinement.client.RefinementLang;
import com.ablackpikatchu.refinement.core.util.TimeManager;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class RefinementItem extends Item {

	public RefinementItem(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public void appendHoverText(ItemStack pStack, World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		if (TimeManager.isTimeOfYear(TimeManager.APRIL_FOOLS))
			pTooltip.add(RefinementLang.ALT_F4_CHEAT_COMPONENT);
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
	}

}

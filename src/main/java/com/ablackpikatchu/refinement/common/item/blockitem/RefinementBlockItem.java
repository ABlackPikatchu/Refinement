package com.ablackpikatchu.refinement.common.item.blockitem;

import java.util.List;

import com.ablackpikatchu.refinement.client.RefinementLang;
import com.ablackpikatchu.refinement.core.util.TimeManager;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class RefinementBlockItem extends BlockItem {

	public RefinementBlockItem(Block pBlock, Properties pProperties) {
		super(pBlock, pProperties);
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		if (TimeManager.isTimeOfYear(TimeManager.APRIL_FOOLS))
			pTooltip.add(RefinementLang.ALT_F4_CHEAT_COMPONENT);
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
	}

}

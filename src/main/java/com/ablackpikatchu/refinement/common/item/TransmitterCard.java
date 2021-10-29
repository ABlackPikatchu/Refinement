package com.ablackpikatchu.refinement.common.item;

import java.util.List;

import com.ablackpikatchu.refinement.api.item.RefinementItem;
import com.ablackpikatchu.refinement.client.RefinementLang;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TransmitterCard extends RefinementItem {

	public TransmitterCard() {
		super(new Item.Properties().tab(RefinementItemGroup.REFINEMENT_MACHINE).stacksTo(1));
	}

	@Override
	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
		if (context.getPlayer().isShiftKeyDown()) {
			NBTHelper.setBlockPos(stack, "linkedPos", context.getClickedPos());
		}
		return super.onItemUseFirst(stack, context);
	}

	@Override
	public void appendHoverText(ItemStack pStack, World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		if (NBTHelper.getBlockPos(pStack, "linkedPos") != null) {
			pTooltip.add(new StringTextComponent(RefinementLang.getComponent("linked_to").getString().replace("%l", NBTHelper.getBlockPos(pStack, "linkedPos").toString().replace("BlockPos{", "").replace("}", ""))));
		}
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
	}

}

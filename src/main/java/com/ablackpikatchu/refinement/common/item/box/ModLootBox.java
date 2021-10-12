package com.ablackpikatchu.refinement.common.item.box;

import java.util.List;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.config.ModJsonConfigs;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.ablackpikatchu.refinement.core.util.text.TextFormattingUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ModLootBox extends Item {

	public ModLootBox() {
		super(new Item.Properties().tab(RefinementItemGroup.REFINEMENT));
	}

	@Override
	public ActionResult<ItemStack> use(World pLevel, PlayerEntity pPlayer, Hand pHand) {

		ItemStack box = pPlayer.getItemInHand(pHand);

		if (NBTHelper.getString(box, "mod") == "")
			return ActionResult.fail(box);

		if (ModJsonConfigs.LOOT_BOXES.getModLootBoxPool(NBTHelper.getString(box, "mod")).isEmpty())
			return ActionResult.fail(box);

		pPlayer.drop(ModJsonConfigs.LOOT_BOXES.getModLootBoxPool(NBTHelper.getString(box, "mod")), false);
		if (!pPlayer.isCreative())
			box.shrink(1);

		return super.use(pLevel, pPlayer, pHand);
	}

	@Override
	public String getDescriptionId(ItemStack pStack) {
		if (NBTHelper.getString(pStack, "mod") == "")
			return super.getDescriptionId(pStack);

		return TextFormattingUtils.capitalizeWord(NBTHelper.getString(pStack, "mod").replace('_', ' ')) + " "
				+ new TranslationTextComponent("component." + Refinement.MOD_ID + ".loot_box").getString()
						.replace('[', ' ').replace(']', ' ');
	}

	@Override
	public void appendHoverText(ItemStack pStack, World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		if (NBTHelper.getString(pStack, "mod") != "")
			pTooltip.add(new StringTextComponent("Loot box for mod: \u00A76" + NBTHelper.getString(pStack, "mod")));
	}

}

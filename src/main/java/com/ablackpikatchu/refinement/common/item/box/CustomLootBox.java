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
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class CustomLootBox extends Item {

	public CustomLootBox() {
		super(new Item.Properties().tab(RefinementItemGroup.REFINEMENT).stacksTo(1));
	}

	@Override
	public ActionResult<ItemStack> use(World pLevel, PlayerEntity pPlayer, Hand pHand) {

		ItemStack box = pPlayer.getItemInHand(pHand);

		if (NBTHelper.getString(box, "pool") == "")
			return ActionResult.fail(box);

		ItemStack stack = ModJsonConfigs.LOOT_BOXES.getCustomLootPoxPool(NBTHelper.getString(box, "pool"));

		if (stack.isEmpty() || stack.getItem() == Items.AIR)
			return ActionResult.fail(box);

		pPlayer.drop(stack, false);
		if (!pPlayer.isCreative())
			box.shrink(1);

		return super.use(pLevel, pPlayer, pHand);
	}

	@Override
	public String getDescriptionId(ItemStack pStack) {
		if (NBTHelper.getString(pStack, "pool") == "")
			return super.getDescriptionId(pStack);
		return TextFormattingUtils.capitalizeWord(NBTHelper.getString(pStack, "pool").replace('_', ' ')) + " "
				+ new TranslationTextComponent("component." + Refinement.MOD_ID + ".loot_box").getString()
						.replace('[', ' ').replace(']', ' ');
	}

	@Override
	public void appendHoverText(ItemStack pStack, World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		if (NBTHelper.getString(pStack, "pool") != "")
			pTooltip.add(new StringTextComponent("Loot Pool: \u00A76" + NBTHelper.getString(pStack, "pool")));
	}

}

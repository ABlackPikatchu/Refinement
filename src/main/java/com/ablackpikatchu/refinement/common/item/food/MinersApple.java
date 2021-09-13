package com.ablackpikatchu.refinement.common.item.food;

import java.util.List;

import com.ablackpikatchu.refinement.core.init.PotionInit;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementFoodGroup;
import com.ablackpikatchu.refinement.core.util.text.ToolTipUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class MinersApple extends Item{

	public MinersApple() {
		super(new Item.Properties()
				.food(new Food.Builder().nutrition(3).saturationMod(5f).alwaysEat().fast().build())
				.rarity(Rarity.RARE).tab(RefinementFoodGroup.REFINEMENT_FOOD).stacksTo(16));

	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

		if (player.hasEffect(PotionInit.NEGATE_FALL.get()))
			return ActionResult.fail(player.getItemInHand(hand));

		return super.use(world, player, hand);
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			
			player.addEffect(new EffectInstance(PotionInit.NEGATE_FALL.get(), 4000, 0));
		}
		return super.finishUsingItem(stack, world, entity);
			
	}
	
	@Override
	public void appendHoverText(ItemStack p_77624_1_, World p_77624_2_, List<ITextComponent> p_77624_3_,
			ITooltipFlag p_77624_4_) {
		String[] shiftTooltipNames = { "miners_apple_shift" };
		ToolTipUtils.renderShiftTooltips(p_77624_3_, shiftTooltipNames, 0);
		super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}

}
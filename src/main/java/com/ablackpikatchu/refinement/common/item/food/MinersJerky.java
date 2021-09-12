package com.ablackpikatchu.refinement.common.item.food;

import com.ablackpikatchu.refinement.core.itemgroup.RefinementFoodGroup;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class MinersJerky extends Item{

	public MinersJerky() {
		super(new Item.Properties()
				.food(new Food.Builder().nutrition(5).saturationMod(8f).alwaysEat().fast().build())
				.rarity(Rarity.RARE).tab(RefinementFoodGroup.REFINEMENT_FOOD).stacksTo(32));
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

		if (player.hasEffect(Effects.DAMAGE_RESISTANCE) && player.hasEffect(Effects.HUNGER))
			return ActionResult.fail(player.getItemInHand(hand));

		return super.use(world, player, hand);
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			
			player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 4000, 0));
			player.addEffect(new EffectInstance(Effects.HUNGER, 600, 0));
		}
		return super.finishUsingItem(stack, world, entity);
			
	}


}

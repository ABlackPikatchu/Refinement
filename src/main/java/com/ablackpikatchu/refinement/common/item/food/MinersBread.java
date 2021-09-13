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

public class MinersBread extends Item {

	public MinersBread() {
		super(new Item.Properties().food(new Food.Builder().nutrition(3).saturationMod(15f).alwaysEat().fast().build())
				.rarity(Rarity.UNCOMMON).tab(RefinementFoodGroup.REFINEMENT_FOOD).stacksTo(16));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

		if (player.hasEffect(Effects.SATURATION))
			return ActionResult.fail(player.getItemInHand(hand));

		return super.use(world, player, hand);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {

		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;

			player.addEffect(new EffectInstance(Effects.SATURATION, 5000, 0));
		}

		return super.finishUsingItem(stack, world, entity);
	}

}

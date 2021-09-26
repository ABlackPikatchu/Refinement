package com.ablackpikatchu.refinement.common.item.food;

import java.util.List;
import java.util.stream.Collectors;

import com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class CuringApple extends Item {

	public CuringApple() {
		super(new Item.Properties().stacksTo(16).food(new Food.Builder().alwaysEat().fast().build())
				.tab(RefinementItemGroup.REFINEMENT_FOOD));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World level, LivingEntity entity) {

		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;

			if (player.getCooldowns().isOnCooldown(stack.getItem()))
				return super.finishUsingItem(stack, level, player);

			List<Effect> effects = player.getActiveEffects().stream().map(EffectInstance::getEffect)
					.filter(effect -> !effect.isBeneficial()).collect(Collectors.toList());

			effects.forEach(effect -> {
				player.removeEffect(effect);
			});

			player.level.playSound(player, player.getX(), player.getY(), player.getZ(),
					SoundEvents.ZOMBIE_VILLAGER_CURE, SoundCategory.MASTER, 0.7f, 1f);
			player.playSound(SoundEvents.ZOMBIE_VILLAGER_CURE, 0.7f, 1f);

			player.getCooldowns().addCooldown(stack.getItem(), 1200);
		}

		return super.finishUsingItem(stack, level, entity);
	}

}

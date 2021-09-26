package com.ablackpikatchu.refinement.common.item.food;

import java.util.ArrayList;

import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ModEffectFood extends Item {

	private ArrayList<EffectInstance> effects = new ArrayList<>();
	private Pair<DamageSource, Float> hurt;
	private ItemStack leftoverItem;

	public ModEffectFood(Properties p_i48487_1_) {
		super(p_i48487_1_);
	}

	public ModEffectFood addEffect(EffectInstance effect) {
		this.effects.add(effect);
		return this;
	}

	public ModEffectFood hurtOnUse(DamageSource source, float amount) {
		this.hurt = new Pair<DamageSource, Float>(source, amount);
		return this;
	}

	public ModEffectFood leftoverItem(ItemStack stack) {
		this.leftoverItem = stack;
		return this;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

		boolean[] cancel = new boolean[1];

		this.effects.forEach(effect -> {
			if (!player.hasEffect(effect.getEffect()))
				cancel[0] = true;
		});

		if (!cancel[0] == true)
			return ActionResult.fail(player.getItemInHand(hand));

		return super.use(world, player, hand);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {

		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			effects.forEach(effect -> {
				player.addEffect(effect);
			});
			if (this.hurt != null) {
				player.hurt(this.hurt.getFirst(), this.hurt.getSecond());
			}
			if (this.leftoverItem != null) {
				player.addItem(this.leftoverItem);
			}
		}

		return super.finishUsingItem(stack, world, entity);
	}

}

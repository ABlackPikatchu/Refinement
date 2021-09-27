package com.ablackpikatchu.refinement.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class Flight extends Effect {

	public Flight() {
		super(EffectType.BENEFICIAL, 0x808080);
	}

	@Override
	public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			if (player.hasEffect(this)) {
				if (player.getEffect(this).getDuration() <= 15)
					player.abilities.mayfly = false;
				else
					player.abilities.mayfly = true;
			}
		}
		super.applyEffectTick(entity, amplifier);
	}

}

package com.ablackpikatchu.refinement.common.effects;

import javax.annotation.Nonnull;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class NegateFall extends Effect {

	public NegateFall() {
		super(EffectType.BENEFICIAL, 0x008000);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplified) {
		return true;
	}

	@Override
	public void applyEffectTick(@Nonnull LivingEntity entity, int amplifier) {
		if (entity.fallDistance > 1.5f) {
			entity.fallDistance = 0f;
		}

	}
}

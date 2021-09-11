package com.ablackpikatchu.refinement.core.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodInit {

	public static final Food MINERS_STEW = new Food.Builder()
			.effect(() -> new EffectInstance(Effects.DIG_SPEED, 800, 1), 1.0f)
			.effect(() -> new EffectInstance(Effects.NIGHT_VISION, 800, 1), 1.0f)
			.effect(() -> new EffectInstance(Effects.HARM, 1, 1), 0.5f)
			.alwaysEat().saturationMod(13.0f)
			.nutrition(6).build();

}

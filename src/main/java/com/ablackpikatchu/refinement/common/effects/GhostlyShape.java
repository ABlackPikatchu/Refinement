package com.ablackpikatchu.refinement.common.effects;

import com.ablackpikatchu.refinement.datafixers.util.text.Color;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.MathHelper;

public class GhostlyShape extends Effect {

	public AttributeModifier[] attributeModifiers;

	public GhostlyShape() {
		super(EffectType.BENEFICIAL, new Color("dark_blue").getColourInt());
	}

	@Override
	public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
		return true;
	}

	private void initializeAttributeModifiers() {
		this.attributeModifiers = new AttributeModifier[1];
		for (int i = 0; i < this.attributeModifiers.length; i++) {
			this.attributeModifiers[i] = new AttributeModifier(this.getRegistryName().toString(), (i + 1) * 0.2f,
					AttributeModifier.Operation.ADDITION);
		}
	}

	@Override
	public void addAttributeModifiers(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn,
			int amplifier) {
		ModifiableAttributeInstance movementSpeed = entityLivingBaseIn.getAttribute(Attributes.MOVEMENT_SPEED);
		initializeAttributeModifiers();
		if (movementSpeed != null) {
			AttributeModifier attributeModifier = this.attributeModifiers[MathHelper.clamp(amplifier + 1, 0,
					this.attributeModifiers.length - 1)];
			movementSpeed.addTransientModifier(attributeModifier);
		}

		entityLivingBaseIn.setInvulnerable(true);
		entityLivingBaseIn.setInvisible(true);

		super.addAttributeModifiers(entityLivingBaseIn, attributeMapIn, amplifier);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn,
			int amplifier) {
		ModifiableAttributeInstance movementSpeed = entityLivingBaseIn.getAttribute(Attributes.MOVEMENT_SPEED);

		if (movementSpeed != null) {
			AttributeModifier attributeModifier = this.attributeModifiers[MathHelper.clamp(amplifier + 1, 0,
					this.attributeModifiers.length - 1)];
			movementSpeed.removeModifier(attributeModifier.getId());
		}

		entityLivingBaseIn.setInvulnerable(false);
		entityLivingBaseIn.setInvisible(false);

		super.removeAttributeModifiers(entityLivingBaseIn, attributeMapIn, amplifier);
	}

}

package com.ablackpikatchu.refinement.common.material;

import java.util.function.Supplier;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum ModItemTier implements IItemTier {

	REFINEDIRON(2, 250, 6.0F, 2.0F, 14, () -> {
		return Ingredient.of(ItemInit.REFINED_IRON_INGOT.get());
	}),

	REFINEDGOLD(0, 48, 12.0F, 0.0F, 22, () -> {
		return Ingredient.of(ItemInit.REFINED_GOLD_INGOT.get());
	}), REFINEDDIAMOND(3, 1832, 8.0F, 3.0F, 10, () -> {
		return Ingredient.of(ItemInit.REFINED_DIAMOND.get());
	}), REFINEDNETHERITE(4, 2532, 9.0F, 4.0F, 15, () -> {
		return Ingredient.of(ItemInit.REFINED_NETHERITE_INGOT.get());
	});
	;

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final LazyValue<Ingredient> repairIngredient;

	private ModItemTier(int level, int uses, float speed, float damage, int enchantmentValue,
			Supplier<Ingredient> repairIngredient) {
		this.level = level;
		this.uses = uses;
		this.speed = speed;
		this.damage = damage;
		this.enchantmentValue = enchantmentValue;
		this.repairIngredient = new LazyValue<>(repairIngredient);
	}

	@Override
	public int getUses() {
		return uses;
	}

	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public float getAttackDamageBonus() {
		return damage;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public int getEnchantmentValue() {
		return enchantmentValue;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

}

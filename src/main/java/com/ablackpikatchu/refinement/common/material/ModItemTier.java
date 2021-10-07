package com.ablackpikatchu.refinement.common.material;

import java.util.function.Supplier;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import static com.ablackpikatchu.refinement.core.config.ModJsonConfigs.TOOLS;

public enum ModItemTier implements IItemTier {

	REFINEDIRON(TOOLS.getRefinedIronValues().level, TOOLS.getRefinedIronValues().durability,
			TOOLS.getRefinedIronValues().speed, TOOLS.getRefinedIronValues().damage,
			TOOLS.getRefinedIronValues().enchantmentValue, () -> {
				return Ingredient.of(ItemInit.REFINED_IRON_INGOT.get());
			}),

	REFINEDGOLD(TOOLS.getRefinedGoldValues().level, TOOLS.getRefinedGoldValues().durability,
			TOOLS.getRefinedGoldValues().speed, TOOLS.getRefinedGoldValues().damage,
			TOOLS.getRefinedGoldValues().enchantmentValue, () -> {
				return Ingredient.of(ItemInit.REFINED_GOLD_INGOT.get());
			}),
	REFINEDDIAMOND(TOOLS.getRefinedDiamondValues().level, TOOLS.getRefinedDiamondValues().durability,
			TOOLS.getRefinedDiamondValues().speed, TOOLS.getRefinedDiamondValues().damage,
			TOOLS.getRefinedDiamondValues().enchantmentValue, () -> {
				return Ingredient.of(ItemInit.REFINED_DIAMOND.get());
			}),

	REFINEDNETHERITE(TOOLS.getRefinedNetheriteValues().level, TOOLS.getRefinedNetheriteValues().durability,
			TOOLS.getRefinedNetheriteValues().speed, TOOLS.getRefinedNetheriteValues().damage,
			TOOLS.getRefinedNetheriteValues().enchantmentValue, () -> {
				return Ingredient.of(ItemInit.REFINED_NETHERITE_INGOT.get());
			});
	;

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final LazyValue<Ingredient> repairIngredient;

	private ModItemTier(int level, int durability, float speed, float damage, int enchantmentValue,
			Supplier<Ingredient> repairIngredient) {
		this.level = level;
		this.uses = durability;
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

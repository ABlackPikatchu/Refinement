package com.ablackpikatchu.refinement.common.material;

import java.util.function.Supplier;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum ModArmorMaterial implements IArmorMaterial {
	REFINEDIRONARMOR("refinedironarmor", 16, new int[] { 3, 6, 7, 3 }, 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.1f,
			() -> Ingredient.of(ItemInit.REFINED_IRON_INGOT.get())),
	REFINEDGOLDARMOR("refinedgoldarmor", 8, new int[] { 1, 3, 5, 2 }, 26, SoundEvents.ARMOR_EQUIP_GOLD, 1.0F, 0.1f,
			() -> Ingredient.of(ItemInit.REFINED_GOLD_INGOT.get())),
	REFINEDDIAMONDARMOR("refineddiamondarmor", 34, new int[] { 4, 7, 9, 4 }, 11, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0F,
			0.1F, () -> Ingredient.of(ItemInit.REFINED_DIAMOND.get())),
	REFINEDNETHERITEARMOR("refinednetheritearmor", 38, new int[] { 4, 7, 9, 4 }, 16, SoundEvents.ARMOR_EQUIP_NETHERITE,
			4.0f, 0.2f, () -> Ingredient.of(ItemInit.REFINED_NETHERITE_INGOT.get()));

	public static final int[] baseDurability = new int[] { 128, 144, 160, 112 };
	private final String name;
	private final int durabilityMultiplier;
	private final int[] armorVal;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Ingredient repairIngredient;

	ModArmorMaterial(String name, int durabilityMultiplier, int[] armorVal, int enchantability, SoundEvent equipSound,
			float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.armorVal = armorVal;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = repairIngredient.get();
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlotType slot) {
		return ModArmorMaterial.baseDurability[slot.getIndex()] * this.durabilityMultiplier;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlotType slot) {
		return this.armorVal[slot.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

}

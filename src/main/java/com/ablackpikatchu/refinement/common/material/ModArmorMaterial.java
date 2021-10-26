package com.ablackpikatchu.refinement.common.material;

import static com.ablackpikatchu.refinement.core.config.ModJsonConfigs.ARMOUR;

import java.util.function.Supplier;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum ModArmorMaterial implements IArmorMaterial {

	REFINED_IRON_ARMOR("refined_iron_armor", ARMOUR.getRefinedIronValues().durabilityMultiplier,
			ARMOUR.getRefinedIronValues().armourValues, ARMOUR.getRefinedIronValues().enchantability,
			SoundEvents.ARMOR_EQUIP_IRON, ARMOUR.getRefinedIronValues().toughness,
			ARMOUR.getRefinedIronValues().knockbackResistance, () -> Ingredient.of(ItemInit.REFINED_IRON_INGOT.get())),

	REFINED_GOLD_ARMOR("refined_gold_armor", ARMOUR.getRefinedGoldValues().durabilityMultiplier,
			ARMOUR.getRefinedGoldValues().armourValues, ARMOUR.getRefinedGoldValues().enchantability,
			SoundEvents.ARMOR_EQUIP_GOLD, ARMOUR.getRefinedGoldValues().toughness,
			ARMOUR.getRefinedGoldValues().knockbackResistance, () -> Ingredient.of(ItemInit.REFINED_GOLD_INGOT.get())),

	REFINED_DIAMOND_ARMOR("refined_diamond_armor", ARMOUR.getRefinedDiamondValues().durabilityMultiplier,
			ARMOUR.getRefinedDiamondValues().armourValues, ARMOUR.getRefinedDiamondValues().enchantability,
			SoundEvents.ARMOR_EQUIP_DIAMOND, ARMOUR.getRefinedDiamondValues().toughness,
			ARMOUR.getRefinedDiamondValues().knockbackResistance, () -> Ingredient.of(ItemInit.REFINED_DIAMOND.get())),

	REFINED_NETHERITE_ARMOR("refined_netherite_armor", ARMOUR.getRefinedNetheriteValues().durabilityMultiplier,
			ARMOUR.getRefinedNetheriteValues().armourValues, ARMOUR.getRefinedNetheriteValues().enchantability,
			SoundEvents.ARMOR_EQUIP_NETHERITE, ARMOUR.getRefinedNetheriteValues().toughness,
			ARMOUR.getRefinedNetheriteValues().knockbackResistance,
			() -> Ingredient.of(ItemInit.REFINED_NETHERITE_INGOT.get()));

	public static final int[] baseDurability = ARMOUR.getBaseDurability();
	
	private final String name;
	private final int durabilityMultiplier;
	private int[] armorVal;
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

	public void setDefenseForSlot(EquipmentSlotType slot, int defense) {
		this.armorVal[slot.getIndex()] = defense;
	}

}

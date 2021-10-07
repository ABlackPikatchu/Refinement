package com.ablackpikatchu.refinement.core.config.entry;

import com.google.gson.annotations.Expose;

public class ArmourSettingsEntry {

	@Expose
	public int durabilityMultiplier;
	@Expose
	public int[] armourValues;
	@Expose
	public int enchantability;
	@Expose
	public float toughness;
	@Expose
	public float knockbackResistance;

	public ArmourSettingsEntry(int durabilityMultiplier, int[] armorVal, int enchantability, float toughness,
			float knockbackResistance) {
		this.durabilityMultiplier = durabilityMultiplier;
		this.armourValues = armorVal;
		this.enchantability = enchantability;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
	}

}

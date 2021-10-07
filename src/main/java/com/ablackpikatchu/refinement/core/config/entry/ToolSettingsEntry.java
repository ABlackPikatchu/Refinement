package com.ablackpikatchu.refinement.core.config.entry;

import com.google.gson.annotations.Expose;

public class ToolSettingsEntry {

	@Expose
	public int level;
	@Expose
	public int durability;
	@Expose
	public float speed;
	@Expose
	public float damage;
	@Expose
	public int enchantmentValue;

	public ToolSettingsEntry(int level, int durability, float speed, float damage, int enchantmentValue) {
		this.level = level;
		this.durability = durability;
		this.speed = speed;
		this.damage = damage;
		this.enchantmentValue = enchantmentValue;
	}

}

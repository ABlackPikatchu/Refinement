package com.ablackpikatchu.refinement.core.config.entry.loottable;

import com.google.gson.annotations.Expose;

import net.minecraft.item.Item;
import net.minecraft.loot.IRandomRange;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class LootTableEntry {

	@Expose
	public String lootTableName;
	@Expose
	public String itemToAdd;
	@Expose
	public RandomValueLootTable lootTable;

	public LootTableEntry(String lootTableName, String itemToAdd, RandomValueLootTable lootTable) {
		this.lootTableName = lootTableName;
		this.itemToAdd = itemToAdd;
		this.lootTable = lootTable;
	}

	public Item getItem() {
		return ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.itemToAdd));
	}

	public static class RandomValueLootTable {

		@Expose
		public float min;
		@Expose
		public float max;

		public RandomValueLootTable(float min, float max) {
			this.min = min;
			this.max = max;
		}

		public IRandomRange toRandomRange() {
			return RandomValueRange.between(this.min, this.max);
		}
	}

}

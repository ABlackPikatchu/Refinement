package com.ablackpikatchu.refinement.core.config.entry;

import javax.annotation.Nonnull;

import com.google.gson.annotations.Expose;

import net.minecraft.item.Item;

public class WeightBasedItemEntry {
	
	@Expose
	public String item;
	@Expose
	public String nbt;
	@Expose
	public int weight;
	@Expose
	public int minAmount;
	@Expose
	public int maxAmount;
	

	public WeightBasedItemEntry(@Nonnull String item, @Nonnull int weight,
			@Nonnull int minAmount, @Nonnull int maxAmount) {
		this.item = item;
		this.nbt = null;
		this.weight = weight;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}
	
	public WeightBasedItemEntry(@Nonnull String item, @Nonnull int weight) {
		this.item = item;
		this.nbt = null;
		this.weight = weight;
		this.minAmount = 1;
		this.maxAmount = 1;
	}
	
	public WeightBasedItemEntry(@Nonnull Item item, String nbt, @Nonnull int weight,
			@Nonnull int minAmount, @Nonnull int maxAmount) {
		this.item = item.getRegistryName().toString();
		this.nbt = nbt;
		this.weight = weight;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}
	
	public WeightBasedItemEntry(@Nonnull String item, String nbt, @Nonnull int weight,
			@Nonnull int minAmount, @Nonnull int maxAmount) {
		this.item = item;
		this.nbt = nbt;
		this.weight = weight;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}
	
	public WeightBasedItemEntry(@Nonnull Item item, @Nonnull int weight,
			@Nonnull int minAmount, @Nonnull int maxAmount) {
		this.item = item.getRegistryName().toString();
		this.nbt = null;
		this.weight = weight;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}

}

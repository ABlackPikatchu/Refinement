package com.ablackpikatchu.refinement.core.config.entry;

import javax.annotation.Nonnull;

import com.google.gson.annotations.Expose;

import net.minecraft.item.Item;

public class ResourceStatueEntry {

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

	public ResourceStatueEntry(@Nonnull String item, @Nonnull int weight, @Nonnull int minAmount,
			@Nonnull int maxAmount) {
		this.item = item;
		this.nbt = null;
		this.weight = weight;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		if (this.maxAmount < this.minAmount)
			this.maxAmount = this.minAmount;
	}

	public ResourceStatueEntry(@Nonnull Item item, @Nonnull int weight, @Nonnull int minAmount,
			@Nonnull int maxAmount) {
		this.item = item.getRegistryName().toString();
		this.nbt = null;
		this.weight = weight;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		if (this.maxAmount < this.minAmount)
			this.maxAmount = this.minAmount;
	}

}

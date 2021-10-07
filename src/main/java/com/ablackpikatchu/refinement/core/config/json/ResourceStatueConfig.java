package com.ablackpikatchu.refinement.core.config.json;

import java.util.LinkedList;
import java.util.List;

import com.ablackpikatchu.refinement.core.config.entry.ResourceStatueEntry;
import com.ablackpikatchu.refinement.core.util.lists.ItemLists;
import com.ablackpikatchu.refinement.datafixers.util.Triple;
import com.google.gson.annotations.Expose;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class ResourceStatueConfig extends JsonConfig {

	@Expose
	private List<ResourceStatueEntry> POOLS;

	@Override
	public String getName() {
		return "resource_statue_pools";
	}

	@Override
	public void reset() {
		this.POOLS = new LinkedList<>();

		for (Item log : ItemLists.LOGS)
			this.POOLS.add(new ResourceStatueEntry(log, 5, 250, 500));
		
		for (Item leaf : ItemLists.LEAVES)
			this.POOLS.add(new ResourceStatueEntry(leaf, 5, 250, 500));

		for (Item dye : ItemLists.DYES)
			this.POOLS.add(new ResourceStatueEntry(dye, 6, 250, 400));

		for (Item glass : ItemLists.GLASS)
			this.POOLS.add(new ResourceStatueEntry(glass, 7, 300, 520));

		for (Item concretePowder : ItemLists.CONCRETE_POWDERS)
			this.POOLS.add(new ResourceStatueEntry(concretePowder, 6, 400, 700));

		for (Item concrete : ItemLists.CONCRETES)
			this.POOLS.add(new ResourceStatueEntry(concrete, 3, 400, 650));

		for (Item stone : ItemLists.STONES)
			this.POOLS.add(new ResourceStatueEntry(stone, 4, 400, 750));

		for (Item wool : ItemLists.WOOLS)
			this.POOLS.add(new ResourceStatueEntry(wool, 2, 200, 420));

		for (Item terracotta : ItemLists.TERRACOTTAS)
			this.POOLS.add(new ResourceStatueEntry(terracotta, 4, 250, 400));

		this.POOLS.add(new ResourceStatueEntry(Items.SAND, 4, 400, 750));
		this.POOLS.add(new ResourceStatueEntry(Items.GRAVEL, 4, 400, 750));

		this.POOLS.add(new ResourceStatueEntry(Items.DIRT, 6, 500, 800));
		
		this.POOLS.add(new ResourceStatueEntry(Items.PRISMARINE, 2, 300, 480));
		this.POOLS.add(new ResourceStatueEntry(Items.PRISMARINE_BRICKS, 2, 300, 480));
		this.POOLS.add(new ResourceStatueEntry(Items.DARK_PRISMARINE, 1, 300, 450));
		
	}

	public Triple<Item, Integer, Integer> getRandomLoot(List<ResourceStatueEntry> loottable) {
		ItemStack stack = ItemStack.EMPTY;

		if (loottable == null || loottable.isEmpty())
			return new Triple<Item, Integer, Integer>(stack.getItem(), 0, 0);

		double totalWeight = 0.0;
		for (ResourceStatueEntry i : loottable) {
			totalWeight += i.weight;
		}

		int idx = 0;
		for (double r = Math.random() * totalWeight; idx < loottable.size() - 1; ++idx) {
			r -= loottable.get(idx).weight;
			if (r <= 0.0)
				break;
		}

		ResourceStatueEntry randomEntry = loottable.get(idx);

		int minAmount = 0;
		int maxAmount = 0;

		try {
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(randomEntry.item));
			stack = new ItemStack(item);
			if (randomEntry.nbt != null) {
				CompoundNBT nbt = JsonToNBT.parseTag(randomEntry.nbt);
				stack.setTag(nbt);
			}
			minAmount = randomEntry.minAmount;
			maxAmount = randomEntry.maxAmount;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Triple<Item, Integer, Integer>(stack.getItem(), minAmount, maxAmount);
	}

	public Triple<Item, Integer, Integer> getRandomLoot() {
		return getRandomLoot(POOLS);
	}

}

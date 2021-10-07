package com.ablackpikatchu.refinement.core.config.json;

import java.util.LinkedList;
import java.util.List;

import com.ablackpikatchu.refinement.core.config.entry.WeightBasedItemEntry;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.util.MathUtils;
import com.google.gson.annotations.Expose;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class LootBoxConfig extends JsonConfig {

	@Expose
	private List<WeightBasedItemEntry> FOOD_POOLS;

	@Override
	public String getName() {
		return "loot_boxes_pools";
	}

	@Override
	protected void reset() {
		this.FOOD_POOLS = new LinkedList<>();

		this.FOOD_POOLS.add(new WeightBasedItemEntry(ItemInit.MINERS_APPLE.get(), 2, 4, 6));
	}

	public ItemStack getRandomLoot(List<WeightBasedItemEntry> loottable) {
		ItemStack stack = ItemStack.EMPTY;

		if (loottable == null || loottable.isEmpty())
			return stack;

		double totalWeight = 0.0;
		for (WeightBasedItemEntry i : loottable) {
			totalWeight += i.weight;
		}

		int idx = 0;
		for (double r = Math.random() * totalWeight; idx < loottable.size() - 1; ++idx) {
			r -= loottable.get(idx).weight;
			if (r <= 0.0)
				break;
		}

		WeightBasedItemEntry randomEntry = loottable.get(idx);

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
			int averageCount = MathUtils.getRandomNumber(minAmount, maxAmount);
			if (averageCount > stack.getMaxStackSize())
				averageCount = stack.getMaxStackSize();
			stack.setCount(averageCount);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stack;
	}

	public ItemStack getLootForBox(BoxType box) {
		switch (box) {
		case FOOD:
			return getRandomLoot(FOOD_POOLS);
		default:
			break;
		}
		return ItemStack.EMPTY;
	}

	public enum BoxType {
		FOOD;
	}
}

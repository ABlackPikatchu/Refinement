package com.ablackpikatchu.refinement.core.config.entry;

import com.ablackpikatchu.refinement.core.util.lists.TradeLists;
import com.google.gson.annotations.Expose;

import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class TradeEntry {
	
	@Expose
	public Type type;
	@Expose
	public String item;
	@Expose
	public String nbt;
	@Expose
	public int numberOfItems;
	@Expose
	public int cost;
	@Expose
	public int villagerXp;
	@Expose
	public int maxUses;
	@Expose
	public float priceMultiplier;
	
	public TradeEntry(Type type, String item, String nbt, int numberOfItems, int cost, int villagerXp, int maxUses, float priceMultiplier) {
		this.type = type;
		this.item = item;
		this.nbt = nbt;
		this.numberOfItems = numberOfItems;
		this.cost = cost;
		this.villagerXp = villagerXp;
		this.maxUses = maxUses;
		this.priceMultiplier = priceMultiplier;
	}
	
	public VillagerTrades.ITrade createTrade() {
		switch (this.type) {
		case EMERALDS_TO_ITEMS: return new TradeLists.EmeraldsToItemsTrade(getStack(numberOfItems), cost, maxUses, villagerXp, priceMultiplier);
		case ITEMS_TO_EMERALDS: return new TradeLists.ItemsToEmeralds(getStack(cost), numberOfItems, cost, maxUses, villagerXp, priceMultiplier);

		default:
			break;
		}
		return null;
	}
	
	public ItemStack getStack(int count) {
		ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.item)), count);
		if (this.nbt != null)
			stack.setTag(new CompoundNBT().getCompound(this.nbt));
		return stack;
	}
	
	public enum Type {
		EMERALDS_TO_ITEMS, ITEMS_TO_EMERALDS;
	}

}

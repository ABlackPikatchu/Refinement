package com.ablackpikatchu.refinement.core.util.lists;

import java.util.ArrayList;
import java.util.Random;

import com.ablackpikatchu.refinement.core.config.ModJsonConfigs;
import com.ablackpikatchu.refinement.core.init.VillagerInit.VillagerProfessions;
import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public class TradeLists {

	public static void fillTradeData() {
		VillagerTrades.ITrade[] materialistTradesArray = new ITrade[] {};
		
		ArrayList<VillagerTrades.ITrade> materialistLevel1 = new ArrayList<>();
		
		ModJsonConfigs.MATERIALIST_TRADES.getLevel1().forEach(trade -> {
			materialistLevel1.add(trade.createTrade());
		});
		
		VillagerTrades.ITrade[] materialsLevel2 = new VillagerTrades.ITrade[] {};
		VillagerTrades.ITrade[] materialsLevel3 = new VillagerTrades.ITrade[] {};
		VillagerTrades.ITrade[] materialsLevel4 = new VillagerTrades.ITrade[] {};
		VillagerTrades.ITrade[] materialsLevel5 = new VillagerTrades.ITrade[] {};

		VillagerTrades.TRADES.put(VillagerProfessions.MATERIALS_VILLAGER.get(), toIntMap(ImmutableMap.of(1,
				materialistLevel1.toArray(materialistTradesArray), 2, materialsLevel2, 3, materialsLevel3, 4, materialsLevel4, 5, materialsLevel5)));
	}

	private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(
			ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
		return new Int2ObjectOpenHashMap<>(p_221238_0_);
	}

	public static class EmeraldsToItemsTrade implements VillagerTrades.ITrade {

		private final ItemStack itemstack;
		private final int cost;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public EmeraldsToItemsTrade(ItemStack itemstack, int cost, int pMaxUses, int pVillagerXp,
				float priceMultiplier) {
			this.itemstack = itemstack;
			this.cost = cost;
			this.maxUses = pMaxUses;
			this.villagerXp = pVillagerXp;
			this.priceMultiplier = priceMultiplier;
		}

		public MerchantOffer getOffer(Entity pTrader, Random pRand) {
			return new MerchantOffer(new ItemStack(Items.EMERALD, cost), itemstack, this.maxUses, this.villagerXp,
					this.priceMultiplier);
		}

	}
	
	public static class ItemsToEmeralds implements VillagerTrades.ITrade {
	      private final ItemStack itemStack;
	      private final int emeraldCost;
	      private final int numberOfItems;
	      private final int maxUses;
	      private final int villagerXp;
	      private final float priceMultiplier;

	      public ItemsToEmeralds(Block pBlock, int pEmeraldCost, int pNumberOfItems, int pMaxUses, int pVillagerXp) {
	         this(new ItemStack(pBlock), pEmeraldCost, pNumberOfItems, pMaxUses, pVillagerXp);
	      }

	      public ItemsToEmeralds(Item pItem, int pEmeraldCost, int pNumberOfItems, int pVillagerXp) {
	         this(new ItemStack(pItem), pEmeraldCost, pNumberOfItems, 12, pVillagerXp);
	      }

	      public ItemsToEmeralds(Item pItem, int pEmeraldCost, int pNumberOfItems, int pMaxUses, int pVillagerXp) {
	         this(new ItemStack(pItem), pEmeraldCost, pNumberOfItems, pMaxUses, pVillagerXp);
	      }

	      public ItemsToEmeralds(ItemStack pItemStack, int pEmeraldCost, int pNumberOfItems, int pMaxUses, int pVillagerXp) {
	         this(pItemStack, pEmeraldCost, pNumberOfItems, pMaxUses, pVillagerXp, 0.05F);
	      }

	      public ItemsToEmeralds(ItemStack pItemStack, int pEmeralCost, int pNumberOfItems, int pMaxUses, int pVillagerXp, float pPriceMultiplier) {
	         this.itemStack = pItemStack;
	         this.emeraldCost = pEmeralCost;
	         this.numberOfItems = pNumberOfItems;
	         this.maxUses = pMaxUses;
	         this.villagerXp = pVillagerXp;
	         this.priceMultiplier = pPriceMultiplier;
	      }

	      public MerchantOffer getOffer(Entity pTrader, Random pRand) {
	         return new MerchantOffer(new ItemStack(this.itemStack.getItem(), this.numberOfItems), new ItemStack(Items.EMERALD, this.emeraldCost), this.maxUses, this.villagerXp, this.priceMultiplier);
	      }
	   }

}

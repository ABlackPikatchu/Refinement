package com.ablackpikatchu.refinement.core.util.lists;

import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.VillagerInit.VillagerProfessions;
import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.merchant.villager.VillagerTrades;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public class TradeLists {

	public static void fillTradeData() {
		VillagerTrades.ITrade[] materialsLevel1 = new VillagerTrades.ITrade[] {
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.UNFIRED_REFINED_CARBON_INGOT.get(), 2, 8, 12) };
		VillagerTrades.ITrade[] materialsLevel2 = new VillagerTrades.ITrade[] {};
		VillagerTrades.ITrade[] materialsLevel3 = new VillagerTrades.ITrade[] {};
		VillagerTrades.ITrade[] materialsLevel4 = new VillagerTrades.ITrade[] {};
		VillagerTrades.ITrade[] materialsLevel5 = new VillagerTrades.ITrade[] {};

		VillagerTrades.TRADES.put(VillagerProfessions.MATERIALS_VILLAGER.get(), toIntMap(ImmutableMap.of(1,
				materialsLevel1, 2, materialsLevel2, 3, materialsLevel3, 4, materialsLevel4, 5, materialsLevel5)));
	}

	private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(
			ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
		return new Int2ObjectOpenHashMap<>(p_221238_0_);
	}

}

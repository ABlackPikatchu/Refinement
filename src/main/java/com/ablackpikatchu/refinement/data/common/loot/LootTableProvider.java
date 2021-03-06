package com.ablackpikatchu.refinement.data.common.loot;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.data.DataGenerator;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.LootTableManager;
import net.minecraft.util.ResourceLocation;

public class LootTableProvider extends net.minecraft.data.LootTableProvider {

	public LootTableProvider(DataGenerator p_i50789_1_) {
		super(p_i50789_1_);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables() {
		return ImmutableList.of(Pair.of(BlockLootTables::new, LootParameterSets.BLOCK));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
		map.forEach(
				(p_218436_2_, p_218436_3_) -> LootTableManager.validate(validationtracker, p_218436_2_, p_218436_3_));
	}

}

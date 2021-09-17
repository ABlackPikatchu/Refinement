package com.ablackpikatchu.refinement.data.common.loot;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ablackpikatchu.refinement.data.maps.LootTableMaps;

import net.minecraft.block.Block;

public class BlockLootTables extends net.minecraft.data.loot.BlockLootTables {

	public static ArrayList<Block> KNOWN_BLOCKS = new ArrayList<>();

	@Override
	protected void addTables() {

		LootTableMaps.addBlockLoot(KNOWN_BLOCKS);
		KNOWN_BLOCKS.forEach(block -> {
			dropSelf(block);
		});

	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return KNOWN_BLOCKS.stream().collect(Collectors.toList());
	}

}

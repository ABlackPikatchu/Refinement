package com.ablackpikatchu.refinement.data.common.loot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.ablackpikatchu.refinement.data.maps.LootTableMaps;
import com.ablackpikatchu.refinement.resourcecrops.common.ModCrop;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.util.IItemProvider;

public class BlockLootTables extends net.minecraft.data.loot.BlockLootTables {

	public static ArrayList<Block> KNOWN_BLOCKS = new ArrayList<>();
	
	public static ArrayList<Block> CROP_BLOCKS = new ArrayList<>();
	public static HashMap<Block, IItemProvider> CROPS = new HashMap<>();

	@Override
	protected void addTables() {

		LootTableMaps.addBlockLoot(KNOWN_BLOCKS);
		KNOWN_BLOCKS.forEach(block -> {
			dropSelf(block);
		});

		LootTableMaps.addCropLoot(CROPS);
		CROPS.forEach((block, resource) -> {
			CROP_BLOCKS.add(block);
			this.add(block, createCropDrops(block, resource));
		});
		
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		KNOWN_BLOCKS.addAll(CROP_BLOCKS);
		return KNOWN_BLOCKS.stream().collect(Collectors.toList());
	}

	protected static LootTable.Builder createCropDrops(Block block, IItemProvider producedResource) {
		return LootTable.lootTable()
				.withPool(
						LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(block)))
				.withPool(LootPool.lootPool()
						.when(BlockStateProperty.hasBlockStateProperties(block).setProperties(
								StatePropertiesPredicate.Builder.properties().hasProperty(ModCrop.AGE, 7)))
						.setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(producedResource)))
				.withPool(LootPool.lootPool()
						.when(BlockStateProperty.hasBlockStateProperties(block).setProperties(
								StatePropertiesPredicate.Builder.properties().hasProperty(ModCrop.AGE, 7)))
						.setRolls(RandomValueRange.between(0, 2)).add(ItemLootEntry.lootTableItem(block)));
	}

}

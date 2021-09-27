package com.ablackpikatchu.refinement.data.common.loot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.ablackpikatchu.refinement.data.maps.LootTableMaps;
import com.ablackpikatchu.refinement.resourcecrops.common.ModCrop;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;

@SuppressWarnings("unused")
public class BlockLootTables extends net.minecraft.data.loot.BlockLootTables {

	private static final ILootCondition.IBuilder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item()
			.hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
	private static final ILootCondition.IBuilder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
	private static final ILootCondition.IBuilder HAS_SHEARS = MatchTool
			.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
	private static final ILootCondition.IBuilder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
	private static final ILootCondition.IBuilder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
	private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

	public static ArrayList<Block> KNOWN_BLOCKS = new ArrayList<>();

	public static ArrayList<Block> CROP_BLOCKS = new ArrayList<>();
	public static HashMap<Block, IItemProvider> CROPS = new HashMap<>();
	
	public static ArrayList<Block> LEAF_BLOCKS = new ArrayList<>();
	public static HashMap<Block, Block> LEAVES = new HashMap<>();

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
		
		LootTableMaps.addLeavesLoot(LEAVES);
		LEAVES.forEach((leaf, sapling) -> {
			LEAF_BLOCKS.add(leaf);
			this.add(leaf, createLeavesDrops(leaf, sapling, NORMAL_LEAVES_SAPLING_CHANCES));
		});

	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		KNOWN_BLOCKS.addAll(CROP_BLOCKS);
		KNOWN_BLOCKS.addAll(LEAF_BLOCKS);
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

	protected static LootTable.Builder createLeavesDrops(Block p_218540_0_, Block p_218540_1_, float... p_218540_2_) {
		return createSilkTouchOrShearsDispatchTable(p_218540_0_,
				applyExplosionCondition(p_218540_0_, ItemLootEntry.lootTableItem(p_218540_1_)).when(
						TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, p_218540_2_))).withPool(LootPool
								.lootPool().setRolls(ConstantRange.exactly(1)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
								.add(applyExplosionDecay(p_218540_0_,
										ItemLootEntry.lootTableItem(Items.STICK)
												.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))).when(
														TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE,
																0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
	}

}

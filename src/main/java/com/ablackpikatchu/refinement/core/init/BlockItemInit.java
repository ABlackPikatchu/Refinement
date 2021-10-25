package com.ablackpikatchu.refinement.core.init;

import static com.ablackpikatchu.refinement.core.init.BlockInit.*;
import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT_MACHINE;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.common.item.blockitem.StorageBinBlockItem;
import com.ablackpikatchu.refinement.core.anotation.registries.RegisterItem;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockItemInit {

	public static final ArrayList<Block> BLOCKS_THAT_NEED_BLOCKITEMS = Lists.newArrayList(BlockInit.REFINED_LOG.get(),
			BlockInit.REFINED_STRIPPED_LOG.get(), BlockInit.REFINED_LEAVES.get(), BlockInit.REFINED_SAPLING.get(),
			BlockInit.CARBON_GENERATOR_BLOCK.get(), ENERGY_GENERATOR_BLOCK.get(), SMELTER_BLOCK.get(),
			ALLOY_SMELTER_BLOCK.get(), SIGNALUM_BLOCK.get(), LUMIUM_BLOCK.get(), ENDERIUM_BLOCK.get(), TIN_BLOCK.get(),
			SILVER_BLOCK.get(), LEAD_BLOCK.get(), COPPER_BLOCK.get(), COPPER_ORE.get(), LEAD_ORE.get(), TIN_ORE.get(),
			SILVER_ORE.get());

	@RegisterItem(registryName = "alpha_storage_bin")
	public static final StorageBinBlockItem ALPHA_STORAGE_BIN_BLOCK_ITEM = new StorageBinBlockItem(
			BlockInit.ALPHA_STORAGE_BIN_BLOCK.get(), new Item.Properties().tab(REFINEMENT_MACHINE));
	
	@RegisterItem(registryName = "beta_storage_bin")
	public static final StorageBinBlockItem BETA_STORAGE_BIN_BLOCK_ITEM = new StorageBinBlockItem(
			BlockInit.BETA_STORAGE_BIN_BLOCK.get(), new Item.Properties().tab(REFINEMENT_MACHINE));
	
	@RegisterItem(registryName = "gamma_storage_bin")
	public static final StorageBinBlockItem GAMMA_STORAGE_BIN_BLOCK_ITEM = new StorageBinBlockItem(
			BlockInit.GAMMA_STORAGE_BIN_BLOCK, new Item.Properties().tab(REFINEMENT_MACHINE));
	
	@RegisterItem(registryName = "epsilon_storage_bin")
	public static final StorageBinBlockItem EPSILON_STORAGE_BIN_BLOCK_ITEM = new StorageBinBlockItem(
			BlockInit.EPSILON_STORAGE_BIN_BLOCK, new Item.Properties().tab(REFINEMENT_MACHINE));
	
	@RegisterItem(registryName = "omega_storage_bin")
	public static final StorageBinBlockItem OMEGA_STORAGE_BIN_BLOCK_ITEM = new StorageBinBlockItem(
			BlockInit.OMEGA_STORAGE_BIN_BLOCK, new Item.Properties().tab(REFINEMENT_MACHINE));


}

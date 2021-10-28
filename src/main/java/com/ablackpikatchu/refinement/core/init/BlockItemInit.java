package com.ablackpikatchu.refinement.core.init;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;
import static com.ablackpikatchu.refinement.common.ModRarity.ALPHA_RARITY;
import static com.ablackpikatchu.refinement.common.ModRarity.BETA_RARITY;
import static com.ablackpikatchu.refinement.common.ModRarity.EPSILON_RARITY;
import static com.ablackpikatchu.refinement.common.ModRarity.GAMMA_RARITY;
import static com.ablackpikatchu.refinement.common.ModRarity.OMEGA_RARITY;
import static com.ablackpikatchu.refinement.core.init.BlockInit.*;
import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT_MACHINE;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.common.item.blockitem.RefinementBlockItem;
import com.ablackpikatchu.refinement.common.item.blockitem.StorageBinBlockItem;
import com.ablackpikatchu.refinement.core.annotation.registries.HoldsRegistries;
import com.ablackpikatchu.refinement.core.annotation.registries.OnRegistryEvent;
import com.ablackpikatchu.refinement.core.annotation.registries.RegisterBlockItem;
import com.ablackpikatchu.refinement.core.annotation.registries.RegisterItem;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.event.RegistryEvent;

@HoldsRegistries
public class BlockItemInit {

	public static final ArrayList<Block> BLOCKS_THAT_NEED_BLOCKITEMS = Lists.newArrayList(BlockInit.REFINED_LOG.get(),
			BlockInit.REFINED_STRIPPED_LOG.get(), BlockInit.REFINED_LEAVES.get(), BlockInit.REFINED_SAPLING.get(),
			ALLOY_SMELTER_BLOCK.get(), SIGNALUM_BLOCK.get(), LUMIUM_BLOCK.get(), ENDERIUM_BLOCK.get(), TIN_BLOCK.get(),
			SILVER_BLOCK.get(), LEAD_BLOCK.get(), COPPER_BLOCK.get(), COPPER_ORE.get(), LEAD_ORE.get(), TIN_ORE.get(),
			SILVER_ORE.get());

	@HoldsRegistries
	public static class Machines {

		@RegisterItem(registryName = "alpha_storage_bin")
		public static final StorageBinBlockItem ALPHA_STORAGE_BIN_BLOCK_ITEM = new StorageBinBlockItem(
				BlockInit.ALPHA_STORAGE_BIN_BLOCK.get(),
				new Item.Properties().tab(REFINEMENT_MACHINE).rarity(ALPHA_RARITY).stacksTo(1));

		@RegisterItem(registryName = "beta_storage_bin")
		public static final StorageBinBlockItem BETA_STORAGE_BIN_BLOCK_ITEM = new StorageBinBlockItem(
				BlockInit.BETA_STORAGE_BIN_BLOCK,
				new Item.Properties().tab(REFINEMENT_MACHINE).rarity(BETA_RARITY).stacksTo(1));

		@RegisterItem(registryName = "gamma_storage_bin")
		public static final StorageBinBlockItem GAMMA_STORAGE_BIN_BLOCK_ITEM = new StorageBinBlockItem(
				BlockInit.GAMMA_STORAGE_BIN_BLOCK,
				new Item.Properties().tab(REFINEMENT_MACHINE).rarity(GAMMA_RARITY).stacksTo(1));

		@RegisterItem(registryName = "epsilon_storage_bin")
		public static final StorageBinBlockItem EPSILON_STORAGE_BIN_BLOCK_ITEM = new StorageBinBlockItem(
				BlockInit.EPSILON_STORAGE_BIN_BLOCK,
				new Item.Properties().tab(REFINEMENT_MACHINE).rarity(EPSILON_RARITY).stacksTo(1));

		@RegisterItem(registryName = "omega_storage_bin")
		public static final StorageBinBlockItem OMEGA_STORAGE_BIN_BLOCK_ITEM = new StorageBinBlockItem(
				BlockInit.OMEGA_STORAGE_BIN_BLOCK,
				new Item.Properties().tab(REFINEMENT_MACHINE).rarity(OMEGA_RARITY).stacksTo(1));

		@RegisterItem(registryName = "grinder")
		public static final BlockItem GRINDER_ITEM = new BlockItem(BlockInit.GRINDER.get(),
				new Item.Properties().tab(REFINEMENT_MACHINE));

		@RegisterItem(registryName = "mixer")
		public static final BlockItem MIXER_ITEM = new BlockItem(BlockInit.MIXER.get(),
				new Item.Properties().tab(REFINEMENT_MACHINE));

		@RegisterItem(registryName = "mold_press")
		public static final BlockItem MOLD_PRESS_ITEM = new BlockItem(BlockInit.MOLD_PRESS.get(),
				new Item.Properties().tab(REFINEMENT_MACHINE));
		
		@RegisterBlockItem
		public static final RefinementBlockItem SMELTER_ITEM = machineItem(BlockInit.SMELTER_BLOCK.get());
		
		@RegisterBlockItem
		public static final RefinementBlockItem ENERGY_GENERATOR_ITEM = machineItem(BlockInit.ENERGY_GENERATOR_BLOCK.get());
		
		@RegisterBlockItem
		public static final RefinementBlockItem CARBON_GENERATOR_ITEM = machineItem(BlockInit.CARBON_GENERATOR_BLOCK.get());
		
		private static RefinementBlockItem machineItem(Block machine) {
			return new RefinementBlockItem(machine, new Item.Properties().tab(REFINEMENT_MACHINE));
		}

	}
	
	@OnRegistryEvent(eventType = com.ablackpikatchu.refinement.core.annotation.registries.OnRegistryEvent.RegistryEvent.ITEMS)
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(BLANK_ORE_ITEM.setRegistryName(new ResourceLocation(MOD_ID, "blank_ore")));
	}
	
	public static final BlockItem BLANK_ORE_ITEM = new BlockItem(BlockInit.BLANK_ORE.get(), new Item.Properties().tab(RefinementItemGroup.REFINEMENT));

}

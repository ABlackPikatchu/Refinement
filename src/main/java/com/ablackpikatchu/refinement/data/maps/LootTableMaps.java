package com.ablackpikatchu.refinement.data.maps;

import java.util.ArrayList;
import java.util.HashMap;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.resourcecrops.core.CropInit;

import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;

public class LootTableMaps {

	public static void addBlockLoot(ArrayList<Block> map) {
		map.add(BlockInit.GRINDER.get());
		map.add(BlockInit.REFINED_LOG.get());
		map.add(BlockInit.REFINED_STRIPPED_LOG.get());
		map.add(BlockInit.REFINED_SAPLING.get());
	}

	/**
	 * Register any crop's block states, models, and loot tables (note: in order for
	 * this to work you need to <b>runData</b> and the textures for the crop have to
	 * exist in <i>refinement:assets/textures/blocks/crops/crop_name</i>) <br>
	 * <br>
	 * The first value of the map should be the crop block, and the second value
	 * should be the resource the crop produces
	 */
	public static void addCropLoot(HashMap<Block, IItemProvider> map) {
		map.put(CropInit.COAL_CROP.get(), CropInit.COAL_ESSENCE.get());
		map.put(CropInit.IRON_CROP.get(), CropInit.IRON_ESSENCE.get());
		map.put(CropInit.GOLD_CROP.get(), CropInit.GOLD_ESSENCE.get());
		map.put(CropInit.DIAMOND_CROP.get(), CropInit.DIAMOND_ESSENCE.get());
		map.put(CropInit.EMERALD_CROP.get(), CropInit.EMERALD_ESSENCE.get());
		map.put(CropInit.LAPIS_CROP.get(), CropInit.LAPIS_ESSENCE.get());
		map.put(CropInit.NATURE_CROP.get(), CropInit.NATURE_ESSENCE.get());
		map.put(CropInit.PURE_CRYSTAL_CROP.get(), CropInit.PURE_CRYSTAL_ESSENCE.get());
		map.put(CropInit.WOOD_CROP.get(), CropInit.WOOD_ESSENCE.get());
	}
	
	public static void addLeavesLoot(HashMap<Block, Block> map) {
		map.put(BlockInit.REFINED_LEAVES.get(), BlockInit.REFINED_SAPLING.get());
	}

}

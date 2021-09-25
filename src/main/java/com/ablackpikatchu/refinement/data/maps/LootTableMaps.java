package com.ablackpikatchu.refinement.data.maps;

import java.util.ArrayList;
import java.util.HashMap;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.CropInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;

public class LootTableMaps {

	public static void addBlockLoot(ArrayList<Block> map) {
		map.add(BlockInit.GRINDER.get());
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
		map.put(CropInit.COAL_CROP.get(), ItemInit.COAL_ESSENCE.get());
		map.put(CropInit.IRON_CROP.get(), ItemInit.IRON_ESSENCE.get());
	}

}

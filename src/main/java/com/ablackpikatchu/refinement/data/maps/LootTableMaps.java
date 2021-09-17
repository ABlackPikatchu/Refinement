package com.ablackpikatchu.refinement.data.maps;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.core.init.BlockInit;

import net.minecraft.block.Block;

public class LootTableMaps {

	public static void addBlockLoot(ArrayList<Block> map) {
		map.add(BlockInit.GRINDER.get());
	}
	
}

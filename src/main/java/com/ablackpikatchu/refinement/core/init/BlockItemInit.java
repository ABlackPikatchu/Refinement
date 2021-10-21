package com.ablackpikatchu.refinement.core.init;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;

import static com.ablackpikatchu.refinement.core.init.BlockInit.*;

import java.util.ArrayList;

public class BlockItemInit {

	public static final ArrayList<Block> BLOCKS_THAT_NEED_BLOCKITEMS = Lists.newArrayList(BlockInit.REFINED_LOG.get(),
			BlockInit.REFINED_STRIPPED_LOG.get(), BlockInit.REFINED_LEAVES.get(), BlockInit.REFINED_SAPLING.get(),
			BlockInit.CARBON_GENERATOR_BLOCK.get(), ENERGY_GENERATOR_BLOCK.get(), SMELTER_BLOCK.get(),
			ALLOY_SMELTER_BLOCK.get(), SIGNALUM_BLOCK.get(), LUMIUM_BLOCK.get(),
			ENDERIUM_BLOCK.get(), TIN_BLOCK.get(), SILVER_BLOCK.get(), LEAD_BLOCK.get(), COPPER_BLOCK.get(),
			COPPER_ORE.get(), LEAD_ORE.get(), TIN_ORE.get(), SILVER_ORE.get());

}

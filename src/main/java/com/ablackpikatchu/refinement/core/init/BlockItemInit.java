package com.ablackpikatchu.refinement.core.init;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;

import static com.ablackpikatchu.refinement.core.init.BlockInit.*;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class BlockItemInit {

    public static final ArrayList<Block> BLOCKS_THAT_NEED_BLOCKITEMS = Lists.newArrayList(BlockInit.REFINED_LOG.get(),
            BlockInit.REFINED_STRIPPED_LOG.get(), BlockInit.REFINED_LEAVES.get(), BlockInit.REFINED_SAPLING.get(), BlockInit.CARBON_GENERATOR_BLOCK.get(), ENERGY_GENERATOR_BLOCK.get());

}

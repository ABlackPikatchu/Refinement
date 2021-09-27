package com.ablackpikatchu.refinement.core.init;

import static com.ablackpikatchu.refinement.core.init.ItemInit.ITEMS;
import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import net.minecraftforge.fml.RegistryObject;

@SuppressWarnings("unused")
public class BlockItemInit {

	public static final ArrayList<Block> BLOCKS_THAT_NEED_BLOCKITEMS = Lists.newArrayList(BlockInit.REFINED_LOG.get(),
			BlockInit.REFINED_STRIPPED_LOG.get(), BlockInit.REFINED_LEAVES.get(), BlockInit.REFINED_SAPLING.get());

}

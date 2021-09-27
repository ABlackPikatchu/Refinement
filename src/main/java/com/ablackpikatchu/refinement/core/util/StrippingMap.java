package com.ablackpikatchu.refinement.core.util;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;

public class StrippingMap {

	public static void createStrippable(Block input, Block output) {
		AxeItem.STRIPABLES = Maps.newHashMap(AxeItem.STRIPABLES);
		AxeItem.STRIPABLES.put(input, output);
	}

	public static void registerStrippables() {
		createStrippable(BlockInit.REFINED_LOG.get(), BlockInit.REFINED_STRIPPED_LOG.get());
	}

}

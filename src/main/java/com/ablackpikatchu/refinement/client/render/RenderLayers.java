package com.ablackpikatchu.refinement.client.render;

import com.ablackpikatchu.refinement.core.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class RenderLayers {
	
	public static void setRenderLayers() {
		RenderTypeLookup.setRenderLayer(BlockInit.MACHINE_FRAME.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.VACCUMULATOR.get(), RenderType.cutout());
		cutout(BlockInit.IRON_CROP.get());
	}
	
	private static void cutout(Block block) {
		RenderTypeLookup.setRenderLayer(block, RenderType.cutout());
	}

}

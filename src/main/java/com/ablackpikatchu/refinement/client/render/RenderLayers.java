package com.ablackpikatchu.refinement.client.render;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.resourcecrops.core.CropInit;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

import net.minecraftforge.fml.RegistryObject;

public class RenderLayers {

	public static void setRenderLayers() {
		RenderTypeLookup.setRenderLayer(BlockInit.MACHINE_FRAME.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.VACCUMULATOR.get(), RenderType.cutout());
		cutout(BlockInit.DNA_SEQUENCER.get());
		
		cutout(BlockInit.REFINED_LEAVES.get());
		cutout(BlockInit.REFINED_SAPLING.get());
		
		cutout(BlockInit.ENERGY_CABLE_BLOCK.get());
		
		CropInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			cutout(block);
		});
	}

	private static void cutout(Block block) {
		RenderTypeLookup.setRenderLayer(block, RenderType.cutout());
	}

}

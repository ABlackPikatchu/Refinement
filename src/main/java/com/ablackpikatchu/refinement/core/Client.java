package com.ablackpikatchu.refinement.core;

import com.ablackpikatchu.refinement.core.init.BlockInit;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class Client {
	
	public static void setRenderLayers() {
		RenderTypeLookup.setRenderLayer(BlockInit.MACHINE_FRAME.get(), RenderType.cutout());
	}

}

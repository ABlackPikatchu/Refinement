package com.ablackpikatchu.refinement.client;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.ModelLoader;

public class ClientSetup {

	public ClientSetup() {
		
		ModelLoader.addSpecialModel(modLoc("block/cable/core"));
		ModelLoader.addSpecialModel(modLoc("block/cable/extension"));
		ModelLoader.addSpecialModel(modLoc("block/cable/straight"));
		
	}
	
	public ResourceLocation modLoc(String value) {
		return new ResourceLocation(Refinement.MOD_ID, value);
	}
	
}

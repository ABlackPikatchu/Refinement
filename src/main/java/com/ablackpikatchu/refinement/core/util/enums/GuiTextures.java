package com.ablackpikatchu.refinement.core.util.enums;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.util.ResourceLocation;

public enum GuiTextures {

	CONTAINER_ELEMENTS(new ResourceLocation(Refinement.MOD_ID, "textures/gui/element/container_elements.png"));
	
	private final ResourceLocation texture;
	
	private GuiTextures(ResourceLocation texture) {
		this.texture = texture;
	}
	
	public ResourceLocation getTextureLocation() {
		return this.texture;
	}
	
}

package com.ablackpikatchu.refinement.client.screen.element;

import com.ablackpikatchu.refinement.api.screen.element.GuiElement;
import com.ablackpikatchu.refinement.core.util.enums.GuiTextures;

@SuppressWarnings("unused")
public class SussyElement extends GuiElement {
	
	private int renderX;
	private int renderY;

	public SussyElement() {
		super(0, 0, 16, 16, GuiTextures.CONTAINER_ELEMENTS.getTextureLocation());
		this.renderX = -1;
		this.renderY = -1;
	}

}

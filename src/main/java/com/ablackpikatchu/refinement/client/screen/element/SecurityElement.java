package com.ablackpikatchu.refinement.client.screen.element;

import com.ablackpikatchu.refinement.api.screen.element.GuiElement;
import com.ablackpikatchu.refinement.core.util.enums.GuiTextures;

public class SecurityElement extends GuiElement {

	public SecurityElement(int x, int y, int width, int height) {
		super(x, y, width, height, GuiTextures.CONTAINER_ELEMENTS.getTextureLocation());
	}
	
	public static class SecurityPublicElement extends SecurityElement {

		public SecurityPublicElement() {
			super(17, 35, 6, 7);
		}
		
	}
	
	public static class SecurityPrivateElement extends SecurityElement {

		public SecurityPrivateElement() {
			super(17, 46, 6, 6);
		}
		
	}

}

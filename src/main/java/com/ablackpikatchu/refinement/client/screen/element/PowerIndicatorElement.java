package com.ablackpikatchu.refinement.client.screen.element;

import com.ablackpikatchu.refinement.api.screen.element.GuiElement;
import com.ablackpikatchu.refinement.core.util.enums.GuiTextures;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.StringTextComponent;

public class PowerIndicatorElement extends GuiElement {

	public PowerIndicatorElement() {
		super(18, 0, 32, 32, GuiTextures.CONTAINER_ELEMENTS.getTextureLocation());
	}

	@Override
	public void renderToolTip(MatrixStack stack, int x, int y) {
		Minecraft minecraft = Minecraft.getInstance();
		FontRenderer fontrenderer = minecraft.font;
		fontrenderer.draw(stack, new StringTextComponent("ye"), (float) x, (float) y, 0xffffff);
	}

}

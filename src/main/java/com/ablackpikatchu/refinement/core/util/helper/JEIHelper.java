package com.ablackpikatchu.refinement.core.util.helper;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class JEIHelper {

	public static void renderText(MatrixStack matrixStack, String text, int xPos, int yPos, int colour) {
			Minecraft minecraft = Minecraft.getInstance();
			FontRenderer fontRenderer = minecraft.font;
			fontRenderer.draw(matrixStack, text, xPos, yPos, colour);
	}
	
}

package com.ablackpikatchu.refinement.client.screen.tileentity;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.api.screen.tileentity.MachineContainerScreen;
import com.ablackpikatchu.refinement.common.container.MoldPressContainer;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MoldPressScreen extends MachineContainerScreen<MoldPressContainer> {

	public static final ResourceLocation MOLD_PRESS_GUI = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/mold_press.png");
	public static final ResourceLocation MOLD_PRESS_JEI_SCREEN = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/jei/mold_press.png");

	public MoldPressScreen(MoldPressContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn, MOLD_PRESS_GUI);

		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 220;
		this.imageHeight = 170;
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int x, int y) {
		this.font.draw(matrixStack, this.inventory.getDisplayName(), 7.0f, 65.0f, 0xA3703A);
		this.font.draw(matrixStack, this.menu.te.getDisplayName(), 5.0f, 7.0f, 0xA3703A);
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
		renderSussyGui(matrixStack, partialTicks, this.topPos + 77);
		this.minecraft.textureManager.bind(MOLD_PRESS_GUI);

		for (int i = 0; i <= 17; ++i) {
			if (this.menu.getProgressionScaled() >= i) {
				this.blit(matrixStack, this.leftPos + 50, this.topPos + 29 + 17 - i, 5, 236 + 17 - i, 40, 1);
			}
		}
	}
}

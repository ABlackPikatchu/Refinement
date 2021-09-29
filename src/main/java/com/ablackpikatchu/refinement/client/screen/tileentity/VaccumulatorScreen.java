package com.ablackpikatchu.refinement.client.screen.tileentity;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.container.VaccumulatorContainer;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class VaccumulatorScreen extends MachineContainerScreen<VaccumulatorContainer> {

	public static final ResourceLocation VACCUMULATOR_GUI = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/vaccumulator.png");

	public VaccumulatorScreen(VaccumulatorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn, VACCUMULATOR_GUI);

		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 176;
		this.imageHeight = 184;
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int x, int y) {
		this.font.draw(matrixStack, this.inventory.getDisplayName(), 8.0f, 91.0f, 0xA3703A);
		this.font.draw(matrixStack, this.menu.te.getDisplayName(), 8.0f, 6.0f, 0xA3703A);
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
		renderSussyGui(matrixStack, partialTicks, this.topPos + 102);
	}
}

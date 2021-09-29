package com.ablackpikatchu.refinement.client.screen.tileentity;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.container.MixerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MixerScreen extends MachineContainerScreen<MixerContainer> {

	public static final ResourceLocation MIXER_GUI = new ResourceLocation(Refinement.MOD_ID, "textures/gui/mixer.png");
	public static final ResourceLocation MIXER_JEI_SCREEN = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/jei/mixer.png");

	public MixerScreen(MixerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn, MIXER_GUI);

		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 176;
		this.imageHeight = 201;
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int x, int y) {
		this.font.draw(matrixStack, this.inventory.getDisplayName(), 7.0f, 70.0f, 0xA3703A);
		this.font.draw(matrixStack, this.menu.te.getDisplayName(), 7.0f, 4.0f, 0xA3703A);
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
		renderSussyGui(matrixStack, partialTicks, this.topPos + 82);
		this.minecraft.textureManager.bind(MIXER_GUI);
		this.blit(matrixStack, this.leftPos + 51, this.topPos + 19, 6, 215, this.menu.getProgressionScaled(), 24);
	}
}

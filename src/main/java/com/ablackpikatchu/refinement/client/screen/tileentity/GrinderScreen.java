package com.ablackpikatchu.refinement.client.screen.tileentity;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.container.GrinderContainer;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrinderScreen extends MachineContainerScreen<GrinderContainer> {

	public static final ResourceLocation GRINDER_GUI = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/grinder.png");
	public static final ResourceLocation GRINDER_JEI_SCREEN = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/jei/grinder.png");

	public GrinderScreen(GrinderContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn, GRINDER_GUI);

		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 220;
		this.imageHeight = 170;
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int x, int y) {
		this.font.draw(matrixStack, this.inventory.getDisplayName(), 7.0f, 65.0f, 0xA3703A);
		this.font.draw(matrixStack, this.menu.te.getDisplayName(), 7.0f, 7.0f, 0xA3703A);
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
		renderSussyGui(matrixStack, partialTicks, this.topPos + 77);
		this.minecraft.textureManager.bind(GRINDER_GUI);
		this.blit(matrixStack, this.leftPos + 68, this.topPos + 9, 2, 207, this.menu.getProgressionScaled(), 47);
	}
}
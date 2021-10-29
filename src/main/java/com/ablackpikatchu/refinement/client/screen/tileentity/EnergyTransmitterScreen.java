package com.ablackpikatchu.refinement.client.screen.tileentity;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.RefinementLang;
import com.ablackpikatchu.refinement.common.container.EnergyTransmitterContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class EnergyTransmitterScreen extends ContainerScreen<EnergyTransmitterContainer> {
	
	public static final ResourceLocation ENERGY_TRANSMITTER_GUI = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/energy_transmitter.png");

	public EnergyTransmitterScreen(EnergyTransmitterContainer pMenu, PlayerInventory pPlayerInventory,
			ITextComponent pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
		
		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 176;
		this.imageHeight = 130;
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}
	
	@Override
	protected void renderLabels(MatrixStack pMatrixStack, int pX, int pY) {
		this.font.draw(pMatrixStack, this.menu.te.getDisplayName(), this.titleLabelX, this.titleLabelY,
				0xA3703A);
		this.font.draw(pMatrixStack, RefinementLang.getComponent("energy.energy_used_per_operation_transmitter").getString().replace("%e", "" + this.menu.energyUsed.get()), 8, 37,
				000000);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack pMatrixStack, float pPartialTicks, int pX, int pY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.textureManager.bind(ENERGY_TRANSMITTER_GUI);
		int x = (this.width - this.imageWidth) / 2;
		int y = (this.height - this.imageHeight) / 2;
		this.blit(pMatrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
	}
 
}

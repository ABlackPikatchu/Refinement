package com.ablackpikatchu.refinement.client.screen.tileentity;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.element.SecurityElement;
import com.ablackpikatchu.refinement.client.screen.element.SecurityElement.SecurityPrivateElement;
import com.ablackpikatchu.refinement.client.screen.element.SecurityElement.SecurityPublicElement;
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
	
	public SecurityElement secElement;

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
		secElement = new SecurityPublicElement();
		if (this.menu.security.get() == 1)
			secElement = new SecurityPrivateElement();
		super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
		renderSussyGui(matrixStack, partialTicks, this.topPos + 77);
		//secElement.render(matrixStack, this.leftPos + 191, this.topPos + 82, partialTicks);
		this.minecraft.textureManager.bind(GRINDER_GUI);
		this.blit(matrixStack, this.leftPos + 68, this.topPos + 9, 2, 207, this.menu.getProgressionScaled(), 47);
	}

	@Override
	public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
		//if (isBetween((int) pMouseX, (int) pMouseY, this.leftPos + 191, this.topPos + 82, 6, 7))
		//	RefinementNetwork.TILE_SECURITY_CHANNEL.sendToServer(new TileSecurityMessage(this.menu.te.getBlockPos()));
		return super.mouseClicked(pMouseX, pMouseY, pButton);
	}
}

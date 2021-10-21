package com.ablackpikatchu.refinement.client.screen.tileentity;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.element.EnergyInfoTextBoxElement;
import com.ablackpikatchu.refinement.client.screen.element.PowerIndicatorElement;
import com.ablackpikatchu.refinement.client.screen.element.SecurityElement;
import com.ablackpikatchu.refinement.client.screen.element.SecurityElement.SecurityPrivateElement;
import com.ablackpikatchu.refinement.client.screen.element.SecurityElement.SecurityPublicElement;
import com.ablackpikatchu.refinement.common.container.GrinderContainer;
import com.ablackpikatchu.refinement.core.util.text.NumberFormatting;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

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
	protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
		this.font.draw(matrixStack, this.inventory.getDisplayName(), 7.0f, 65.0f, 0xA3703A);
		this.font.draw(matrixStack, this.menu.te.getDisplayName(), 7.0f, 7.0f, 0xA3703A);
		if (isBetween(mouseX, mouseY, this.leftPos + 176, this.topPos + 0, 32, 32) && this.menu.usingEnergy()) {
			isHoveringOverEnergyInfo = true;
			EnergyInfoTextBoxElement textBoxElement = new EnergyInfoTextBoxElement();
			int dropDownNumber = 10;
			int xPos = mouseX - this.leftPos - textBoxElement.getWidth();
			int yPos = mouseY - this.topPos;
			textBoxElement.render(matrixStack, xPos, yPos + dropDownNumber, 0.0f);
			this.font.draw(matrixStack,
					new StringTextComponent("Energy Stored: \u00A7b"
							+ NumberFormatting.toThousandsFormat(this.menu.currentEnergy.get(), 1) + "\u00A7f FE"),
					xPos + 3, yPos + dropDownNumber + 4, 0xffffff);
			this.font.draw(matrixStack,
					new StringTextComponent("Energy Used: \u00A7b" + this.menu.energyUsed.get() + "\u00A7f FE/tick"),
					xPos + 3, yPos + dropDownNumber + 4 + 10, 0xffffff);
			this.font.draw(matrixStack,
					new StringTextComponent(
							"Max Transfer: \u00A7b" + this.menu.te.energyStorage.getMaxReceive() + "\u00A7f FE/tick"),
					xPos + 3, yPos + dropDownNumber + 4 + 20, 0xffffff);
		} else
			isHoveringOverEnergyInfo = false;
	}
	
	@Override
	public ArrayList<Integer> getNoEnergyInfoRenderSlots() {
		return Lists.newArrayList(0, 1);
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		secElement = new SecurityPublicElement();
		if (this.menu.security.get() == 1)
			secElement = new SecurityPrivateElement();
		super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
		renderSussyGui(matrixStack, partialTicks, this.topPos + 77);
		
		PowerIndicatorElement powerIndicatorElement = new PowerIndicatorElement();
		if (this.menu.usingEnergy())
			powerIndicatorElement.render(matrixStack, this.leftPos + 176, this.topPos + 0, partialTicks);
		
		//secElement.render(matrixStack, this.leftPos + 191, this.topPos + 82, partialTicks);
		this.minecraft.textureManager.bind(GRINDER_GUI);
		this.blit(matrixStack, this.leftPos + 68, this.topPos + 9, 2, 207, this.menu.getProgressionScaled(), 47);
		renderFuelSlot(matrixStack, this.menu.usingEnergy());
		renderEnergyBar(matrixStack, this.menu.usingEnergy());
	}

	@Override
	public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
		//if (isBetween((int) pMouseX, (int) pMouseY, this.leftPos + 191, this.topPos + 82, 6, 7))
		//	RefinementNetwork.TILE_SECURITY_CHANNEL.sendToServer(new TileSecurityMessage(this.menu.te.getBlockPos()));
		return super.mouseClicked(pMouseX, pMouseY, pButton);
	}
	
	public void renderEnergyBar(MatrixStack matrixStack, boolean usingEnergy) {
		if (usingEnergy) {
			for (int i = 0; i <= 69; ++i) {
				if (this.menu.getEnergyScaled() >= i) {
					this.blit(matrixStack, this.leftPos + 161, this.topPos + 4 + 69 - i, 249, 0 + 69 - i, 7, 1);
				}
			}
		} else
			this.blit(matrixStack, this.leftPos + 161, this.topPos + 4, 242, 176, 7, 69);
	}

	public void renderFuelSlot(MatrixStack matrixStack, boolean usingEnergy) {
		if (!usingEnergy)
			this.blit(matrixStack, this.leftPos + 7, this.topPos + 43, 215, 228, 18, 18);
	}
}

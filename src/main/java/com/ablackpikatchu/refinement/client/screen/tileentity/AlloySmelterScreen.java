package com.ablackpikatchu.refinement.client.screen.tileentity;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.element.EnergyInfoTextBoxElement;
import com.ablackpikatchu.refinement.client.screen.element.PowerIndicatorElement;
import com.ablackpikatchu.refinement.common.container.AlloySmelterContainer;
import com.ablackpikatchu.refinement.core.util.text.NumberFormatting;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class AlloySmelterScreen extends MachineContainerScreen<AlloySmelterContainer> {

	public static final ResourceLocation ALLOY_SMELTER_GUI = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/alloy_smelter.png");

	public static final ResourceLocation ALLOY_SMELTER_JEI_SCREEN = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/jei/alloy_smelter.png");

	public AlloySmelterScreen(AlloySmelterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn, ALLOY_SMELTER_GUI);

		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 220;
		this.imageHeight = 201;
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
		super.renderLabels(matrixStack, mouseX, mouseY);
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
		return Lists.newArrayList(0, 1, 2, 3, 4);
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
		renderSussyGui(matrixStack, partialTicks, this.topPos + 84);

		PowerIndicatorElement powerIndicatorElement = new PowerIndicatorElement();
		if (this.menu.usingEnergy())
			powerIndicatorElement.render(matrixStack, this.leftPos + 176, this.topPos + 0, partialTicks);

		bind(ALLOY_SMELTER_GUI);
		this.blit(matrixStack, this.leftPos + 98, this.topPos + 25, 4, 216, this.menu.getProgressionScaled(), 17);
		renderEnergyBar(matrixStack, this.menu.usingEnergy());
		renderFuelSlot(matrixStack, this.menu.usingEnergy());
	}

	public void renderEnergyBar(MatrixStack matrixStack, boolean usingEnergy) {
		if (usingEnergy) {
			for (int i = 0; i <= 76; ++i) {
				if (this.menu.getEnergyScaled() >= i) {
					this.blit(matrixStack, this.leftPos + 161, this.topPos + 4 + 76 - i, 249, 0 + 76 - i, 7, 1);
				}
			}
		} else
			this.blit(matrixStack, this.leftPos + 161, this.topPos + 4, 241, 175, 7, 76);
	}

	public void renderFuelSlot(MatrixStack matrixStack, boolean usingEnergy) {
		if (!usingEnergy)
			this.blit(matrixStack, this.leftPos + 7, this.topPos + 50, 215, 228, 18, 18);
	}

}

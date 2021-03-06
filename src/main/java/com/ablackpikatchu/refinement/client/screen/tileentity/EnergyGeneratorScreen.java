package com.ablackpikatchu.refinement.client.screen.tileentity;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.api.screen.tileentity.MachineContainerScreen;
import com.ablackpikatchu.refinement.client.screen.element.EnergyInfoTextBoxElement;
import com.ablackpikatchu.refinement.client.screen.element.PowerIndicatorElement;
import com.ablackpikatchu.refinement.common.container.EnergyGeneratorContainer;
import com.ablackpikatchu.refinement.core.util.text.NumberFormatting;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class EnergyGeneratorScreen extends MachineContainerScreen<EnergyGeneratorContainer> {

    public static final ResourceLocation ENERGY_GENERATOR_GUI = new ResourceLocation(Refinement.MOD_ID,
            "textures/gui/energy_generator.png");

    public EnergyGeneratorScreen(EnergyGeneratorContainer screenContainer, PlayerInventory inv,
                                 ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, ENERGY_GENERATOR_GUI);

        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 220;
        this.imageHeight = 201;
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.font.draw(matrixStack, this.inventory.getDisplayName(), 7.0f, 73.0f, 0xA3703A);
        this.font.draw(matrixStack, this.menu.te.getDisplayName(), 7.0f, 7.0f, 0xA3703A);
        if (isBetween(mouseX, mouseY, this.leftPos + 176, this.topPos + 0, 32, 32)) {
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
            this.font.draw(matrixStack, new StringTextComponent("Energy Made: \u00A7b"
                            + this.menu.energyMade.get() + "\u00A7f FE/tick"),
                    xPos + 3, yPos + dropDownNumber + 4 + 10, 0xffffff);
            this.font.draw(matrixStack,
                    new StringTextComponent(
                            "Max Transfer: \u00A7b" + this.menu.te.energyStorage.getMaxExtract() + "\u00A7f FE/tick"),
                    xPos + 3, yPos + dropDownNumber + 4 + 20, 0xffffff);
        } else
        	isHoveringOverEnergyInfo = false;
    }
    
    @Override
    public ArrayList<Integer> getNoEnergyInfoRenderSlots() {
    	return Lists.newArrayList(0);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        renderSussyGui(matrixStack, partialTicks, this.topPos + 84);

        PowerIndicatorElement powerIndicatorElement = new PowerIndicatorElement();
        powerIndicatorElement.render(matrixStack, this.leftPos + 176, this.topPos + 0, partialTicks);

        this.minecraft.textureManager.bind(ENERGY_GENERATOR_GUI);

        // Energy bar
        for (int i = 0; i <= 76; ++i) {
            if (this.menu.getEnergyScaled() >= i) {
                this.blit(matrixStack, this.leftPos + 161, this.topPos + 4 + 76 - i, 249, 0 + 76 - i, 7, 1);
            }
        }
        this.blit(matrixStack, this.leftPos + 45, this.topPos + 32, 2, 231, this.menu.getProgressionScaled(), 22);

    }

}

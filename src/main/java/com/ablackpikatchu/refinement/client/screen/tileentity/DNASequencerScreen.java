package com.ablackpikatchu.refinement.client.screen.tileentity;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.api.screen.tileentity.MachineContainerScreen;
import com.ablackpikatchu.refinement.common.container.DNASequencerContainer;
import com.ablackpikatchu.refinement.datafixers.util.text.Color;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DNASequencerScreen extends MachineContainerScreen<DNASequencerContainer> {

	public static final ResourceLocation DNA_SEQUENCER_GUI = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/dna_sequencer.png");
	public static final ResourceLocation DNA_SEQUENCER_JEI_SCREEN = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/jei/dna_sequencer.png");

	public DNASequencerScreen(DNASequencerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn, DNA_SEQUENCER_GUI);

		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 220;
		this.imageHeight = 201;

		//this.addButton(new Button(this.leftPos + 63, this.topPos + 26, 30, 30, DialogTexts.GUI_DONE,
		//		(p_214187_1_) -> System.out.println("yes")));
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int x, int y) {
		this.font.draw(matrixStack, this.inventory.getDisplayName(), 7.0f, 70.0f, 0xA3703A);
		this.font.draw(matrixStack, this.menu.te.getDisplayName(), 7.0f, 4.0f, 0xA3703A);
		if (this.menu.hasValidRecipe()) {
			if (this.menu.getSuccessProbability() < 100)
				this.font.draw(matrixStack, "Success Chance: " + this.menu.getSuccessProbability() + "%", 62.0f, 50.0f,
						new Color("dark_blue").getColourInt());
			else
				this.font.draw(matrixStack, "Success Chance:" + this.menu.getSuccessProbability() + "%", 62.0f, 50.0f,
						new Color("dark_blue").getColourInt());
		}
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
		renderSussyGui(matrixStack, partialTicks, this.topPos + 82);
		bind(DNA_SEQUENCER_GUI);
		this.blit(matrixStack, this.leftPos + 62, this.topPos + 26, 3, 231, this.menu.getProgressionScaled(), 22);
	}

}

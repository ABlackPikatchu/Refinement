package com.ablackpikatchu.refinement.client.screen.tileentity;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.container.DNASequencerContainer;
import com.ablackpikatchu.refinement.core.config.ClientConfig;
import com.ablackpikatchu.refinement.datafixers.util.text.Color;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DNASequencerScreen extends ContainerScreen<DNASequencerContainer> {

	private int ticks;
	private int sus1 = this.menu.te.getLevel().random.nextInt(8);
	private int sus2 = this.menu.te.getLevel().random.nextInt(8);
	private int sus3 = this.menu.te.getLevel().random.nextInt(8);
	private int sus4 = this.menu.te.getLevel().random.nextInt(8);

	public static final ResourceLocation DNA_SEQUENCER_GUI = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/dna_sequencer.png");
	public static final ResourceLocation DNA_SEQUENCER_JEI_SCREEN = new ResourceLocation(Refinement.MOD_ID,
			"textures/gui/jei/dna_sequencer.png");

	public DNASequencerScreen(DNASequencerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);

		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 176;
		this.imageHeight = 201;
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
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

	@SuppressWarnings({ "resource", "deprecation" })
	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		ticks++;
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.textureManager.bind(DNA_SEQUENCER_GUI);
		int x = (this.width - this.imageWidth) / 2;
		int y = (this.height - this.imageHeight) / 2;
		this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);

		if (ClientConfig.SUSSY_GUI.get()) {
			if (ticks >= 200) {
				sus1 = this.menu.te.getLevel().random.nextInt(8);
				sus2 = this.menu.te.getLevel().random.nextInt(8);
				sus3 = this.menu.te.getLevel().random.nextInt(8);
				sus4 = this.menu.te.getLevel().random.nextInt(8);
				ticks = 0;
			}

			this.blit(matrixStack, this.leftPos + (8 + (18 * sus1)), this.topPos + 82, 194, 63, 16, 16);
			this.blit(matrixStack, this.leftPos + (8 + (18 * sus2)), this.topPos + 100, 194, 63, 16, 16);
			this.blit(matrixStack, this.leftPos + (8 + (18 * sus3)), this.topPos + 118, 194, 63, 16, 16);
			this.blit(matrixStack, this.leftPos + (8 + (18 * sus4)), this.topPos + 140, 194, 63, 16, 16);
		}

		this.blit(matrixStack, this.leftPos + 62, this.topPos + 26, 179, 8, this.menu.getProgressionScaled(), 22);
	}

}

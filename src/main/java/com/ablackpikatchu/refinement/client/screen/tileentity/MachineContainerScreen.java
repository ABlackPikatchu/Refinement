package com.ablackpikatchu.refinement.client.screen.tileentity;

import java.util.Random;

import com.ablackpikatchu.refinement.client.screen.element.SussyElement;
import com.ablackpikatchu.refinement.core.config.ClientConfig;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MachineContainerScreen<T extends Container> extends ContainerScreen<T> {

	public final ResourceLocation guiTexture;
	private Random random = new Random();

	private int ticks;
	private int sus1 = random.nextInt(8);
	private int sus2 = random.nextInt(8);
	private int sus3 = random.nextInt(8);
	private int sus4 = random.nextInt(8);
	protected SussyElement sussyElement = new SussyElement();
	
	public MachineContainerScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn,
			ResourceLocation guiTexture) {
		super(screenContainer, inv, titleIn);
		this.guiTexture = guiTexture;
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.textureManager.bind(this.guiTexture);
		int x = (this.width - this.imageWidth) / 2;
		int y = (this.height - this.imageHeight) / 2;
		this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
		ticks++;
	}

	protected void renderSussyGui(MatrixStack matrixStack, float partialTicks, int topInventoryStart) {
		if (ClientConfig.SUSSY_GUI.get()) {
			if (ticks >= 200) {
				sus1 = random.nextInt(8);
				sus2 = random.nextInt(8);
				sus3 = random.nextInt(8);
				sus4 = random.nextInt(8);
				ticks = 0;
			}

			sussyElement.render(matrixStack, this.leftPos + (8 + (18 * sus1)), topInventoryStart, partialTicks);
			sussyElement.render(matrixStack, this.leftPos + (8 + (18 * sus2)), topInventoryStart + 18, partialTicks);
			sussyElement.render(matrixStack, this.leftPos + (8 + (18 * sus3)), topInventoryStart + 36, partialTicks);
			sussyElement.render(matrixStack, this.leftPos + (8 + (18 * sus4)), topInventoryStart + 58, partialTicks);
		}
	}

}

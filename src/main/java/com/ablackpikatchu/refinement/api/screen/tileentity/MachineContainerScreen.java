package com.ablackpikatchu.refinement.api.screen.tileentity;

import java.util.ArrayList;
import java.util.Random;

import com.ablackpikatchu.refinement.api.container.MachineContainer;
import com.ablackpikatchu.refinement.api.screen.element.GuiElement;
import com.ablackpikatchu.refinement.client.RefinementLang;
import com.ablackpikatchu.refinement.client.screen.element.EnergyInfoTextBoxElement;
import com.ablackpikatchu.refinement.client.screen.element.PowerIndicatorElement;
import com.ablackpikatchu.refinement.client.screen.element.SussyElement;
import com.ablackpikatchu.refinement.core.config.ClientConfig;
import com.ablackpikatchu.refinement.core.util.text.NumberFormatting;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public abstract class MachineContainerScreen<T extends MachineContainer<?>> extends ContainerScreen<T> {

	protected final ArrayList<GuiElement> elements = new ArrayList<>();

	public final ResourceLocation guiTexture;
	private Random random = new Random();

	private int ticks;
	private int sus1 = random.nextInt(8);
	private int sus2 = random.nextInt(8);
	private int sus3 = random.nextInt(8);
	private int sus4 = random.nextInt(8);
	protected SussyElement sussyElement = new SussyElement();

	protected int mX;
	protected int mY;

	protected PowerIndicatorElement powerIndicatorElement = new PowerIndicatorElement();

	public MachineContainerScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn,
			ResourceLocation guiTexture) {
		super(screenContainer, inv, titleIn);
		this.guiTexture = guiTexture;
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
		updateElements();
		drawElements(matrixStack, true);
		font.draw(matrixStack, menu.te.getDisplayName(), titleLabelX, titleLabelY,
				0xA3703A);
		font.draw(matrixStack, inventory.getDisplayName(), inventoryLabelX,
				inventoryLabelY, 0xA3703A);
	}

	protected void renderUsingEnergy(MatrixStack matrixStack, int mouseX, int mouseY, int currentEnergy, int energyUsed, int maxReceive) {
		isHoveringOverEnergyInfo = true;
		EnergyInfoTextBoxElement textBoxElement = new EnergyInfoTextBoxElement();
		int dropDownNumber = 10;
		int xPos = mouseX - leftPos - textBoxElement.getWidth();
		int yPos = mouseY - topPos;
		textBoxElement.render(matrixStack, xPos, yPos + dropDownNumber, 0.0f);
		font.draw(matrixStack,
				new StringTextComponent(RefinementLang.ENERGY_STORED.getString().replace("%e", NumberFormatting.toThousandsFormat(currentEnergy, 1))),
				xPos + 3, yPos + dropDownNumber + 4, 0xffffff);
		font.draw(matrixStack,
				new StringTextComponent(RefinementLang.ENERGY_USED.getString().replace("%e", "" + energyUsed)),
				xPos + 3, yPos + dropDownNumber + 4 + 10, 0xffffff);
		font.draw(matrixStack,
				new StringTextComponent(RefinementLang.ENERGY_MAX_TRANSFER.getString().replace("%e", "" + maxReceive)),
				xPos + 3, yPos + dropDownNumber + 4 + 20, 0xffffff);
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

		mX = mouseX - leftPos;
		mY = mouseY - topPos;

		updateElements();

		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		minecraft.textureManager.bind(this.guiTexture);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		this.blit(matrixStack, x, y, 0, 0, imageWidth, imageHeight);
		ticks++;
		updateElements();
		drawElements(matrixStack, false);
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

			sussyElement.render(matrixStack, leftPos + (8 + (18 * sus1)), topInventoryStart, partialTicks);
			sussyElement.render(matrixStack, leftPos + (8 + (18 * sus2)), topInventoryStart + 18, partialTicks);
			sussyElement.render(matrixStack, leftPos + (8 + (18 * sus3)), topInventoryStart + 36, partialTicks);
			sussyElement.render(matrixStack, leftPos + (8 + (18 * sus4)), topInventoryStart + 58, partialTicks);
		}
	}

	public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuilder();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.normal(x + 0, (y + height), this.getBlitOffset())
		.uv(((textureX + 0) * 0.00390625F), ((textureY + height) * 0.00390625F)).endVertex();
		bufferbuilder.normal((x + width), (y + height), this.getBlitOffset())
		.uv(((textureX + width) * 0.00390625F), ((textureY + height) * 0.00390625F))
		.endVertex();
		bufferbuilder.normal((x + width), (y + 0), this.getBlitOffset())
		.uv(((textureX + width) * 0.00390625F), ((textureY + 0) * 0.00390625F)).endVertex();
		bufferbuilder.normal((x + 0), (y + 0), this.getBlitOffset())
		.uv(((textureX + 0) * 0.00390625F), ((textureY + 0) * 0.00390625F)).endVertex();
		tessellator.end();
	}

	protected boolean isBetween(int mouseX, int mouseY, int startX, int startY, int width, int height) {
		return numberIsBetween(mouseX, startX, startX + width) && numberIsBetween(mouseY, startY, startY + height);
	}

	private boolean numberIsBetween(int number, int c1, int c2) {
		return number <= c2 && number >= c1;
	}

	protected void bind(ResourceLocation texture) {
		minecraft.textureManager.bind(texture);
	}

	@Override
	public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {

		/**
		elements.forEach(elem -> {
			if (this.isBetween((int) pMouseX, (int) pMouseY, elem.renderX, elem.renderY, elem.getWidth(),
					elem.getHeight())) {
				//elem.handleClick((int) pMouseX, (int) pMouseY, this.menu);
			}
		});
		 **/

		return super.mouseClicked(pMouseX, pMouseY, pButton);
	}

	@SuppressWarnings("unchecked")
	protected <Z> Z addElement(GuiElement element) {
		elements.add(element);
		return (Z) element;
	}

	private void updateElements() {

		for (int i = elements.size(); i-- > 0;) {
			GuiElement c = elements.get(i);
			if (c.visible() && c.enabled()) {
				c.update(mX, mY);
			}
		}
	}

	protected void drawElements(MatrixStack matrixStack, boolean foreground) {

		if (foreground) {
			for (GuiElement c : elements) {
				if (c.visible()) {
					c.drawForeground(matrixStack, mX, mY);
				}
			}
		} else {
			for (GuiElement c : elements) {
				if (c.visible()) {
					c.drawBackground(matrixStack, mX, mY);
				}
			}
		}
	}

	/**
	 * If the machine is using energy, return an array list of what slots need to
	 * <b><strong>NOT</strong></b> be rendered from the <strong>machine's</strong>
	 * inventory when the player is hovering over the energy info area. (based on
	 * {@link #isHoveringOverEnergyInfo})
	 *
	 * @return slots that wont be rendered when the player is hovering over the
	 *         energy info area
	 */
	public ArrayList<Integer> getNoEnergyInfoRenderSlots() {
		return Lists.newArrayList();
	}

	/**
	 * Make this true if the machine is using energy and if the player is hovering
	 * over the energy info area (false when the player is not hovering over the
	 * energy info area).
	 */
	public boolean isHoveringOverEnergyInfo;

	@Override
	public void renderSlot(MatrixStack pPoseStack, Slot pSlot) {
		boolean noRender = false;
		if (isHoveringOverEnergyInfo) {
			if (getNoEnergyInfoRenderSlots().contains(pSlot.getSlotIndex())) {
				for (int slotIndex : getNoEnergyInfoRenderSlots()) {
					if (slotIndex == pSlot.getSlotIndex()) {
						noRender = pSlot.equals(menu.slots.get(slotIndex));
					}
				}
			}
		}
		if (!pSlot.isActive()) {
			noRender = true;
		}
		if (!noRender) {
			super.renderSlot(pPoseStack, pSlot);
		}
	}

}

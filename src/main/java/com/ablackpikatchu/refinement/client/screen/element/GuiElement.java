package com.ablackpikatchu.refinement.client.screen.element;

import java.util.function.BooleanSupplier;

import com.ablackpikatchu.refinement.common.container.MachineContainer;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Class for creating GUI Elements
 * @author matyrobbrt
 *
 */
@OnlyIn(Dist.CLIENT)
public class GuiElement extends Widget {
	
	protected BooleanSupplier enabled = () -> true;
    protected BooleanSupplier visible = () -> true;

	public ResourceLocation TEXTURE;
	
	public int renderX;
	public int renderY;

	/**
	 * Creates a new GUI Element
	 * @param x the start x position of the element in its texture
	 * @param y the start y position of the element in its texture
	 * @param width the width of the element
	 * @param height the height of the element
	 * @param texture the location of the elements' texture
	 */
	public GuiElement(int x, int y, int width, int height, ResourceLocation texture) {
		super(x, y, width, height, new StringTextComponent(""));
		this.TEXTURE = texture;
	}
	
	public GuiElement(int x, int y, int width, int height, ResourceLocation texture, ITextComponent text) {
		super(x, y, width, height, text);
		this.TEXTURE = texture;
	}
	
	public void drawBackground(MatrixStack matrixStack, int mouseX, int mouseY) {

    }

    public void drawForeground(MatrixStack matrixStack, int mouseX, int mouseY) {

    }
    
    public void update(int mouseX, int mouseY) {

    }
    
    public <Z extends TileEntity> void handleClick(int mouseX, int mouseY, MachineContainer<Z> container) {
    	
    }

	/**
	 * Render the element (note: most elements will use this method, but some of the more advanced ones will override it)
	 * @param stack the MatrixStack to render the element in
	 * @param x the top x position in which to render the element
	 * @param y the top y position in which to render the element
	 */
	@Override
	public void render(MatrixStack stack, int x, int y, float partialTicks) {
		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getTextureManager().bind(TEXTURE);
		this.blit(stack, x, y, this.x, this.y, width, height);
	}
	
	public final GuiElement setEnabled(BooleanSupplier enabled) {
		this.enabled = enabled;
		return this;
	}
	
	public boolean enabled() {
		return this.enabled.getAsBoolean();
	}
	
	public final GuiElement setVisible(BooleanSupplier visible) {
		this.visible = visible;
		return this;
	}
	
	public boolean visible() {
		return this.visible.getAsBoolean();
	}

}

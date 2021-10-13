package com.ablackpikatchu.refinement.client.screen.element;

import com.ablackpikatchu.refinement.common.container.MachineContainer;
import com.ablackpikatchu.refinement.common.security.ISecurableTile;
import com.ablackpikatchu.refinement.core.network.RefinementNetwork;
import com.ablackpikatchu.refinement.core.network.message.TileSecurityMessage;
import com.ablackpikatchu.refinement.core.util.enums.GuiTextures;

import net.minecraft.tileentity.TileEntity;

public class SecurityElement extends GuiElement {

	public SecurityElement(int x, int y, int width, int height, int renderX, int renderY) {
		super(x, y, width, height, GuiTextures.CONTAINER_ELEMENTS.getTextureLocation());
		this.renderX = renderX;
		this.renderY = renderY;
	}

	@Override
	public <Z extends TileEntity> void handleClick(int mouseX, int mouseY, MachineContainer<Z> container) {
		if (container.te instanceof ISecurableTile)
			RefinementNetwork.TILE_SECURITY_CHANNEL.sendToServer(new TileSecurityMessage(container.te.getBlockPos()));
		super.handleClick(mouseX, mouseY, container);
	}
	
	public static class SecurityPublicElement extends SecurityElement {

		public SecurityPublicElement(int renderX, int renderY) {
			super(17, 35, 6, 7, renderX, renderY);
		}
		
	}
	
	public static class SecurityPrivateElement extends SecurityElement {

		public SecurityPrivateElement(int renderX, int renderY) {
			super(17, 46, 6, 6, renderX, renderY);
		}
		
	}

}

package com.ablackpikatchu.refinement.core.network.message.to_server;

import com.ablackpikatchu.refinement.api.network.message.IRefinementMessage;
import com.ablackpikatchu.refinement.common.te.security.ISecurableTile;
import com.ablackpikatchu.refinement.common.te.security.SecurityType;

import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

import net.minecraftforge.fml.network.NetworkEvent.Context;

public class TileSecurityMessage implements IRefinementMessage {

	public BlockPos pos;

	public TileSecurityMessage(BlockPos pos) {
		this.pos = pos;
	}
	
	public static TileSecurityMessage decode(PacketBuffer buffer) {
		return new TileSecurityMessage(buffer.readBlockPos());
	}	

	@Override
	public void handle(Context context) {
		TileEntity tile = context.getSender().level.getBlockEntity(pos);
		if (tile != null && tile instanceof ISecurableTile) {
			ISecurableTile securableTile = (ISecurableTile) tile;
			if (securableTile.getSecurity() == null)
				securableTile.setSecurity(SecurityType.PUBLIC);
			else
				securableTile.setSecurity(securableTile.getSecurity().next());
			context.getSender()
					.sendMessage(new StringTextComponent(
							"Successfully modified the security of the machine to " + securableTile.getSecurity()),
							context.getSender().getUUID());
			tile.setChanged();
		}
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeBlockPos(pos);
	}

}

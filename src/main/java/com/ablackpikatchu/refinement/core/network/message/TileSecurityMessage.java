package com.ablackpikatchu.refinement.core.network.message;

import java.util.function.Supplier;

import com.ablackpikatchu.refinement.common.security.ISecurableTile;
import com.ablackpikatchu.refinement.common.security.SecurityType;

import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

import net.minecraftforge.fml.network.NetworkEvent;

public class TileSecurityMessage {

	public BlockPos pos;

	public TileSecurityMessage(BlockPos pos) {
		this.pos = pos;
	}

	public static void encode(TileSecurityMessage message, PacketBuffer buffer) {
		buffer.writeBlockPos(message.pos);
	}

	public static TileSecurityMessage decode(PacketBuffer buffer) {
		return new TileSecurityMessage(buffer.readBlockPos());
	}

	public static void handle(TileSecurityMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			BlockPos pos = message.pos;
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
		});
		context.setPacketHandled(true);
	}

}

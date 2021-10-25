package com.ablackpikatchu.refinement.core.network.message;

import java.util.function.Supplier;

import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.ablackpikatchu.refinement.core.util.helper.PlayerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

import net.minecraftforge.fml.network.NetworkEvent;

public class ToggleBinAutoRefillMessage {

	public int playerSlot;

	public ToggleBinAutoRefillMessage(int playerSlot) {
		this.playerSlot = playerSlot;
	}

	public static void encode(ToggleBinAutoRefillMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.playerSlot);
	}

	public static ToggleBinAutoRefillMessage decode(PacketBuffer buffer) {
		return new ToggleBinAutoRefillMessage(buffer.readInt());
	}

	public static void handle(ToggleBinAutoRefillMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (context.getSender() != null) {
				ItemStack stack = context.getSender().inventory.getItem(message.playerSlot);
				NBTHelper.flipBoolean(stack, "RefillEnabled");
				context.getSender().inventory.setChanged();
				PlayerHelper.updatePlayerInventory(context.getSender());
			}
		});
		context.setPacketHandled(true);
	}

}

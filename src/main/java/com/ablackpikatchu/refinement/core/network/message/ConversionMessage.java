package com.ablackpikatchu.refinement.core.network.message;

import java.util.function.Supplier;

import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.util.Conversions;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;

import net.minecraftforge.fml.network.NetworkEvent;

public class ConversionMessage {
	public int key;

	public ConversionMessage() {

	}

	public ConversionMessage(int key) {
		this.key = key;
	}

	public static void encode(ConversionMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.key);
	}

	public static ConversionMessage decode(PacketBuffer buffer) {
		return new ConversionMessage(buffer.readInt());
	}

	public static void handle(ConversionMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			ServerPlayerEntity player = context.getSender();
			if (CommonConfig.ENABLE_CONVERSION.get())
				Conversions.convert(player);
		});
		context.setPacketHandled(true);
	}

}

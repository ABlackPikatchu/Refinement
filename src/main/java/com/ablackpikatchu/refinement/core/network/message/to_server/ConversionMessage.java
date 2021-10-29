package com.ablackpikatchu.refinement.core.network.message.to_server;

import com.ablackpikatchu.refinement.api.network.message.IRefinementMessage;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.util.Conversions;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;

import net.minecraftforge.fml.network.NetworkEvent.Context;

public class ConversionMessage implements IRefinementMessage {

	public static ConversionMessage decode(PacketBuffer buffer) {
		return new ConversionMessage();
	}

	@Override
	public void handle(Context context) {
		ServerPlayerEntity player = context.getSender();
		if (CommonConfig.ENABLE_CONVERSION.get())
			Conversions.convert(player);
	}

	@Override
	public void encode(PacketBuffer buffer) {
	}

}

package com.ablackpikatchu.refinement.core.network.message;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;

import net.minecraftforge.fml.network.NetworkEvent;

public interface IRefinementMessage {
	
	void handle(NetworkEvent.Context context);

    void encode(PacketBuffer buffer);
    
    static <MSG extends IRefinementMessage> void handle(MSG message, Supplier<NetworkEvent.Context> ctx) {
        if (message != null) {
            NetworkEvent.Context context = ctx.get();
            context.enqueueWork(() -> message.handle(context));
            context.setPacketHandled(true);
        }
    }

}

package com.ablackpikatchu.refinement.core.network;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.network.message.ConversionMessage;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class RefinementNetwork {

	public static final String NETWORK_VERSION = "0.1.0";
	
	public static final SimpleChannel CONVERSION_CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Refinement.MOD_ID, "conversion_network"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	
	public static void init() {
		CONVERSION_CHANNEL.registerMessage(0, ConversionMessage.class, ConversionMessage::encode, ConversionMessage::decode, ConversionMessage::handle);
	}

}

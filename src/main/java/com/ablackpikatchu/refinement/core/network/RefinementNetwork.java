package com.ablackpikatchu.refinement.core.network;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.api.network.BaseNetwork;
import com.ablackpikatchu.refinement.core.network.message.to_client.UpdateBinMessage;
import com.ablackpikatchu.refinement.core.network.message.to_server.ConversionMessage;
import com.ablackpikatchu.refinement.core.network.message.to_server.TileSecurityMessage;
import com.ablackpikatchu.refinement.core.network.message.to_server.ToggleBinAutoRefillMessage;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class RefinementNetwork extends BaseNetwork {

	public static final String NETWORK_VERSION = "0.3.0";
	
	public static final SimpleChannel CONVERSION_CHANNEL = newSimpleChannel("conversion_network");
	public static final SimpleChannel TILE_CHANNEL = newSimpleChannel("tile_network");
	public static final SimpleChannel STORAGE_BIN_CHANNEL = newSimpleChannel("storage_bin_network");
	
	public static void init() {
		registerClientToServer(CONVERSION_CHANNEL, ConversionMessage.class, ConversionMessage::decode);
		
		registerClientToServer(TILE_CHANNEL, TileSecurityMessage.class, TileSecurityMessage::decode);
		
		registerServerToClient(STORAGE_BIN_CHANNEL, UpdateBinMessage.class, UpdateBinMessage::decode);
		registerClientToServer(STORAGE_BIN_CHANNEL, ToggleBinAutoRefillMessage.class, ToggleBinAutoRefillMessage::decode);
	}
	
	private static SimpleChannel newSimpleChannel(String name) {
		return NetworkRegistry.newSimpleChannel(
				new ResourceLocation(Refinement.MOD_ID, name), () -> NETWORK_VERSION,
				version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	}
	
}

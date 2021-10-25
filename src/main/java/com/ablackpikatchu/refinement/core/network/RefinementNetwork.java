package com.ablackpikatchu.refinement.core.network;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.network.message.ConversionMessage;
import com.ablackpikatchu.refinement.core.network.message.TileSecurityMessage;
import com.ablackpikatchu.refinement.core.network.message.ToggleBinAutoRefillMessage;
import com.ablackpikatchu.refinement.core.network.message.UpdateBinMessage;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class RefinementNetwork {

	public static final String NETWORK_VERSION = "0.3.0";
	
	public static final SimpleChannel CONVERSION_CHANNEL = newSimpleChannel("conversion_network");
	public static final SimpleChannel TILE_CHANNEL = newSimpleChannel("tile_network");
	public static final SimpleChannel STORAGE_BIN_CHANNEL = newSimpleChannel("storage_bin_network");
	
	private static int ID = 0;
	
	public static void init() {
		CONVERSION_CHANNEL.registerMessage(nextId(), ConversionMessage.class, ConversionMessage::encode, ConversionMessage::decode, ConversionMessage::handle);
		
		TILE_CHANNEL.registerMessage(nextId(), TileSecurityMessage.class, TileSecurityMessage::encode, TileSecurityMessage::decode, TileSecurityMessage::handle);
		
		STORAGE_BIN_CHANNEL.registerMessage(nextId(), UpdateBinMessage.class, UpdateBinMessage::encode, UpdateBinMessage::decode, UpdateBinMessage::handle);
		STORAGE_BIN_CHANNEL.registerMessage(nextId(), ToggleBinAutoRefillMessage.class, ToggleBinAutoRefillMessage::encode, ToggleBinAutoRefillMessage::decode, ToggleBinAutoRefillMessage::handle);
	}
	
	public static int nextId() {
        return ID++;
    }
	
	private static SimpleChannel newSimpleChannel(String name) {
		return NetworkRegistry.newSimpleChannel(
				new ResourceLocation(Refinement.MOD_ID, name), () -> NETWORK_VERSION,
				version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	}

}

package com.ablackpikatchu.refinement.common.command.quote;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class Quote {

	public final ServerPlayerEntity author;
	public final String quote;
	
	public Quote(ServerPlayerEntity author, String quote) {
		this.author = author;
		this.quote = quote;
	}
	
	public CompoundNBT toNbt() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putUUID("author", author.getUUID());
		nbt.putString("quote", quote);
		return nbt;
	}
	
	public static Quote fromNbt(CompoundNBT nbt) {
		ServerPlayerEntity player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(nbt.getUUID("author"));
		String quote = nbt.getString("quote");
		return new Quote(player, quote);
	}
	
}

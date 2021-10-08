package com.ablackpikatchu.refinement.common.capability.playerpower;

import net.minecraft.entity.player.PlayerEntity;

public interface IPlayerPower {
	
	void setChanged(PlayerEntity player);
	
	void setFlight(Boolean enabled);
	
	boolean getFlight();

}

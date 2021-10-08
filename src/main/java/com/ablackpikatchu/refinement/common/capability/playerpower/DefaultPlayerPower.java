package com.ablackpikatchu.refinement.common.capability.playerpower;

import net.minecraft.entity.player.PlayerEntity;

public class DefaultPlayerPower implements IPlayerPower {
	
	private boolean flight;

	@Override
	public void setFlight(Boolean enabled) {
		this.flight = enabled;
	}

	@Override
	public boolean getFlight() {
		return this.flight;
	}

	@Override
	public void setChanged(PlayerEntity player) {
		PlayerPowerEventHandler.setChanged(player);
	}

}

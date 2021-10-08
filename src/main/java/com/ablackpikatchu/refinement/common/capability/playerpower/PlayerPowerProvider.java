package com.ablackpikatchu.refinement.common.capability.playerpower;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerPowerProvider implements ICapabilitySerializable<CompoundNBT> {
	
	private final DefaultPlayerPower playerPower = new DefaultPlayerPower();
	private final LazyOptional<IPlayerPower> playerPowerOptional = LazyOptional.of(() -> playerPower);
	
	public void invalidate() {
		playerPowerOptional.invalidate();
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return playerPowerOptional.cast();
	}

	@Override
    public CompoundNBT serializeNBT() {
        if (CapabilityPlayerPower.PLAYER_POWER_CAPABILITY == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityPlayerPower.PLAYER_POWER_CAPABILITY.writeNBT(playerPower, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (CapabilityPlayerPower.PLAYER_POWER_CAPABILITY != null) {
        	CapabilityPlayerPower.PLAYER_POWER_CAPABILITY.readNBT(playerPower, null, nbt);
        }
    }

}

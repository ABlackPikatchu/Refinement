package com.ablackpikatchu.refinement.common.capability.playerpower;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CapabilityPlayerPower {

	@CapabilityInject(IPlayerPower.class)
	public static Capability<IPlayerPower> PLAYER_POWER_CAPABILITY = null;

	public static void register() {
		//CapabilityManager.INSTANCE.register(IPlayerPower.class, new Storage(), DefaultPlayerPower::new);
	}

	public static class Storage implements Capability.IStorage<IPlayerPower> {

		@Override
		public INBT writeNBT(Capability<IPlayerPower> capability, IPlayerPower instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();
			tag.putBoolean("flight", instance.getFlight());
			return tag;
		}

		@Override
		public void readNBT(Capability<IPlayerPower> capability, IPlayerPower instance, Direction side, INBT nbt) {
			instance.setFlight(((CompoundNBT) nbt).getBoolean("flight"));
		}

	}

}

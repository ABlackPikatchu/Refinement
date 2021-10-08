package com.ablackpikatchu.refinement.common.capability.playerpower;


import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.FORGE)
public class PlayerPowerEventHandler {
	
	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity) {
			//PlayerPowerProvider provider = new PlayerPowerProvider();
			//event.addCapability(new ResourceLocation(Refinement.MOD_ID, "power"), provider);
			//event.addListener(provider::invalidate);
		}
	}
	
	public static void setChanged(PlayerEntity player) {
		player.getCapability(CapabilityPlayerPower.PLAYER_POWER_CAPABILITY).ifPresent(cap -> {
			player.abilities.mayfly = cap.getFlight();
		});
	}

}

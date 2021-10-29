package com.ablackpikatchu.refinement.client.render;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.particle.ColouredParticle;
import com.ablackpikatchu.refinement.common.particle.DNAParticle;
import com.ablackpikatchu.refinement.core.init.ParticleTypesInit;

import net.minecraft.client.Minecraft;

import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD)
public class Particles {

	@SuppressWarnings("resource")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticles(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particleEngine.register(ParticleTypesInit.DNA_PARTICLES.get(), DNAParticle.Factory::new);
		Minecraft.getInstance().particleEngine.register(ParticleTypesInit.COLOURED_PARTICLE.get(), ColouredParticle.Factory::new);
	}
	
}

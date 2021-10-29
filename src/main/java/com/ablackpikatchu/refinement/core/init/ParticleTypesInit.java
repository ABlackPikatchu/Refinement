package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.particle.ColouredParticle.ColouredParticleData;
import com.ablackpikatchu.refinement.common.particle.ColouredParticle.ColouredParticleType;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleTypesInit {

	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister
			.create(ForgeRegistries.PARTICLE_TYPES, Refinement.MOD_ID);

	public static final RegistryObject<BasicParticleType> DNA_PARTICLES = PARTICLE_TYPES.register("dna_particles",
			() -> new BasicParticleType(true));

	public static final RegistryObject<ParticleType<ColouredParticleData>> COLOURED_PARTICLE = PARTICLE_TYPES
			.register("coloured_particle", ColouredParticleType::new);

}

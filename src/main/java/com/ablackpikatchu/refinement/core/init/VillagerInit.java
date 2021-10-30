package com.ablackpikatchu.refinement.core.init;

import java.lang.reflect.InvocationTargetException;

import com.ablackpikatchu.refinement.Refinement;
import com.google.common.collect.ImmutableSet;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VillagerInit {

	public static class PointOfInterests {

		public static final DeferredRegister<PointOfInterestType> POINT_OF_INTEREST_TYPES = DeferredRegister
				.create(ForgeRegistries.POI_TYPES, Refinement.MOD_ID);

		public static final RegistryObject<PointOfInterestType> MATERIALIST_POI = POINT_OF_INTEREST_TYPES
				.register("materials", () -> new PointOfInterestType("materialist",
						PointOfInterestType.getBlockStates(BlockInit.MATERIALS_STATION.get()), 1, 1));

	}

	public static class VillagerProfessions {

		public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister
				.create(ForgeRegistries.PROFESSIONS, Refinement.MOD_ID);

		public static final RegistryObject<VillagerProfession> MATERIALIST_VILLAGER = VILLAGER_PROFESSIONS.register(
				"materials",
				() -> new VillagerProfession("materialist", VillagerInit.PointOfInterests.MATERIALIST_POI.get(),
						ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CARTOGRAPHER));

	}

	public static void registerPOIS() {
		try {
			ObfuscationReflectionHelper
					.findMethod(PointOfInterestType.class, "registerBlockStates", PointOfInterestType.class)
					.invoke(null, VillagerInit.PointOfInterests.MATERIALIST_POI.get());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}

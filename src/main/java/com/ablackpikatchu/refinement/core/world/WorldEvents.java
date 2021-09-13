package com.ablackpikatchu.refinement.core.world;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.world.gen.OreGen;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Refinement.MOD_ID)
public class WorldEvents {
	
	@SubscribeEvent
	public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
		OreGen.generateOres(event);
	}

}

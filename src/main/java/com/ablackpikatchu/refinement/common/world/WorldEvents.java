package com.ablackpikatchu.refinement.common.world;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.world.gen.OreGen;

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

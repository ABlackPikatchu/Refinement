package com.ablackpikatchu.refinement.core.util;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.tileentity.*;
import com.ablackpikatchu.refinement.client.ter.ResourceStatueTER;

import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.*;
import com.ablackpikatchu.refinement.core.init.KeybindsInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;

import net.minecraft.client.gui.ScreenManager;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {

		KeybindsInit.register(event);

		ScreenManager.register(GRINDER_CONTAINER_TYPE.get(), GrinderScreen::new);
		ScreenManager.register(MIXER_CONTAINER_TYPE.get(), MixerScreen::new);
		ScreenManager.register(MOLD_PRESS_CONTAINER_TYPE.get(), MoldPressScreen::new);
		ScreenManager.register(DNA_SEQUENCER_CONTAINER_TYPE.get(), DNASequencerScreen::new);

		ScreenManager.register(VACCUMULATOR_CONTAINER_TYPE.get(), VaccumulatorScreen::new);

		ScreenManager.register(CARBON_GENERATOR_CONTAINER_TYPE.get(), CarbonGeneratorScreen::new);
		ScreenManager.register(ENERGY_GENERATOR_CONTAINER_TYPE.get(), EnergyGeneratorScreen::new);

		ScreenManager.register(SMELTER_CONTAINER_TYPE.get(), SmelterScreen::new);
		
		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.RESOURCE_STATUE_TILE_ENTITY_TYPE.get(), ResourceStatueTER::new);
	}

}

package com.ablackpikatchu.refinement.core.util;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.tileentity.*;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.init.KeybindsInit;

import net.minecraft.client.gui.ScreenManager;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {

		KeybindsInit.register(event);

		ScreenManager.register(ContainerTypesInit.GRINDER_CONTAINER_TYPE.get(), GrinderScreen::new);
		ScreenManager.register(ContainerTypesInit.MIXER_CONTAINER_TYPE.get(), MixerScreen::new);
		ScreenManager.register(ContainerTypesInit.MOLD_PRESS_CONTAINER_TYPE.get(), MoldPressScreen::new);
		ScreenManager.register(ContainerTypesInit.DNA_SEQUENCER_CONTAINER_TYPE.get(), DNASequencerScreen::new);

		ScreenManager.register(ContainerTypesInit.VACCUMULATOR_CONTAINER_TYPE.get(), VaccumulatorScreen::new);
		
		ScreenManager.register(ContainerTypesInit.CARBON_GENERATOR_CONTAINER_TYPE.get(), CarbonGeneratorScreen::new);
		ScreenManager.register(ContainerTypesInit.ENERGY_GENERATOR_CONTAINER_TYPE.get(), EnergyGeneratorScreen::new);
	}

}

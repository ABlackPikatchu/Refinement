package com.ablackpikatchu.refinement.client;

import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.render.model.EnergyCableBakedModel;
import com.ablackpikatchu.refinement.client.screen.tileentity.AlloySmelterScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.CarbonGeneratorScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.DNASequencerScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.EnergyGeneratorScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.GrinderScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.MixerScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.MoldPressScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.SmelterScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.VaccumulatorScreen;
import com.ablackpikatchu.refinement.client.ter.ResourceStatueTER;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.KeybindsInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
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
		ScreenManager.register(ALLOY_SMELTER_CONTAINER_TYPE.get(), AlloySmelterScreen::new);
		
		ScreenManager.register(VACCUMULATOR_CONTAINER_TYPE.get(), VaccumulatorScreen::new);

		ScreenManager.register(CARBON_GENERATOR_CONTAINER_TYPE.get(), CarbonGeneratorScreen::new);
		ScreenManager.register(ENERGY_GENERATOR_CONTAINER_TYPE.get(), EnergyGeneratorScreen::new);

		ScreenManager.register(SMELTER_CONTAINER_TYPE.get(), SmelterScreen::new);

		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.RESOURCE_STATUE_TILE_ENTITY_TYPE.get(),
				ResourceStatueTER::new);
	}

	@SubscribeEvent
	public static void onModelBake(ModelBakeEvent e) {
		Map<ResourceLocation, EnergyCableBakedModel> cableModels = new HashMap<>();

		cableModels.put(BlockInit.ENERGY_CABLE_BLOCK.getId(), new EnergyCableBakedModel(
				e.getModelRegistry().get(new ResourceLocation(Refinement.MOD_ID + ":block/cable/core")),
				e.getModelRegistry()
						.get(new ResourceLocation(Refinement.MOD_ID + ":block/cable/extension")),
				e.getModelRegistry()
						.get(new ResourceLocation(Refinement.MOD_ID + ":block/cable/straight"))));
		
		on: for (ResourceLocation id : e.getModelRegistry().keySet()) {
            for (Entry<ResourceLocation, EnergyCableBakedModel> entry : cableModels.entrySet()) {
                if (isCableModel(id, entry.getKey())) {
                    e.getModelRegistry().put(id, entry.getValue());
                    continue on;
                }
            }
        }
	}
	
	private static boolean isCableModel(ResourceLocation modelId, ResourceLocation pipeId) {
        return modelId instanceof ModelResourceLocation
            && modelId.getNamespace().equals(Refinement.MOD_ID)
            && modelId.getPath().equals(pipeId.getPath())
            && !((ModelResourceLocation) modelId).getVariant().equals("inventory");
    }

}

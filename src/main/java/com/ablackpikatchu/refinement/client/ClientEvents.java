package com.ablackpikatchu.refinement.client;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;
import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.ALLOY_SMELTER_CONTAINER_TYPE;
import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.CARBON_GENERATOR_CONTAINER_TYPE;
import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.DNA_SEQUENCER_CONTAINER_TYPE;
import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.ENERGY_GENERATOR_CONTAINER_TYPE;
import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.GRINDER_CONTAINER_TYPE;
import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.MIXER_CONTAINER_TYPE;
import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.MOLD_PRESS_CONTAINER_TYPE;
import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.SMELTER_CONTAINER_TYPE;
import static com.ablackpikatchu.refinement.core.init.ContainerTypesInit.VACCUMULATOR_CONTAINER_TYPE;
import static com.ablackpikatchu.refinement.core.init.ItemInit.AUTO_EJECT_UPGRADE;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.render.model.EnergyCableBakedModel;
import com.ablackpikatchu.refinement.client.render.ter.ResourceStatueTER;
import com.ablackpikatchu.refinement.client.render.ter.StorageBinTER;
import com.ablackpikatchu.refinement.client.screen.tileentity.AlloySmelterScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.CarbonGeneratorScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.DNASequencerScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.EnergyGeneratorScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.GrinderScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.MixerScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.MoldPressScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.SmelterScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.VaccumulatorScreen;
import com.ablackpikatchu.refinement.common.item.AutoEjectUpgrade;
import com.ablackpikatchu.refinement.common.item.AutoImportUpgrade;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.KeybindsInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
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
		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.STORAGE_BIN_TILE_ENTITY_TYPE.get(),
				StorageBinTER::new);
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

	@SubscribeEvent
	public static void registerPredicates(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
		// Item Predicates
		ItemModelsProperties.register(ItemInit.SPEED_UPGRADE.get(),
				new ResourceLocation(Refinement.MOD_ID, "stack_count"), (stack, world, entity) -> {
					if (stack.getCount() > 3 && stack.getCount() <= 6)
						return 2F;
					else if (stack.getCount() > 6)
						return 3F;
					else
						return 1F;
				});

		ItemModelsProperties.register(ItemInit.MAGNET.get(), new ResourceLocation(Refinement.MOD_ID, "magnet_state"),
				(stack, world, entity) -> {
					if (entity instanceof PlayerEntity) {
						PlayerEntity player = (PlayerEntity) entity;

						Minecraft mc = Minecraft.getInstance();
						boolean isFirstPersonView = mc.player.getUUID().equals(player.getUUID())
								&& !mc.options.getCameraType().isFirstPerson();
						if (isFirstPersonView)
							return 3F;
						else if (NBTHelper.getBoolean(stack, "Enabled"))
							return 2F;
						else
							return 1F;
					} else
						return 1F;
				});

		ItemModelsProperties.register(AUTO_EJECT_UPGRADE.get(),
				new ResourceLocation(MOD_ID, "auto_eject_upgrade_direction"), (stack, world, entity) -> {
					Direction direction = Direction
							.byName(NBTHelper.getString(stack, AutoEjectUpgrade.DIRECTION_PROPERTY));
					if (NBTHelper.getString(stack, AutoEjectUpgrade.DIRECTION_PROPERTY) == "")
						return 0.0f;
					
					switch (direction) {
					case UP: return 1.0f;
					case DOWN: return 2.0f;
					case NORTH: return 3.0f;
					case EAST: return 4.0f;
					case SOUTH: return 5.0f;
					case WEST: return 6.0f;
					default:
						return 0.0f;
					}

				});
		
		ItemModelsProperties.register(ItemInit.AUTO_IMPORT_UPGRADE.get(),
				new ResourceLocation(MOD_ID, "auto_import_upgrade_direction"), (stack, world, entity) -> {
					Direction direction = Direction
							.byName(NBTHelper.getString(stack, AutoImportUpgrade.DIRECTION_PROPERTY));
					if (NBTHelper.getString(stack, AutoImportUpgrade.DIRECTION_PROPERTY) == "")
						return 0.0f;
					
					switch (direction) {
					case UP: return 1.0f;
					case DOWN: return 2.0f;
					case NORTH: return 3.0f;
					case EAST: return 4.0f;
					case SOUTH: return 5.0f;
					case WEST: return 6.0f;
					default:
						return 0.0f;
					}

				});
		});
		
	}

}

package com.ablackpikatchu.refinement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ablackpikatchu.refinement.client.render.RenderLayers;
import com.ablackpikatchu.refinement.common.events.PlayerEvents;
import com.ablackpikatchu.refinement.core.config.ClientConfig;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.PotionInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.network.RefinementNetwork;
import com.ablackpikatchu.refinement.core.util.Conversions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.IRecipeSerializer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import software.bernie.geckolib3.GeckoLib;

@Mod("refinement")
@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD)
public class Refinement {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "refinement";

	public Refinement() {
		GeckoLib.initialize();

		// mod bus events
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.SPEC, "refinement-common.toml");
		ModLoadingContext.get().registerConfig(Type.CLIENT, ClientConfig.SPEC, "refinement-client.toml");

		modBus.addGenericListener(IRecipeSerializer.class, RecipeInit::registerRecipes);

		BlockInit.BLOCKS.register(modBus);
		ItemInit.ITEMS.register(modBus);
		TileEntityTypesInit.TILE_ENTITY_TYPE.register(modBus);
		ContainerTypesInit.CONTAINER_TYPES.register(modBus);
		PotionInit.EFFECTS.register(modBus);

		MinecraftForge.EVENT_BUS.register(this);
		
		modBus.addListener(this::commonSetup);

		// forge bus events
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		forgeBus.addListener(this::oreConversion);
		forgeBus.addListener(EventPriority.HIGHEST, PlayerEvents::onInteract);
	}

	public void oreConversion(TickEvent.ServerTickEvent event) {
		if (CommonConfig.ENABLE_CONVERSION.get() && CommonConfig.INVENTORY_TRIGGER_CONVERSION.get()) {
			for (PlayerEntity player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
				Conversions.convert(player);
			}
		}
	}

	@SubscribeEvent
	public static void clientSetup(final FMLClientSetupEvent event) {
		RenderLayers.setRenderLayers();
	}
	
	public void commonSetup(final FMLCommonSetupEvent event) {
		RefinementNetwork.init();
	}
}

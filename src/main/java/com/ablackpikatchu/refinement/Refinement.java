package com.ablackpikatchu.refinement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ablackpikatchu.refinement.core.Client;
import com.ablackpikatchu.refinement.core.config.ClientConfig;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.PotionInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("refinement")
@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD)
public class Refinement {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "refinement";

	public Refinement() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.SPEC, "refinement-common.toml");
		ModLoadingContext.get().registerConfig(Type.CLIENT, ClientConfig.SPEC, "refinement-client.toml");

		bus.addGenericListener(IRecipeSerializer.class, RecipeInit::registerRecipes);

		BlockInit.BLOCKS.register(bus);
		ItemInit.ITEMS.register(bus);
		TileEntityTypesInit.TILE_ENTITY_TYPE.register(bus);
		ContainerTypesInit.CONTAINER_TYPES.register(bus);
		PotionInit.EFFECTS.register(bus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	
	@SubscribeEvent
	public static void clientSetup(final FMLClientSetupEvent event) {
		Client.setRenderLayers();
	}
}

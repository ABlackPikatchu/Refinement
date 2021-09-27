package com.ablackpikatchu.refinement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ablackpikatchu.refinement.client.render.RenderLayers;
import com.ablackpikatchu.refinement.common.recipe.conditions.CropsEnabledCondition;
import com.ablackpikatchu.refinement.common.recipe.conditions.EnableableCondition;
import com.ablackpikatchu.refinement.core.config.ClientConfig;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.BlockItemInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.init.CropInit;
import com.ablackpikatchu.refinement.core.init.FeatureInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.ParticleTypesInit;
import com.ablackpikatchu.refinement.core.init.PotionInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.init.VillagerInit;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup;
import com.ablackpikatchu.refinement.core.network.RefinementNetwork;
import com.ablackpikatchu.refinement.core.util.Conversions;
import com.ablackpikatchu.refinement.core.util.StrippingMap;
import com.ablackpikatchu.refinement.core.util.lists.TradeLists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import software.bernie.geckolib3.GeckoLib;

/**
 * The Refinement mod
 * 
 * @author matyrobbrt
 * @author ABlackPikatchu
 */
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

		ParticleTypesInit.PARTICLE_TYPES.register(modBus);
		BlockInit.BLOCKS.register(modBus);
		CropInit.BLOCKS.register(modBus);
		ItemInit.ITEMS.register(modBus);
		TileEntityTypesInit.TILE_ENTITY_TYPE.register(modBus);
		ContainerTypesInit.CONTAINER_TYPES.register(modBus);
		PotionInit.EFFECTS.register(modBus);
		VillagerInit.VillagerProfessions.VILLAGER_PROFESSIONS.register(modBus);
		VillagerInit.PointOfInterests.POINT_OF_INTEREST_TYPES.register(modBus);
		FeatureInit.FEATURES.register(modBus);

		MinecraftForge.EVENT_BUS.register(this);

		modBus.addListener(this::commonSetup);
		modBus.addListener(this::onLoadComplete);

		modBus.addGenericListener(IRecipeSerializer.class, this::recipeConditions);

		// forge bus events
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		forgeBus.addListener(this::oreConversion);
	}

	public void oreConversion(TickEvent.ServerTickEvent event) {
		if (CommonConfig.ENABLE_CONVERSION.get() && CommonConfig.INVENTORY_TRIGGER_CONVERSION.get()) {
			for (PlayerEntity player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
				Conversions.convert(player);
			}
		}
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		BlockItemInit.BLOCKS_THAT_NEED_BLOCKITEMS.forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(RefinementItemGroup.REFINEMENT))
					.setRegistryName(block.getRegistryName()));
		});
	}

	public void recipeConditions(RegistryEvent.Register<IRecipeSerializer<?>> event) {
		CraftingHelper.register(CropsEnabledCondition.Serializer.INSTANCE);
		CraftingHelper.register(EnableableCondition.Serializer.INSTANCE);
	}

	@SubscribeEvent
	public static void clientSetup(final FMLClientSetupEvent event) {
		RenderLayers.setRenderLayers();
	}

	public void commonSetup(final FMLCommonSetupEvent event) {
		RefinementNetwork.init();
		event.enqueueWork(() -> {
			VillagerInit.registerPOIS();
			TradeLists.fillTradeData();
		});

	}

	public void onLoadComplete(final FMLLoadCompleteEvent event) {
		StrippingMap.registerStrippables();
	}
}

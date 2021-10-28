package com.ablackpikatchu.refinement;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ablackpikatchu.refinement.client.ClientSetup;
import com.ablackpikatchu.refinement.client.render.RenderLayers;
import com.ablackpikatchu.refinement.common.block.StorageBinBlock;
import com.ablackpikatchu.refinement.common.capability.playerpower.CapabilityPlayerPower;
import com.ablackpikatchu.refinement.common.events.RegistryEvents;
import com.ablackpikatchu.refinement.common.recipe.conditions.CropsEnabledCondition;
import com.ablackpikatchu.refinement.common.recipe.conditions.EnableableCondition;
import com.ablackpikatchu.refinement.common.te.misc_tes.StorageBinTileEntity;
import com.ablackpikatchu.refinement.common.te.upgrade.IUpgradableTile;
import com.ablackpikatchu.refinement.core.config.ClientConfig;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.config.ModJsonConfigs;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.BlockItemInit;
import com.ablackpikatchu.refinement.core.init.CommandInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
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
import com.ablackpikatchu.refinement.data.client.LangProvider;
import com.ablackpikatchu.refinement.resourcecrops.core.CropInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

/**
 * The Refinement mod
 * 
 * @author matyrobbrt
 * @author ABlackPikatchu
 */
@Mod(Refinement.MOD_ID)
@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD)
public class Refinement {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "refinement";

	public static final String CONFIG_DIR_PATH = "config/" + MOD_ID + "/";
	public static final File CONFIF_DIR = new File(CONFIG_DIR_PATH);

	public Refinement() {
		// GeckoLib.initialize();

		// mod bus events
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSetup::new);

		if (!CONFIF_DIR.exists()) {
			CONFIF_DIR.mkdirs();
			LOGGER.info("Created Refinement Config folder!");
		}

		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.SPEC, MOD_ID + "/common.toml");
		ModLoadingContext.get().registerConfig(Type.CLIENT, ClientConfig.SPEC, MOD_ID + "/client.toml");

		modBus.addGenericListener(IRecipeSerializer.class, RecipeInit::registerRecipes);

		ParticleTypesInit.PARTICLE_TYPES.register(modBus);
		LOGGER.info("Particle Types Registered!");

		BlockInit.BLOCKS.register(modBus);
		LOGGER.info("Blocks Registered!");

		CropInit.BLOCKS.register(modBus);
		LOGGER.info("Crops Registered!");

		ItemInit.ITEMS.register(modBus);
		LOGGER.info("Items Registered!");

		PotionInit.EFFECTS.register(modBus);
		LOGGER.info("Effects + Potions Registered!");

		TileEntityTypesInit.TILE_ENTITY_TYPE.register(modBus);
		LOGGER.info("Tile Entity Types Registered!");

		ContainerTypesInit.CONTAINER_TYPES.register(modBus);
		LOGGER.info("Conatiner Types Registered!");

		VillagerInit.VillagerProfessions.VILLAGER_PROFESSIONS.register(modBus);
		LOGGER.info("Villager Professtion Registered!");

		VillagerInit.PointOfInterests.POINT_OF_INTEREST_TYPES.register(modBus);
		LOGGER.info("POI Types Registered!");

		FeatureInit.FEATURES.register(modBus);
		LOGGER.info("Features Registered!");

		MinecraftForge.EVENT_BUS.register(this);

		modBus.addListener(this::constructMod);
		modBus.addListener(this::commonSetup);
		modBus.addListener(this::registerDispenseBehaviours);
		modBus.addListener(this::onLoadComplete);

		modBus.addGenericListener(IRecipeSerializer.class, this::recipeConditions);

		// forge bus events
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(this::oreConversion);
		forgeBus.addListener(this::onPlayerLeftClickBlock);
		forgeBus.addListener(this::onPlayerRightClickBlock);
		forgeBus.addListener(EventPriority.NORMAL, this::onRegisterCommands);
	}
	
	public static ResourceLocation rl(String value) {
		return new ResourceLocation(MOD_ID, value);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onRegisterLootTables(LootTableLoadEvent event) {
		ModJsonConfigs.LOOT_TABLES.getToAddLootTables().forEach(table -> {
			if (event.getName().equals(new ResourceLocation(table.lootTableName))) {
				event.getTable().addPool(LootPool.lootPool().setRolls(table.lootTable.toRandomRange())
						.add(ItemLootEntry.lootTableItem(table.getItem())).build());
			}
		});
	}

	public void onRegisterCommands(final RegisterCommandsEvent event) {
		CommandInit.registerCommands(event);
	}

	public void oreConversion(TickEvent.ServerTickEvent event) {
		if (CommonConfig.ENABLE_CONVERSION.get() && CommonConfig.INVENTORY_TRIGGER_CONVERSION.get()) {
			for (PlayerEntity player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
				Conversions.convert(player);
			}
		}
	}

	public void onPlayerLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
		BlockPos pos = event.getPos();
		BlockState state = event.getWorld().getBlockState(pos);
		Block block = state.getBlock();
		if (event.getPlayer().isCreative() && block instanceof StorageBinBlock) {
			if (!((StorageBinBlock) block).creativeCanBreakBlock(state, event.getWorld(), pos, event.getPlayer())) {
				state.attack(event.getWorld(), pos, event.getPlayer());
				event.setCanceled(true);
			}
		}
	}

	public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() == Hand.MAIN_HAND && event.getItemStack().isEmpty()) {
			TileEntity tile = event.getWorld().getBlockEntity(event.getPos());
			if (tile instanceof StorageBinTileEntity) {
				event.setUseBlock(Event.Result.ALLOW);
			}
		}
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		BlockItemInit.BLOCKS_THAT_NEED_BLOCKITEMS.forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(RefinementItemGroup.REFINEMENT))
					.setRegistryName(block.getRegistryName()));
			LangProvider.ALL_BLOCKS.add(block.getRegistryName().getPath());
		});
	}

	public void recipeConditions(RegistryEvent.Register<IRecipeSerializer<?>> event) {
		CraftingHelper.register(CropsEnabledCondition.Serializer.INSTANCE);
		CraftingHelper.register(EnableableCondition.Serializer.INSTANCE);
	}

	@SubscribeEvent
	public static void clientSetup(final FMLClientSetupEvent event) {
		event.enqueueWork(RenderLayers::setRenderLayers);
	}

	public void commonSetup(final FMLCommonSetupEvent event) {
		ModJsonConfigs.register();
		CapabilityPlayerPower.register();
		RefinementNetwork.init();
		event.enqueueWork(() -> {
			VillagerInit.registerPOIS();
			TradeLists.fillTradeData();
		});
	}

	public void registerDispenseBehaviours(final FMLCommonSetupEvent event) {
		DefaultDispenseItemBehavior upgradeTileBehaviour = new DefaultDispenseItemBehavior() {
			@Override
			protected ItemStack execute(IBlockSource pSource, ItemStack pStack) {
				Direction direction = pSource.getBlockState().getValue(DispenserBlock.FACING);
				BlockPos blockpos = pSource.getPos().relative(direction);
				if (pSource.getLevel().getBlockEntity(blockpos) instanceof IUpgradableTile)
					return ((IUpgradableTile) pSource.getLevel().getBlockEntity(blockpos))
							.executeDispenserBehaviour(pSource, pStack);
				return pStack;
			}
		};
		DispenserBlock.registerBehavior(ItemInit.ENERGY_ABILITY_UPGRADE.get(), upgradeTileBehaviour);
		DispenserBlock.registerBehavior(ItemInit.SPEED_UPGRADE.get(), upgradeTileBehaviour);
		DispenserBlock.registerBehavior(ItemInit.AUTO_EJECT_UPGRADE.get(), upgradeTileBehaviour);
		DispenserBlock.registerBehavior(ItemInit.AUTO_IMPORT_UPGRADE.get(), upgradeTileBehaviour);
	}

	public void constructMod(FMLConstructModEvent event) {
		ModJsonConfigs.registerBeforeRegistries();
		RegistryEvents.init();
	}

	public void onLoadComplete(final FMLLoadCompleteEvent event) {
		StrippingMap.registerStrippables();
	}
}

package com.ablackpikatchu.refinement.client;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.item.AutoEjectUpgrade;
import com.ablackpikatchu.refinement.common.item.AutoImportUpgrade;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.ablackpikatchu.refinement.core.init.ItemInit.*;
import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD)
public class ClientEvents {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {

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
	}

}

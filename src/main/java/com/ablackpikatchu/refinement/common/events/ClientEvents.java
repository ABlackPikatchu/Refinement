package com.ablackpikatchu.refinement.common.events;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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

		ItemModelsProperties.register(ItemInit.MAGNET.get(),
				new ResourceLocation(Refinement.MOD_ID, "magnet_state"), (stack, world, entity) -> {
					if (entity instanceof PlayerEntity) {
						PlayerEntity player = (PlayerEntity) entity;
						
						Minecraft mc = Minecraft.getInstance();
						boolean isFirstPersonView = mc.player.getUUID().equals(player.getUUID()) &&
								!mc.options.getCameraType().isFirstPerson();
						if (isFirstPersonView) return 3F;
						else if (NBTHelper.getBoolean(stack, "Enabled")) return 2F;
						else return 1F;
					} else
						return 1F;
				});
	}

}

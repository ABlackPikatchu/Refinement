package com.ablackpikatchu.refinement.common.events;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.ItemInit;

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
		
		//Item Predicated
		ItemModelsProperties.register(ItemInit.SPEED_UPGRADE.get(),
				new ResourceLocation(Refinement.MOD_ID, "stack_count"), (stack, world, entity) -> {
					if (stack.getCount() > 3 && stack.getCount() <= 6)
						return 2F;
					else if (stack.getCount() > 6)
						return 3F;
					else
						return 1F;
				});
	}

}

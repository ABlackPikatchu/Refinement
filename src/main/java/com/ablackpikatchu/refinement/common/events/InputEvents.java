package com.ablackpikatchu.refinement.common.events;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.KeybindsInit;
import com.ablackpikatchu.refinement.core.network.RefinementNetwork;
import com.ablackpikatchu.refinement.core.network.message.ConversionMessage;

import net.minecraft.client.Minecraft;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {

	@SubscribeEvent
	public static void onKeyPress(InputEvent.KeyInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.level == null)
			return;
		onInput(mc, event.getKey(), event.getAction());
	}

	@SubscribeEvent
	public static void onMouseClick(InputEvent.MouseInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.level == null)
			return;
		onInput(mc, event.getButton(), event.getAction());
	}

	private static void onInput(Minecraft mc, int key, int action) {
		if (KeybindsInit.conversionKey.isDown()) {
			RefinementNetwork.CONVERSION_CHANNEL.sendToServer(new ConversionMessage(key));
		}
	}

}

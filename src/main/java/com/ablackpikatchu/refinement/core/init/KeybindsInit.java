package com.ablackpikatchu.refinement.core.init;

import java.awt.event.KeyEvent;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.client.settings.KeyBinding;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class KeybindsInit {
	
	public static KeyBinding conversionKey;

	public static void register(final FMLClientSetupEvent event) {
		conversionKey = create("conversion_key", KeyEvent.VK_C);
		
		ClientRegistry.registerKeyBinding(conversionKey);
	}

	private static KeyBinding create(String name, int key) {
		return new KeyBinding("key." + Refinement.MOD_ID + "." + name, key, "key.category." + Refinement.MOD_ID);
	}

}

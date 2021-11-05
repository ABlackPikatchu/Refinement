package com.ablackpikatchu.refinement.common.events;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.api.event.ItemMagnetedEvent;
import com.ablackpikatchu.refinement.api.event.ItemVacuumulatedEvent;
import com.ablackpikatchu.refinement.api.event.QuoteEvent;
import com.ablackpikatchu.refinement.core.init.TagInit;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Refinement.MOD_ID)
public class MiscEvents {

	// @SubscribeEvent
	public static void onQuoteAdded(QuoteEvent.QuoteAddEvent event) {
		if (event.getQuote().quote.startsWith("a")) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onItemVacuumulated(ItemVacuumulatedEvent event) {
		if (TagInit.Items.VACUUMULATOR_BLACKLISTED.contains(event.getItem().getItem().getItem())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onItemMagneted(ItemMagnetedEvent event) {
		if (TagInit.Items.MAGNET_BLACKLISTED.contains(event.getItem().getItem().getItem())) {
			event.setCanceled(true);
		}
	}

}

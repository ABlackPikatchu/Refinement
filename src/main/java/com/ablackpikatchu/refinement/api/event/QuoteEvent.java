package com.ablackpikatchu.refinement.api.event;

import javax.annotation.Nonnull;

import com.ablackpikatchu.refinement.common.command.quote.Quote;

import net.minecraft.entity.player.ServerPlayerEntity;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public abstract class QuoteEvent extends RefinementEvent {

	private final Quote quote;

	protected QuoteEvent(Quote quote) {
		this.quote = quote;
	}

	@Nonnull
	public Quote getQuote() { return this.quote; }

	@Override
	public boolean isCancelable() { return true; }

	/**
	 * If the return is true, the event was cancelled
	 * 
	 * @param quote
	 * @param adder
	 * @return
	 */
	public static boolean onQuoteAdded(Quote quote, ServerPlayerEntity adder) {
		return MinecraftForge.EVENT_BUS.post(new QuoteAddEvent(quote, adder));
	}

	/**
	 * If the return is true, the event was cancelled
	 * 
	 * @param quote
	 * @param adder
	 * @return
	 */
	public static boolean onQuoteDeleted(Quote quote) {
		return MinecraftForge.EVENT_BUS.post(new QuoteDeleteEvent(quote));
	}

	public static class QuoteAddEvent extends QuoteEvent {

		private final ServerPlayerEntity adder;

		private QuoteAddEvent(Quote quote, ServerPlayerEntity adder) {
			super(quote);
			this.adder = adder;
		}

		public ServerPlayerEntity getAdder() { return this.adder; }

	}

	public static class QuoteDeleteEvent extends QuoteEvent {

		protected QuoteDeleteEvent(Quote quote) {
			super(quote);
		}

	}

}

package com.ablackpikatchu.refinement.common.command.quote.impl;

import java.util.Random;

import com.ablackpikatchu.refinement.common.command.quote.Quote;
import com.ablackpikatchu.refinement.common.command.quote.QuoteCommand;
import com.ablackpikatchu.refinement.common.command.quote.QuoteManager;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public class RandomQuoteCommand extends QuoteCommand {

	public RandomQuoteCommand(int permissionLevel, boolean enabled) {
		super(permissionLevel, enabled);
	}

	@Override
	public void build(LiteralArgumentBuilder<CommandSource> builder) {
		builder.executes(this::randomQuote);
	}
	
	public int randomQuote(CommandContext<CommandSource> context) {
		if (!checkQuotesEnabled(context))
			return 0;
		
		QuoteManager manager = getManager(context);
		int randIndex = new Random().nextInt(manager.getQuotesNumber());
		Quote quote = manager.getQuote(randIndex);
		context.getSource()
		.sendSuccess(new StringTextComponent(randIndex + ". \u00A75" + quote.quote + "\u00A7f - "
				+ quote.author.getName().getString()), true);
		
		return 0;
	}

	@Override
	public String getName() { return "random_quote"; }

}

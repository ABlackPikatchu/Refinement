package com.ablackpikatchu.refinement.common.command.quote.impl;

import com.ablackpikatchu.refinement.api.event.QuoteEvent;
import com.ablackpikatchu.refinement.common.command.quote.Quote;
import com.ablackpikatchu.refinement.common.command.quote.QuoteCommand;
import com.ablackpikatchu.refinement.common.command.quote.QuoteManager;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class DeleteQuoteCommand extends QuoteCommand {

	public DeleteQuoteCommand(int permissionLevel, boolean enabled) {
		super(permissionLevel, enabled);
	}

	@Override
	public void build(LiteralArgumentBuilder<CommandSource> builder) {
		builder.then(Commands.argument("index", IntegerArgumentType.integer(0))
				.executes(context -> deleteQuote(context, IntegerArgumentType.getInteger(context, "index"))));
	}

	public int deleteQuote(CommandContext<CommandSource> context, int index) {
		if (!checkQuotesEnabled(context))
			return 0;

		QuoteManager manager = QuoteManager.get(context.getSource().getLevel());
		if (manager.quoteExists(index)) {
			Quote quote = manager.getQuote(index);
			if (!QuoteEvent.onQuoteDeleted(quote)) {
				manager.removeQuote(index);
				context.getSource()
						.sendSuccess(new StringTextComponent("Succesfully deleted the quote with the index: " + index
								+ "! In case you want it, this was the quote: \n\u00A75" + quote.quote + "\u00A7f - "
								+ quote.author.getName().getString()), true);
			} else
				context.getSource().sendFailure(new StringTextComponent("The deletion of the quote was cancelled!"));
		} else {
			context.getSource().sendFailure(new StringTextComponent("Unknown quote with the index " + index
					+ "! Use the command \u00A76/quote list -1\u00A7f to show the amount of quotes this server has!"));
		}

		return 0;
	}

	@Override
	public String getName() { return "remove"; }

}

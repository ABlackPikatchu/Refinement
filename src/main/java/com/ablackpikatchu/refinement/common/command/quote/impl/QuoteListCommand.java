package com.ablackpikatchu.refinement.common.command.quote.impl;

import com.ablackpikatchu.refinement.common.command.quote.QuoteCommand;
import com.ablackpikatchu.refinement.common.command.quote.QuoteManager;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class QuoteListCommand extends QuoteCommand {

	public QuoteListCommand(int permissionLevel, boolean enabled) {
		super(permissionLevel, enabled);
	}

	@Override
	public void build(LiteralArgumentBuilder<CommandSource> builder) {
		builder.then(Commands.argument("page", IntegerArgumentType.integer(-1))
				.executes(context -> getQuoteList(context, IntegerArgumentType.getInteger(context, "page"))));
	}

	public int getQuoteList(CommandContext<CommandSource> context, int page) {
		if (!checkQuotesEnabled(context))
			return 0;

		QuoteManager manager = QuoteManager.get(context.getSource().getLevel());

		if (page == -1) {
			context.getSource().sendSuccess(new StringTextComponent("There are a total of " + manager.getQuotesNumber()
					+ " quotes in this server! Use the command \u00A76/quote list 1\u00A7f to show the first 10 quotes!"),
					false);
		} else {
			int pagesNumber = manager.getQuotesNumber() / 10;
			if (manager.getQuotesNumber() % 10 > 0)
				pagesNumber++;

			if (page > pagesNumber)
				context.getSource().sendFailure(
						new StringTextComponent("The page you specified has an invalid index! There are only "
								+ pagesNumber + " quote pages!"));
			else {
				int startIndex = (page - 1) * 10;
				int endIndex = page * 10;
				if (endIndex > manager.getQuotesNumber())
					endIndex = manager.getQuotesNumber();
				StringBuilder quotesBuilder = new StringBuilder();
				for (int i = startIndex; i < endIndex; i++) {
					if (manager.quoteExists(i)) {
						if (i != startIndex)
							quotesBuilder.append("\n");
						quotesBuilder.append(i + ". \u00A75" + manager.getQuote(i).quote + "\u00A7f - "
								+ manager.getQuote(i).author.getName().getString());
					}
				}
				context.getSource().sendSuccess(new StringTextComponent(quotesBuilder.toString()), false);
			}
		}

		return 0;
	}

	@Override
	public String getName() { return "list"; }

}

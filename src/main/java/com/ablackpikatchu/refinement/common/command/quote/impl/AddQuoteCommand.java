package com.ablackpikatchu.refinement.common.command.quote.impl;

import com.ablackpikatchu.refinement.common.command.quote.Quote;
import com.ablackpikatchu.refinement.common.command.quote.QuoteCommand;
import com.ablackpikatchu.refinement.common.command.quote.QuoteManager;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class AddQuoteCommand extends QuoteCommand {

	public AddQuoteCommand(int permissionLevel, boolean enabled) {
		super(permissionLevel, enabled);
	}

	@Override
	public void build(LiteralArgumentBuilder<CommandSource> builder) {
		builder.then(Commands.argument("author", EntityArgument.player())
				.then(Commands.argument("quote", StringArgumentType.string()).executes(context -> addCommand(context,
						EntityArgument.getPlayer(context, "author"), StringArgumentType.getString(context, "quote")))));
	}

	public int addCommand(CommandContext<CommandSource> context, ServerPlayerEntity author, String quote) {
		if (checkQuotesEnabled(context)) {
			QuoteManager manager = QuoteManager.get(context.getSource().getLevel());
			manager.addQuote(new Quote(author, quote));
			try {
				author.sendMessage(new StringTextComponent(
						"You have been quoted by " + context.getSource().getPlayerOrException().getName().getString()
								+ "! The quote is: \n" + quote),
						context.getSource().getPlayerOrException().getUUID());
			} catch (CommandSyntaxException e) {}
			context.getSource().sendSuccess(
					new StringTextComponent("Added a new quote with the index of " + (manager.getQuotesNumber() - 1)),
					true);
		}
		return 0;
	}

	@Override
	public String getName() { return "add"; }

}

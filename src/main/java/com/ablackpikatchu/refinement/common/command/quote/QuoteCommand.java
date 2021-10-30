package com.ablackpikatchu.refinement.common.command.quote;

import com.ablackpikatchu.refinement.common.command.BaseCommand;
import com.ablackpikatchu.refinement.core.config.ServerConfig;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public abstract class QuoteCommand extends BaseCommand {

	protected QuoteCommand(int permissionLevel, boolean enabled) {
		super(permissionLevel, enabled);
	}

	protected boolean checkQuotesEnabled(CommandContext<CommandSource> context) {
		if (Boolean.FALSE.equals(ServerConfig.QUOTES_ENABLED.get())) {
			context.getSource().sendFailure(new StringTextComponent("Quotes are disabled in this server!"));
		}
		return ServerConfig.QUOTES_ENABLED.get();
	}
	
	protected QuoteManager getManager(CommandContext<CommandSource> context) {
		return QuoteManager.get(context.getSource().getLevel());
	}

}

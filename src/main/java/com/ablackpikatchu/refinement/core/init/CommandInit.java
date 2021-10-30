package com.ablackpikatchu.refinement.core.init;

import static net.minecraft.command.Commands.literal;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.command.BaseCommand;
import com.ablackpikatchu.refinement.common.command.impl.GiveResourceStatueCommand;
import com.ablackpikatchu.refinement.common.command.impl.ReloadCommand;
import com.ablackpikatchu.refinement.common.command.impl.ResetConfigCommand;
import com.ablackpikatchu.refinement.common.command.quote.QuoteCommand;
import com.ablackpikatchu.refinement.common.command.quote.impl.AddQuoteCommand;
import com.ablackpikatchu.refinement.common.command.quote.impl.DeleteQuoteCommand;
import com.ablackpikatchu.refinement.common.command.quote.impl.QuoteByIndexCommand;
import com.ablackpikatchu.refinement.common.command.quote.impl.QuoteListCommand;
import com.ablackpikatchu.refinement.common.command.quote.impl.RandomQuoteCommand;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;

import net.minecraftforge.event.RegisterCommandsEvent;

/**
 * Permission levels
 * 
 * 0 - All Players
 * 1 - No Commands
 * 2 - /give, /clear, /difficulty
 * 3 - /ban, /kick, /op
 * 4 - /stop (to stop the server)
 * 
 * @author matyrobbrt
 *
 */
public class CommandInit {
	
	private static final ArrayList<BaseCommand> commands = new ArrayList<>();
	private static final ArrayList<QuoteCommand> quoteCommands = new ArrayList<>();

	public static void registerCommands(final RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
		
		commands.add(new ReloadCommand(2, true));
		commands.add(new ResetConfigCommand(2, true));
		commands.add(new GiveResourceStatueCommand(2, true));
		
		quoteCommands.add(new AddQuoteCommand(0, true));
		quoteCommands.add(new QuoteListCommand(2, true));
		quoteCommands.add(new DeleteQuoteCommand(PermissionLevels.GIVE_CLEAR, true));
		quoteCommands.add(new RandomQuoteCommand(PermissionLevels.ALL_PLAYERS, true));
		quoteCommands.add(new QuoteByIndexCommand(PermissionLevels.ALL_PLAYERS, true));
		
		commands.forEach(command -> {
			if (command.isEnabled()) {
				LiteralArgumentBuilder<CommandSource> builder = literal(command.getName());
	            builder.requires(sender -> sender.hasPermission(command.getPermissionLevel()));
	            command.build(builder);
	            dispatcher.register(literal(Refinement.MOD_ID).then(builder));
			}
		});
		
		quoteCommands.forEach(quoteCmd -> {
			if (quoteCmd.isEnabled()) {
				LiteralArgumentBuilder<CommandSource> builder = literal(quoteCmd.getName());
	            builder.requires(sender -> sender.hasPermission(quoteCmd.getPermissionLevel()));
	            quoteCmd.build(builder);
	            dispatcher.register(literal("quotes").then(builder));
			}
		});
	}
	
	public static class PermissionLevels {
		
		public static final int ALL_PLAYERS = 0;
		public static final int NO_COMMANDS = 1;
		public static final int GIVE_CLEAR = 2;
		public static final int BAN_KICK_OP = 3;
		public static final int STOP_THE_SERVER = 4;
		
	}

}

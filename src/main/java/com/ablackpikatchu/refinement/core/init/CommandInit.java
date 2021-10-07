package com.ablackpikatchu.refinement.core.init;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.command.BaseCommand;
import com.ablackpikatchu.refinement.common.command.impl.GiveResourceStatueCommand;
import com.ablackpikatchu.refinement.common.command.impl.ReloadCommand;
import com.ablackpikatchu.refinement.common.command.impl.ResetConfigCommand;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;

import net.minecraftforge.event.RegisterCommandsEvent;

import static net.minecraft.command.Commands.literal;

public class CommandInit {
	
	private static final ArrayList<BaseCommand> commands = new ArrayList<>();

	public static void registerCommands(final RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
		
		commands.add(new ReloadCommand(2, true));
		commands.add(new ResetConfigCommand(2, true));
		commands.add(new GiveResourceStatueCommand(2, true));
		
		commands.forEach(command -> {
			if (command.isEnabled()) {
				LiteralArgumentBuilder<CommandSource> builder = literal(command.getName());
	            builder.requires((sender) -> sender.hasPermission(command.getPermissionLevel()));
	            command.build(builder);
	            dispatcher.register(literal(Refinement.MOD_ID).then(builder));
			}
		});
	}

}

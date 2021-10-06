package com.ablackpikatchu.refinement.core.init;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.common.command.BaseCommand;
import com.ablackpikatchu.refinement.common.command.impl.ReloadCommand;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;

import net.minecraftforge.event.RegisterCommandsEvent;

public class CommandInit {
	
	private static final ArrayList<BaseCommand> commands = new ArrayList<>();

	public static void registerCommands(final RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
		
		commands.add(new ReloadCommand("reload_refinement_configs", 2, true));
		
		commands.forEach(command -> {
			if (command.isEnabled() && command.setExecution() != null) {
				dispatcher.register(command.getBuilder());
			}
		});
	}

}

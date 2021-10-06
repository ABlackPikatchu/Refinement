package com.ablackpikatchu.refinement.common.command.impl;

import com.ablackpikatchu.refinement.common.command.BaseCommand;
import com.ablackpikatchu.refinement.core.config.ModJsonConfigs;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public class ReloadCommand extends BaseCommand {

	public ReloadCommand(String name, int permissionLevel, boolean enabled) {
		super(name, permissionLevel, enabled);
	}

	private int reloadConfigs(CommandContext<CommandSource> context) {
		try {
			ModJsonConfigs.register();
			context.getSource().getPlayerOrException().sendMessage(
					new StringTextComponent("Reloaded Refinement Configs"),
					context.getSource().getPlayerOrException().getUUID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public LiteralArgumentBuilder<CommandSource> setExecution() {
		return builder.executes(this::reloadConfigs);
	}

}

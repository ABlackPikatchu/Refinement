package com.ablackpikatchu.refinement.common.command.impl;

import com.ablackpikatchu.refinement.common.command.BaseCommand;
import com.ablackpikatchu.refinement.core.config.ModJsonConfigs;
import com.ablackpikatchu.refinement.core.util.text.TextFormattingUtils;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public class ReloadCommand extends BaseCommand {

	public ReloadCommand(int permissionLevel, boolean enabled) {
		super(permissionLevel, enabled);
	}
	
	@Override
	public String getName() {
		return "reload_configs";
	}
	
	@Override
	public void build(LiteralArgumentBuilder<CommandSource> builder) {
		builder.executes(this::reloadConfigs);
	}

	private int reloadConfigs(CommandContext<CommandSource> context) {
		try {
			ModJsonConfigs.register();
			context.getSource().getPlayerOrException().sendMessage(
					new StringTextComponent("Reloaded Refinement Configs"),
					context.getSource().getPlayerOrException().getUUID());
		} catch (Exception e) {
			try {
				context.getSource().getPlayerOrException()
						.sendMessage(TextFormattingUtils.colouredStringTextComponent(
								new StringTextComponent("Refinemenet configs could not be reloaded! " + e.toString()),
								0xd02090), context.getSource().getPlayerOrException().getUUID());
			} catch (CommandSyntaxException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return 0;
	}

}

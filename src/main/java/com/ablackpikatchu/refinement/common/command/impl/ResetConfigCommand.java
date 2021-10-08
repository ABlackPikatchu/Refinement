package com.ablackpikatchu.refinement.common.command.impl;

import static net.minecraft.command.Commands.literal;

import com.ablackpikatchu.refinement.common.command.BaseCommand;
import com.ablackpikatchu.refinement.core.config.ModJsonConfigs;
import com.ablackpikatchu.refinement.core.config.json.JsonConfig;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public class ResetConfigCommand extends BaseCommand {

	public ResetConfigCommand(int permissionLevel, boolean enabled) {
		super(permissionLevel, enabled);
	}

	@Override
	public void build(LiteralArgumentBuilder<CommandSource> builder) {
		for (Type type : Type.values()) {
			builder.then(literal(type.name().toUpperCase()).executes(context -> this.resetConfigs(context, type)));
		}
	}

	private int resetConfigs(CommandContext<CommandSource> context, Type type) throws CommandSyntaxException {
		type.resetConfig(context);
		return 0;
	}

	@Override
	public String getName() {
		return "reset_config";
	}

	private enum Type {
		RESOURCE_STATUE(ModJsonConfigs.RESOURCE_STATUE), LOOT_BOXES(ModJsonConfigs.LOOT_BOXES),
		ARMOUR(ModJsonConfigs.ARMOUR), TOOLS(ModJsonConfigs.TOOLS), ORE_UNIFY(ModJsonConfigs.ORE_CONVERSION),
		MATERIALIST_TRADES(ModJsonConfigs.MATERIALIST_TRADES);

		private final JsonConfig config;

		private Type(JsonConfig config) {
			this.config = config;
		}

		private void resetConfig(CommandContext<CommandSource> context) {
			this.config.generateConfig();
			context.getSource().sendSuccess(
					new StringTextComponent("The " + this.config.getName() + " config has been reset!"), true);
		}
	}

}

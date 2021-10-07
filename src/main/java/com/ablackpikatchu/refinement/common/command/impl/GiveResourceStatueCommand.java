package com.ablackpikatchu.refinement.common.command.impl;

import com.ablackpikatchu.refinement.common.command.BaseCommand;
import com.ablackpikatchu.refinement.common.item.ResourceStatueItem;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.ItemArgument;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;

public class GiveResourceStatueCommand extends BaseCommand {

	public GiveResourceStatueCommand(int permissionLevel, boolean enabled) {
		super(permissionLevel, enabled);
	}

	@Override
	public String getName() {
		return "give_resource_statue";
	}

	@Override
	public void build(LiteralArgumentBuilder<CommandSource> builder) {
		builder.then(Commands.argument("item_produced", ItemArgument.item())
				.then(Commands.argument("max_produced", IntegerArgumentType.integer(1))
						.executes(context -> this.giveStatue(context, 0))
						.then(Commands.argument("already_produced", IntegerArgumentType.integer(0))
								.executes(context -> this.giveStatue(context,
										IntegerArgumentType.getInteger(context, "already_produced"))))));
	}

	public int giveStatue(CommandContext<CommandSource> context, int alreadyProduced) {
		ItemStack statue = ItemInit.RESOURCE_STATUE_ITEM.get().getDefaultInstance();

		ResourceStatueItem.setResourceProduced(statue,
				ItemArgument.getItem(context, "item_produced").getItem().getRegistryName().toString());
		ResourceStatueItem.setMaxProduce(statue, IntegerArgumentType.getInteger(context, "max_produced"));
		ResourceStatueItem.setProduced(statue, alreadyProduced);

		try {
			context.getSource().getPlayerOrException().inventory.add(statue);
			context.getSource().sendSuccess(new StringTextComponent("Gave you a new Resource Statue!"), true);
		} catch (CommandSyntaxException e) {
			context.getSource().sendFailure(new StringTextComponent("Failed to give you that Resource Statue!"));
			e.printStackTrace();
		}

		return 0;
	}

}

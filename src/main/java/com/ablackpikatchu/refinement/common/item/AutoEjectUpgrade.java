package com.ablackpikatchu.refinement.common.item;

import java.util.ArrayList;
import java.util.List;

import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.google.common.collect.Lists;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class AutoEjectUpgrade extends Item {

	public static final ArrayList<Direction> DIRECTIONS = Lists.newArrayList(Direction.UP, Direction.DOWN,
			Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);

	public static final String DIRECTION_PROPERTY = "direction";

	public AutoEjectUpgrade(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {

		ItemStack upgrade = player.getItemInHand(hand);
		Direction lastDirection = Direction.byName(NBTHelper.getString(upgrade, DIRECTION_PROPERTY));

		int lastIndex = DIRECTIONS.indexOf(lastDirection);
		int newIndex = lastIndex + 1;
		if (newIndex >= DIRECTIONS.size())
			newIndex = 0;

		Direction newDirection = DIRECTIONS.get(newIndex);
		NBTHelper.setString(upgrade, DIRECTION_PROPERTY, newDirection.toString());
		
		player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.0f, 1.0f);

		return super.use(level, player, hand);
	}
	
	/**
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		
		ItemStack upgrade = context.getItemInHand();
		NBTHelper.setString(upgrade, DIRECTION_PROPERTY, context.getClickedFace().getOpposite().toString());
		context.getPlayer().playSound(SoundEvents.UI_BUTTON_CLICK, 1.0f, 1.0f);
		
		return super.useOn(context);
	}
	**/
	
	

	@Override
	public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if (NBTHelper.getString(stack, DIRECTION_PROPERTY) != "")
			tooltip.add(new StringTextComponent("Direction: \u00A76" + NBTHelper.getString(stack, DIRECTION_PROPERTY)));
		
		tooltip.add(new StringTextComponent("Right click to cycle through directions!"));

		super.appendHoverText(stack, level, tooltip, flag);
	}

}

package com.ablackpikatchu.refinement.common.item.box;

import java.util.List;
import java.util.Random;

import com.ablackpikatchu.refinement.core.init.TagInit;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class FoodBox extends Item {

	public FoodBox() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).tab(RefinementItemGroup.REFINEMENT));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
		ItemStack box = player.getItemInHand(hand);

		List<Item> chanceItems = TagInit.Items.FOOD_BOX.getValues();
		Random rand = new Random();

		Item randomItem = chanceItems.get(rand.nextInt(chanceItems.size()));
		ItemStack randomItemStack = new ItemStack(randomItem);

		player.drop(randomItemStack, false);
		box.shrink(1);

		return super.use(worldIn, player, hand);
	}

}

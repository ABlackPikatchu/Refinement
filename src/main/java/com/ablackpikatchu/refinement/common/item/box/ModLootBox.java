package com.ablackpikatchu.refinement.common.item.box;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ModLootBox extends Item {
	
	private final ResourceLocation tag;

	public ModLootBox(Properties properties, ResourceLocation tag) {
		super(properties);
		this.tag = tag;
	}
	
	public ITag.INamedTag<Item> getTag() {
		return ItemTags.bind(this.tag.toString());
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
		ItemStack box = player.getItemInHand(hand);

		List<Item> chanceItems = this.getTag().getValues();
		Random rand = new Random();

		Item randomItem = chanceItems.get(rand.nextInt(chanceItems.size()));
		ItemStack randomItemStack = new ItemStack(randomItem);

		player.drop(randomItemStack, false);
		box.shrink(1);

		return super.use(worldIn, player, hand);
	}

}

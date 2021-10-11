package com.ablackpikatchu.refinement.common.item.box;

import com.ablackpikatchu.refinement.core.config.ModJsonConfigs;
import com.ablackpikatchu.refinement.core.config.json.LootBoxConfig.BoxType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class RefinementLootBox extends Item {
	
	public final BoxType boxType;

	public RefinementLootBox(Properties properties, BoxType boxType) {
		super(properties);
		this.boxType = boxType;
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
		
		ItemStack box = player.getItemInHand(hand);

		player.drop(ModJsonConfigs.LOOT_BOXES.getLootForBox(this.boxType), false);
		if (!player.isCreative()) box.shrink(1);
		
		return super.use(worldIn, player, hand);
	}

}

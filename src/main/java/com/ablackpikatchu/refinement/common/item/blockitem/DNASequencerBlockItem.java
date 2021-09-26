package com.ablackpikatchu.refinement.common.item.blockitem;

import com.ablackpikatchu.refinement.core.init.CropInit;
import com.ablackpikatchu.refinement.core.util.helper.PlayerHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DNASequencerBlockItem extends BlockItem {

	private int ticks;
	private int maxTicks = 1200;

	public DNASequencerBlockItem(Block p_i48527_1_, Properties p_i48527_2_) {
		super(p_i48527_1_, p_i48527_2_);
	}

	@Override
	public void inventoryTick(ItemStack stack, World level, Entity entity, int slot, boolean isSelected) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			if (ticks <= maxTicks)
				ticks++;

			if (ticks >= maxTicks) {
				ItemStack natureEssence = new ItemStack(CropInit.NATURE_ESSENCE.get());
				if (PlayerHelper.canFitItem(player, natureEssence)) {
					player.inventory.add(natureEssence);
					ticks = 0;
				}
			}
		}
	}

}

package com.ablackpikatchu.refinement.common.block;

import java.util.List;
import java.util.Random;

import com.ablackpikatchu.refinement.core.init.ItemTagInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlankOreBlock extends Block {

	public BlankOreBlock() {
		super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(10f).sound(SoundType.METAL)
				.harvestLevel(4));
	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		List<Item> oreItems = ItemTagInit.Items.ORES.getValues();
		Random rand = new Random();

		ItemStack ore = new ItemStack(oreItems.get(rand.nextInt(oreItems.size())), 1);
		System.out.println(ore);
		ItemEntity oreEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), ore);

		worldIn.addFreshEntity(oreEntity);
	}

}

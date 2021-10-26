package com.ablackpikatchu.refinement.common.block;

import java.util.List;

import com.ablackpikatchu.refinement.core.init.TagInit;
import com.google.common.collect.Lists;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

public class BlankOreBlock extends Block {

	public BlankOreBlock() {
		super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(10f).sound(SoundType.METAL)
				.harvestLevel(4));
	}
	
	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
		return MathHelper.nextInt(RANDOM, 2, 10);
	}
	
	@Override
	public List<ItemStack> getDrops(BlockState pState, Builder pBuilder) {
		List<Item> oreItems = TagInit.Items.ORES.getValues();
		ItemStack ore = new ItemStack(oreItems.get(RANDOM.nextInt(oreItems.size())), 1);
		return Lists.newArrayList(ore);
	}

}

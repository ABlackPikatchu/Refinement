package com.ablackpikatchu.refinement.core.world.feature;

import java.util.Random;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

@SuppressWarnings("unused")
public class RefinedTreeFeature extends Feature<NoFeatureConfig> {
	
	private static final Direction[] DIRECTIONS = new Direction[] { Direction.NORTH, Direction.EAST, Direction.SOUTH,
			Direction.WEST };

	private static final BlockState LOG = BlockInit.REFINED_LOG.get().defaultBlockState();
	private static final BlockState LEAVES = BlockInit.REFINED_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 2);

	public RefinedTreeFeature(Codec<NoFeatureConfig> p_i231953_1_) {
		super(p_i231953_1_);
	}
	
	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos,
			NoFeatureConfig config) {

		while (pos.getY() > 1 && isAirOrLeaves(reader, pos)) {
			pos = pos.below();
		}

		// Trunks
		int height = 4 + rand.nextInt(7);
		if (pos.getY() >= 1 && pos.getY() + 7 + 1 < reader.getMaxBuildHeight()) {
			for (int i = pos.getY() + 1; i < pos.getY() + height + 1; i++) {
				reader.setBlock(new BlockPos(pos.getX(), i, pos.getZ()), LOG, 3);
			}
		}
		else {
			return false;
		}

		// Leaves
		
		if (reader.getBlockState(new BlockPos(pos.getX(), pos.getY() + height + 1, pos.getZ())).getBlock() == Blocks.AIR) reader.setBlock(new BlockPos(pos.getX(), pos.getY() + height + 1, pos.getZ()), LEAVES, 3);
		
		for (int i = 0; i < 5; i++) {
				if (reader.getBlockState(new BlockPos(pos.getX() + 1, pos.getY() + height - i, pos.getZ())).getBlock() == Blocks.AIR) reader.setBlock(new BlockPos(pos.getX() + 1, pos.getY() + height - i, pos.getZ()), LEAVES, 3);
				if (reader.getBlockState(new BlockPos(pos.getX() - 1, pos.getY() + height - i, pos.getZ())).getBlock() == Blocks.AIR) reader.setBlock(new BlockPos(pos.getX() - 1, pos.getY() + height - i, pos.getZ()), LEAVES, 3);
				if (reader.getBlockState(new BlockPos(pos.getX(), pos.getY() + height - i, pos.getZ() + 1)).getBlock() == Blocks.AIR) reader.setBlock(new BlockPos(pos.getX(), pos.getY() + height - i, pos.getZ() + 1), LEAVES, 3);
				if (reader.getBlockState(new BlockPos(pos.getX(), pos.getY() + height - i, pos.getZ() - 1)).getBlock() == Blocks.AIR) reader.setBlock(new BlockPos(pos.getX(), pos.getY() + height - i, pos.getZ() - 1), LEAVES, 3);
		}

		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean isAirOrLeaves(IWorldGenerationBaseReader reader, BlockPos pos) {
		if (!(reader instanceof IWorldReader)) {
			return reader.isStateAtPosition(pos, state -> state.isAir() || state.is(BlockTags.LEAVES));
		} else {
			return reader.isStateAtPosition(pos, state -> state.canBeReplacedByLeaves((IWorldReader) reader, pos));
		}
	}

}

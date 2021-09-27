package com.ablackpikatchu.refinement.core.world.feature;

import java.util.Random;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
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
	private static final BlockState HORIZONTAL_LOG = BlockInit.REFINED_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
	private static final BlockState LEAVES = BlockInit.REFINED_LEAVES.get().defaultBlockState()
			.setValue(LeavesBlock.DISTANCE, 2);

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
		int height = 8 + rand.nextInt(7);
		if (pos.getY() >= 1 && pos.getY() + 7 + 1 < reader.getMaxBuildHeight()) {
			for (int i = pos.getY() + 1; i < pos.getY() + height / 2 + 1; i++) {
				reader.setBlock(new BlockPos(pos.getX(), i, pos.getZ()), LOG, 3);
			}
		} else {
			return false;
		}
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		placeHorizontalLog(reader, x + 1, y + height / 2, z);
		placeHorizontalLog(reader, x + 2, y + height / 2, z);
		placeHorizontalLog(reader, x + 3, y + height / 2, z);
		placeVerticalLog(reader, x + 3, y + height / 2 + 1, z);
		
		placeLeaf(reader, x + 3, y + height / 2 + 2, z);
		
		placeHorizontalLog(reader, x - 1, y + height / 2, z);
		placeHorizontalLog(reader, x - 2, y + height / 2, z);
		placeHorizontalLog(reader, x - 3, y + height / 2, z);
		placeHorizontalLog(reader, x - 4, y + height / 2, z);
		placeVerticalLog(reader, x - 4, y + height / 2 + 1, z);
		
		placeLeaf(reader, x - 4, y + height / 2 + 2, z);

		/* Leaves

		// Top Centre
		placeLeaf(reader, x, y + height + 1, z);
		
		// Top Corner
		placeLeaf(reader, x + 1, y + height + 1, z - 1);
		placeLeaf(reader, x + 1, y + height + 1, z + 1);
		placeLeaf(reader, x - 1, y + height + 1, z - 1);
		placeLeaf(reader, x - 1, y + height + 1, z + 1);

		for (int i = 0; i < height / 2; i++) {
			
			double yPos = y + height - i;
			
			// Layer 1
			placeLeaf(reader, x + 1, y + height - i, z);
			placeLeaf(reader, x - 1, y + height - i, z);
			placeLeaf(reader, x, y + height - i, z + 1);
			placeLeaf(reader, x, y + height - i, z - 1);
			
			// Corner 1
			placeLeaf(reader, x + 1, yPos, z - 1);
			placeLeaf(reader, x + 1, yPos, z + 1);
			placeLeaf(reader, x - 1, yPos, z - 1);
			placeLeaf(reader, x - 1, yPos, z + 1);
			
			// Layer 2
			placeLeaf(reader, x + 2, y + height - i, z);
			placeLeaf(reader, x - 2, y + height - i, z);
			placeLeaf(reader, x, y + height - i, z + 2);
			placeLeaf(reader, x, y + height - i, z - 2);
		}
		*/

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

	public static void placeLeaf(ISeedReader reader, double x, double y, double z) {
		if (isAir(reader, new BlockPos(x, y, z)))
			reader.setBlock(new BlockPos(x, y, z), LEAVES, 3);
	}
	
	/**
	 * Places a leaf circle around the block pos specified
	 * @param reader
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void placeLeafCircle(ISeedReader reader, double x, double y, double z) {
		placeLeaf(reader, x, y, z);
		
		placeLeaf(reader, x + 1, y, z);
		placeLeaf(reader, x - 1, y, z);
		placeLeaf(reader, x, y, z + 1);
		placeLeaf(reader, x, y, z - 1);
			
		placeLeaf(reader, x + 1, y, z + 1);
		placeLeaf(reader, x + 1, y, z - 1);
		placeLeaf(reader, x - 1, y, z + 1);
		placeLeaf(reader, x - 1, y, z - 1);
	}
	
	public static void placeVerticalLog(ISeedReader reader, double x, double y, double z) {
		if (isAir(reader, new BlockPos(x, y, z)))
			reader.setBlock(new BlockPos(x, y, z), LOG, 3);
	}
	
	public static void placeHorizontalLog(ISeedReader reader, double x, double y, double z) {
		if (isAir(reader, new BlockPos(x, y, z)))
			reader.setBlock(new BlockPos(x, y, z), HORIZONTAL_LOG, 3);
	}

}

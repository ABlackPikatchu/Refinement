package com.ablackpikatchu.refinement.common.block;

import java.util.Random;

import com.ablackpikatchu.refinement.common.world.TreeSpawner;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class RefinedSaplingBlock extends SaplingBlock {

	private final TreeSpawner tree;

	public RefinedSaplingBlock(TreeSpawner p_i48337_1_, Properties p_i48337_2_) {
		super(null, p_i48337_2_);
		this.tree = p_i48337_1_;
	}

	public void advanceTree(ServerWorld p_226942_1_, BlockPos p_226942_2_, BlockState p_226942_3_, Random p_226942_4_) {
		if (p_226942_3_.getValue(STAGE) == 0) {
			p_226942_1_.setBlock(p_226942_2_, p_226942_3_.cycle(STAGE), 4);
		} else {
			tree.spawn(p_226942_1_, p_226942_1_.getChunkSource().getGenerator(), p_226942_2_, p_226942_3_, p_226942_4_);
		}

	}

}

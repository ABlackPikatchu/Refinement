package com.ablackpikatchu.refinement.common.block.crop;

import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class IronCrop extends CropsBlock {

	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] { Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };

	public IronCrop(Properties p_i48440_1_) {
		super(p_i48440_1_);
	}

	@Override
	protected IItemProvider getBaseSeedId() {
		return ItemInit.IRON_SEEDS.get();
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
	}

	@Override
	public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity p_225533_4_, Hand p_225533_5_,
			BlockRayTraceResult p_225533_6_) {
		
		if (this.getAge(state) == 7) {
			ItemStack drops = new ItemStack(ItemInit.IRON_ESSENCE.get());
			level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), drops));
			level.setBlockAndUpdate(pos, state.setValue(this.getAgeProperty(), 0));
		}

		return ActionResultType.FAIL;
	}

}

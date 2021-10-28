package com.ablackpikatchu.refinement.common.block.machine;

import java.util.Random;
import java.util.stream.Stream;

import com.ablackpikatchu.refinement.common.block.MachineBlock;
import com.ablackpikatchu.refinement.common.te.machine.DNASequencerTileEntity;
import com.ablackpikatchu.refinement.core.init.ParticleTypesInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class DNASequencerBlock extends MachineBlock {

	public DNASequencerBlock() {
		super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(10f).sound(SoundType.METAL)
				.harvestLevel(4).noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, LIT);
	}
	
	@Override
	public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
		switch (pState.getValue(FACING)) {
		case NORTH: return BlockVoxelShapes.NORTH_SHAPE;
		case SOUTH: return BlockVoxelShapes.SOUTH_SHAPE;
		case EAST: return BlockVoxelShapes.EAST_SHAPE;
		case WEST: return BlockVoxelShapes.WEST_SHAPE;
		default:
			return BlockVoxelShapes.NORTH_SHAPE;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		if (state.getValue(LIT) == true)
			return 7;
		else
			return 0;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.DNA_SEQUENCER_TILE_ENTITY_TYPE.get().create();
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (!worldIn.isClientSide()) {
			TileEntity te = worldIn.getBlockEntity(pos);
			if (te instanceof DNASequencerTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (DNASequencerTileEntity) te, pos);
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
		return Container.getRedstoneSignalFromBlockEntity(worldIn.getBlockEntity(pos));
	}

	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(worldIn, pos, state, placer, stack);
		if (stack.hasCustomHoverName()) {
			TileEntity tile = worldIn.getBlockEntity(pos);
			if (tile instanceof DNASequencerTileEntity) {
				((DNASequencerTileEntity) tile).setCustomName(stack.getDisplayName());
			}
		}
	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			worldIn.removeBlockEntity(pos);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World level, BlockPos pos, Random rand) {
		if (Boolean.TRUE.equals(state.getValue(MachineBlock.LIT)) && (rand.nextInt(40) + 1) <= 20) {
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY() + 0.7D;
			double d2 = (double) pos.getZ() + 0.5D;
			if (rand.nextInt(8) <= 4)
				d2 = pos.getZ() + 0.8D;
			if (rand.nextInt(10) <= 5)
				d2 = pos.getZ() + 0.8D;
			level.addParticle(ParticleTypesInit.DNA_PARTICLES.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}
	
	public static final class BlockVoxelShapes {
		
		public static final VoxelShape NORTH_SHAPE = Stream.of(
				Block.box(7.5, 9.75, 5.5, 8.5, 10.75, 6.5),
				Block.box(1, 0, 1, 15, 1, 15),
				Block.box(5, 1, 3, 11, 2, 9),
				Block.box(5, 8, 3, 11, 9, 9),
				Block.box(10, 2, 3, 11, 8, 4),
				Block.box(10, 2, 8, 11, 8, 9),
				Block.box(5, 2, 8, 6, 8, 9),
				Block.box(5, 2, 3, 6, 8, 4),
				Block.box(5.5, 2, 3.5, 10.5, 8, 8.5),
				Block.box(6.5, 8.75, 4.5, 9.5, 9.75, 7.5),
				Block.box(10, 1, 10, 14, 2, 14),
				Block.box(10, 4, 10, 14, 5, 14),
				Block.box(13, 2, 10, 14, 4, 11),
				Block.box(13, 2, 13, 14, 4, 14),
				Block.box(10, 2, 13, 11, 4, 14),
				Block.box(10, 2, 10, 11, 4, 11),
				Block.box(10.5, 2, 10.5, 13.5, 4, 13.5),
				Block.box(11.55, 4.75, 11.5, 12.55, 7.75, 12.5),
				Block.box(2, 1, 10, 6, 2, 14),
				Block.box(2, 4, 10, 6, 5, 14),
				Block.box(2, 2, 10, 3, 4, 11),
				Block.box(2, 2, 13, 3, 4, 14),
				Block.box(5, 2, 13, 6, 4, 14),
				Block.box(5, 2, 10, 6, 4, 11),
				Block.box(2.5, 2, 10.5, 5.5, 4, 13.5),
				Block.box(3.4499999999999997, 4.75, 11.5, 4.449999999999999, 7.75, 12.5),
				Block.box(3.4499999999999997, 7.75, 11.5, 12.55, 8.75, 12.5),
				Block.box(7.5, 8.75, 11.5, 8.5, 11.75, 12.5),
				Block.box(7.5, 10.75, 5.5, 8.5, 11.75, 11.5)
				).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
		
		public static final VoxelShape SOUTH_SHAPE = Stream.of(
				Block.box(7.5, 9.75, 9.5, 8.5, 10.75, 10.5),
				Block.box(1, 0, 1, 15, 1, 15),
				Block.box(5, 1, 7, 11, 2, 13),
				Block.box(5, 8, 7, 11, 9, 13),
				Block.box(5, 2, 12, 6, 8, 13),
				Block.box(5, 2, 7, 6, 8, 8),
				Block.box(10, 2, 7, 11, 8, 8),
				Block.box(10, 2, 12, 11, 8, 13),
				Block.box(5.5, 2, 7.5, 10.5, 8, 12.5),
				Block.box(6.5, 8.75, 8.5, 9.5, 9.75, 11.5),
				Block.box(2, 1, 2, 6, 2, 6),
				Block.box(2, 4, 2, 6, 5, 6),
				Block.box(2, 2, 5, 3, 4, 6),
				Block.box(2, 2, 2, 3, 4, 3),
				Block.box(5, 2, 2, 6, 4, 3),
				Block.box(5, 2, 5, 6, 4, 6),
				Block.box(2.5, 2, 2.5, 5.5, 4, 5.5),
				Block.box(3.4499999999999993, 4.75, 3.5, 4.449999999999999, 7.75, 4.5),
				Block.box(10, 1, 2, 14, 2, 6),
				Block.box(10, 4, 2, 14, 5, 6),
				Block.box(13, 2, 5, 14, 4, 6),
				Block.box(13, 2, 2, 14, 4, 3),
				Block.box(10, 2, 2, 11, 4, 3),
				Block.box(10, 2, 5, 11, 4, 6),
				Block.box(10.5, 2, 2.5, 13.5, 4, 5.5),
				Block.box(11.55, 4.75, 3.5, 12.55, 7.75, 4.5),
				Block.box(3.4499999999999993, 7.75, 3.5, 12.55, 8.75, 4.5),
				Block.box(7.5, 8.75, 3.5, 8.5, 11.75, 4.5),
				Block.box(7.5, 10.75, 4.5, 8.5, 11.75, 10.5)
				).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
		
		public static final VoxelShape EAST_SHAPE = Stream.of(
				Block.box(9.5, 9.75, 7.5, 10.5, 10.75, 8.5),
				Block.box(1, 0, 1, 15, 1, 15),
				Block.box(7, 1, 5, 13, 2, 11),
				Block.box(7, 8, 5, 13, 9, 11),
				Block.box(12, 2, 10, 13, 8, 11),
				Block.box(7, 2, 10, 8, 8, 11),
				Block.box(7, 2, 5, 8, 8, 6),
				Block.box(12, 2, 5, 13, 8, 6),
				Block.box(7.5, 2, 5.5, 12.5, 8, 10.5),
				Block.box(8.5, 8.75, 6.5, 11.5, 9.75, 9.5),
				Block.box(2, 1, 10, 6, 2, 14),
				Block.box(2, 4, 10, 6, 5, 14),
				Block.box(5, 2, 13, 6, 4, 14),
				Block.box(2, 2, 13, 3, 4, 14),
				Block.box(2, 2, 10, 3, 4, 11),
				Block.box(5, 2, 10, 6, 4, 11),
				Block.box(2.5, 2, 10.5, 5.5, 4, 13.5),
				Block.box(3.5, 4.75, 11.55, 4.5, 7.75, 12.55),
				Block.box(2, 1, 2, 6, 2, 6),
				Block.box(2, 4, 2, 6, 5, 6),
				Block.box(5, 2, 2, 6, 4, 3),
				Block.box(2, 2, 2, 3, 4, 3),
				Block.box(2, 2, 5, 3, 4, 6),
				Block.box(5, 2, 5, 6, 4, 6),
				Block.box(2.5, 2, 2.5, 5.5, 4, 5.5),
				Block.box(3.5, 4.75, 3.4499999999999993, 4.5, 7.75, 4.449999999999999),
				Block.box(3.5, 7.75, 3.4499999999999993, 4.5, 8.75, 12.55),
				Block.box(3.5, 8.75, 7.5, 4.5, 11.75, 8.5),
				Block.box(4.5, 10.75, 7.5, 10.5, 11.75, 8.5)
				).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
		
		public static final VoxelShape WEST_SHAPE = Stream.of(
				Block.box(5.5, 9.75, 7.5, 6.5, 10.75, 8.5),
				Block.box(1, 0, 1, 15, 1, 15),
				Block.box(3, 1, 5, 9, 2, 11),
				Block.box(3, 8, 5, 9, 9, 11),
				Block.box(3, 2, 5, 4, 8, 6),
				Block.box(8, 2, 5, 9, 8, 6),
				Block.box(8, 2, 10, 9, 8, 11),
				Block.box(3, 2, 10, 4, 8, 11),
				Block.box(3.5, 2, 5.5, 8.5, 8, 10.5),
				Block.box(4.5, 8.75, 6.5, 7.5, 9.75, 9.5),
				Block.box(10, 1, 2, 14, 2, 6),
				Block.box(10, 4, 2, 14, 5, 6),
				Block.box(10, 2, 2, 11, 4, 3),
				Block.box(13, 2, 2, 14, 4, 3),
				Block.box(13, 2, 5, 14, 4, 6),
				Block.box(10, 2, 5, 11, 4, 6),
				Block.box(10.5, 2, 2.5, 13.5, 4, 5.5),
				Block.box(11.5, 4.75, 3.4499999999999993, 12.5, 7.75, 4.449999999999999),
				Block.box(10, 1, 10, 14, 2, 14),
				Block.box(10, 4, 10, 14, 5, 14),
				Block.box(10, 2, 13, 11, 4, 14),
				Block.box(13, 2, 13, 14, 4, 14),
				Block.box(13, 2, 10, 14, 4, 11),
				Block.box(10, 2, 10, 11, 4, 11),
				Block.box(10.5, 2, 10.5, 13.5, 4, 13.5),
				Block.box(11.5, 4.75, 11.55, 12.5, 7.75, 12.55),
				Block.box(11.5, 7.75, 3.4499999999999993, 12.5, 8.75, 12.55),
				Block.box(11.5, 8.75, 7.5, 12.5, 11.75, 8.5),
				Block.box(5.5, 10.75, 7.5, 11.5, 11.75, 8.5)
				).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
		
	}
}

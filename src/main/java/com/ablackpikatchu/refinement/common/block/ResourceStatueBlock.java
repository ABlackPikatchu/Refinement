package com.ablackpikatchu.refinement.common.block;

import com.ablackpikatchu.refinement.common.item.ResourceStatueItem;
import com.ablackpikatchu.refinement.common.te.misc_tes.ResourceStatueTileEntity;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ResourceStatueBlock extends Block {
	
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	protected static final VoxelShape LOWER_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 24.0D, 16.0D);

	public ResourceStatueBlock() {
		super(AbstractBlock.Properties.of(Material.HEAVY_METAL).noOcclusion().strength(4.0f));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}
	
	@Override
	public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
		return LOWER_SHAPE;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos,
			ISelectionContext pContext) {
		return LOWER_SHAPE;
	}
	
	@Override
	public VoxelShape getOcclusionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
		return LOWER_SHAPE;
	}
	
	@Override
	public VoxelShape getBlockSupportShape(BlockState pState, IBlockReader pReader, BlockPos pPos) {
		return LOWER_SHAPE;
	}
	
	@Override
	public VoxelShape getInteractionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
		return LOWER_SHAPE;
	}
	
	@Override
	public VoxelShape getVisualShape(BlockState pState, IBlockReader pReader, BlockPos pPos,
			ISelectionContext pContext) {
		return LOWER_SHAPE;
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
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.RESOURCE_STATUE_TILE_ENTITY_TYPE.get().create();
	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		TileEntity tile = worldIn.getBlockEntity(pos);
		if (tile instanceof ResourceStatueTileEntity) {
			ResourceStatueTileEntity statue = (ResourceStatueTileEntity) tile;
			ItemStack stack = new ItemStack(getBlock());

			ResourceStatueItem.setResourceProduced(stack, statue.producedItem.getRegistryName().toString());
			ResourceStatueItem.setMaxProduce(stack, statue.maxProduce);
			ResourceStatueItem.setProduced(stack, statue.producedNumber);

			ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
					stack);
			itemEntity.setDefaultPickUpDelay();
			worldIn.addFreshEntity(itemEntity);
		}
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			worldIn.removeBlockEntity(pos);
		}
	}
	
	@Override
	public void setPlacedBy(World pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
		super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
		TileEntity tile = pLevel.getBlockEntity(pPos);
		if (tile instanceof ResourceStatueTileEntity) {
			ResourceStatueTileEntity statue = (ResourceStatueTileEntity) tile;
			statue.setProducedItem(ResourceStatueItem.getResourceProduced(pStack));
			statue.setMaxProduce(ResourceStatueItem.getMaxProduce(pStack));
			statue.setProduced(ResourceStatueItem.getProduced(pStack));
			TileEntityHelper.updateTE(tile);
		}
	}

}

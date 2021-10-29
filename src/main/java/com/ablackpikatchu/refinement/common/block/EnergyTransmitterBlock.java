package com.ablackpikatchu.refinement.common.block;

import java.util.List;

import com.ablackpikatchu.refinement.common.te.misc_tes.EnergyTransmitterTileEntity;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.helper.WorldHelper;
import com.google.common.collect.Lists;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootParameters;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
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

import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;

public class EnergyTransmitterBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	
	public EnergyTransmitterBlock() {
		super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(10f).sound(SoundType.METAL)
				.harvestLevel(4).noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
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
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getClickedFace());
	}
	
	@Override
	public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
		switch (pState.getValue(FACING)) {
		case UP: return BlockShapes.UP;
		case DOWN: return BlockShapes.DOWN;
		case NORTH: return BlockShapes.NORTH;
		case SOUTH: return BlockShapes.SOUTH;
		case EAST: return BlockShapes.EAST;
		case WEST: return BlockShapes.WEST;

		default:
			return BlockShapes.UP;
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.ENERGY_TRANSMITTER_TILE_ENTITY_TYPE.get().create();
	}
	
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isClientSide()) {
			TileEntity te = worldIn.getBlockEntity(pos);
			if (te instanceof EnergyTransmitterTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (EnergyTransmitterTileEntity) te, pos);
			}
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public void setPlacedBy(World pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
		if (WorldHelper.getTileEntity(EnergyTransmitterTileEntity.class, pLevel, pPos, false) != null) {
			EnergyTransmitterTileEntity tile = WorldHelper.getTileEntity(EnergyTransmitterTileEntity.class, pLevel, pPos, false);
			tile.resetParticleColour();
		}
		super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
	}
	
	@Override
	public List<ItemStack> getDrops(BlockState pState, net.minecraft.loot.LootContext.Builder pBuilder) {
		List<ItemStack> stacks = Lists.newArrayList(this.asItem().getDefaultInstance());
		if (pBuilder.getParameter(LootParameters.BLOCK_ENTITY) instanceof EnergyTransmitterTileEntity) {
			ItemStackHandler handler = ((EnergyTransmitterTileEntity) pBuilder.getParameter(LootParameters.BLOCK_ENTITY)).getItemHandler();
			for (int i = 0; i <= handler.getSlots() - 1; i++) {
				stacks.add(handler.getStackInSlot(i));
			}
		}
		return stacks;
	}
	
	public static final class BlockShapes {
		
		public static final VoxelShape UP = VoxelShapes.join(Block.box(3, 0, 3, 13, 1, 13), Block.box(4, 1, 4, 12, 2, 12), IBooleanFunction.OR);
		public static final VoxelShape DOWN = VoxelShapes.join(Block.box(3, 15, 3, 13, 16, 13), Block.box(4, 14, 4, 12, 15, 12), IBooleanFunction.OR);
		public static final VoxelShape SOUTH = VoxelShapes.join(Block.box(3, 3, 0, 13, 13, 1), Block.box(4, 4, 1, 12, 12, 2), IBooleanFunction.OR);
		public static final VoxelShape EAST = VoxelShapes.join(Block.box(0, 3, 3, 1, 13, 13), Block.box(1, 4, 4, 2, 12, 12), IBooleanFunction.OR);
		public static final VoxelShape NORTH = VoxelShapes.join(Block.box(3, 3, 15, 13, 13, 16), Block.box(4, 4, 14, 12, 12, 15), IBooleanFunction.OR);
		public static final VoxelShape WEST = VoxelShapes.join(Block.box(15, 3, 3, 16, 13, 13), Block.box(14, 4, 4, 15, 12, 12), IBooleanFunction.OR);
		
	}

}

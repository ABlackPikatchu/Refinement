package com.ablackpikatchu.refinement.common.block;

import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class EnergyCableBlock extends Block {

	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	public static final BooleanProperty UP = BooleanProperty.create("up");
	public static final BooleanProperty DOWN = BooleanProperty.create("down");

	public EnergyCableBlock() {
		super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(10f).sound(SoundType.METAL)
				.harvestLevel(4).noOcclusion());

		this.registerDefaultState(defaultBlockState().setValue(NORTH, false).setValue(EAST, false)
				.setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false));
	}

	/*
	@Override
	public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
		for (Direction direction : Direction.values()) {
			if (world.getBlockState(pos.relative(direction)) == world.getBlockState(neighbor)) {
				TileEntity te = world.getBlockEntity(pos.relative(direction));
				if (te != null) {
					IEnergyStorage handler = te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite())
							.orElse(null);
					if (handler != null && handler.canReceive())
						setConnection(state, direction, true);
					else
						setConnection(state, direction, false);
				} else
					setConnection(state, direction, false);
			}
		}
	}

	@Override
	public void onPlace(BlockState state, World world, BlockPos pos, BlockState pOldState, boolean pIsMoving) {
		for (Direction direction : Direction.values()) {
			TileEntity te = world.getBlockEntity(pos.relative(direction));
			if (te instanceof EnergyCableTileEntity)
				continue;
			if (te != null) {
				IEnergyStorage handler = te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite())
						.orElse(null);
				if (handler != null && handler.canReceive())
					setConnection(state, direction, true);
				else
					setConnection(state, direction, false);
			} else
				setConnection(state, direction, false);
		}
	}
	*/

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder);

		pBuilder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			worldIn.removeBlockEntity(pos);
		}
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.ENERGY_CABLE_TILE_ENTITY_TYPE.get().create();
	}

	public void setConnection(BlockState blockState, Direction direction, boolean state) {
		switch (direction) {
		case UP: {
			if (blockState.getValue(UP) != state)
				blockState.setValue(UP, state);
		}
		case DOWN: {
			if (blockState.getValue(DOWN) != state)
				blockState.setValue(DOWN, state);
		}
		case NORTH: {
			if (blockState.getValue(NORTH) != state)
				blockState.setValue(NORTH, state);
		}
		case SOUTH: {
			if (blockState.getValue(SOUTH) != state)
				blockState.setValue(SOUTH, state);
		}
		case EAST: {
			if (blockState.getValue(EAST) != state)
				blockState.setValue(EAST, state);
		}
		case WEST: {
			if (blockState.getValue(WEST) != state)
				blockState.setValue(WEST, state);
		}
		}
	}

}

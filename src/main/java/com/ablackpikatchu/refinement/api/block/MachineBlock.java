package com.ablackpikatchu.refinement.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;

public abstract class MachineBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BooleanProperty.create("lit");

	protected MachineBlock(Properties properties) {
		super(properties);
	}

	/**
	 * Execute this function in when defining the light level of a machine block in
	 * its properties
	 *
	 * @param state
	 * @return
	 */
	public static int lightLevel(BlockState state) {
		return Boolean.TRUE.equals(state.getValue(LIT)) ? 7 : 0;
	}

}

package com.ablackpikatchu.refinement.common.block;

import net.minecraft.block.Block;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;

public abstract class MachineBlock extends Block {
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BooleanProperty.create("lit");

	protected MachineBlock(Properties properties) {
		super(properties);
	}

}

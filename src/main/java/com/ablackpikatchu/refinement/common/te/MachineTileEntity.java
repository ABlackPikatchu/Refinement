package com.ablackpikatchu.refinement.common.te;

import net.minecraft.tileentity.TileEntityType;

public abstract class MachineTileEntity extends SidedInventoryTileEntity {

	protected MachineTileEntity(TileEntityType<?> typeIn, int inventorySize) {
		super(typeIn, inventorySize);
	}

}

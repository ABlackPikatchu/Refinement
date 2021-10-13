package com.ablackpikatchu.refinement.common.container;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;

public abstract class MachineContainer<T extends TileEntity> extends Container {

	public final T te;
	
	protected MachineContainer(ContainerType<?> pMenuType, int pContainerId, T te) {
		super(pMenuType, pContainerId);
		this.te = te;
	}

}

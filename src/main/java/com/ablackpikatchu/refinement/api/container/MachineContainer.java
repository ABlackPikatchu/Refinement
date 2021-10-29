package com.ablackpikatchu.refinement.api.container;

import com.ablackpikatchu.refinement.api.te.SidedInventoryTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public abstract class MachineContainer<T extends SidedInventoryTileEntity> extends Container {

	public final T te;

	protected MachineContainer(ContainerType<?> pMenuType, int pContainerId, T te) {
		super(pMenuType, pContainerId);
		this.te = te;
	}

	public ItemStack handleShiftClick(PlayerEntity playerIn, int index, int slots) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (!slot.isActive())
			return ItemStack.EMPTY;
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if ((index < slots && !this.moveItemStackTo(stack1, slots, this.slots.size(), true)) || !this.moveItemStackTo(stack1, 0, slots, false))
				return ItemStack.EMPTY;

			if (stack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return stack;
	}

}

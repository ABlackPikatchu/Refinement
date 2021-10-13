package com.ablackpikatchu.refinement.common.container;

import java.util.Objects;

import com.ablackpikatchu.refinement.common.te.misc_tes.VaccumulatorTileEntity;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class VaccumulatorContainer extends MachineContainer<VaccumulatorTileEntity> {

	private final IWorldPosCallable canInteractWithCallable;

	public VaccumulatorContainer(final int windowId, final PlayerInventory playerInventory,
			final VaccumulatorTileEntity te) {
		super(ContainerTypesInit.VACCUMULATOR_CONTAINER_TYPE.get(), windowId, te);
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

		// Main Inventory
		int startX = 8;
		int startY = 18;
		int slotSizePlus2 = 18;
		for (int row = 0; row < 4; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(te, (row * 9) + column, startX + (column * slotSizePlus2),
						startY + (row * slotSizePlus2)));
			}
		}

		// Main Player Inventory
		int startPlayerInvY = startY * 5 + 12;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * slotSizePlus2),
						startPlayerInvY + (row * slotSizePlus2)));
			}
		}

		// Hotbar
		int hotbarY = startPlayerInvY + (startPlayerInvY / 2) + 7;
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, startX + (column * slotSizePlus2), hotbarY));
		}
	}

	public VaccumulatorContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static VaccumulatorTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof VaccumulatorTileEntity) {
			return (VaccumulatorTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return stillValid(canInteractWithCallable, p_75145_1_, BlockInit.VACCUMULATOR.get());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < VaccumulatorTileEntity.slots
					&& !this.moveItemStackTo(stack1, VaccumulatorTileEntity.slots, this.slots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if (!this.moveItemStackTo(stack1, 0, VaccumulatorTileEntity.slots, false)) {
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return stack;
	}

}

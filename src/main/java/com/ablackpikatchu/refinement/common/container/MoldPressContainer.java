package com.ablackpikatchu.refinement.common.container;

import java.util.Objects;

import com.ablackpikatchu.refinement.common.slot.ItemsReservedSlot;
import com.ablackpikatchu.refinement.common.slot.OutputSlot;
import com.ablackpikatchu.refinement.common.slot.itemspecific.CarbonSlot;
import com.ablackpikatchu.refinement.common.slot.itemspecific.UpgradeSlot;
import com.ablackpikatchu.refinement.common.te.machine.MoldPressTileEntity;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.util.FunctionalIntReferenceHolder;
import com.ablackpikatchu.refinement.core.util.enums.Upgrades;
import com.ablackpikatchu.refinement.core.util.lists.ItemLists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MoldPressContainer extends MachineContainer<MoldPressTileEntity> {
	
	private final IWorldPosCallable canInteractWithCallable;
	public FunctionalIntReferenceHolder currentWaitTime;
	public FunctionalIntReferenceHolder maxWaitTime;
	
	public MoldPressContainer(final int windowId, final PlayerInventory playerInv,
			final MoldPressTileEntity te) {
		super(ContainerTypesInit.MOLD_PRESS_CONTAINER_TYPE.get(), windowId, te);
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

		// Tile Entity
		this.addSlot(new Slot((IInventory) te, 0, 62, 6)); //Input
		this.addSlot(new OutputSlot((IInventory) te, 1, 119, 6)); //Output
		this.addSlot(new ItemsReservedSlot((IInventory) te, 2, 62, 53, ItemLists.MOLDS)); //Mold
		this.addSlot(new CarbonSlot(te, 3, 8, 44)); //Coal
		this.addSlot(new UpgradeSlot((IInventory) te, 4, 197, 113, Upgrades.SPEED)); //Speed Upgrade
		this.addSlot(new UpgradeSlot(te, 5, 179, 113, Upgrades.AUTO_EJECT)); // Auto eject upgrade
		this.addSlot(new UpgradeSlot(te, 6, 197, 95, Upgrades.AUTO_IMPORT)); // Auto Import Upgrade

		// Main Player Inventory
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 159 - (4 - row) * 18 - 10));
			}
		}

		// Player Hotbar
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInv, col, 8 + col * 18, 135));
		}

		this.addDataSlot(currentWaitTime = new FunctionalIntReferenceHolder(() -> this.te.currentWaitTime,
				value -> this.te.currentWaitTime = value));
		this.addDataSlot(maxWaitTime = new FunctionalIntReferenceHolder(() -> this.te.maxWaitTime,
				value -> this.te.maxWaitTime = value));
	}

	public MoldPressContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static MoldPressTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof MoldPressTileEntity) {
			return (MoldPressTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return stillValid(canInteractWithCallable, p_75145_1_, BlockInit.MOLD_PRESS.get());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < MoldPressTileEntity.slots
					&& !this.moveItemStackTo(stack1, MoldPressTileEntity.slots, this.slots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if (!this.moveItemStackTo(stack1, 0, MoldPressTileEntity.slots, false)) {
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

	@OnlyIn(Dist.CLIENT)
	public int getProgressionScaled() {
		return this.currentWaitTime.get() != 0 && this.maxWaitTime.get() != 0
				? this.currentWaitTime.get() * 17 / this.maxWaitTime.get()
				: 0;
	}
}

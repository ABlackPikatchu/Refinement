package com.ablackpikatchu.refinement.common.container;

import java.util.Objects;

import com.ablackpikatchu.refinement.common.slot.OutputSlot;
import com.ablackpikatchu.refinement.common.slot.itemspecific.CarbonSlot;
import com.ablackpikatchu.refinement.common.slot.itemspecific.UpgradeSlot;
import com.ablackpikatchu.refinement.common.te.machine.DNASequencerTileEntity;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.util.FunctionalIntReferenceHolder;
import com.ablackpikatchu.refinement.core.util.enums.Upgrades;

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

public class DNASequencerContainer extends MachineContainer<DNASequencerTileEntity> {

	private final IWorldPosCallable canInteractWithCallable;
	public FunctionalIntReferenceHolder currentWaitTime;
	public FunctionalIntReferenceHolder maxWaitTime;
	public FunctionalIntReferenceHolder succesProbability;

	public DNASequencerContainer(final int windowId, final PlayerInventory playerInv, final DNASequencerTileEntity te) {
		super(ContainerTypesInit.DNA_SEQUENCER_CONTAINER_TYPE.get(), windowId, te);
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

		// Tile Entity
		this.addSlot(new Slot((IInventory) te, 0, 44, 14)); // Input 1
		this.addSlot(new Slot((IInventory) te, 1, 44, 43)); // Input 2
		this.addSlot(new OutputSlot((IInventory) te, 2, 135, 28)); // Output
		this.addSlot(new CarbonSlot(te, 3, 8, 49)); // Coal
		this.addSlot(new UpgradeSlot((IInventory) te, 4, 197, 118, Upgrades.SPEED)); //Speed Upgrade
		this.addSlot(new UpgradeSlot(te, 5, 179, 118, Upgrades.AUTO_EJECT)); // Auto eject upgrade
		this.addSlot(new UpgradeSlot(te, 6, 197, 100, Upgrades.AUTO_IMPORT)); // Auto Import Upgrade

		// Main Player Inventory
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 164 - (4 - row) * 18 - 10));
			}
		}

		// Player Hotbar
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInv, col, 8 + col * 18, 140));
		}

		this.addDataSlot(currentWaitTime = new FunctionalIntReferenceHolder(() -> this.te.currentWaitTime,
				value -> this.te.currentWaitTime = value));
		this.addDataSlot(maxWaitTime = new FunctionalIntReferenceHolder(() -> this.te.maxWaitTime,
				value -> this.te.maxWaitTime = value));
		this.addDataSlot(succesProbability = new FunctionalIntReferenceHolder(() -> this.te.successProbability,
				value -> this.te.successProbability = value));
	}

	public DNASequencerContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static DNASequencerTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof DNASequencerTileEntity) {
			return (DNASequencerTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return stillValid(canInteractWithCallable, p_75145_1_, BlockInit.DNA_SEQUENCER.get());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (!slot.isActive())
			return ItemStack.EMPTY;
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < DNASequencerTileEntity.slots
					&& !this.moveItemStackTo(stack1, DNASequencerTileEntity.slots, this.slots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if (!this.moveItemStackTo(stack1, 0, DNASequencerTileEntity.slots, false)) {
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
				? this.currentWaitTime.get() * 71 / this.maxWaitTime.get()
				: 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean hasValidRecipe() {
		return (this.succesProbability.get() != -1);
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getSuccessProbability() {
		return this.succesProbability.get();
	}

}

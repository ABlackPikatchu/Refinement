package com.ablackpikatchu.refinement.common.container;

import java.util.Objects;

import com.ablackpikatchu.refinement.common.te.misc_tes.EnergyTransmitterTileEntity;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.util.FunctionalIntReferenceHolder;
import com.ablackpikatchu.refinement.core.util.InventoryUtils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import net.minecraftforge.items.SlotItemHandler;

public class EnergyTransmitterContainer extends Container {
	
	private final IWorldPosCallable canInteractWithCallable;
	public final EnergyTransmitterTileEntity te;
	
	public FunctionalIntReferenceHolder energyUsed;

	public EnergyTransmitterContainer(final int windowId, final PlayerInventory playerInv,
			final EnergyTransmitterTileEntity te) {
		super(ContainerTypesInit.ENERGY_TRANSMITTER_CONTAINER_TYPE.get(), windowId);
		this.te = te;
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());
		
		InventoryUtils.createPlayerSlots(playerInv, 8, 48).forEach(this::addSlot);
		
		for (int i = 0; i <= 8; i++) {
			this.addSlot(new SlotItemHandler(te.getItemHandler(), i, 8 + i * 18, 18));
		}
		
		this.addDataSlot(energyUsed = new FunctionalIntReferenceHolder(() -> this.te.energyStorage.energyUsed,
				value -> this.te.energyStorage.energyUsed = value));
	}

	public EnergyTransmitterContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static EnergyTransmitterTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof EnergyTransmitterTileEntity) {
			return (EnergyTransmitterTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return stillValid(canInteractWithCallable, p_75145_1_, BlockInit.ENERGY_TRANSMITTER_BLOCK);
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < 9
					&& !this.moveItemStackTo(stack1, 9, this.slots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if (!this.moveItemStackTo(stack1, 0, 9, false)) {
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

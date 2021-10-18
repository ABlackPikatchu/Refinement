package com.ablackpikatchu.refinement.common.container;

import java.util.Objects;

import com.ablackpikatchu.refinement.common.slot.FuelSlot;
import com.ablackpikatchu.refinement.common.slot.OutputSlot;
import com.ablackpikatchu.refinement.common.slot.itemspecific.UpgradeSlot;
import com.ablackpikatchu.refinement.common.te.machine.AlloySmelterTileEntity;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.util.FunctionalIntReferenceHolder;
import com.ablackpikatchu.refinement.core.util.InventoryUtils;
import com.ablackpikatchu.refinement.core.util.enums.Upgrades;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AlloySmelterContainer extends MachineContainer<AlloySmelterTileEntity> {

	private final IWorldPosCallable canInteractWithCallable;

	public FunctionalIntReferenceHolder currentWaitTime;
	public FunctionalIntReferenceHolder maxWaitTime;

	public FunctionalIntReferenceHolder energyUsed;
	public FunctionalIntReferenceHolder currentEnergy;

	public FunctionalIntReferenceHolder usingEnergy;

	public AlloySmelterContainer(final int windowId, final PlayerInventory playerInv, final AlloySmelterTileEntity te) {
		super(ContainerTypesInit.ALLOY_SMELTER_CONTAINER_TYPE.get(), windowId, te);
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

		this.addSlot(new Slot(te, 0, 23, 25));
		this.addSlot(new Slot(te, 1, 41, 25));
		this.addSlot(new Slot(te, 2, 59, 25));
		this.addSlot(new Slot(te, 3, 77, 25));

		this.addSlot(new OutputSlot(te, 4, 132, 25));

		this.addSlot(new FuelSlot(te, 5, 8, 51));

		this.addSlot(new UpgradeSlot(te, 6, 197, 120, Upgrades.SPEED));
		this.addSlot(new UpgradeSlot(te, 7, 179, 120, Upgrades.AUTO_EJECT));
		this.addSlot(new UpgradeSlot(te, 8, 197, 102, Upgrades.AUTO_IMPORT));
		this.addSlot(new UpgradeSlot(te, 9, 179, 102, Upgrades.ENERGY_ABILITY));

		InventoryUtils.createPlayerSlots(playerInv, 8, 84).forEach(this::addSlot);

		this.addDataSlot(currentWaitTime = new FunctionalIntReferenceHolder(() -> this.te.currentWaitTime,
				value -> this.te.currentWaitTime = value));
		this.addDataSlot(maxWaitTime = new FunctionalIntReferenceHolder(() -> this.te.maxWaitTime,
				value -> this.te.maxWaitTime = value));

		this.addDataSlot(energyUsed = new FunctionalIntReferenceHolder(() -> this.te.energyStorage.energyUsed,
				value -> this.te.energyStorage.energyUsed = value));
		this.addDataSlot(currentEnergy = new FunctionalIntReferenceHolder(() -> this.te.energyStorage.getEnergyStored(),
				value -> this.te.energyStorage.setEnergy(value)));

		this.addDataSlot(usingEnergy = new FunctionalIntReferenceHolder(() -> this.te.getItem(9).getCount(),
				value -> this.te.getItem(9).setCount(value)));
	}

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return stillValid(canInteractWithCallable, p_75145_1_, BlockInit.ALLOY_SMELTER_BLOCK.get());
	}

	public AlloySmelterContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static AlloySmelterTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof AlloySmelterTileEntity) {
			return (AlloySmelterTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		return handleShiftClick(playerIn, index, AlloySmelterTileEntity.slots);
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgressionScaled() {
		return this.currentWaitTime.get() != 0 && this.maxWaitTime.get() != 0
				? this.currentWaitTime.get() * 24 / this.maxWaitTime.get()
				: 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getEnergyScaled() {
		return this.currentEnergy.get() != 0 && this.te.energyStorage.getMaxEnergyStored() != 0
				? this.currentEnergy.get() * 76 / this.te.energyStorage.getMaxEnergyStored()
				: 0;
	}

	public boolean usingEnergy() {
		if (this.usingEnergy.get() == 1)
			return true;
		else
			return false;
	}

}

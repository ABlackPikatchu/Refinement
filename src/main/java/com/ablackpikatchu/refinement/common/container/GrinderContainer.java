package com.ablackpikatchu.refinement.common.container;

import java.util.Objects;

import com.ablackpikatchu.refinement.api.container.MachineContainer;
import com.ablackpikatchu.refinement.common.inventory.slot.FuelSlot;
import com.ablackpikatchu.refinement.common.inventory.slot.OutputSlot;
import com.ablackpikatchu.refinement.common.inventory.slot.RecipeIngredientSlot;
import com.ablackpikatchu.refinement.common.inventory.slot.itemspecific.UpgradeSlot;
import com.ablackpikatchu.refinement.common.recipe.GrinderRecipe;
import com.ablackpikatchu.refinement.common.te.machine.GrinderTileEntity;
import com.ablackpikatchu.refinement.common.te.upgrade.Upgrade;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.util.FunctionalIntReferenceHolder;

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

public class GrinderContainer extends MachineContainer<GrinderTileEntity> {

	private final IWorldPosCallable canInteractWithCallable;
	public FunctionalIntReferenceHolder currentWaitTime;
	public FunctionalIntReferenceHolder maxWaitTime;
	
	public FunctionalIntReferenceHolder security;
	
	public FunctionalIntReferenceHolder energyUsed;
	public FunctionalIntReferenceHolder currentEnergy;

	public FunctionalIntReferenceHolder usingEnergy;
	
	public GrinderContainer(final int windowId, final PlayerInventory playerInv,
			final GrinderTileEntity te) {
		super(ContainerTypesInit.GRINDER_CONTAINER_TYPE.get(), windowId, te);
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

		// Tile Entity
		this.addSlot(new RecipeIngredientSlot<GrinderRecipe>(RecipeInit.GRINDER_RECIPE, te.getLevel(), (IInventory) te, 0, 35, 23)); //Input
		this.addSlot(new OutputSlot((IInventory) te, 1, 125, 23)); //Output
		this.addSlot(new FuelSlot(te, 2, 8, 44)); //Coal
		this.addSlot(new UpgradeSlot((IInventory) te, 3, 197, 113, Upgrade.SPEED)); //Speed Upgrade
		this.addSlot(new UpgradeSlot((IInventory) te, 4, 179, 113, Upgrade.AUTO_EJECT)); // Auto eject upgrade
		this.addSlot(new UpgradeSlot(te, 5, 197, 95, Upgrade.AUTO_IMPORT)); // Auto import upgrade
		this.addSlot(new UpgradeSlot(te, 6, 179, 95, Upgrade.ENERGY_ABILITY)); // Energy Ability upgrade

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
		
		this.addDataSlot(security = new FunctionalIntReferenceHolder(() -> this.te.getSecurityInt(), 
				value -> this.te.setIntSecurity(value)));
		
		this.addDataSlot(energyUsed = new FunctionalIntReferenceHolder(() -> this.te.energyStorage.energyUsed,
				value -> this.te.energyStorage.energyUsed = value));
		this.addDataSlot(currentEnergy = new FunctionalIntReferenceHolder(() -> this.te.energyStorage.getEnergyStored(),
				value -> this.te.energyStorage.setEnergy(value)));

		this.addDataSlot(usingEnergy = new FunctionalIntReferenceHolder(() -> this.te.getItem(6).getCount(),
				value -> this.te.getItem(6).setCount(value)));
	}

	public GrinderContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static GrinderTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof GrinderTileEntity) {
			return (GrinderTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return stillValid(canInteractWithCallable, p_75145_1_, BlockInit.GRINDER.get());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		return handleShiftClick(playerIn, index, GrinderTileEntity.slots);
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgressionScaled() {
		return this.currentWaitTime.get() != 0 && this.maxWaitTime.get() != 0
				? this.currentWaitTime.get() * 59 / this.maxWaitTime.get()
				: 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getEnergyScaled() {
		return this.currentEnergy.get() != 0 && this.te.energyStorage.getMaxEnergyStored() != 0
				? this.currentEnergy.get() * 69 / this.te.energyStorage.getMaxEnergyStored()
				: 0;
	}

	public boolean usingEnergy() {
		if (this.usingEnergy.get() == 1)
			return true;
		else
			return false;
	}
}
package com.ablackpikatchu.refinement.api.te;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.common.te.security.SecurityType;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.util.energy.ModEnergyStorage;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class MachineTileEntity extends SidedInventoryTileEntity {

	protected boolean usingEnergy;
	public ModEnergyStorage energyStorage;
	public boolean hasPower = false;
	protected final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

	protected boolean isWorking;

	protected MachineTileEntity(TileEntityType<?> typeIn, int inventorySize) {
		super(typeIn, inventorySize);
	}

	public boolean isUsingEnergy() { return usingEnergy; }

	public boolean hasCarbon(int carbonSlot) {
		return this.getItem(carbonSlot).getItem() == ItemInit.REFINED_CARBON_INGOT.get()
				&& this.getItem(carbonSlot).getCount() >= usedCarbon;
	}

	protected boolean hasEnoughPowerToWork() {
		return energyStorage.getEnergyStored() >= energyStorage.energyUsed;
	}

	public boolean canWork() {
		if (usingEnergy)
			return hasEnoughPowerToWork();
		else
			return hasCarbon(getFuelSlot());
	}

	public void consumeFuel() {
		if (usingEnergy) {
			energyStorage.useEnergy();
		} else {
			this.getItem(getFuelSlot()).shrink(usedCarbon);
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityEnergy.ENERGY && usingEnergy && energyStorage != null)
			return energy.cast();
		return super.getCapability(cap, side);
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		energy.invalidate();
	}

	public void handleEnergyAbilityUpgrade(int energyAbilityUpgradeSlot) {
		if (this.getItem(energyAbilityUpgradeSlot).getItem() == ItemInit.ENERGY_ABILITY_UPGRADE.get()) {
			if (!usingEnergy) {
				usingEnergy = true;
				TileEntityHelper.updateTE(this);
			}
		} else {
			if (usingEnergy) {
				usingEnergy = false;
				TileEntityHelper.updateTE(this);
			}
		}
	}

	@Nullable
	public int getFuelSlot() { return -1; }

	@Override
	public CompoundNBT save(CompoundNBT tags) {
		tags.putBoolean("UsingEnergy", usingEnergy);
		if (energyStorage != null) {
			tags.putInt("energyStored", energyStorage.getEnergyStored());
		}
		if (ownerUUID != null) {
			tags.putUUID("owner", ownerUUID);
		}
		// if (security != null)
		// tags.putString("security", security.getName());
		return super.save(tags);
	}

	@Override
	public void load(BlockState stateIn, CompoundNBT tags) {
		super.load(stateIn, tags);
		usingEnergy = tags.getBoolean("UsingEnergy");
		if (energyStorage != null) {
			energyStorage.setEnergy(tags.getInt("energyStored"));
		}
		if (tags.hasUUID("owner")) {
			ownerUUID = tags.getUUID("owner");
			// security = SecurityType.byName(tags.getString("security"));
		}
	}

	public void setIntSecurity(int sec) {
		switch (sec) {
		case 0:
			security = SecurityType.PUBLIC;
		case 1:
			security = SecurityType.PRIVATE;
		}
	}

	public int getSecurityInt() {
		if (security == SecurityType.PRIVATE)
			return 1;
		else
			return 0;
	}

}

package com.ablackpikatchu.refinement.common.energy_cable;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.energy.ModEnergyStorage;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyCableTileEntity extends TileEntity implements ITickableTileEntity {

	public final ModEnergyStorage energyStorage = createEnergy();
	private boolean hasPower = false;
	private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

	private ModEnergyStorage createEnergy() {
		return new ModEnergyStorage(100000, 1000) {
			@Override
			protected void onEnergyChanged() {
				boolean newHasPower = hasEnoughPowerToWork();
				if (newHasPower != hasPower) {
					hasPower = newHasPower;
				}
				setChanged();
			}
		};
	}

	private boolean hasEnoughPowerToWork() {
		return energyStorage.getEnergyStored() >= energyStorage.energyUsed;
	}

	public EnergyCableTileEntity() {
		super(TileEntityTypesInit.ENERGY_CABLE_TILE_ENTITY_TYPE.get());
	}

	@Override
	public void tick() {
		if (!this.level.isClientSide) {
			sendOutPower(this.energyStorage, Direction.values());
			setConnections(Direction.values());
		}
	}

	public void sendOutPower(ModEnergyStorage energyStorage, Direction... directions) {
		AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
		if (capacity.get() > 0) {
			for (Direction direction : directions) {
				TileEntity te = level.getBlockEntity(worldPosition.relative(direction));
				if (te != null) {
					boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite())
							.map(handler -> {
								if (handler.canReceive()) {
									int received = handler.receiveEnergy(
											Math.min(capacity.get(), energyStorage.getMaxExtract()), false);
									capacity.addAndGet(-received);
									energyStorage.consumeEnergy(received);
									TileEntityHelper.updateTE(this);
									TileEntityHelper.updateTE(te);
									return capacity.get() > 0;
								} else {
									return true;
								}
							}).orElse(true);
					if (!doContinue)
						return;
				}
			}
		}
	}

	public void setConnections(Direction... directions) {
		for (Direction direction : directions) {
			TileEntity te = this.level.getBlockEntity(worldPosition.relative(direction, 1));
			if (te != null) {
				IEnergyStorage handler = te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).orElse(null);
				if (handler != null && handler.canReceive())
					setConnection(direction, true);
				else setConnection(direction, false);
			} else
				setConnection(direction, false);
		}
	}

	public void setConnection(Direction direction, boolean state) {
		switch (direction) {
		case UP:
			TileEntityHelper.setStateProperty(this, EnergyCableBlock.UP, state);
		case DOWN:
			TileEntityHelper.setStateProperty(this, EnergyCableBlock.DOWN, state);
		case NORTH:
			TileEntityHelper.setStateProperty(this, EnergyCableBlock.NORTH, state);
		case SOUTH:
			TileEntityHelper.setStateProperty(this, EnergyCableBlock.SOUTH, state);
		case EAST:
			TileEntityHelper.setStateProperty(this, EnergyCableBlock.EAST, state);
		case WEST:
			TileEntityHelper.setStateProperty(this, EnergyCableBlock.WEST, state);
		}
	}

	@Override
	public void load(BlockState stateIn, CompoundNBT tags) {
		energyStorage.setEnergy(tags.getInt("energyStored"));
		super.load(stateIn, tags);
	}

	@Override
	public CompoundNBT save(CompoundNBT tags) {
		tags.putInt("energyStored", energyStorage.getEnergyStored());
		super.save(tags);
		return tags;
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		energy.invalidate();
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityEnergy.ENERGY) {
			return energy.cast();
		}
		return super.getCapability(cap, side);
	}

}

package com.ablackpikatchu.refinement.core.util.energy;

import java.util.concurrent.atomic.AtomicInteger;

import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

public class ModEnergyStorage extends EnergyStorage {

    public int energyUsed;
    public int energyMade;

    public ModEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public int getMaxReceive() {
        return this.maxReceive;
    }

    public int getMaxExtract() {
        return this.maxExtract;
    }

    protected void onEnergyChanged() {

    }

    public boolean canReceive(int amount) {
        return this.energy + amount <= this.capacity;
    }

    public boolean canMakeEnergy() {
        return canReceive(this.energyMade);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        onEnergyChanged();
    }

    public void addEnergy(int energy) {
        if (this.energy + energy > getMaxEnergyStored()) return;
        this.energy += energy;
        if (this.energy > getMaxEnergyStored()) {
            this.energy = getEnergyStored();
        }
        onEnergyChanged();
    }

    public void consumeEnergy(int energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
        onEnergyChanged();
    }

    public void useEnergy() {
        this.energy -= energyUsed;
        if (this.energy < 0) {
            this.energy = 0;
        }
        onEnergyChanged();
    }

    public void makeEnergy() {
        this.addEnergy(this.energyMade);
    }
    
    public void sendOutPower(World level, BlockPos position, Direction... directions) {
		AtomicInteger capacity = new AtomicInteger(getEnergyStored());
		if (capacity.get() > 0) {
			for (Direction direction : directions) {
				TileEntity te = level.getBlockEntity(position.relative(direction));
				if (te != null) {
					boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite())
							.map(handler -> {
								if (handler.canReceive()) {
									int received = handler.receiveEnergy(
											Math.min(capacity.get(), getMaxExtract()), false);
									capacity.addAndGet(-received);
									consumeEnergy(received);
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
    
    public CompoundNBT serializeNBT() {
    	CompoundNBT nbt = new CompoundNBT();
    	nbt.putInt("EnergyStored", getEnergyStored());
    	return nbt;
    }
    
    public void deserializeNBT(CompoundNBT nbt) {
    	this.energy = nbt.getInt("EnergyStored");
    }

}

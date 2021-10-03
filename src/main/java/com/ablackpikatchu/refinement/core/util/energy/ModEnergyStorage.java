package com.ablackpikatchu.refinement.core.util.energy;

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

}

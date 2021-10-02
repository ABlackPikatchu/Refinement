package com.ablackpikatchu.refinement.core.util.energy;

import net.minecraftforge.energy.EnergyStorage;

public class ModEnergyStorage extends EnergyStorage {
	
	public int energyUsed;

	public ModEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }
	
	public int getMaxRecive() {
		return this.maxReceive;
	}

    protected void onEnergyChanged() {

    }

    public void setEnergy(int energy) {
        this.energy = energy;
        onEnergyChanged();
    }

    public void addEnergy(int energy) {
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
	
}

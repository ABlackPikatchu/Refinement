package com.ablackpikatchu.refinement.core.config.entry;

import javax.annotation.Nullable;

import com.google.gson.annotations.Expose;

public class ItemEntry {
	
	@Expose public String ITEM;
    @Expose public int AMOUNT;
    @Expose public String NBT;

    public ItemEntry(String item, int amount, @Nullable String nbt) {
        this.ITEM = item;
        this.AMOUNT = amount;
        this.NBT = nbt;
    }

}

package com.ablackpikatchu.refinement.core.config.entry;

import java.util.List;

import com.google.gson.annotations.Expose;

public class ModLootBoxEntry {
	
	@Expose
	public String modid;
	@Expose
	public List<WeightBasedItemEntry> pools;
	
	public ModLootBoxEntry(String modid, List<WeightBasedItemEntry> pools) {
		this.modid = modid;
		this.pools = pools;
	}

}

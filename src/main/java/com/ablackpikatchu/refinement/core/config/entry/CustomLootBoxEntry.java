package com.ablackpikatchu.refinement.core.config.entry;

import java.util.List;

import com.google.gson.annotations.Expose;

public class CustomLootBoxEntry {
	
	@Expose
	public String poolName;
	@Expose
	public List<WeightBasedItemEntry> pools;
	
	public CustomLootBoxEntry(String poolName, List<WeightBasedItemEntry> pools) {
		this.poolName = poolName;
		this.pools = pools;
	}

}

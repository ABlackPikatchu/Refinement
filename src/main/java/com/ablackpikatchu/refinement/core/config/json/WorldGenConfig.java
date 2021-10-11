package com.ablackpikatchu.refinement.core.config.json;

import java.util.LinkedList;
import java.util.List;

import com.ablackpikatchu.refinement.core.config.entry.OreEntry;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.google.gson.annotations.Expose;

public class WorldGenConfig extends JsonConfig {
	
	@Expose
	private List<OreEntry> ORE_GEN;

	@Override
	protected void reset() {
		this.ORE_GEN = new LinkedList<>();
		ORE_GEN.add(new OreEntry(BlockInit.PURE_CRYSTAL_ORE.get(), 6, 4, 20, OreEntry.Type.NATURAL_STONE));
		ORE_GEN.add(new OreEntry(BlockInit.BLANK_ORE.get(), 2, 5, 25, OreEntry.Type.NATURAL_STONE));
	}
	
	public List<OreEntry> getOreGen() {
		return this.ORE_GEN;
	}
	
	@Override
	public String getName() {
		return "world_gen";
	}

}

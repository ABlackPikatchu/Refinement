package com.ablackpikatchu.refinement.core.config.json;

import java.util.LinkedList;
import java.util.List;

import com.ablackpikatchu.refinement.core.config.entry.TradeEntry;
import com.google.gson.annotations.Expose;

public class MaterialistTrades extends JsonConfig {
	
	@Expose
	private List<TradeEntry> LEVEL_1;

	@Override
	public String getName() {
		return "materialist_trades";
	}

	@Override
	protected void reset() {
		this.LEVEL_1 = new LinkedList<>();
		
		this.LEVEL_1.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "minecraft:golden_apple", null, 12, 13, 14, 12, 0.05f));
	}
	
	public List<TradeEntry> getLevel1() {
		return this.LEVEL_1;
	}

}

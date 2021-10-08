package com.ablackpikatchu.refinement.core.config.json;

import java.util.LinkedList;
import java.util.List;

import com.ablackpikatchu.refinement.core.config.entry.TradeEntry;
import com.google.gson.annotations.Expose;

public class MaterialistTrades extends JsonConfig {
	
	@Expose
	private List<TradeEntry> LEVEL_1;
	@Expose
	private List<TradeEntry> LEVEL_2;
	@Expose
	private List<TradeEntry> LEVEL_3;
	@Expose
	private List<TradeEntry> LEVEL_4;
	@Expose
	private List<TradeEntry> LEVEL_5;

	@Override
	public String getName() {
		return "materialist_trades";
	}

	@Override
	protected void reset() {
		this.LEVEL_1 = new LinkedList<>();
		this.LEVEL_2 = new LinkedList<>();
		this.LEVEL_3 = new LinkedList<>();
		this.LEVEL_4 = new LinkedList<>();
		this.LEVEL_5 = new LinkedList<>();
		
		this.LEVEL_1.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "minecraft:golden_apple", null, 12, 13, 14, 12, 0.05f));
		this.LEVEL_2.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "minecraft:golden_apple", null, 12, 13, 14, 12, 0.05f));
		this.LEVEL_3.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "minecraft:golden_apple", null, 12, 13, 14, 12, 0.05f));
		this.LEVEL_4.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "minecraft:golden_apple", null, 12, 13, 14, 12, 0.05f));
		this.LEVEL_5.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "minecraft:golden_apple", null, 12, 13, 14, 12, 0.05f));
	}
	
	public List<TradeEntry> getLevel1() {
		return this.LEVEL_1;
	}
	public List<TradeEntry> getLevel2() {
		return this.LEVEL_2;
	}
	public List<TradeEntry> getLevel3() {
		return this.LEVEL_3;
	}
	public List<TradeEntry> getLevel4() {
		return this.LEVEL_4;
	}
	public List<TradeEntry> getLevel5() {
		return this.LEVEL_5;
	}

}

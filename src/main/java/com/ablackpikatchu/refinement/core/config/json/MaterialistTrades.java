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
		
		this.LEVEL_1.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "refinement:refined_iron_ingot", null, 3, 13, 14, 12, 0.05f));
		this.LEVEL_1.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "refinement:refined_gold_ingot", null, 2, 13, 14, 12, 0.05f));
		
		this.LEVEL_2.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "refinement:refined_bonemeal", null, 6, 13, 14, 12, 0.05f));
		this.LEVEL_2.add(new TradeEntry(TradeEntry.Type.ITEMS_TO_EMERALDS, "refinement:pure_crystal", null, 6, 1, 14, 12, 0.05f));
		
		this.LEVEL_3.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "refinement:machine_parts", null, 4, 13, 14, 7, 0.05f));
		this.LEVEL_3.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "refinement:food_box", null, 1, 25, 14, 3, 0.05f));
		
		this.LEVEL_4.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "refinement:miners_bread", null, 4, 12, 14, 5, 0.05f));
		this.LEVEL_4.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "refinement:miners_stew", null, 1, 9, 14, 5, 0.05f));
		
		this.LEVEL_5.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "refinement:speed_upgrade", null, 2, 16, 14, 2, 0.05f));
		this.LEVEL_5.add(new TradeEntry(TradeEntry.Type.EMERALDS_TO_ITEMS, "refinement:blank_ore", null, 3, 9, 14, 4, 0.05f));
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

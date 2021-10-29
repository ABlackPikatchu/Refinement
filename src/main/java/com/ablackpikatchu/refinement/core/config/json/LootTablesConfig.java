package com.ablackpikatchu.refinement.core.config.json;

import java.util.LinkedList;
import java.util.List;

import com.ablackpikatchu.refinement.api.config.json.JsonConfig;
import com.ablackpikatchu.refinement.core.config.entry.LootTableEntry;
import com.google.gson.annotations.Expose;

public class LootTablesConfig extends JsonConfig {

	@Expose
	public List<LootTableEntry> ADD_TO_LOOT_TABLES;

	@Override
	public String getName() {
		return "loot_tables";
	}

	@Override
	protected void reset() {
		this.ADD_TO_LOOT_TABLES = new LinkedList<>();
		this.ADD_TO_LOOT_TABLES.add(new LootTableEntry("minecraft:chests/end_city_treasure",
				"refinement:resource_statue", new LootTableEntry.RandomValueLootTable(0, 1)));
	}
	
	public List<LootTableEntry> getToAddLootTables() {
		return this.ADD_TO_LOOT_TABLES;
	}

}

package com.ablackpikatchu.refinement.core.config;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.config.json.LootBoxConfig;
import com.ablackpikatchu.refinement.core.config.json.ResourceStatueConfig;

public class ModJsonConfigs {
	
	public static ResourceStatueConfig RESOURCE_STATUE;
	public static LootBoxConfig LOOT_BOXES;
	
	public static void register() {
		RESOURCE_STATUE = (ResourceStatueConfig) new ResourceStatueConfig().readConfig();
		LOOT_BOXES = (LootBoxConfig) new LootBoxConfig().readConfig();
		
		Refinement.LOGGER.info("JSON Configs Loaded!");
	}

}

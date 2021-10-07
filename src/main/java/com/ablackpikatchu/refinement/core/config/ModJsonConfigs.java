package com.ablackpikatchu.refinement.core.config;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.config.json.ArmourConfig;
import com.ablackpikatchu.refinement.core.config.json.LootBoxConfig;
import com.ablackpikatchu.refinement.core.config.json.OreConversionConfig;
import com.ablackpikatchu.refinement.core.config.json.ResourceStatueConfig;
import com.ablackpikatchu.refinement.core.config.json.ToolsConfig;

public class ModJsonConfigs {

	public static ResourceStatueConfig RESOURCE_STATUE;
	public static LootBoxConfig LOOT_BOXES;
	public static OreConversionConfig ORE_CONVERSION;

	public static ArmourConfig ARMOUR;
	public static ToolsConfig TOOLS;

	public static void register() {
		RESOURCE_STATUE = (ResourceStatueConfig) new ResourceStatueConfig().readConfig();
		LOOT_BOXES = (LootBoxConfig) new LootBoxConfig().readConfig();
		ORE_CONVERSION = (OreConversionConfig) new OreConversionConfig().readConfig();

		Refinement.LOGGER.info("JSON Configs Loaded!");
	}

	public static void registerBeforeRegistries() {
		ARMOUR = (ArmourConfig) new ArmourConfig().readConfig();
		TOOLS = (ToolsConfig) new ToolsConfig().readConfig();

		Refinement.LOGGER.info("Before regsitries JSON Configs Loaded!");
	}

}

package com.ablackpikatchu.refinement.core.config;

import com.ablackpikatchu.refinement.core.config.json.ResourceStatueConfig;

public class ModJsonConfigs {
	
	public static ResourceStatueConfig RESOURCE_STATUE;
	
	public static void register() {
		RESOURCE_STATUE = (ResourceStatueConfig) new ResourceStatueConfig().readConfig();
	}

}

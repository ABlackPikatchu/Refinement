package com.ablackpikatchu.refinement.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	//Quotes
	public static final ForgeConfigSpec.ConfigValue<Boolean> QUOTES_ENABLED;
	
	static {
		
		BUILDER.push("Quotes_Config");
		
		QUOTES_ENABLED = BUILDER.comment("If the quote system should be enabled. (Default value is true)").define("quotesEnabled", true);
		
		BUILDER.pop();
		SPEC = BUILDER.build();
		
	}
}

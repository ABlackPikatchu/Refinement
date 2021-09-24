package com.ablackpikatchu.refinement.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	//GUI
	public static final ForgeConfigSpec.ConfigValue<Boolean> SUSSY_GUI;
	
	static {
		
		BUILDER.push("GUI_Config");
		
		SUSSY_GUI = BUILDER.comment("If true, the Refinement GUIs will become sussy!").define("Sussy GUIs", false);
		
		BUILDER.pop();
		SPEC = BUILDER.build();
		
	}
}

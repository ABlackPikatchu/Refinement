package com.ablackpikatchu.refinement.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
	
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	//Grinder
	public static final ForgeConfigSpec.ConfigValue<Integer> GRINDER_DEFAULT_PROCESS_TIME;
	
	//Mixer
	public static final ForgeConfigSpec.ConfigValue<Integer> MIXER_DEFAULT_PROCESS_TIME;
	
	static {
		
		BUILDER.push("Machine Config");
		
		
		BUILDER.push("Grinder");
		GRINDER_DEFAULT_PROCESS_TIME = BUILDER.comment("The default process time for the Grinder, in ticks. (with no speed upgrades) (Default value is 200)").define("Default Process Time", 200);
		BUILDER.pop();
		
		BUILDER.push("Mixer");
		MIXER_DEFAULT_PROCESS_TIME = BUILDER.comment("The default process time for the Mixer, in ticks. (with no speed upgrades) (Default value is 300)").define("Default Process Time", 300);
		
		
		BUILDER.pop();
		SPEC = BUILDER.build();
		
	}
}

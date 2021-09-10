package com.ablackpikatchu.refinement.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
	
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	//Grinder
	public static final ForgeConfigSpec.ConfigValue<Integer> GRINDER_DEFAULT_PROCESS_TIME;
	public static final ForgeConfigSpec.ConfigValue<Integer> GRINDER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;
	
	//Mixer
	public static final ForgeConfigSpec.ConfigValue<Integer> MIXER_DEFAULT_PROCESS_TIME;
	public static final ForgeConfigSpec.ConfigValue<Integer> MIXER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;
	
	//Mold Press
	//Grinder
		public static final ForgeConfigSpec.ConfigValue<Integer> MOLD_PRESS_DEFAULT_PROCESS_TIME;
		public static final ForgeConfigSpec.ConfigValue<Integer> MOLD_PRESS_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;
	
	static {
		
		BUILDER.push("Machine Config");
		
		
		BUILDER.push("Grinder");
		GRINDER_DEFAULT_PROCESS_TIME = BUILDER.comment("The default process time for the Grinder, in ticks. (with no speed upgrades) (Default value is 200)").define("Default Process Time", 200);
		GRINDER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment("The ticks amount each speed upgrades decreses the process time of the Grinder. (Default value is 15)").define("Speed Upgrade Time Decreased", 15);
		BUILDER.pop();
		
		BUILDER.push("Mixer");
		MIXER_DEFAULT_PROCESS_TIME = BUILDER.comment("The default process time for the Mixer, in ticks. (with no speed upgrades) (Default value is 300)").define("Default Process Time", 300);
		MIXER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment("The ticks amount each speed upgrades decreses the process time of the Mixer. (Default value is 10)").define("Speed Upgrade Time Decreased", 10);
		BUILDER.pop();
		
		BUILDER.push("Mold Press");
		MOLD_PRESS_DEFAULT_PROCESS_TIME = BUILDER.comment("The default process time for the Mold Press, in ticks. (with no speed upgrades) (Default value is 200)").define("Default Process Time", 200);
		MOLD_PRESS_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment("The ticks amount each speed upgrades decreses the process time of the Mixer. (Default value is 15)").define("Speed Upgrade Time Decreased", 15);
		BUILDER.pop();
		
		BUILDER.pop();
		SPEC = BUILDER.build();
		
	}
}

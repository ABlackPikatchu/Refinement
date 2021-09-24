package com.ablackpikatchu.refinement.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {

	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	// Grinder
	public static final ForgeConfigSpec.ConfigValue<Integer> GRINDER_DEFAULT_PROCESS_TIME;
	public static final ForgeConfigSpec.ConfigValue<Integer> GRINDER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;

	// Mixer
	public static final ForgeConfigSpec.ConfigValue<Integer> MIXER_DEFAULT_PROCESS_TIME;
	public static final ForgeConfigSpec.ConfigValue<Integer> MIXER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;

	// Mold Press
	public static final ForgeConfigSpec.ConfigValue<Integer> MOLD_PRESS_DEFAULT_PROCESS_TIME;
	public static final ForgeConfigSpec.ConfigValue<Integer> MOLD_PRESS_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;

	// Magnet
	public static final ForgeConfigSpec.ConfigValue<Double> MAGNET_RANGE;

	// Vaccumulator
	public static final ForgeConfigSpec.ConfigValue<Double> VACCUMULATOR_RANGE;

	// Conversions
	public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_CONVERSION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> INVENTORY_TRIGGER_CONVERSION;

	// Crops
	public static final ForgeConfigSpec.ConfigValue<Boolean> CROPS_ENABLED;

	static {

		BUILDER.push("Machine_Config");

		BUILDER.push("Grinder");
		GRINDER_DEFAULT_PROCESS_TIME = BUILDER.comment(
				"The default process time for the Grinder, in ticks. (with no speed upgrades) (Default value is 200)")
				.define("Default Process Time", 200);
		GRINDER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment(
				"The ticks amount each speed upgrades decreses the process time of the Grinder. (Default value is 15)")
				.define("Speed Upgrade Time Decreased", 15);
		BUILDER.pop();

		BUILDER.push("Mixer");
		MIXER_DEFAULT_PROCESS_TIME = BUILDER.comment(
				"The default process time for the Mixer, in ticks. (with no speed upgrades) (Default value is 300)")
				.define("Default Process Time", 300);
		MIXER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment(
				"The ticks amount each speed upgrades decreses the process time of the Mixer. (Default value is 10)")
				.define("Speed Upgrade Time Decreased", 10);
		BUILDER.pop();

		BUILDER.push("Mold_Press");
		MOLD_PRESS_DEFAULT_PROCESS_TIME = BUILDER.comment(
				"The default process time for the Mold Press, in ticks. (with no speed upgrades) (Default value is 200)")
				.define("Default Process Time", 200);
		MOLD_PRESS_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment(
				"The ticks amount each speed upgrades decreses the process time of the Mixer. (Default value is 15)")
				.define("Speed Upgrade Time Decreased", 15);
		BUILDER.pop();

		BUILDER.pop();

		BUILDER.push("Conversions");

		ENABLE_CONVERSION = BUILDER.comment("If the ore unfiying should be enabled (Default value is false)")
				.define("Conversion Enabled", false);
		INVENTORY_TRIGGER_CONVERSION = BUILDER.comment(
				"(ONLY IF Conversion Enabled IS TRUE) If the ore unifying should trigger in the player inventory (without pressing the Ore Unify key) (Default value is true)")
				.define("Conversion Inventory Trigger", true);

		BUILDER.pop();

		BUILDER.push("Magnets");

		MAGNET_RANGE = BUILDER.comment("The range of the Magnet (in blocks) (Default value is 7.0)")
				.define("Magnet Range", 7.0);

		VACCUMULATOR_RANGE = BUILDER.comment("The range of the Vaccumulator (in blocks) (Default value is 7.0)")
				.define("Vaccumulator Range", 7.0);

		BUILDER.pop();

		BUILDER.push("Ore_Crops");

		CROPS_ENABLED = BUILDER.comment(
				"If the ore crops should be enabled (if false, their recipe will not exist) (Default value is false)")
				.define("Crops Enabled", false);

		BUILDER.pop();

		SPEC = BUILDER.build();

	}
}

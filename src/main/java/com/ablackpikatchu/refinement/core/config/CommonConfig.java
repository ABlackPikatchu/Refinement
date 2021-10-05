package com.ablackpikatchu.refinement.core.config;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {

	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	// Grinder
	public static ForgeConfigSpec.ConfigValue<Integer> GRINDER_DEFAULT_PROCESS_TIME;
	public static ForgeConfigSpec.ConfigValue<Integer> GRINDER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;

	// Mixer
	public static ForgeConfigSpec.ConfigValue<Integer> MIXER_DEFAULT_PROCESS_TIME;
	public static ForgeConfigSpec.ConfigValue<Integer> MIXER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;

	// Mold Press
	public static ForgeConfigSpec.ConfigValue<Integer> MOLD_PRESS_DEFAULT_PROCESS_TIME;
	public static ForgeConfigSpec.ConfigValue<Integer> MOLD_PRESS_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;

	// DNA Sequencer
	public static ForgeConfigSpec.ConfigValue<Integer> DNA_SEQUENCER_DEFAULT_PROCESS_TIME;
	public static ForgeConfigSpec.ConfigValue<Integer> DNA_SEQUENCER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;

	// Carbon Generator
	public static ForgeConfigSpec.ConfigValue<Integer> CARBON_GENERATOR_DEFAULT_PROCESS_TIME;
	public static ForgeConfigSpec.ConfigValue<Integer> CARBON_GENERATOR_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;
	public static ForgeConfigSpec.ConfigValue<Integer> CARBON_GENERATOR_DEFAULT_ENERGY_USAGE;
	public static ForgeConfigSpec.ConfigValue<Integer> CARBON_GENERATOR_ENERGY_USAGE_PER_SPEED_UPGRADE;
	
	// Smelter
	public static ForgeConfigSpec.ConfigValue<Integer> SMELTER_DEFAULT_PROCESS_TIME;
	public static ForgeConfigSpec.ConfigValue<Integer> SMELTER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;
	public static ForgeConfigSpec.ConfigValue<Integer> SMELTER_DEFAULT_ENERGY_USAGE;
	public static ForgeConfigSpec.ConfigValue<Integer> SMELTER_ENERGY_USAGE_PER_SPEED_UPGRADE;

	// Energy Generator
	public static ForgeConfigSpec.ConfigValue<Integer> ENERGY_GENERATOR_FUEL_LASTING;
	public static ForgeConfigSpec.ConfigValue<Integer> ENERGY_GENERATOR_ENERGY_MADE;

	// Magnet
	public static final ForgeConfigSpec.ConfigValue<Boolean> MAGNET_ENABLED;
	public static final ForgeConfigSpec.ConfigValue<Double> MAGNET_RANGE;

	// Vaccumulator
	public static final ForgeConfigSpec.ConfigValue<Double> VACCUMULATOR_RANGE;

	// Conversions
	public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_CONVERSION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> INVENTORY_TRIGGER_CONVERSION;

	// Crops
	public static final ForgeConfigSpec.ConfigValue<Boolean> CROPS_ENABLED;

	// Armour Upgrading
	public static final ForgeConfigSpec.ConfigValue<List<String>> ARMOUR_UPGRADING_EFFECTS;

	static {

		BUILDER.push("Machine_Config");

		addGrinderConfig();
		addMixerConfig();
		addMoldPressConfig();
		addDNASequencerConfig();
		addCarbonGeneratorConfig();
		addEnergyGeneratorConfig();
		addSmelterConfig();

		BUILDER.pop();

		BUILDER.push("Conversions");

		ENABLE_CONVERSION = BUILDER.comment("If the ore unifying should be enabled (Default value is false)")
				.define("Conversion Enabled", false);
		INVENTORY_TRIGGER_CONVERSION = BUILDER.comment(
				"(ONLY IF Conversion Enabled IS TRUE) If the ore unifying should trigger in the player inventory (without pressing the Ore Unify key) (Default value is true)")
				.define("Conversion Inventory Trigger", true);

		BUILDER.pop();

		BUILDER.push("Magnets");

		MAGNET_ENABLED = BUILDER.comment("If the magnet and the vacuumulator should be enabled (Default value is true)")
				.define("Magnet Enabled", true);

		MAGNET_RANGE = BUILDER.comment("The range of the Magnet (in blocks) (Default value is 7.0)")
				.define("Magnet Range", 7.0);

		VACCUMULATOR_RANGE = BUILDER.comment("The range of the Vacuumulator (in blocks) (Default value is 7.0)")
				.define("Vacuumulator Range", 7.0);

		BUILDER.pop();

		BUILDER.push("Ore_Crops");

		CROPS_ENABLED = BUILDER.comment(
				"If the ore crops should be enabled (if false, their recipe will not exist, and they will not show up in the creative tabs, nor in JEI) (Default value is false)")
				.define("Crops Enabled", false);

		BUILDER.pop();

		BUILDER.push("Armour_Upgrading");

		ARMOUR_UPGRADING_EFFECTS = BUILDER
				.comment("The possible effects that an armour piece can recipe when upgraded.")
				.define("Upgrading Effects", Lists.newArrayList("minecraft:night_vision", "minecraft:haste",
						"minecraft:water_breathing", "refinement:negate_fall"));

		BUILDER.pop();

		SPEC = BUILDER.build();

	}

	public static void addGrinderConfig() {
		BUILDER.push("Grinder");
		GRINDER_DEFAULT_PROCESS_TIME = BUILDER.comment(
				"The default process of the Grinder, in ticks. (with no speed upgrades) (Default value is 200)")
				.define("Default Process Time", 200);
		GRINDER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment(
				"The ticks amount each speed upgrades decreases from the process time of the Grinder. (Default value is 15)")
				.define("Speed Upgrade Time Decreased", 15);
		BUILDER.pop();
	}

	public static void addMixerConfig() {
		BUILDER.push("Mixer");
		MIXER_DEFAULT_PROCESS_TIME = BUILDER.comment(
				"The default process of the Mixer, in ticks. (with no speed upgrades) (Default value is 300)")
				.define("Default Process Time", 300);
		MIXER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment(
				"The ticks amount each speed upgrades decreases from the process time of the Mixer. (Default value is 10)")
				.define("Speed Upgrade Time Decreased", 10);
		BUILDER.pop();
	}

	public static void addMoldPressConfig() {
		BUILDER.push("Mold_Press");
		MOLD_PRESS_DEFAULT_PROCESS_TIME = BUILDER.comment(
				"The default process of the Mold Press, in ticks. (with no speed upgrades) (Default value is 200)")
				.define("Default Process Time", 200);
		MOLD_PRESS_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment(
				"The ticks amount each speed upgrades decreases from the process time of the Mold Press. (Default value is 15)")
				.define("Speed Upgrade Time Decreased", 15);
		BUILDER.pop();
	}

	public static void addDNASequencerConfig() {
		BUILDER.push("DNA_Sequencer");
		DNA_SEQUENCER_DEFAULT_PROCESS_TIME = BUILDER.comment(
				"The default process of the DNA Sequencer, in ticks. (with no speed upgrades) (Default value is 400)")
				.define("Default Process Time", 400);
		DNA_SEQUENCER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment(
				"The ticks amount each speed upgrades decreases from the process time of the DNA Sequencer. (Default value is 10)")
				.define("Speed Upgrade Time Decreased", 10);
		BUILDER.pop();
	}

	public static void addCarbonGeneratorConfig() {
		BUILDER.push("Carbon_Generator");

		CARBON_GENERATOR_DEFAULT_PROCESS_TIME = BUILDER.comment(
				"The default process of the Carbon Generator, in ticks. (with no speed upgrades) (Default value is 250)")
				.define("Default Process Time", 250);
		CARBON_GENERATOR_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment(
				"The ticks amount each speed upgrades decreases from the process time of the Carbon Generator. (Default value is 12)")
				.define("Speed Upgrade Time Decreased", 12);
		CARBON_GENERATOR_DEFAULT_ENERGY_USAGE = BUILDER.comment(
				"The default energy usage (FE/tick) of the Carbon Generator, per tick. (with no speed upgrades) (Default value is 50)")
				.define("Default Energy Usage", 50);
		CARBON_GENERATOR_ENERGY_USAGE_PER_SPEED_UPGRADE = BUILDER.comment(
				"The amount of FE/tick the Carbon Generator furthermore consumes for each speed upgrade. (Default value is 10)")
				.define("Speed Upgrade Energy Usage", 10);

		BUILDER.pop();
	}

	public static void addEnergyGeneratorConfig() {
		BUILDER.push("Energy_Generator");

		ENERGY_GENERATOR_FUEL_LASTING = BUILDER.comment(
				"The lasting of fuel in the Energy Generator, int ticks. (With no speed upgrades) (Default value is 200)")
				.define("Default Process Time", 200);
		ENERGY_GENERATOR_ENERGY_MADE = BUILDER.comment("The energy made by the Energy Generator (per tick)")
				.define("Energy Made", 50);

		BUILDER.pop();
	}
	
	public static void addSmelterConfig() {
		BUILDER.push("Smelter");

		SMELTER_DEFAULT_PROCESS_TIME = BUILDER.comment(
				"The default process of the Smelter, in ticks. (with no speed upgrades) (Default value is 150)")
				.define("Default Process Time", 150);
		SMELTER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = BUILDER.comment(
				"The ticks amount each speed upgrades decreases from the process time of the Smelter. (Default value is 10)")
				.define("Speed Upgrade Time Decreased", 10);
		SMELTER_DEFAULT_ENERGY_USAGE = BUILDER.comment(
				"The default energy usage (FE/tick) of the Smelter, per tick. (with no speed upgrades) (Default value is 40)")
				.define("Default Energy Usage", 40);
		SMELTER_ENERGY_USAGE_PER_SPEED_UPGRADE = BUILDER.comment(
				"The amount of FE/tick the Smelter furthermore consumes for each speed upgrade. (Default value is 10)")
				.define("Speed Upgrade Energy Usage", 10);

		BUILDER.pop();
	}

}

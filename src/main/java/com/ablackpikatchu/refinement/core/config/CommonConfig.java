package com.ablackpikatchu.refinement.core.config;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class CommonConfig {

	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	// Grinder
	public static ForgeConfigSpec.ConfigValue<Integer> GRINDER_DEFAULT_PROCESS_TIME;
	public static ForgeConfigSpec.ConfigValue<Integer> GRINDER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;
	public static ConfigValue<Boolean> GRINDER_ENERGY_ABILITY_COMPATIBLE;

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
	public static final ConfigValue<Boolean> MAGNET_TAKES_DAMAGE;
	public static final ConfigValue<Boolean> MAGNET_IGNORES_THROWER;

	// Vacuumulator
	public static final ForgeConfigSpec.ConfigValue<Double> VACCUMULATOR_RANGE;

	// Conversions
	public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_CONVERSION;
	public static final ForgeConfigSpec.ConfigValue<Boolean> INVENTORY_TRIGGER_CONVERSION;

	// Crops
	public static final ForgeConfigSpec.ConfigValue<Boolean> CROPS_ENABLED;

	// Armour Upgrading
	public static final ForgeConfigSpec.ConfigValue<List<String>> ARMOUR_UPGRADING_EFFECTS;

	// Resource Statues
	public static final ForgeConfigSpec.ConfigValue<Integer> RESOURCE_STATUES_PRODUCE_TIME;

	//@formatter:off
	static {

		BUILDER.push("MachineConfig");
		addGrinderConfig();
		addMixerConfig();
		addMoldPressConfig();
		addDNASequencerConfig();
		addAlloySmelterConfig();
		addCarbonGeneratorConfig();
		addEnergyGeneratorConfig();
		addEnergyTransmitterConfig();
		addSmelterConfig();
		BUILDER.pop();

		BUILDER.push("Conversions");
		ENABLE_CONVERSION = config("If the ore unifying should be enabled.", "conversionEnabled", false);
		INVENTORY_TRIGGER_CONVERSION = config("(ONLY IF `conversionEnabled` IS TRUE) If the ore unifying should trigger in the player inventory (without pressing the Ore Unify key)", "conversionInventoryTrigger", true);
		BUILDER.pop();

		BUILDER.push("Magnets");
		MAGNET_ENABLED = config("If the magnet and the vacuumulator should be enabled.", "magnetEnabled", true);
		MAGNET_RANGE = config("The range of the Magnet (in blocks)", "magnetRange", 7.0);
		MAGNET_TAKES_DAMAGE = config("If the magnet should be damaged when it attracts an item.", "magnetTakesDamage", true);
		MAGNET_IGNORES_THROWER = config("If true, the magnet will still attract an item even though the player holding the magnet, recently threw said item. (the player has pickup cooldown on the item)","magnetIgnoresThrower", false);
		VACCUMULATOR_RANGE = config("The range of the Vacuumulator (in blocks)", "vacuumulatorRange", 7.0);
		BUILDER.pop();

		BUILDER.push("OreCrops");
		CROPS_ENABLED = config("If the ore crops should be enabled (if false, their recipe will not exist, and they will not show up in the creative tabs, nor in JEI)", "cropsEnabled", false);
		BUILDER.pop();

		BUILDER.push("ArmourUpgrading");
		ARMOUR_UPGRADING_EFFECTS = config("The possible effects that an armour piece can recipe when upgraded.", "upgradingEffects", Lists.newArrayList("minecraft:night_vision", "minecraft:haste", 
				"minecraft:water_breathing", "refinement:negate_fall"));
		BUILDER.pop();

		BUILDER.push("ResourceStatues");
		RESOURCE_STATUES_PRODUCE_TIME = config("The time a Resource Statue needs in order to produce one item, in ticks.", "producingTime", 350);
		BUILDER.pop();

		SPEC = BUILDER.build();

	}

	public static ForgeConfigSpec.ConfigValue<Integer> GRINDER_ENERGY_USAGE_PER_SPEED_UPGRADE;
	public static ForgeConfigSpec.ConfigValue<Integer> GRINDER_DEFAULT_ENERGY_USAGE;

	public static void addGrinderConfig() {
		BUILDER.push("Grinder");
		GRINDER_DEFAULT_PROCESS_TIME = config("The default process of the Grinder, in ticks. (with no speed upgrades)", "defaultProcessTime", 200);
		GRINDER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = config("The ticks amount each speed upgrades decreases from the process time of the Grinder.", "speedUpgradeTimeDecreased", 15);
		GRINDER_ENERGY_ABILITY_COMPATIBLE = config("If the Grinder is compatible with the Energy Ability Upgrade", "energyAbilityCompatible", true);
		GRINDER_ENERGY_USAGE_PER_SPEED_UPGRADE = config("(Only for Grinders that have the Energy Ability Upgrade) The default energy usage (FE/tick) of the Grinder, per tick. (with no speed upgrades)", "defaultEnergyUsage", 7);
		GRINDER_DEFAULT_ENERGY_USAGE = config("(Only for Grinders that have the Energy Ability Upgrade) The amount of FE/tick the Grinder furthermore consumes for each speed upgrade.", "speedUpgradeEnergyUsage", 55);
		BUILDER.pop();
	}

	public static void addMixerConfig() {
		BUILDER.push("Mixer");
		MIXER_DEFAULT_PROCESS_TIME = config("The default process of the Mixer, in ticks. (with no speed upgrades)", "defaultProcessTime", 300);
		MIXER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = config("The ticks amount each speed upgrades decreases from the process time of the Mixer.", "speedUpgradeTimeDecreased", 10);
		BUILDER.pop();
	}

	public static void addMoldPressConfig() {
		BUILDER.push("MoldPress");
		MOLD_PRESS_DEFAULT_PROCESS_TIME = config("The default process of the Mold Press, in ticks. (with no speed upgrades)", "defaultProcessTime", 200);
		MOLD_PRESS_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = config("The ticks amount each speed upgrades decreases from the process time of the Mold Press.", "speedUpgradeTimeDecreased", 15);
		BUILDER.pop();
	}

	public static void addDNASequencerConfig() {
		BUILDER.push("DNA_Sequencer");
		DNA_SEQUENCER_DEFAULT_PROCESS_TIME = config("The default process of the DNA Sequencer, in ticks. (with no speed upgrades)", "defaultProcessTime", 400);
		DNA_SEQUENCER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = config("The ticks amount each speed upgrades decreases from the process time of the DNA Sequencer.", "speedUpgradeTimeDecreased", 10);
		BUILDER.pop();
	}

	// Alloy Smelter
	public static ForgeConfigSpec.ConfigValue<Integer> ALLOY_SMELTER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE;
	public static ForgeConfigSpec.ConfigValue<Integer> ALLOY_SMELTER_ENERGY_USAGE_PER_SPEED_UPGRADE;
	public static ForgeConfigSpec.ConfigValue<Integer> ALLOY_SMELTER_DEFAULT_ENERGY_USAGE;
	public static ForgeConfigSpec.ConfigValue<Boolean> ALLOY_SMELTER_ENERGY_ABILITY_COMPATIBLE;

	public static void addAlloySmelterConfig() {
		BUILDER.push("AlloySmelter");
		ALLOY_SMELTER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = config("The ticks amount each speed upgrades decreases from the process time of the Alloy Smelter.", "speedUpgradeTimeDecreased", 20);
		ALLOY_SMELTER_ENERGY_ABILITY_COMPATIBLE = config("If the Alloy Smelter is compatible with the Energy Ability Upgrade", "energyAbilityCompatible", true);
		ALLOY_SMELTER_DEFAULT_ENERGY_USAGE = config("(Only for Alloy Smelters that have the Energy Ability Upgrade) The default energy usage (FE/tick) of the Alloy Smelter, per tick. (with no speed upgrades)", "defaultEnergyUsage", 60);
		ALLOY_SMELTER_ENERGY_USAGE_PER_SPEED_UPGRADE = config("(Only for Alloy Smelters that have the Energy Ability Upgrade) The amount of FE/tick the Alloy Smelter furthermore consumes for each speed upgrade.", "speedUpgradeEnergyUsage", 10);
		BUILDER.pop();
	}

	public static void addCarbonGeneratorConfig() {
		BUILDER.push("CarbonGenerator");
		CARBON_GENERATOR_DEFAULT_PROCESS_TIME = config("The default process of the Carbon Generator, in ticks. (with no speed upgrades)", "defaultProcess ime", 250);
		CARBON_GENERATOR_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = config("The ticks amount each speed upgrades decreases from the process time of the Carbon Generator.", "speedUpgradeTimeDecreased", 12);
		CARBON_GENERATOR_DEFAULT_ENERGY_USAGE = config("The default energy usage (FE/tick) of the Carbon Generator, per tick. (with no speed upgrades)", "defaultEnergyUsage", 50);
		CARBON_GENERATOR_ENERGY_USAGE_PER_SPEED_UPGRADE = config("The amount of FE/tick the Carbon Generator furthermore consumes for each speed upgrade.", "speedUpgradeEnergyUsage", 10);
		BUILDER.pop();
	}

	public static void addEnergyGeneratorConfig() {
		BUILDER.push("EnergyGenerator");
		ENERGY_GENERATOR_FUEL_LASTING = config("The lasting of fuel in the Energy Generator, in ticks.", "fuelLasting", 200);
		ENERGY_GENERATOR_ENERGY_MADE = config("The energy made by the Energy Generator (per tick)", "energyMade", 50);
		BUILDER.pop();
	}

	public static ForgeConfigSpec.ConfigValue<Integer> ENERGY_TRANSMITTER_ENERGY_USED_PER_OPERATION;

	public static void addEnergyTransmitterConfig() {
		BUILDER.push("EnergyTransmitter");
		ENERGY_TRANSMITTER_ENERGY_USED_PER_OPERATION = config("The amount of energy the Energy Transmitter uses per transfer operation. This amount is multiplied by the number of Transmitter Cards the transmitter has (cardsInInv * energyUsedPerOperation).", "energyUsedPerOperation", 40);
		BUILDER.pop();
	}

	public static void addSmelterConfig() {
		BUILDER.push("Smelter");
		SMELTER_DEFAULT_PROCESS_TIME = config("The default process of the Smelter, in ticks. (with no speed upgrades)", "defaultProcessTime", 150);
		SMELTER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE = config("The ticks amount each speed upgrades decreases from the process time of the Smelter.", "speedUpgradeTimeDecreased", 10);
		SMELTER_DEFAULT_ENERGY_USAGE = config("The default energy usage (FE/tick) of the Smelter, per tick. (with no speed upgrades)", "defaultEnergyUsage", 40);
		SMELTER_ENERGY_USAGE_PER_SPEED_UPGRADE = config("The amount of FE/tick the Smelter furthermore consumes for each speed upgrade.", "speedUpgradeEnergyUsage", 10);
		BUILDER.pop();
	}
	
	//@formatter:on
	private static <T> ConfigValue<T> config(String comment, String path, T defaultValue,
			boolean addDefaultValueComment) {
//		return addDefaultValueComment
//				? BUILDER.comment(comment + " [Default: " + defaultValue.toString() + "]").define(path, defaultValue)
//				: BUILDER.comment(comment).define(path, defaultValue);
		return addDefaultValueComment
				? BUILDER.comment(comment, "default: " + defaultValue.toString()).define(path, defaultValue)
				: BUILDER.comment(comment).define(path, defaultValue);
	}

	private static <T> ConfigValue<T> config(String comment, String path, T defaultValue) {
		return config(comment, path, defaultValue, true);
	}

}

package com.ablackpikatchu.refinement.data.client;

import java.util.ArrayList;
import java.util.List;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.PotionInit;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup;
import com.ablackpikatchu.refinement.core.util.text.TextFormattingUtils;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;

import static com.ablackpikatchu.refinement.core.init.BlockInit.*;

public class LangProvider extends LanguageProvider {

	public static List<String> ALL_BLOCKS = new ArrayList<String>();

	public LangProvider(DataGenerator gen) {
		super(gen, Refinement.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {

		// Blocks
		add(BlockInit.BLANK_ORE.get(), "Blank Ore");
		add(BlockInit.GRINDER.get(), "Grinder");
		add(BlockInit.MACHINE_FRAME.get(), "Machine Frame");
		add(BlockInit.MIXER.get(), "Mixer");
		add(BlockInit.MOLD_PRESS.get(), "Mold Press");
		add(BlockInit.PURE_CRYSTAL_ORE.get(), "Pure Crystal Ore");
		add(BlockInit.REFINED_CARBON_BLOCK.get(), "Refined Carbon Block");
		add(BlockInit.REFINED_DIAMOND_BLOCK.get(), "Refined Diamond Block");
		add(BlockInit.REFINED_GOLD_BLOCK.get(), "Refined Gold Block");
		add(BlockInit.REFINED_IRON_BLOCK.get(), "Refined Iron Block");
		add(BlockInit.REFINED_NETHERITE_BLOCK.get(), "Refined Netherite Block");
		add(BlockInit.VACCUMULATOR.get(), "Vacuumulator");
		add(BlockInit.DNA_SEQUENCER.get(), "DNA Sequencer");
		add(BlockInit.MATERIALS_STATION.get(), "Materialist Station");
		add(BlockInit.RESOURCE_STATUE_BLOCK.get(), "Resource Statue");
		add(CARBON_GENERATOR_BLOCK.get(), "Carbon Generator");
		add(SMELTER_BLOCK.get(), "Smelter");
		add(ALLOY_SMELTER_BLOCK.get(), "Alloy Smelter");
		add(ENERGY_GENERATOR_BLOCK.get(), "Energy Generator");
		
		add(SIGNALUM_BLOCK.get(), "Signalum Block");
		add(LUMIUM_BLOCK.get(), "Lumium Block");
		add(ENDERIUM_BLOCK.get(), "Enderium Block");
		
		add(COPPER_BLOCK.get(), "Copper Block");
		add(TIN_BLOCK.get(), "Tin Block");
		add(SILVER_BLOCK.get(), "Silver Block");
		add(LEAD_BLOCK.get(), "Lead Block");
		
		add(ALPHA_STORAGE_BIN_BLOCK.get(), "Alpha Storage Bin");
		add(BETA_STORAGE_BIN_BLOCK, "Beta Storage Bin");
		
		add(BlockInit.REFINED_LOG.get(), "Refined Log");
		add(BlockInit.REFINED_STRIPPED_LOG.get(), "Refined Stripped Log");
		add(BlockInit.REFINED_LEAVES.get(), "Refined Leaves");
		add(BlockInit.REFINED_SAPLING.get(), "Refined Sapling");

		// Effects
		add(PotionInit.NEGATE_FALL.get(), "Negate Fall Damage");
		add(PotionInit.GHOSTLY_SHAPE.get(), "Ghostly Shape");
		add(PotionInit.FLIGHT.get(), "Flight");

		// Containers
		addContainer("mixer", "Mixer");
		addContainer("grinder", "Grinder");
		addContainer("mold_press", "Mold Press");
		addContainer("vaccumulator", "Vacuumulator");
		addContainer("dna_sequencer", "DNA Sequencer");
		addContainer("carbon_generator", "Carbon Generator");
		addContainer("energy_generator", "Energy Generator");
		addContainer("smelter", "Smelter");
		addContainer("alloy_smelter", "Alloy Smelter");
		
		// Category
		addCategory("mixer_recipe", "Mixer");
		addCategory("mold_press_recipe", "Mold Press");
		addCategory("grinder_recipe", "Grinder");
		addCategory("dna_sequencer", "DNA Sequencer");
		addCategory("anvil", "Anvil");
		addCategory("alloy_smelting", "Alloy Smelting");

		// Item Group
		addItemGroup(RefinementItemGroup.REFINEMENT.getName(), "Refinement");
		addItemGroup("refinement_materials", "Refinement Materials");
		addItemGroup("refinement_tools_weapons", "Refinement Tools & Weapons");
		addItemGroup("refinement_machine", "Refinement Machines");
		addItemGroup("refinement_armor", "Refinement Armor");
		addItemGroup("refinement_food", "Refinement Food");
		addItemGroup("refinement_resource_crops", "Refinement Resource Crops");

		// Death Messages
		add("death.attack.minersStewDamage", "%1$s choked on precious metals");
		add("death.attack.minersStewDamage.player", "%1$s's lack of air defeated them while fighting %2$s");

		// Tooltips
		addTooltip("hold_shift", "Hold \u00A7eSHIFT\u00A7r for more information!");
		addTooltip("miners_stew_shift", "It hurts on the way down but it has great benefits!");
		addTooltip("miners_jerky_shift",
				"It uses the food from your stomach to make you resist more powerful attacks!");
		addTooltip("miners_apple_shift", "It makes your feet stronger, letting them absorb any fall damage!");
		addTooltip("miners_carrot_shift", "It makes your legs longer, letting you jump further!");

		// Key Binds
		addKeybind("conversion_key", "Ore Unify");
		add("key.category." + Refinement.MOD_ID, "Refinement");

		// Villager
		addVillagerName("materials", "Materialist");
		
		// Component
		addComponent("loot_box", "Loot Box");
		addComponent("ownership_error", "You cannot open this machine as you do not own it.");
		addComponent("compatible_machines", "Compatible Machines");
		addComponent("stack_limit", "Stack Limit: §6%l");
		addComponent("count", "Count: §6%c");
		addComponent("stored_item", "Stored Item: §6%i");

		// Blocks
		BlockInit.BLOCKS.getEntries().parallelStream().map(RegistryObject<Block>::get).forEach(block -> {
			ALL_BLOCKS.add(block.getRegistryName().getPath());
		});

		// Items
		ItemInit.ITEMS.getEntries().stream().map(RegistryObject<Item>::get).forEach(item -> {
			if (!ALL_BLOCKS.contains(item.getRegistryName().getPath())) {
				String name = item.getRegistryName().getPath().replace("_", " ");
				try {
					add(item, TextFormattingUtils.capitalizeWord(name));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void add(RegistryObject<Block> block, String name) {
		add(block.get(), name);
	}

	private void addTooltip(String key, String value) {
		this.add("tooltip." + Refinement.MOD_ID + "." + key, value);
	}

	private void addContainer(String key, String value) {
		this.add("container." + Refinement.MOD_ID + "." + key, value);
	}

	private void addItemGroup(String key, String value) {
		this.add("itemGroup." + key, value);
	}

	private void addCategory(String key, String value) {
		this.add("category." + Refinement.MOD_ID + "." + key, value);
	}

	private void addKeybind(String key, String value) {
		this.add("key." + Refinement.MOD_ID + "." + key, value);
	}

	private void addVillagerName(String key, String value) {
		this.add("entity.minecraft.villager." + Refinement.MOD_ID + "." + key, value);
	}
	
	private void addComponent(String key, String value) {
		this.add("component." + Refinement.MOD_ID + "." + key, value);
	}

}

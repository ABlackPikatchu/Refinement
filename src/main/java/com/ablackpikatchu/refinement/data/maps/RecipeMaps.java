package com.ablackpikatchu.refinement.data.maps;

import java.util.HashMap;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.TagInit;
import com.ablackpikatchu.refinement.datafixers.util.recipe.IngredientInput;
import com.ablackpikatchu.refinement.datafixers.util.recipe.Output;
import com.ablackpikatchu.refinement.datafixers.util.recipe.TagInput;
import com.ablackpikatchu.refinement.datafixers.util.recipe.dna_sequencer.DNASequencerInput;
import com.ablackpikatchu.refinement.datafixers.util.recipe.mixer.MixerInput;
import com.ablackpikatchu.refinement.datafixers.util.recipe.mold_press.MoldPressInput;
import com.ablackpikatchu.refinement.resourcecrops.core.CropInit;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class RecipeMaps {

	public static void addShapelessBlockIngotEntries(HashMap<Block, Item> map) {
		map.put(BlockInit.REFINED_CARBON_BLOCK.get(), ItemInit.REFINED_CARBON_INGOT.get());
		map.put(BlockInit.REFINED_IRON_BLOCK.get(), ItemInit.REFINED_IRON_INGOT.get());
		map.put(BlockInit.REFINED_GOLD_BLOCK.get(), ItemInit.REFINED_GOLD_INGOT.get());
		map.put(BlockInit.REFINED_DIAMOND_BLOCK.get(), ItemInit.REFINED_DIAMOND.get());
		map.put(BlockInit.REFINED_NETHERITE_BLOCK.get(), ItemInit.REFINED_NETHERITE_INGOT.get());
	}

	public static void addShapelessIngotNuggetEntries(HashMap<Item, Item> map) {
		map.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_NUGGET.get());
		map.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_NUGGET.get());
	}

	public static void addDustFromSanding(HashMap<Item, Item> map) {
		map.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_DUST.get());
		map.put(ItemInit.REFINED_CARBON_INGOT.get(), ItemInit.REFINED_CARBON_DUST.get());
		map.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_DUST.get());
		map.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_DUST.get());
		map.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_DUST.get());
		map.put(ItemInit.PURE_CRYSTAL.get(), ItemInit.REFINING_DUST.get());
		map.put(Items.IRON_INGOT, ItemInit.IRON_DUST.get());
		map.put(Items.GOLD_INGOT, ItemInit.GOLD_DUST.get());
		map.put(Items.DIAMOND, ItemInit.DIAMOND_DUST.get());
		map.put(Items.NETHERITE_INGOT, ItemInit.NETHERITE_DUST.get());
		map.put(Items.COAL, ItemInit.COAL_DUST.get());
		map.put(Items.CHARCOAL, ItemInit.CHARCOAL_DUST.get());
	}

	public static void addArmours(HashMap<Item, Item> helmetMap, HashMap<Item, Item> chestplateMap,
			HashMap<Item, Item> leggingsMap, HashMap<Item, Item> bootsMap) {
		helmetMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_HELMET.get());
		helmetMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_HELMET.get());
		helmetMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_HELMET.get());
		helmetMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_HELMET.get());

		chestplateMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_CHESTPLATE.get());
		chestplateMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_CHESTPLATE.get());
		chestplateMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_CHESTPLATE.get());
		chestplateMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_CHESTPLATE.get());

		leggingsMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_LEGGINGS.get());
		leggingsMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_LEGGINGS.get());
		leggingsMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_LEGGINGS.get());
		leggingsMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_LEGGINGS.get());

		bootsMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_BOOTS.get());
		bootsMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_BOOTS.get());
		bootsMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_BOOTS.get());
		bootsMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_BOOTS.get());
	}

	public static void addTools(HashMap<Item, Item> axeMap, HashMap<Item, Item> pickaxeMap,
			HashMap<Item, Item> shovelMap, HashMap<Item, Item> hoeMap, HashMap<Item, Item> swordMap) {
		axeMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_AXE.get());
		axeMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_AXE.get());
		axeMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_AXE.get());
		axeMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_AXE.get());

		pickaxeMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_PICKAXE.get());
		pickaxeMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_PICKAXE.get());
		pickaxeMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_PICKAXE.get());
		pickaxeMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_PICKAXE.get());

		shovelMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_SHOVEL.get());
		shovelMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_SHOVEL.get());
		shovelMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_SHOVEL.get());
		shovelMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_SHOVEL.get());

		hoeMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_HOE.get());
		hoeMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_HOE.get());
		hoeMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_HOE.get());
		hoeMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_HOE.get());

		swordMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_SWORD.get());
		swordMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_SWORD.get());
		swordMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_SWORD.get());
		swordMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_SWORD.get());

	}

	public static void addMixing(HashMap<Item, Item> dusts) {
		dusts.put(ItemInit.IRON_DUST.get(), ItemInit.REFINED_IRON_DUST.get());
		dusts.put(ItemInit.GOLD_DUST.get(), ItemInit.REFINED_GOLD_DUST.get());
		dusts.put(ItemInit.DIAMOND_DUST.get(), ItemInit.REFINED_DIAMOND_DUST.get());
		dusts.put(ItemInit.NETHERITE_DUST.get(), ItemInit.REFINED_NETHERITE_DUST.get());
		dusts.put(ItemInit.COAL_DUST.get(), ItemInit.REFINED_CARBON_DUST.get());
	}

	public static void addGrinderRecipes(HashMap<IngredientInput, Output> map, HashMap<TagInput, Output> tagMap) {
		map.put(new IngredientInput(Items.GRAVEL, 16), new Output(Items.SAND, 16));
		map.put(new IngredientInput(Items.CHARCOAL, 1), new Output(ItemInit.CHARCOAL_DUST.get(), 1));
		map.put(new IngredientInput(Items.COAL, 1), new Output(ItemInit.COAL_DUST.get(), 1));
		map.put(new IngredientInput(Items.COAL_ORE, 1), new Output(ItemInit.COAL_DUST.get(), 2));
		map.put(new IngredientInput(Items.DIAMOND, 1), new Output(ItemInit.DIAMOND_DUST.get(), 1));
		map.put(new IngredientInput(Items.DIAMOND_ORE, 1), new Output(ItemInit.DIAMOND_DUST.get(), 2));
		map.put(new IngredientInput(Items.GOLD_INGOT, 1), new Output(ItemInit.GOLD_DUST.get(), 1));
		map.put(new IngredientInput(Items.GOLD_ORE, 1), new Output(ItemInit.GOLD_DUST.get(), 2));
		map.put(new IngredientInput(Items.IRON_INGOT, 1), new Output(ItemInit.IRON_DUST.get(), 1));
		map.put(new IngredientInput(Items.IRON_ORE, 1), new Output(ItemInit.IRON_DUST.get(), 2));
		map.put(new IngredientInput(Items.NETHERITE_INGOT, 1), new Output(ItemInit.NETHERITE_DUST.get(), 1));
		map.put(new IngredientInput(Ingredient.of(ItemInit.PURE_CRYSTAL.get()), 1),
				new Output(ItemInit.REFINING_DUST.get(), 1));
		map.put(new IngredientInput(Ingredient.of(ItemInit.PURE_CRYSTAL_ORE.get()), 1),
				new Output(ItemInit.REFINING_DUST.get(), 2));
		map.put(new IngredientInput(Ingredient.of(ItemInit.REFINED_CARBON_INGOT.get()), 1),
				new Output(ItemInit.REFINED_CARBON_DUST.get(), 1));
		map.put(new IngredientInput(Ingredient.of(ItemInit.REFINED_DIAMOND.get()), 1),
				new Output(ItemInit.REFINED_DIAMOND_DUST.get(), 1));
		map.put(new IngredientInput(Ingredient.of(ItemInit.REFINED_IRON_INGOT.get()), 1),
				new Output(ItemInit.REFINED_IRON_DUST.get(), 1));
		map.put(new IngredientInput(Ingredient.of(ItemInit.REFINED_GOLD_INGOT.get()), 1),
				new Output(ItemInit.REFINED_GOLD_DUST.get(), 1));
		map.put(new IngredientInput(Ingredient.of(ItemInit.REFINED_NETHERITE_INGOT.get()), 1),
				new Output(ItemInit.REFINED_NETHERITE_DUST.get(), 1));

		tagMap.put(new TagInput(TagInit.Items.COBBLESTONE, 16), new Output(Items.GRAVEL, 16));
	}

	public static void addMixerRecipes(HashMap<MixerInput, Output> map) {

		// Item
		map.put(new MixerInput(new IngredientInput(Items.GRAVEL, 1), new IngredientInput(Items.SAND, 1)),
				new Output(ItemInit.GRIT.get(), 4));

		// Tag
		map.put(new MixerInput(new TagInput(TagInit.Items.DIAMOND_DUST, 1),
				new IngredientInput(ItemInit.IRON_INFUSED_GRIT.get(), 1)),
				new Output(ItemInit.DIAMOND_INFUSED_GRIT.get(), 4));
		map.put(new MixerInput(new TagInput(TagInit.Items.NETHERITE_DUST, 1),
				new IngredientInput(ItemInit.DIAMOND_INFUSED_GRIT.get(), 1)),
				new Output(ItemInit.NETHERITE_INFUSED_GRIT.get(), 4));
		map.put(new MixerInput(new TagInput(TagInit.Items.IRON_DUST, 1), new IngredientInput(ItemInit.GRIT.get(), 1)),
				new Output(ItemInit.IRON_INFUSED_GRIT.get(), 4));

		map.put(new MixerInput(new TagInput(TagInit.Items.COAL_DUST, 1),
				new IngredientInput(ItemInit.REFINING_DUST.get(), 1)),
				new Output(ItemInit.REFINED_CARBON_DUST.get(), 2));
		map.put(new MixerInput(new TagInput(TagInit.Items.IRON_DUST, 1),
				new IngredientInput(ItemInit.REFINING_DUST.get(), 1)), new Output(ItemInit.REFINED_IRON_DUST.get(), 2));
		map.put(new MixerInput(new TagInput(TagInit.Items.GOLD_DUST, 1),
				new IngredientInput(ItemInit.REFINING_DUST.get(), 1)), new Output(ItemInit.REFINED_GOLD_DUST.get(), 2));
		map.put(new MixerInput(new TagInput(TagInit.Items.DIAMOND_DUST, 1),
				new IngredientInput(ItemInit.REFINING_DUST.get(), 1)),
				new Output(ItemInit.REFINED_DIAMOND_DUST.get(), 2));
		map.put(new MixerInput(new TagInput(TagInit.Items.NETHERITE_DUST, 1),
				new IngredientInput(ItemInit.REFINING_DUST.get(), 1)),
				new Output(ItemInit.REFINED_NETHERITE_DUST.get(), 2));

	}

	public static void addMoldPressRecipes(HashMap<MoldPressInput, Output> map) {
		map.put(new MoldPressInput(new IngredientInput(ItemInit.REFINED_CARBON_DUST.get(), 1),
				ItemInit.INGOT_MOLD.get()), new Output(ItemInit.UNFIRED_REFINED_CARBON_INGOT.get(), 1));
		map.put(new MoldPressInput(new IngredientInput(ItemInit.REFINED_IRON_DUST.get(), 1), ItemInit.INGOT_MOLD.get()),
				new Output(ItemInit.UNFIRED_REFINED_IRON_INGOT.get(), 1));
		map.put(new MoldPressInput(new IngredientInput(ItemInit.REFINED_GOLD_DUST.get(), 1), ItemInit.INGOT_MOLD.get()),
				new Output(ItemInit.UNFIRED_REFINED_GOLD_INGOT.get(), 1));
		map.put(new MoldPressInput(new IngredientInput(ItemInit.REFINED_NETHERITE_DUST.get(), 1),
				ItemInit.INGOT_MOLD.get()), new Output(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT.get(), 1));
		map.put(new MoldPressInput(new IngredientInput(ItemInit.REFINED_DIAMOND_DUST.get(), 1),
				ItemInit.GEM_MOLD.get()), new Output(ItemInit.UNFIRED_REFINED_DIAMOND.get(), 1));
		map.put(new MoldPressInput(new IngredientInput(ItemInit.REFINING_DUST.get(), 1), ItemInit.GEM_MOLD.get()),
				new Output(ItemInit.PURE_CRYSTAL.get(), 1));
		map.put(new MoldPressInput(new IngredientInput(ItemInit.REFINED_IRON_DUST.get(), 2),
				ItemInit.COGWHEEL_MOLD.get()), new Output(ItemInit.UNFIRED_REFINED_IRON_COGWHEEL.get(), 1));
		map.put(new MoldPressInput(new IngredientInput(ItemInit.REFINED_GOLD_DUST.get(), 2),
				ItemInit.COGWHEEL_MOLD.get()), new Output(ItemInit.UNFIRED_REFINED_GOLD_COGWHEEL.get(), 1));
		map.put(new MoldPressInput(new IngredientInput(ItemInit.REFINED_DIAMOND_DUST.get(), 2),
				ItemInit.COGWHEEL_MOLD.get()), new Output(ItemInit.UNFIRED_REFINED_DIAMOND_COGWHEEL.get(), 1));

	}

	public static void addDNASequencerRecipes(HashMap<DNASequencerInput, Output> map) {
		map.put(new DNASequencerInput(new IngredientInput(CropInit.IRON_ESSENCE.get(), 4),
				new IngredientInput(Items.COBBLESTONE, 2), 60).setCropsEnabledCondition(true),
				new Output(Items.IRON_INGOT, 2));
		map.put(new DNASequencerInput(new IngredientInput(CropInit.COAL_ESSENCE.get(), 4),
				new IngredientInput(Items.COBBLESTONE, 2), 60).setCropsEnabledCondition(true),
				new Output(Items.COAL, 2));
		map.put(new DNASequencerInput(new IngredientInput(CropInit.GOLD_ESSENCE.get(), 4),
				new IngredientInput(Items.COBBLESTONE, 2), 60).setCropsEnabledCondition(true),
				new Output(Items.GOLD_INGOT, 2));
		map.put(new DNASequencerInput(new IngredientInput(CropInit.LAPIS_ESSENCE.get(), 4),
				new IngredientInput(Items.COBBLESTONE, 2), 60).setCropsEnabledCondition(true),
				new Output(Items.LAPIS_LAZULI, 2));
		map.put(new DNASequencerInput(new IngredientInput(CropInit.EMERALD_ESSENCE.get(), 4),
				new IngredientInput(Items.COBBLESTONE, 2), 60).setCropsEnabledCondition(true),
				new Output(Items.EMERALD, 2));
		map.put(new DNASequencerInput(new IngredientInput(CropInit.DIAMOND_ESSENCE.get(), 4),
				new IngredientInput(Items.COBBLESTONE, 2), 60).setCropsEnabledCondition(true),
				new Output(Items.DIAMOND, 2));
		map.put(new DNASequencerInput(new IngredientInput(CropInit.PURE_CRYSTAL_ESSENCE.get(), 4),
				new IngredientInput(Items.COBBLESTONE, 2), 60).setCropsEnabledCondition(true),
				new Output(ItemInit.PURE_CRYSTAL.get(), 2));
		
	}

}
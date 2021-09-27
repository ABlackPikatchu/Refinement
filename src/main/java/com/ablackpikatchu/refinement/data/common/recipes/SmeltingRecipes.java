package com.ablackpikatchu.refinement.data.common.recipes;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.SmeltingRecipe.Blasting;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.SmeltingRecipe.Cooking.CampfireCooking;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.SmeltingRecipe.Cooking.Smoking;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.SmeltingRecipe.Smelting;

import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class SmeltingRecipes {

	public static ArrayList<Smelting> smeltingRecipes() {
		ArrayList<Smelting> smelting = new ArrayList<>();

		smelting.add(new Smelting(Ingredient.of(ItemInit.PURE_CRYSTAL_ORE.get()), ItemInit.PURE_CRYSTAL.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_COGWHEEL_MOLD.get()), ItemInit.COGWHEEL_MOLD.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_REFINED_DIAMOND_COGWHEEL.get()), ItemInit.REFINED_DIAMOND_COGWHEEL.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.DIAMOND_DUST.get()), Items.DIAMOND));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_GEM_MOLD.get()), ItemInit.GEM_MOLD.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_REFINED_GOLD_COGWHEEL.get()), ItemInit.REFINED_GOLD_COGWHEEL.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_INGOT_MOLD.get()), ItemInit.INGOT_MOLD.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_REFINED_IRON_COGWHEEL.get()), ItemInit.REFINED_IRON_COGWHEEL.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.IRON_DUST.get()), Items.IRON_INGOT));
		smelting.add(new Smelting(Ingredient.of(ItemInit.GOLD_DUST.get()), Items.GOLD_INGOT));
		smelting.add(new Smelting(Ingredient.of(ItemInit.COAL_DUST.get()), Items.COAL));
		smelting.add(new Smelting(Ingredient.of(ItemInit.CHARCOAL_DUST.get()), Items.CHARCOAL));
		smelting.add(new Smelting(Ingredient.of(ItemInit.NETHERITE_DUST.get()), Items.NETHERITE_INGOT));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()), ItemInit.REFINED_CARBON_INGOT.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_REFINED_DIAMOND.get()), ItemInit.REFINED_DIAMOND.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_REFINED_GOLD_INGOT.get()), ItemInit.REFINED_GOLD_INGOT.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_REFINED_IRON_INGOT.get()), ItemInit.REFINED_IRON_INGOT.get()));
		smelting.add(new Smelting(Ingredient.of(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT.get()), ItemInit.REFINED_NETHERITE_INGOT.get()));
		
		smelting.add(new Smelting(Ingredient.of(BlockInit.REFINED_LOG.get()), ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()));
		smelting.add(new Smelting(Ingredient.of(BlockInit.REFINED_STRIPPED_LOG.get()), ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()));
		smelting.add(new Smelting(Ingredient.of(BlockInit.REFINED_LEAVES.get()), ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()));

		return smelting;
	}

	public static ArrayList<Blasting> blastingRecipes() {
		ArrayList<Blasting> blasting = new ArrayList<>();

		blasting.add(new Blasting(Ingredient.of(ItemInit.PURE_CRYSTAL_ORE.get()), ItemInit.PURE_CRYSTAL.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_COGWHEEL_MOLD.get()), ItemInit.COGWHEEL_MOLD.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_REFINED_DIAMOND_COGWHEEL.get()), ItemInit.REFINED_DIAMOND_COGWHEEL.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.DIAMOND_DUST.get()), Items.DIAMOND));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_GEM_MOLD.get()), ItemInit.GEM_MOLD.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_REFINED_GOLD_COGWHEEL.get()), ItemInit.REFINED_GOLD_COGWHEEL.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_INGOT_MOLD.get()), ItemInit.INGOT_MOLD.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_REFINED_IRON_COGWHEEL.get()), ItemInit.REFINED_IRON_COGWHEEL.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.IRON_DUST.get()), Items.IRON_INGOT));
		blasting.add(new Blasting(Ingredient.of(ItemInit.GOLD_DUST.get()), Items.GOLD_INGOT));
		blasting.add(new Blasting(Ingredient.of(ItemInit.COAL_DUST.get()), Items.COAL));
		blasting.add(new Blasting(Ingredient.of(ItemInit.CHARCOAL_DUST.get()), Items.CHARCOAL));
		blasting.add(new Blasting(Ingredient.of(ItemInit.NETHERITE_DUST.get()), Items.NETHERITE_INGOT));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()), ItemInit.REFINED_CARBON_INGOT.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_REFINED_DIAMOND.get()), ItemInit.REFINED_DIAMOND.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_REFINED_GOLD_INGOT.get()), ItemInit.REFINED_GOLD_INGOT.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_REFINED_IRON_INGOT.get()), ItemInit.REFINED_IRON_INGOT.get()));
		blasting.add(new Blasting(Ingredient.of(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT.get()), ItemInit.REFINED_NETHERITE_INGOT.get()));

		return blasting;
	}

	public static class Cooking {

		public static ArrayList<Smoking> smokingRecipes() {
			ArrayList<Smoking> smoking = new ArrayList<>();

			smoking.add(new Smoking(Ingredient.of(BlockInit.REFINED_LOG.get()), ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()));
			smoking.add(new Smoking(Ingredient.of(BlockInit.REFINED_STRIPPED_LOG.get()), ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()));
			smoking.add(new Smoking(Ingredient.of(BlockInit.REFINED_LEAVES.get()), ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()));

			return smoking;
		}

		public static ArrayList<CampfireCooking> campfireCookingRecipes() {
			ArrayList<CampfireCooking> campfireCooking = new ArrayList<>();
			
			campfireCooking.add(new CampfireCooking(Ingredient.of(BlockInit.REFINED_LOG.get()), ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()));
			campfireCooking.add(new CampfireCooking(Ingredient.of(BlockInit.REFINED_STRIPPED_LOG.get()), ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()));
			campfireCooking.add(new CampfireCooking(Ingredient.of(BlockInit.REFINED_LEAVES.get()), ItemInit.UNFIRED_REFINED_CARBON_INGOT.get()));

			return campfireCooking;
		}
	}

}

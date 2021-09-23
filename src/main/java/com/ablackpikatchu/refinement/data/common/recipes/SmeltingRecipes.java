package com.ablackpikatchu.refinement.data.common.recipes;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.SmeltingRecipe.Blasting;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.SmeltingRecipe.Cooking.CampfireCooking;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.SmeltingRecipe.Cooking.Smoking;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.SmeltingRecipe.Smelting;

import net.minecraft.item.crafting.Ingredient;

public class SmeltingRecipes {

	public static ArrayList<Smelting> smeltingRecipes() {
		ArrayList<Smelting> smelting = new ArrayList<>();

		smelting.add(new Smelting(Ingredient.of(ItemInit.PURE_CRYSTAL_ORE.get()), ItemInit.PURE_CRYSTAL.get()));

		return smelting;
	}

	public static ArrayList<Blasting> blastingRecipes() {
		ArrayList<Blasting> blasting = new ArrayList<>();

		blasting.add(new Blasting(Ingredient.of(ItemInit.PURE_CRYSTAL_ORE.get()), ItemInit.PURE_CRYSTAL.get()));

		return blasting;
	}

	public static class Cooking {

		public static ArrayList<Smoking> smokingRecipes() {
			ArrayList<Smoking> smoking = new ArrayList<>();

			//smoking.add(new Smoking(Ingredient.of(ItemInit.INPUT.get()), ItemInit.OUTPUT.get()));

			return smoking;
		}

		public static ArrayList<CampfireCooking> campfireCookingRecipes() {
			ArrayList<CampfireCooking> campfireCooking = new ArrayList<>();
			
			//campfireCooking.add(new CampfireCooking(Ingredient.of(ItemInit.INPUT.get()), ItemInit.OUTPUT.get()));

			return campfireCooking;
		}
	}

}

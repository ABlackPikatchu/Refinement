package com.ablackpikatchu.refinement.data.common.recipes;

import java.util.HashMap;

import com.ablackpikatchu.refinement.data.common.recipes.qol.Dyeing;
import com.ablackpikatchu.refinement.data.common.recipes.qol.NonWoodSlabs;
import com.ablackpikatchu.refinement.data.common.recipes.qol.NonWoodStairs;
import com.ablackpikatchu.refinement.data.common.recipes.qol.Wood;

import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.util.ResourceLocation;

public class QOLRecipes {
	
	public static HashMap<ShapedRecipeBuilder, ResourceLocation> getShapedDyeingRecipes() {
		return Dyeing.Shaped.getRecipes();
	}
	
	public static HashMap<ShapelessRecipeBuilder, ResourceLocation> getShapelessDyeingRecipes() {
		return Dyeing.Shapeless.getRecipes();
	}
	
	public static HashMap<ShapedRecipeBuilder, ResourceLocation> getShapedWoodRecipes() {
		return Wood.Shaped.getRecipes();
	}
	
	public static HashMap<ShapelessRecipeBuilder, ResourceLocation> getShapelessWoodRecipes() {
		return Wood.Shapeless.getRecipes();
	}
	
	public static HashMap<ShapedRecipeBuilder, ResourceLocation> getShapedStairsRecipes() {
		return NonWoodStairs.Shaped.getRecipes();
	}
	
	public static HashMap<ShapelessRecipeBuilder, ResourceLocation> getShapelessSlabsRecipes() {
		return NonWoodSlabs.Shapeless.getRecipes();
	}
	
}

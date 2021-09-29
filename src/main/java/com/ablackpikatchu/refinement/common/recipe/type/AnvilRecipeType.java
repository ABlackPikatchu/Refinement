package com.ablackpikatchu.refinement.common.recipe.type;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.recipe.AnvilRecipe;

import net.minecraft.item.crafting.IRecipeType;

public class AnvilRecipeType implements IRecipeType<AnvilRecipe> {

	@Override
	public String toString() {
		return Refinement.MOD_ID + ":anvil";
	}
	
}

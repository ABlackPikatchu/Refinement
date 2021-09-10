package com.ablackpikatchu.refinement.common.recipe.type;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.recipe.MoldPressRecipe;

import net.minecraft.item.crafting.IRecipeType;

public class MoldPressRecipeType implements IRecipeType<MoldPressRecipe> {
	
	@Override
	public String toString() {
		return Refinement.MOD_ID + ":mold_press";
	}

}

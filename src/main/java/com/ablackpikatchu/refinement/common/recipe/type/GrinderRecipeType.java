package com.ablackpikatchu.refinement.common.recipe.type;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.recipe.GrinderRecipe;

import net.minecraft.item.crafting.IRecipeType;

public class GrinderRecipeType implements IRecipeType<GrinderRecipe> {
	
	@Override
	public String toString() {
		return Refinement.MOD_ID + ":grinder";
	}

}

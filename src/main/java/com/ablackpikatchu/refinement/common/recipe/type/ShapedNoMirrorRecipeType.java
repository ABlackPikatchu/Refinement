package com.ablackpikatchu.refinement.common.recipe.type;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.recipe.ShapedNoMirrorRecipe;

import net.minecraft.item.crafting.IRecipeType;

public class ShapedNoMirrorRecipeType implements IRecipeType<ShapedNoMirrorRecipe> {
	
	@Override
	public String toString() {
		return Refinement.MOD_ID + ":shaped_no_mirror";
	}

}

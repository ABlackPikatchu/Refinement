package com.ablackpikatchu.refinement.common.recipe.type;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.recipe.MixerRecipe;

import net.minecraft.item.crafting.IRecipeType;

public class MixerRecipeType implements IRecipeType<MixerRecipe> {
	@Override
	public String toString() {
		return Refinement.MOD_ID + ":mixer";
	}
}

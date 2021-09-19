package com.ablackpikatchu.refinement.common.recipe.type;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.recipe.OreUnifyRecipe;

import net.minecraft.item.crafting.IRecipeType;

public class OreUnifyRecipeType implements IRecipeType<OreUnifyRecipe> {

	@Override
	public String toString() {
		return Refinement.MOD_ID + "ore_unify";
	}
	
}

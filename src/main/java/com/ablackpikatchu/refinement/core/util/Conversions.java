package com.ablackpikatchu.refinement.core.util;

import com.ablackpikatchu.refinement.common.recipe.OreUnifyRecipe;
import com.ablackpikatchu.refinement.core.init.RecipeInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class Conversions {

	public static void convert(PlayerEntity player) {
		for (final IRecipe<?> recipe : player.level.getRecipeManager()
				.getAllRecipesFor(RecipeInit.ORE_UNIFY_RECIPE)) {
			final OreUnifyRecipe oreUnifyRecipe = (OreUnifyRecipe) recipe;
			for (int i = 0; i <= player.inventory.getContainerSize(); ++i) {
				if (oreUnifyRecipe.isValid(player.inventory.getItem(i))) {
					int count = player.inventory.getItem(i).getCount();
					player.inventory.setItem(i, new ItemStack(oreUnifyRecipe.getResultItem().getItem(), count));
					player.inventory.setChanged();
				}
			}
		}
	}
	
}

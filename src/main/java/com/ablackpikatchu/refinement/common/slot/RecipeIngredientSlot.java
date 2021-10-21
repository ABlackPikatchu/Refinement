package com.ablackpikatchu.refinement.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;

public class RecipeIngredientSlot<T extends IRecipe<IInventory>> extends Slot {
	
	public final IRecipeType<T> recipe;
	public final World level;
	
	public RecipeIngredientSlot(IRecipeType<T> recipe, World level, IInventory pContainer, int pIndex, int pX, int pY) {
		super(pContainer, pIndex, pX, pY);
		this.recipe = recipe;
		this.level = level;
	}
	
	@Override
	public boolean mayPlace(ItemStack pStack) {
		for (T currentRecipe : this.level.getRecipeManager().getAllRecipesFor(this.recipe)) {
			for (Ingredient ingredient : currentRecipe.getIngredients()) {
				if (ingredient.test(pStack))
					return true;
			}
		}
		return false;
	}

}

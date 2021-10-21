package com.ablackpikatchu.refinement.common.compat.patchouli;

import com.ablackpikatchu.refinement.common.recipe.GrinderRecipe;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class GrinderRecipeProcessor implements IComponentProcessor {

	private GrinderRecipe recipe;

	@SuppressWarnings("resource")
	@Override
	public void setup(IVariableProvider variables) {
		String recipeId = variables.get("recipe").asString();
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
		IRecipe<?> recipeVar = manager.byKey(new ResourceLocation(recipeId)).orElseThrow(IllegalArgumentException::new);
		if (recipeVar instanceof GrinderRecipe) {
			recipe = (GrinderRecipe) recipeVar;
		} else
			throw new IllegalArgumentException("Provided Recipe was not a grinder recipe!");
	}

	@Override
	public IVariable process(String key) {
		if (key.startsWith("item")) {
			int index = Integer.parseInt(key.substring(4)) - 1;
			Ingredient ingredient;
			
			if (index < 2) 
				ingredient = recipe.getIngredients().get(index);
			else 
				return IVariable.from(recipe.getResultItem());
				
			ItemStack[] stacks = ingredient.getItems();
			ItemStack stack = stacks.length == 0 ? ItemStack.EMPTY : stacks[0];

			return IVariable.from(stack);
		} else if (key.equals("recipe_output")) {
			ItemStack out = recipe.getResultItem();
			return IVariable.wrap(out.getCount() + "x$(br)" + out.getDisplayName().toString());
		} else if (key.equals("icount")) {
			return IVariable.wrap(recipe.getResultItem().getCount());
		} else if (key.equals("iname")) {
			String name = recipe.getResultItem().getDisplayName().getString();
			name = name.replace('[', ' ');
			name = name.replace(']', ' ');
			name = name + "$(item)";
			return IVariable.wrap(name);
		}
		
		return null;
	}

}

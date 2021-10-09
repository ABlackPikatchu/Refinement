package com.ablackpikatchu.refinement.datafixers.util.recipe.mold_press;

import com.ablackpikatchu.refinement.data.common.recipes.builder.MoldPressRecipeBuilder;
import com.ablackpikatchu.refinement.datafixers.util.recipe.IngredientInput;
import com.ablackpikatchu.refinement.datafixers.util.recipe.TagInput;

import net.minecraft.item.Item;

public class MoldPressInput {

	private final Item mold;
	private final IngredientInput input;
	private final TagInput tag;

	public MoldPressInput(IngredientInput input, Item mold) {
		this.input = input;
		this.tag = null;
		this.mold = mold;
	}

	public MoldPressInput(TagInput tag, Item mold) {
		this.input = null;
		this.tag = tag;
		this.mold = mold;
	}

	public IngredientInput getInput() {
		return this.input;
	}

	public TagInput getTag() {
		return this.tag;
	}

	public MoldPressRecipeBuilder getMoldPressRecipe(MoldPressRecipeBuilder recipe) {

		if (tag != null)
			recipe.addIngredient(tag.getTag(), tag.getCount());
		else
			recipe.addIngredient(input.getIngredient(), input.getCount());

		recipe.addMold(mold);

		return recipe;
	}
}

package com.ablackpikatchu.refinement.datafixers.util.recipe.mixer;

import com.ablackpikatchu.refinement.data.common.recipes.builder.MixerRecipeBuilder;
import com.ablackpikatchu.refinement.datafixers.util.recipe.IngredientInput;
import com.ablackpikatchu.refinement.datafixers.util.recipe.TagInput;

public class MixerInput {

	private final IngredientInput mainInput;
	private final IngredientInput secondaryInput;
	private final TagInput mainTag;
	private final TagInput secondaryTag;

	public MixerInput(IngredientInput mainInput, IngredientInput secondaryInput) {
		this.mainInput = mainInput;
		this.secondaryInput = secondaryInput;
		this.mainTag = null;
		this.secondaryTag = null;
	}

	public MixerInput(TagInput mainTag, IngredientInput secondaryInput) {
		this.mainInput = null;
		this.mainTag = mainTag;
		this.secondaryInput = secondaryInput;
		this.secondaryTag = null;
	}

	public MixerInput(IngredientInput mainInput, TagInput secondaryTag) {
		this.mainInput = mainInput;
		this.secondaryInput = null;
		this.mainTag = null;
		this.secondaryTag = secondaryTag;
	}

	public MixerInput(TagInput mainTag, TagInput secondaryTag) {
		this.mainInput = null;
		this.secondaryInput = null;
		this.mainTag = mainTag;
		this.secondaryTag = secondaryTag;
	}

	public IngredientInput getMainInput() {
		return this.mainInput;
	}

	public IngredientInput getSecondaryInput() {
		return this.secondaryInput;
	}

	public TagInput getMainTag() {
		return this.mainTag;
	}

	public TagInput getSecondaryTag() {
		return this.secondaryTag;
	}

	public MixerRecipeBuilder getMixerRecipe(MixerRecipeBuilder recipe) {

		if (mainTag != null)
			recipe.addIngredient(mainTag.getTag(), mainTag.getCount());
		else
			recipe.addIngredient(mainInput.getIngredient(), mainInput.getCount());

		if (secondaryTag != null)
			recipe.addSecondaryIngredient(secondaryTag.getTag(), secondaryTag.getCount());
		else
			recipe.addSecondaryIngredient(secondaryInput.getIngredient(), secondaryInput.getCount());

		return recipe;
	}

}

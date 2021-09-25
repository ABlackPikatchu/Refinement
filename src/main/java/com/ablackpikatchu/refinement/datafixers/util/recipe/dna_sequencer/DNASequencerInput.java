package com.ablackpikatchu.refinement.datafixers.util.recipe.dna_sequencer;

import com.ablackpikatchu.refinement.data.common.recipes.DNASequencerRecipeBuilder;
import com.ablackpikatchu.refinement.datafixers.util.recipe.IngredientInput;
import com.ablackpikatchu.refinement.datafixers.util.recipe.TagInput;

/**
 * Class for creating DNA Sequencer data gen recipe inputs
 * @author matyrobbrt
 *
 */
public class DNASequencerInput {

	private final IngredientInput mainInput;
	private final IngredientInput secondaryInput;
	private final TagInput mainTag;
	private final TagInput secondaryTag;
	private final int successProbability;
	private final boolean noFuelRequired;

	/**
	 * Creates a new {@link DNASequencerRecipeBuilder} recipe input
	 * @param mainInput the main input
	 * @param secondaryInput the secondary input
	 * @param successProbability the success chance of the recipe (if is greater than 100 it will be set to 100)
	 * @param noFuelRequired if the recipe should not consume fuel (if true, it WILL NOT consume fuel; if false, the recipe WILL consume fuel)
	 */
	public DNASequencerInput(IngredientInput mainInput, IngredientInput secondaryInput, int successProbability, boolean noFuelRequired) {
		this.mainInput = mainInput;
		this.secondaryInput = secondaryInput;
		this.mainTag = null;
		this.secondaryTag = null;
		if (successProbability <= 100) this.successProbability = successProbability;
		else this.successProbability = 100;
		this.noFuelRequired = noFuelRequired;
	}

	/**
	 * See {@link DNASequencerInput#DNASequencerInput}
	 * @param mainTag
	 * @param secondaryInput
	 * @param successProbability
	 * @param noFuelRequired
	 */
	public DNASequencerInput(TagInput mainTag, IngredientInput secondaryInput, int successProbability, boolean noFuelRequired) {
		this.mainInput = null;
		this.mainTag = mainTag;
		this.secondaryInput = secondaryInput;
		this.secondaryTag = null;
		if (successProbability <= 100) this.successProbability = successProbability;
		else this.successProbability = 100;
		this.noFuelRequired = noFuelRequired;
	}

	/**
	 * See {@link DNASequencerInput#DNASequencerInput}
	 * @param mainInput
	 * @param secondaryTag
	 * @param successProbability
	 * @param noFuelRequired
	 */
	public DNASequencerInput(IngredientInput mainInput, TagInput secondaryTag, int successProbability, boolean noFuelRequired) {
		this.mainInput = mainInput;
		this.secondaryInput = null;
		this.mainTag = null;
		this.secondaryTag = secondaryTag;
		if (successProbability <= 100) this.successProbability = successProbability;
		else this.successProbability = 100;
		this.noFuelRequired = noFuelRequired;
	}

	/**
	 * See {@link DNASequencerInput#DNASequencerInput}
	 * @param mainTag
	 * @param secondaryTag
	 * @param successProbability
	 * @param noFuelRequired
	 */
	public DNASequencerInput(TagInput mainTag, TagInput secondaryTag, int successProbability, boolean noFuelRequired) {
		this.mainInput = null;
		this.secondaryInput = null;
		this.mainTag = mainTag;
		this.secondaryTag = secondaryTag;
		if (successProbability <= 100) this.successProbability = successProbability;
		else this.successProbability = 100;
		this.noFuelRequired = noFuelRequired;
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
	
	public int getSuccessProbability() {
		return this.successProbability;
	}
	
	public boolean noFuelRequired() {
		return this.noFuelRequired;
	}

	public DNASequencerRecipeBuilder getDNASequencerRecipe(DNASequencerRecipeBuilder recipe) {

		if (mainTag != null)
			recipe.addIngredient(mainTag.getTag(), mainTag.getCount());
		else
			recipe.addIngredient(mainInput.getIngredient(), mainInput.getCount());

		if (secondaryTag != null)
			recipe.addSecondaryIngredient(secondaryTag.getTag(), secondaryTag.getCount());
		else
			recipe.addSecondaryIngredient(secondaryInput.getIngredient(), secondaryInput.getCount());
		
		recipe.setSuccessProbability(successProbability);
		recipe.setNoFuelRequired(noFuelRequired);

		return recipe;
	}

}

package com.ablackpikatchu.refinement.common.recipe.type;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.recipe.DNASequencerRecipe;

import net.minecraft.item.crafting.IRecipeType;

public class DNASequencerRecipeType implements IRecipeType<DNASequencerRecipe> {

	@Override
	public String toString() {
		return Refinement.MOD_ID + ":dna_sequencer";
	}
	
}

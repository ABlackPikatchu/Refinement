package com.ablackpikatchu.refinement.datafixers.util.json;

import net.minecraft.item.crafting.Ingredient;

public class JSONInput {

	private final Ingredient ingredient;
	private final int count;
	
	public JSONInput(Ingredient ingredient, int count) {
		this.ingredient = ingredient;
		this.count = count;
	}
	
	public Ingredient getIngredient() {
		return this.ingredient;
	}
	
	public int getCount() {
		return this.count;
	}
	
}

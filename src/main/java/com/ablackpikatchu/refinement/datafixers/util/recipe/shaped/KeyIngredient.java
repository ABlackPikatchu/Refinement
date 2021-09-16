package com.ablackpikatchu.refinement.datafixers.util.recipe.shaped;

import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;

import net.minecraftforge.fml.RegistryObject;

public class KeyIngredient {

	private final Character key;
	private final Ingredient ingredient;

	public KeyIngredient(Character key, RegistryObject<Item> item) {
		this.key = key;
		this.ingredient = Ingredient.of(item.get());
	}

	public KeyIngredient(Character key, Item item) {
		this.key = key;
		this.ingredient = Ingredient.of(item);
	}

	public KeyIngredient(Character key, ITag<Item> tag) {
		this.key = key;
		this.ingredient = Ingredient.of(tag);
	}

	public Character getKey() {
		return this.key;
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}

	public void getShapedRecipe(ShapedRecipeBuilder recipe) {
		recipe.define(this.key, this.ingredient);
	}

}

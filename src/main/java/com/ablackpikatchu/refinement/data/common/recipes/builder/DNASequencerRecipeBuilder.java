package com.ablackpikatchu.refinement.data.common.recipes.builder;

import java.util.ArrayList;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.common.recipe.DNASequencerRecipe;
import com.ablackpikatchu.refinement.common.recipe.conditions.CropsEnabledCondition;
import com.ablackpikatchu.refinement.core.util.NameUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class DNASequencerRecipeBuilder {

	private final IRecipeSerializer<?> serializer;
	private final ArrayList<Ingredient> ingredients = new ArrayList<>();
	private int inputCount = 0;
	private final ArrayList<Ingredient> ingredients2 = new ArrayList<>();
	private int inputCount2 = 0;
	private String tag;
	private boolean isTag = false;
	private String tag2;
	private boolean isTag2 = false;
	private final Item resultItem;
	private final int resultCount;
	private int successProbability;
	private boolean noFuelRequired;
	private boolean cropsEnabled;

	public DNASequencerRecipeBuilder(Item resultItem, int count) {
		this.serializer = DNASequencerRecipe.SERIALIZER;
		this.resultItem = resultItem;
		this.resultCount = count;
	}

	public static DNASequencerRecipeBuilder recipeBuilder(IItemProvider result, int count) {
		return new DNASequencerRecipeBuilder(result.asItem(), count);
	}

	public DNASequencerRecipeBuilder addIngredient(IItemProvider item) {
		return addIngredient(Ingredient.of(item));
	}

	public DNASequencerRecipeBuilder addIngredient(IItemProvider item, int count) {
		return addIngredient(Ingredient.of(item), count);
	}

	public DNASequencerRecipeBuilder addIngredient(Ingredient ingredient) {
		return addIngredient(ingredient, 1);
	}

	public DNASequencerRecipeBuilder addIngredient(Ingredient ingredient, int count) {
		this.ingredients.add(ingredient);
		this.inputCount = count;
		return this;
	}

	public DNASequencerRecipeBuilder addIngredient(ITag<Item> tag, int count) {
		this.isTag = true;
		this.tag = tag.toString();
		this.inputCount = count;
		return this;
	}

	public DNASequencerRecipeBuilder addSecondaryIngredient(ITag<Item> tag, int count) {
		this.isTag2 = true;
		this.tag2 = tag.toString();
		this.inputCount2 = count;
		return this;
	}

	public DNASequencerRecipeBuilder addSecondaryIngredient(IItemProvider item) {
		return addSecondaryIngredient(Ingredient.of(item));
	}

	public DNASequencerRecipeBuilder addSecondaryIngredient(IItemProvider item, int count) {
		return addSecondaryIngredient(Ingredient.of(item), count);
	}

	public DNASequencerRecipeBuilder addSecondaryIngredient(Ingredient ingredient) {
		return addSecondaryIngredient(ingredient, 1);
	}

	public DNASequencerRecipeBuilder addSecondaryIngredient(Ingredient ingredient, int count) {
		this.ingredients2.add(ingredient);
		this.inputCount2 = count;
		return this;
	}

	public void build(Consumer<IFinishedRecipe> consumer) {
		String name = this.resultItem.getRegistryName().getPath();
		build(consumer, new ResourceLocation(serializer.getRegistryName() + "/" + name));
	}

	public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation recipeId) {
		consumer.accept(new Result(recipeId));
	}

	public DNASequencerRecipeBuilder setSuccessProbability(int successProbability) {
		if (successProbability <= 100)
			this.successProbability = successProbability;
		else
			this.successProbability = 100;
		return this;
	}

	public DNASequencerRecipeBuilder setNoFuelRequired(boolean noFuelRequired) {
		this.noFuelRequired = noFuelRequired;
		return this;
	}

	public DNASequencerRecipeBuilder setCropsEnabledCondition(boolean cropsEnabled) {
		this.cropsEnabled = cropsEnabled;
		return this;
	}

	public class Result implements IFinishedRecipe {
		private final ResourceLocation recipeId;

		public Result(ResourceLocation recipeId) {
			this.recipeId = recipeId;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			if (cropsEnabled)
				addCondition(json);
			if (!isTag)
				json.add("input", serializeInput());
			else
				json.add("input", serializeTag(tag));
			if (!isTag2)
				json.add("secondary_input", serializeSecondaryInput());
			else
				json.add("secondary_input", serializeTag(tag2));
			json.add("output", serializeResult());
			json.addProperty("no_fuel_required", noFuelRequired);
		}

		private JsonObject serializeInput() {
			JsonObject ret = new JsonObject();
			for (Ingredient ingredient : ingredients) {
				for (ItemStack stack : ingredient.getItems()) {
					ret.addProperty("item", NameUtils.from(stack.getItem()).toString());
				}
			}
			ret.addProperty("count", inputCount);
			return ret;
		}

		private JsonObject serializeSecondaryInput() {
			JsonObject ret = new JsonObject();
			for (Ingredient ingredient : ingredients2) {
				for (ItemStack stack : ingredient.getItems()) {
					ret.addProperty("item", NameUtils.from(stack.getItem()).toString());
				}
			}
			ret.addProperty("count", inputCount2);
			return ret;
		}

		private JsonObject serializeResult() {
			JsonObject ret = new JsonObject();
			ret.addProperty("item", NameUtils.from(resultItem).toString());
			if (resultCount > 1) {
				ret.addProperty("count", resultCount);
			}
			if (successProbability >= 0) {
				ret.addProperty("success_chance", successProbability);
			}
			return ret;
		}

		private JsonObject serializeTag(String tag) {
			JsonObject ret = new JsonObject();
			ret.addProperty("tag", tag.substring(tag.indexOf("[") + 1).replace("]", ""));
			ret.addProperty("count", inputCount);
			return ret;
		}

		private void addCondition(JsonObject ret) {
			JsonArray conditions = new JsonArray();
			JsonObject condition = new JsonObject();
			condition.addProperty("type", CropsEnabledCondition.ID.toString());
			condition.addProperty("value", true);
			conditions.add(condition);
			ret.add("conditions", conditions);
		}

		@Override
		public ResourceLocation getId() {
			return recipeId;
		}

		@Override
		public IRecipeSerializer<?> getType() {
			return serializer;
		}

		@Nullable
		@Override
		public JsonObject serializeAdvancement() {
			return null;
		}

		@Nullable
		@Override
		public ResourceLocation getAdvancementId() {
			return null;
		}
	}

}

package com.ablackpikatchu.refinement.data.common.recipes.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.common.recipe.AlloySmeltingRecipe;
import com.ablackpikatchu.refinement.core.util.NameUtils;
import com.ablackpikatchu.refinement.datafixers.util.recipe.IIngredient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class AlloySmelterRecipeBuilder {

	private final IRecipeSerializer<?> serializer;

	private List<IIngredient> ingredients = new LinkedList<>();
	private int processTime = -1;

	private final Item resultItem;
	private final int resultCount;

	public AlloySmelterRecipeBuilder(Item resultItem, int count) {
		this.serializer = AlloySmeltingRecipe.SERIALIZER;
		this.resultItem = resultItem;
		this.resultCount = count;
	}

	public AlloySmelterRecipeBuilder addIngredient(IIngredient ingredient) {
		if (this.ingredients.size() < 4)
			this.ingredients.add(ingredient);
		else
			throw new IllegalStateException("Recipe cannot have more than 4 ingredients!");
		return this;
	}
	
	public AlloySmelterRecipeBuilder setProcessTime(int processTime) {
		this.processTime = processTime;
		return this;
	}

	public static AlloySmelterRecipeBuilder recipeBuilder(IItemProvider result, int count) {
		return new AlloySmelterRecipeBuilder(result.asItem(), count);
	}

	public void build(Consumer<IFinishedRecipe> consumer) {
		String name = this.resultItem.getRegistryName().getPath();
		build(consumer, new ResourceLocation(serializer.getRegistryName() + "/" + name));
	}

	public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation recipeId) {
		consumer.accept(new Result(recipeId));
	}

	public class Result implements IFinishedRecipe {
		private final ResourceLocation recipeId;

		public Result(ResourceLocation recipeId) {
			this.recipeId = recipeId;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			serializeInputs(json);
			serializeResult(json);
			if (processTime >= 0)
				json.addProperty("process_time", processTime);
		}
		
		public void serializeInputs(JsonObject json) {
			JsonArray ingreds = new JsonArray();
			ingredients.forEach(ingredient -> ingreds.add(ingredient.toJson()));
			json.add("ingredients", ingreds);
		}
		
		private void serializeResult(JsonObject json) {
			JsonObject ret = new JsonObject();
			ret.addProperty("item", NameUtils.from(resultItem).toString());
			if (resultCount > 1) {
				ret.addProperty("count", resultCount);
			}
			json.add("output", ret);
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

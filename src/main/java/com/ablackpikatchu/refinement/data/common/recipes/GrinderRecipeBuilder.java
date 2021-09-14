package com.ablackpikatchu.refinement.data.common.recipes;

import java.util.ArrayList;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.common.recipe.GrinderRecipe;
import com.ablackpikatchu.refinement.core.util.NameUtils;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class GrinderRecipeBuilder {

	private final IRecipeSerializer<?> serializer;
	private final ArrayList<Ingredient> ingredients = new ArrayList<>();
	private int inputCount = 0;
	private String tag;
	private boolean isTag = false;
	private final Item resultItem;
	private final int resultCount;

	public GrinderRecipeBuilder(IRecipeSerializer<?> serializer, Item resultItem, int count) {
		this.serializer = serializer;
		this.resultItem = resultItem;
		this.resultCount = count;
	}

	public static GrinderRecipeBuilder recipeBuilder(IItemProvider result, int count) {
		return new GrinderRecipeBuilder(GrinderRecipe.SERIALIZER, result.asItem(), count);
	}

	public GrinderRecipeBuilder addIngredient(IItemProvider item) {
		return addIngredient(Ingredient.of(item));
	}

	public GrinderRecipeBuilder addIngredient(IItemProvider item, int count) {
		return addIngredient(Ingredient.of(item), count);
	}

	public GrinderRecipeBuilder addIngredient(ITag<Item> tag) {
		this.isTag = true;
		this.tag = tag.toString();
		this.inputCount = 1;
		return this;
	}

	public GrinderRecipeBuilder addIngredient(ITag<Item> tag, int count) {
		this.isTag = true;
		this.tag = tag.toString();
		this.inputCount = count;
		return this;
	}

	public GrinderRecipeBuilder addIngredient(Ingredient ingredient) {
		return addIngredient(ingredient, 1);
	}

	public GrinderRecipeBuilder addIngredient(Ingredient ingredient, int count) {
		this.ingredients.add(ingredient);
		this.inputCount = count;
		return this;
	}

	public void build(Consumer<IFinishedRecipe> consumer) {
		String name  = this.resultItem.getRegistryName().getPath();
		if (!isTag) build(consumer, new ResourceLocation(serializer.getRegistryName() + "/" + name + "_from_" + getIngredientName()));
		else build(consumer, new ResourceLocation(serializer.getRegistryName() + "/" + name + "_from_tag_" + tag.substring(tag.indexOf(':') + 1).replace("]", "")));
	}

	public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation recipeId) {
		consumer.accept(new Result(recipeId));
	}

	private String getIngredientName() {
		for (Ingredient ingredient : ingredients) {
			for (ItemStack stack : ingredient.getItems()) {
				return stack.getItem().getRegistryName().getPath();
			}
		}
		return null;
	}

	public class Result implements IFinishedRecipe {
		private final ResourceLocation recipeId;

		public Result(ResourceLocation recipeId) {
			this.recipeId = recipeId;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			if (isTag) json.add("input", serializeTag());
			else json.add("input", serializeIngredients());
			json.add("output", serializeResult());
		}

		private JsonObject serializeIngredients() {
			JsonObject ret = new JsonObject();
			for (Ingredient ingredient : ingredients) {
				for (ItemStack stack : ingredient.getItems()) {
					ret.addProperty("item", NameUtils.from(stack.getItem()).toString());
				}
			}
			ret.addProperty("count", inputCount);
			return ret;
		}
		
		private JsonObject serializeTag() {
			JsonObject ret = new JsonObject();
			ret.addProperty("tag", tag.substring(tag.indexOf("[") + 1).replace("]", ""));
			ret.addProperty("count", inputCount);
			return ret;
		}

		private JsonObject serializeResult() {
			JsonObject ret = new JsonObject();
			ret.addProperty("item", NameUtils.from(resultItem).toString());
			if (resultCount > 1) {
				ret.addProperty("count", resultCount);
			}
			return ret;
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

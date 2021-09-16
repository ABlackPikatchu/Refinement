package com.ablackpikatchu.refinement.data.common.recipes;

import java.util.ArrayList;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.common.recipe.MixerRecipe;
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

public class MixerRecipeBuilder {
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

	public MixerRecipeBuilder(Item resultItem, int count) {
		this.serializer = MixerRecipe.SERIALIZER;
		this.resultItem = resultItem;
		this.resultCount = count;
	}

	public static MixerRecipeBuilder recipeBuilder(IItemProvider result, int count) {
		return new MixerRecipeBuilder(result.asItem(), count);
	}

	public MixerRecipeBuilder addIngredient(IItemProvider item) {
		return addIngredient(Ingredient.of(item));
	}

	public MixerRecipeBuilder addIngredient(IItemProvider item, int count) {
		return addIngredient(Ingredient.of(item), count);
	}

	public MixerRecipeBuilder addIngredient(Ingredient ingredient) {
		return addIngredient(ingredient, 1);
	}

	public MixerRecipeBuilder addIngredient(Ingredient ingredient, int count) {
		this.ingredients.add(ingredient);
		this.inputCount = count;
		return this;
	}
	
	public MixerRecipeBuilder addIngredient(ITag<Item> tag, int count) {
		this.isTag = true;
		this.tag = tag.toString();
		this.inputCount = count;
		return this;
	}
	
	public MixerRecipeBuilder addSecondaryIngredient(ITag<Item> tag, int count) {
		this.isTag2 = true;
		this.tag2 = tag.toString();
		this.inputCount2 = count;
		return this;
	}
	
	public MixerRecipeBuilder addSecondaryIngredient(IItemProvider item) {
		return addSecondaryIngredient(Ingredient.of(item));
	}

	public MixerRecipeBuilder addSecondaryIngredient(IItemProvider item, int count) {
		return addSecondaryIngredient(Ingredient.of(item), count);
	}

	public MixerRecipeBuilder addSecondaryIngredient(Ingredient ingredient) {
		return addSecondaryIngredient(ingredient, 1);
	}

	public MixerRecipeBuilder addSecondaryIngredient(Ingredient ingredient, int count) {
		this.ingredients2.add(ingredient);
		this.inputCount2 = count;
		return this;
	}

	public void build(Consumer<IFinishedRecipe> consumer) {
		String name  = this.resultItem.getRegistryName().getPath();
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
			if (!isTag) json.add("input", serializeInput());
			else json.add("input", serializeTag(tag));
			if (!isTag2) json.add("secondary_input", serializeSecondaryInput());
			else json.add("secondary_input", serializeTag(tag2));
			json.add("output", serializeResult());
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
			return ret;
		}
		
		private JsonObject serializeTag(String tag) {
			JsonObject ret = new JsonObject();
			ret.addProperty("tag", tag.substring(tag.indexOf("[") + 1).replace("]", ""));
			ret.addProperty("count", inputCount);
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

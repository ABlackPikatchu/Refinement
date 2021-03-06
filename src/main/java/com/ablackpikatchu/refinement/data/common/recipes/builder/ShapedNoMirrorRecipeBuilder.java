package com.ablackpikatchu.refinement.data.common.recipes.builder;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.common.recipe.ShapedNoMirrorRecipe;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.shaped.KeyIngredient;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.shaped.Pattern;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class ShapedNoMirrorRecipeBuilder {
	
	private final IRecipeSerializer<?> serializer;

	private final Item result;
	private final int count;
	private final List<String> rows = Lists.newArrayList();
	private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
	private final Advancement.Builder advancement = Advancement.Builder.advancement();
	private String group;

	public ShapedNoMirrorRecipeBuilder(IItemProvider pResult, int pCount) {
		this.result = pResult.asItem();
		this.count = pCount;
		this.serializer = ShapedNoMirrorRecipe.SERIALIZER;
	}

	/**
	 * Creates a new builder for a shaped recipe.
	 */
	public static ShapedNoMirrorRecipeBuilder shaped(IItemProvider pResult) {
		return shaped(pResult, 1);
	}

	/**
	 * Creates a new builder for a shaped recipe.
	 */
	public static ShapedNoMirrorRecipeBuilder shaped(IItemProvider pResult, int pCount) {
		return new ShapedNoMirrorRecipeBuilder(pResult, pCount);
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public ShapedNoMirrorRecipeBuilder define(Character pSymbol, ITag<Item> pTag) {
		return this.define(pSymbol, Ingredient.of(pTag));
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public ShapedNoMirrorRecipeBuilder define(Character pSymbol, IItemProvider pItem) {
		return this.define(pSymbol, Ingredient.of(pItem));
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public ShapedNoMirrorRecipeBuilder define(Character pSymbol, Ingredient pIngredient) {
		if (this.key.containsKey(pSymbol)) {
			throw new IllegalArgumentException("Symbol '" + pSymbol + "' is already defined!");
		} else if (pSymbol == ' ') {
			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
		} else {
			this.key.put(pSymbol, pIngredient);
			return this;
		}
	}

	/**
	 * Adds a new entry to the patterns for this recipe.
	 */
	public ShapedNoMirrorRecipeBuilder pattern(String pPattern) {
		if (!this.rows.isEmpty() && pPattern.length() != this.rows.get(0).length()) {
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		} else {
			this.rows.add(pPattern);
			return this;
		}
	}
	
	public ShapedNoMirrorRecipeBuilder pattern(Pattern pattern) {
		pattern(pattern.getTop());
		pattern(pattern.getMiddle());
		pattern(pattern.getBottom());
		return this;
	}
	
	public ShapedNoMirrorRecipeBuilder setIngredients(KeyIngredient... ingredients) {
		for (KeyIngredient ingredient : ingredients) {
			ingredient.getShapedNoMirrorRecipe(this);
		}
		return this;
	}

	public ShapedNoMirrorRecipeBuilder unlockedBy(String p_200465_1_, ICriterionInstance p_200465_2_) {
		this.advancement.addCriterion(p_200465_1_, p_200465_2_);
		return this;
	}

	public ShapedNoMirrorRecipeBuilder group(String p_200473_1_) {
		this.group = p_200473_1_;
		return this;
	}

	public void save(Consumer<IFinishedRecipe> p_200464_1_) {
		this.save(p_200464_1_, new ResourceLocation(serializer.getRegistryName() + "/" + this.result.getRegistryName().getPath()));
	}

	public void save(Consumer<IFinishedRecipe> p_200466_1_, String p_200466_2_) {
		ResourceLocation resourcelocation = this.result.getRegistryName();
		if ((new ResourceLocation(p_200466_2_)).equals(resourcelocation)) {
			throw new IllegalStateException("Shaped Recipe " + p_200466_2_ + " should remove its 'save' argument");
		} else {
			this.save(p_200466_1_, new ResourceLocation(p_200466_2_));
		}
	}

	public void save(Consumer<IFinishedRecipe> p_200467_1_, ResourceLocation p_200467_2_) {
		this.ensureValid(p_200467_2_);
		p_200467_1_.accept(new ShapedNoMirrorRecipeBuilder.Result(p_200467_2_, this.result, this.count,
				this.group == null ? "" : this.group, this.rows, this.key, this.advancement,
				new ResourceLocation(p_200467_2_.getNamespace(), "recipes/"
						+ this.result.getItemCategory().getRecipeFolderName() + "/" + p_200467_2_.getPath())));
	}

	/**
	 * Makes sure that this recipe is valid and obtainable.
	 */
	private void ensureValid(ResourceLocation pId) {
		if (this.rows.isEmpty()) {
			throw new IllegalStateException("No pattern is defined for shaped recipe " + pId + "!");
		} else {
			Set<Character> set = Sets.newHashSet(this.key.keySet());
			set.remove(' ');

			for (String s : this.rows) {
				for (int i = 0; i < s.length(); ++i) {
					char c0 = s.charAt(i);
					if (!this.key.containsKey(c0) && c0 != ' ') {
						throw new IllegalStateException(
								"Pattern in recipe " + pId + " uses undefined symbol '" + c0 + "'");
					}

					set.remove(c0);
				}
			}

			if (!set.isEmpty()) {
				throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + pId);
			} else if (this.rows.size() == 1 && this.rows.get(0).length() == 1) {
				throw new IllegalStateException("Shaped recipe " + pId
						+ " only takes in a single item - should it be a shapeless recipe instead?");
			}
		}
	}

	public class Result implements IFinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final List<String> pattern;
		private final Map<Character, Ingredient> key;
		@SuppressWarnings("unused")
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;

		public Result(ResourceLocation p_i48271_2_, Item p_i48271_3_, int p_i48271_4_, String p_i48271_5_,
				List<String> p_i48271_6_, Map<Character, Ingredient> p_i48271_7_, Advancement.Builder p_i48271_8_,
				ResourceLocation p_i48271_9_) {
			this.id = p_i48271_2_;
			this.result = p_i48271_3_;
			this.count = p_i48271_4_;
			this.group = p_i48271_5_;
			this.pattern = p_i48271_6_;
			this.key = p_i48271_7_;
			this.advancement = p_i48271_8_;
			this.advancementId = p_i48271_9_;
		}

		public void serializeRecipeData(JsonObject pJson) {
			if (!this.group.isEmpty()) {
				pJson.addProperty("group", this.group);
			}

			JsonArray jsonarray = new JsonArray();

			for (String s : this.pattern) {
				jsonarray.add(s);
			}

			pJson.add("pattern", jsonarray);
			JsonObject jsonobject = new JsonObject();

			for (Entry<Character, Ingredient> entry : this.key.entrySet()) {
				jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
			}

			pJson.add("key", jsonobject);
			JsonObject jsonobject1 = new JsonObject();
			jsonobject1.addProperty("item", this.result.getRegistryName().toString());
			if (this.count > 1) {
				jsonobject1.addProperty("count", this.count);
			}

			pJson.add("result", jsonobject1);
		}

		public IRecipeSerializer<?> getType() {
			return serializer;
		}

		/**
		 * Gets the ID for the recipe.
		 */
		public ResourceLocation getId() {
			return this.id;
		}

		/**
		 * Gets the ID for the advancement associated with this recipe. Should not be
		 * null if {@link #getAdvancementJson} is non-null.
		 */
		@Nullable
		public ResourceLocation getAdvancementId() {
			return this.advancementId;
		}

		@Override
		public JsonObject serializeAdvancement() {
			// TODO Auto-generated method stub
			return null;
		}
	}

}

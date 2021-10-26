package com.ablackpikatchu.refinement.common.recipe;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistryEntry;

public class InWorldTierUpgradingRecipe extends AbstractModRecipe {
	
	public final ResourceLocation id;
	private final Ingredient tierUpgrader;
	private final Ingredient input;
	private final ItemStack output;
	
	public InWorldTierUpgradingRecipe(ResourceLocation id, Ingredient tierUpgrader, Ingredient input, ItemStack output) {
		this.id = id;
		this.tierUpgrader = tierUpgrader;
		this.input = input;
		this.output = output;
	}
	
	public static final Serializer SERIALIZER = new Serializer();

	@Override
	public ItemStack getResultItem() {
		return output;
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeInit.IN_WORLD_TIER_UPGRADING;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> i = NonNullList.create();
		i.add(tierUpgrader);
		i.add(input);
		return i;
	}
	
	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<InWorldTierUpgradingRecipe> {
		
		public Serializer() {
			this.setRegistryName(new ResourceLocation(MOD_ID, "in_world_tier_upgrading"));
		}

		@Override
		public InWorldTierUpgradingRecipe fromJson(ResourceLocation pRecipeId, JsonObject json) {
			final JsonElement inputEl = JSONUtils.isArrayNode(json, "input") ? JSONUtils.getAsJsonArray(json, "input")
					: JSONUtils.getAsJsonObject(json, "input");
			final JsonElement tierUpgraderEl = JSONUtils.isArrayNode(json, "tier_upgrader") ? JSONUtils.getAsJsonArray(json, "tier_upgrader")
					: JSONUtils.getAsJsonObject(json, "tier_upgrader");
			final ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
			final Ingredient input = Ingredient.fromJson(inputEl);
			final Ingredient tierUpgrader = Ingredient.fromJson(tierUpgraderEl);
			return new InWorldTierUpgradingRecipe(pRecipeId, tierUpgrader, input, output);
		}

		@Override
		public InWorldTierUpgradingRecipe fromNetwork(ResourceLocation pRecipeId, PacketBuffer buffer) {
			final Ingredient tierUpgrader = Ingredient.fromNetwork(buffer);
			final Ingredient input = Ingredient.fromNetwork(buffer);
			final ItemStack output = buffer.readItem();
			return new InWorldTierUpgradingRecipe(pRecipeId, tierUpgrader, input, output);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, InWorldTierUpgradingRecipe recipe) {
			recipe.tierUpgrader.toNetwork(buffer);
			recipe.input.toNetwork(buffer);
			buffer.writeItem(recipe.output);
		}

	}
	
	public static class InWorldTierUpgradingRecipeType implements IRecipeType<InWorldTierUpgradingRecipe> {
		@Override
		public String toString() {
			return new ResourceLocation(MOD_ID, "in_world_tier_upgrading").toString();
		}
	}

}

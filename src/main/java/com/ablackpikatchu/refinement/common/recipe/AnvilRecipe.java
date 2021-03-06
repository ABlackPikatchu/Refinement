package com.ablackpikatchu.refinement.common.recipe;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

import com.ablackpikatchu.refinement.api.recipe.AbstractRefinementRecipe;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.util.helper.JSONHelper;
import com.ablackpikatchu.refinement.datafixers.util.json.JSONInput;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistryEntry;

public class AnvilRecipe extends AbstractRefinementRecipe {
	
	public static final Serializer SERIALIZER = new Serializer();
	public static final ResourceLocation REGISTRY_NAME = new ResourceLocation(MOD_ID, "anvil");

	private final Ingredient left;
	private final int leftCount;
	private final Ingredient right;
	private final int rightCount;
	private final ItemStack output;
	private final int xpRequired;

	private final ResourceLocation id;

	public AnvilRecipe(ResourceLocation id, Ingredient left, int leftCount, Ingredient right, int rightCount, ItemStack output, int xpRequired) {
		this.id = id;
		this.left = left;
		this.leftCount = leftCount;
		this.right = right;
		this.rightCount = rightCount;
		this.output = output;
		this.xpRequired = xpRequired;
	}

	@Override
	public ItemStack getResultItem() {
		return this.output;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeInit.ANVIL_RECIPE;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> i = NonNullList.create();
		i.add(left);
		i.add(right);
		return i;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(Items.ANVIL);
	}

	public boolean isValid(ItemStack left, ItemStack right) {
		return (this.left.test(left) && this.leftCount <= left.getCount() && this.right.test(right) && this.rightCount <= right.getCount());
	}

	public int getLeftCount() {
		return this.leftCount;
	}
	
	public int getRightCount() {
		return this.rightCount;
	}
	
	public int getXPRequired() {
		return this.xpRequired;
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<AnvilRecipe> {
		public Serializer() {
			this.setRegistryName(new ResourceLocation(MOD_ID, "anvil"));
		}

		@Override
		public AnvilRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			JSONInput leftElement = JSONHelper.getInputWithCount(json, "left");
			Ingredient left = leftElement.getIngredient();
			int leftCount = leftElement.getCount();
			
			JSONInput rightElement = JSONHelper.getInputWithCount(json, "right");
			Ingredient right = rightElement.getIngredient();
			int rightCount = rightElement.getCount();
			
			final ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
			int xpRequired = 1;
			if (json.has("levels_required"))
				xpRequired = json.get("levels_required").getAsInt();
			return new AnvilRecipe(recipeId, left, leftCount, right, rightCount, output, xpRequired);
		}

		@Override
		public AnvilRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			final Ingredient left = Ingredient.fromNetwork(buffer);
			int leftCount = 1;
			for (ItemStack stack : left.getItems()) {
				leftCount = stack.getCount();
			}
			
			final Ingredient right = Ingredient.fromNetwork(buffer);
			int rightCount = 1;
			for (ItemStack stack : left.getItems()) {
				rightCount = stack.getCount();
			}
			
			final ItemStack output = buffer.readItem();
			final int xpRequired = buffer.readInt();
			return new AnvilRecipe(recipeId, left, leftCount, right, rightCount, output, xpRequired);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, AnvilRecipe recipe) {
			recipe.left.toNetwork(buffer);
			recipe.right.toNetwork(buffer);
			buffer.writeItem(recipe.output);
			buffer.writeInt(recipe.xpRequired);
		}
	}

}

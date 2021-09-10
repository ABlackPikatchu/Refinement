package com.ablackpikatchu.refinement.common.recipe;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class GrinderRecipe implements IRecipe<IInventory> {
	public static final Serializer SERIALIZER = new Serializer();

	private final Ingredient input;
	private final int count;
	private final ItemStack output;
	private final ResourceLocation id;
	private final Ingredient refinedCoal = Ingredient.of(new ItemStack(ItemInit.REFINED_CARBON_INGOT.get()));

	public GrinderRecipe(ResourceLocation id, Ingredient input, ItemStack output, int count) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.count = count;
	}

	@Override
	public boolean matches(IInventory p_77569_1_, World p_77569_2_) {
		return this.input.test(p_77569_1_.getItem(0));
	}

	@Override
	public ItemStack assemble(IInventory p_77572_1_) {
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
		return true;
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
		return RecipeInit.GRINDER_RECIPE;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> i = NonNullList.create();
		i.add(input);
		i.add(refinedCoal);
		return i;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(BlockInit.GRINDER.get().asItem());
	}
	
	public boolean isValid(ItemStack input) {
		return (this.input.test(input) && input.getCount() >= this.count);
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<GrinderRecipe> {
		public Serializer() {
			this.setRegistryName(Refinement.MOD_ID, "grinder");
		}

		@Override
		public GrinderRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			final JsonElement inputEl = JSONUtils.isArrayNode(json, "input") ? JSONUtils.getAsJsonArray(json, "input")
					: JSONUtils.getAsJsonObject(json, "input");
			final ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
			final Ingredient input = Ingredient.fromJson(inputEl);
			JsonObject object = JSONUtils.getAsJsonObject(json, "input");
			int actualCount = 1;
			if (object.has("count")) {
				int count = object.get("count").getAsInt();
                if (count > 1) {
                	actualCount = count;
                    for (ItemStack stack : input.getItems()) {
                        stack.setCount(count);
                    }
                }
			}
			return new GrinderRecipe(recipeId, input, output, actualCount);
		}

		@Override
		public GrinderRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			final Ingredient input = Ingredient.fromNetwork(buffer);
			int count = 1;
			for (ItemStack stack : input.getItems()) {
				count = stack.getCount();
			}
			final ItemStack output = buffer.readItem();
			return new GrinderRecipe(recipeId, input, output, count);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, GrinderRecipe recipe) {
			recipe.input.toNetwork(buffer);
			buffer.writeItem(recipe.output);
		}
	}

}
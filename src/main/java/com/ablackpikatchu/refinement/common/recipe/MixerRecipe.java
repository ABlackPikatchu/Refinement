package com.ablackpikatchu.refinement.common.recipe;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

import java.util.Map;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.util.InventoryUtils;
import com.google.common.collect.ImmutableMap.Builder;
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

public class MixerRecipe implements IRecipe<IInventory> {
	
	public static final Serializer SERIALIZER = new Serializer();
	public static final ResourceLocation REGISTRY_NAME = new ResourceLocation(MOD_ID, "mixer");

	private final Ingredient input;
	private final Ingredient secondaryInput;
	private final int inputCount;
	private final int secondaryInputCount;
	private final ItemStack output;
	private final ResourceLocation id;
	private final Ingredient refinedCoal = Ingredient.of(new ItemStack(ItemInit.REFINED_CARBON_INGOT.get()));
	
	private final Map<Ingredient, Integer> ingredientsMap;

	public MixerRecipe(ResourceLocation id, Ingredient input, Ingredient secondaryInput, ItemStack output,
			int inputCount, int secondaryInputCount) {
		this.id = id;
		this.input = input;
		this.secondaryInput = secondaryInput;
		this.inputCount = inputCount;
		this.secondaryInputCount = secondaryInputCount;
		this.output = output;
		ingredientsMap = (new Builder<Ingredient, Integer>()).put(input, inputCount).put(secondaryInput, secondaryInputCount).build();
	}
	
	@Override
	public boolean matches(IInventory inv, World pLevel) {
		for (Map.Entry<Ingredient, Integer> entry : ingredientsMap.entrySet()) {
			int required = entry.getValue();
			int found = InventoryUtils.getTotalCount(inv, entry.getKey());
			if (found < required) {
				return false;
			}
		}

		// Check for non-matching items
		for (int i = 0; i < 1; ++i) {
			ItemStack stack = inv.getItem(i);
			if (!stack.isEmpty()) {
				boolean foundMatch = false;
				for (Map.Entry<Ingredient, Integer> entry : ingredientsMap.entrySet()) {
					if (entry.getKey().test(stack)) {
						foundMatch = true;
						break;
					}
				}
				if (!foundMatch) {
					return false;
				}
			}
		}

		return true;
	}
	
	public void consumeIngredients(IInventory inv) {
		ingredientsMap.forEach(((ingredient, count) -> InventoryUtils.consumeItems(inv, ingredient, count)));
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
		return RecipeInit.MIXER_RECIPE;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> i = NonNullList.create();
		i.add(input);
		i.add(secondaryInput);
		i.add(refinedCoal);
		return i;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(BlockInit.MIXER.get().asItem());
	}

	public boolean isValid(ItemStack input, ItemStack input2) {
		return (this.input.test(input) && this.secondaryInput.test(input2) && input.getCount() >= this.inputCount
				&& input2.getCount() >= this.secondaryInputCount);
	}
	
	public int getInputCount() {
		return this.inputCount;
	}
	
	public int getSecondaryInputCount() {
		return this.secondaryInputCount;
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<MixerRecipe> {
		public Serializer() {
			this.setRegistryName(new ResourceLocation(MOD_ID, "mixer"));
		}

		@Override
		public MixerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			final JsonElement inputEl = JSONUtils.isArrayNode(json, "input") ? JSONUtils.getAsJsonArray(json, "input")
					: JSONUtils.getAsJsonObject(json, "input");
			final JsonElement inputEl2 = JSONUtils.isArrayNode(json, "secondary_input")
					? JSONUtils.getAsJsonArray(json, "secondary_input")
					: JSONUtils.getAsJsonObject(json, "secondary_input");
			final ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
			final Ingredient input = Ingredient.fromJson(inputEl);
			final Ingredient input2 = Ingredient.fromJson(inputEl2);
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
			JsonObject object2 = JSONUtils.getAsJsonObject(json, "secondary_input");
			int actualCount2 = 1;
			if (object2.has("count")) {
				int count = object2.get("count").getAsInt();
				if (count > 1) {
					actualCount2 = count;
					for (ItemStack stack : input2.getItems()) {
						stack.setCount(count);
					}
				}
			}
			return new MixerRecipe(recipeId, input, input2, output, actualCount, actualCount2);
		}

		@Override
		public MixerRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			final Ingredient input = Ingredient.fromNetwork(buffer);
			int count = 1;
			for (ItemStack stack : input.getItems()) {
				count = stack.getCount();
			}
			final Ingredient input2 = Ingredient.fromNetwork(buffer);
			int count2 = 1;
			for (ItemStack stack : input2.getItems()) {
				count2 = stack.getCount();
			}
			final ItemStack output = buffer.readItem();
			return new MixerRecipe(recipeId, input, input2, output, count, count2);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, MixerRecipe recipe) {
			recipe.input.toNetwork(buffer);
			recipe.secondaryInput.toNetwork(buffer);
			buffer.writeItem(recipe.output);
		}
	}

	@Override
	public ItemStack assemble(IInventory p_77572_1_) {
		return ItemStack.EMPTY;
	}
}

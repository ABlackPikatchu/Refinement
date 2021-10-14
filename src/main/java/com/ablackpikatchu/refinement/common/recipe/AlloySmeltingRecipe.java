package com.ablackpikatchu.refinement.common.recipe;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.util.InventoryUtils;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AlloySmeltingRecipe extends AbstractModRecipe {

	public static final Serializer SERIALIZER = new Serializer();

	private final ResourceLocation id;
	private final Map<Ingredient, Integer> ingredients = new LinkedHashMap<>();
	private final ItemStack output;
	private final int processTime;

	public AlloySmeltingRecipe(ResourceLocation id, ItemStack output, int processTime) {
		this.id = id;
		this.output = output;
		this.processTime = processTime;
	}
	
	public int getProcessTime() {
		return processTime;
	}

	public void consumeIngredients(IInventory inv) {
		ingredients.forEach(((ingredient, count) -> InventoryUtils.consumeItems(inv, ingredient, count)));
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(BlockInit.ALLOY_SMELTER_BLOCK.get());
	}

	@Override
	public boolean matches(IInventory inv, World pLevel) {
		for (Ingredient ingredient : ingredients.keySet()) {
			int required = ingredients.get(ingredient);
			int found = InventoryUtils.getTotalCount(inv, ingredient);
			if (found < required) {
				return false;
			}
		}

		// Check for non-matching items
		for (int i = 0; i < 4; ++i) {
			ItemStack stack = inv.getItem(i);
			if (!stack.isEmpty()) {
				boolean foundMatch = false;
				for (Ingredient ingredient : ingredients.keySet()) {
					if (ingredient.test(stack)) {
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

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.create();
		getIngredientMap().forEach((ingredient, count) -> {
			for (ItemStack stack : ingredient.getItems()) {
				stack.setCount(count);
			}
			if (!ingredient.isEmpty() || ingredient != null)
				ingredients.add(ingredient);
		});
		for (int i = 0; i <= 3; i++) {
			if (ingredients.size() - 1 < i) {
				ingredients.add(Ingredient.EMPTY);
			}
		}
		ingredients.add(Ingredient.of(ItemInit.REFINED_CARBON_INGOT.get()));
		return ingredients;
	}

	public Map<Ingredient, Integer> getIngredientMap() {
		return ImmutableMap.copyOf(ingredients);
	}

	@Override
	public ItemStack getResultItem() {
		return this.output.copy();
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
		return RecipeInit.ALLOY_SMELTING_RECIPE;
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<AlloySmeltingRecipe> {

		public Serializer() {
			this.setRegistryName(new ResourceLocation(MOD_ID, "alloy_smelting"));
		}

		@Override
		public AlloySmeltingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
			int processTime = 400;
			if (json.has("process_time")) processTime = json.get("process_time").getAsInt();
			AlloySmeltingRecipe recipe = new AlloySmeltingRecipe(recipeId, output, processTime);

			JSONUtils.getAsJsonArray(json, "ingredients").forEach(element -> {
				Ingredient ingredient = CraftingHelper.getIngredient(element);

				JsonObject object = element.getAsJsonObject();
				int actualCount = 1;
				if (object.has("count")) {
					int count = object.get("count").getAsInt();
					if (count > 1) {
						actualCount = count;
						for (ItemStack stack : ingredient.getItems()) {
							stack.setCount(count);
						}
					}
				}
				recipe.ingredients.put(ingredient, actualCount);
			});

			return recipe;
		}

		@Override
		public AlloySmeltingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			AlloySmeltingRecipe recipe = new AlloySmeltingRecipe(recipeId, buffer.readItem(), buffer.readInt());

			int ingredientCount = buffer.readByte();
			for (int i = 0; i < ingredientCount; ++i) {
				Ingredient ingredient = Ingredient.fromNetwork(buffer);
				int count = buffer.readByte();
				recipe.ingredients.put(ingredient, count);
			}

			return recipe;
		}

		@Override
		public void toNetwork(PacketBuffer buffer, AlloySmeltingRecipe recipe) {
			buffer.writeItem(recipe.output);
			buffer.writeInt(recipe.processTime);
			
			buffer.writeByte(recipe.ingredients.size());
			recipe.ingredients.forEach((ingredient, count) -> {
				ingredient.toNetwork(buffer);
				buffer.writeByte(count);
			});
		}

	}

	public static class AlloySmeltingRecipeType implements IRecipeType<AlloySmeltingRecipe> {
		@Override
		public String toString() {
			return Refinement.MOD_ID + ":alloy_smelting";
		}
	}

}

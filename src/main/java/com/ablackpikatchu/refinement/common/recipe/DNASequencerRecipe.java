package com.ablackpikatchu.refinement.common.recipe;

import java.util.Random;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.util.helper.JSONHelper;
import com.ablackpikatchu.refinement.datafixers.util.json.JSONInput;
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

public class DNASequencerRecipe implements IRecipe<IInventory> {

	public static final Serializer SERIALIZER = new Serializer();

	private final Ingredient input;
	private final Ingredient secondaryInput;
	private final ItemStack output;
	private final int inputCount;
	private final int secondaryInputCount;
	private int successProbability;
	private final boolean noFuelRequired;
	private final ResourceLocation id;
	private final Ingredient refinedCoal = Ingredient.of(new ItemStack(ItemInit.REFINED_CARBON_INGOT.get()));

	private Random rand = new Random();

	public DNASequencerRecipe(ResourceLocation id, Ingredient input, Ingredient secondaryInput, ItemStack output,
			int inputCount, int secondaryInputCount, int successProbability, boolean noFuelRequired) {
		this.id = id;
		this.input = input;
		this.secondaryInput = secondaryInput;
		this.output = output;
		this.inputCount = inputCount;
		this.secondaryInputCount = secondaryInputCount;
		this.successProbability = successProbability;
		this.noFuelRequired = noFuelRequired;
	}

	@Override
	public boolean matches(IInventory p_77569_1_, World p_77569_2_) {
		return false;
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
		return RecipeInit.DNA_SEQUENCER_RECIPE;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> i = NonNullList.create();
		i.add(input);
		i.add(secondaryInput);
		if (this.noFuelRequired)
			i.add(Ingredient.EMPTY);
		else
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

	public int getSuccessProbability() {
		return this.successProbability;
	}

	public boolean isOutputSuccess() {
		return ((rand.nextInt(100) + 1) <= this.successProbability);
	}

	public boolean isFuelRequired() {
		return !this.noFuelRequired;
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<DNASequencerRecipe> {
		public Serializer() {
			this.setRegistryName(Refinement.MOD_ID, "dna_sequencer");
		}

		@Override
		public DNASequencerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			JSONInput element1 = JSONHelper.getInputWithCount(json, "input");
			JSONInput element2 = JSONHelper.getInputWithCount(json, "secondary_input");
			Ingredient input1 = element1.getIngredient();
			Ingredient input2 = element2.getIngredient();
			int count1 = element1.getCount();
			int count2 = element2.getCount();
			final ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));

			JsonObject outputObject = JSONUtils.getAsJsonObject(json, "output");
			int successProbability = 100;
			if (outputObject.has("success_chance"))
				successProbability = outputObject.get("success_chance").getAsInt();
			boolean noFuelRequired = false;
			if (json.has("no_fuel_required"))
				noFuelRequired = json.get("no_fuel_required").getAsBoolean();
			return new DNASequencerRecipe(recipeId, input1, input2, output, count1, count2, successProbability,
					noFuelRequired);
		}

		@Override
		public DNASequencerRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
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
			final int successProbability = buffer.readInt();
			final boolean noFuelRequired = buffer.readBoolean();
			return new DNASequencerRecipe(recipeId, input, input2, output, count, count2, successProbability,
					noFuelRequired);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, DNASequencerRecipe recipe) {
			recipe.input.toNetwork(buffer);
			recipe.secondaryInput.toNetwork(buffer);
			buffer.writeItem(recipe.output);
			buffer.writeInt(recipe.successProbability);
			buffer.writeBoolean(recipe.noFuelRequired);
		}
	}

}

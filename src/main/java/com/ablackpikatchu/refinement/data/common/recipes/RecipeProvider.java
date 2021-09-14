package com.ablackpikatchu.refinement.data.common.recipes;

import java.util.HashMap;
import java.util.function.Consumer;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.TagInit;
import com.ablackpikatchu.refinement.data.maps.RecipeMaps;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class RecipeProvider extends net.minecraft.data.RecipeProvider {

	public static HashMap<Block, Item> SHAPELESS_BLOCK_INGOT = new HashMap<>();
	public static HashMap<Item, Item> SHAPELESS_INGOT_NUGGET = new HashMap<>();

	public static HashMap<Item, Item> SANDING_DUST = new HashMap<>();
	public static HashMap<Item, Item> MIXING_DUST = new HashMap<>();

	public static HashMap<Item, Item> HELMETS = new HashMap<>();
	public static HashMap<Item, Item> CHESTPLATES = new HashMap<>();
	public static HashMap<Item, Item> LEGGINGS = new HashMap<>();
	public static HashMap<Item, Item> BOOTS = new HashMap<>();

	public static HashMap<Item, Item> AXES = new HashMap<>();
	public static HashMap<Item, Item> PICKAXES = new HashMap<>();
	public static HashMap<Item, Item> SHOVELS = new HashMap<>();
	public static HashMap<Item, Item> HOES = new HashMap<>();
	public static HashMap<Item, Item> SWORDS = new HashMap<>();

	public static HashMap<ItemStack, Pair<Item, Integer>> GRINDER_RECIPES = new HashMap<>();
	public static HashMap<Pair<ITag<Item>, Integer>, Pair<Item, Integer>> GRINDER_RECIPES_TAG = new HashMap<>();

	public static HashMap<Pair<Pair<Item, Integer>, Pair<Item, Integer>>, Pair<Item, Integer>> MIXER_RECIPES = new HashMap<>();

	public RecipeProvider(DataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		RecipeMaps.addShapelessBlockIngotEntries(SHAPELESS_BLOCK_INGOT);
		SHAPELESS_BLOCK_INGOT.forEach((block, item) -> {
			ShapelessRecipeBuilder.shapeless(block).requires(item, 9).unlockedBy("has_item", has(item)).save(consumer,
					storageBlocks(item.getRegistryName().getPath() + "_to_" + block.getRegistryName().getPath()));
			ShapelessRecipeBuilder.shapeless(item, 9).requires(block.asItem())
					.unlockedBy("has_item", has(block.asItem())).save(consumer, storageBlocks(
							block.getRegistryName().getPath() + "_to_" + item.getRegistryName().getPath()));
		});

		RecipeMaps.addShapelessIngotNuggetEntries(SHAPELESS_INGOT_NUGGET);
		SHAPELESS_INGOT_NUGGET.forEach((ingot, nugget) -> {
			ShapelessRecipeBuilder.shapeless(ingot).requires(nugget, 9).unlockedBy("has_item", has(nugget)).save(
					consumer, nuggets(nugget.getRegistryName().getPath() + "_to_" + ingot.getRegistryName().getPath()));
			ShapelessRecipeBuilder.shapeless(nugget, 9).requires(ingot).unlockedBy("has_item", has(ingot)).save(
					consumer, nuggets(ingot.getRegistryName().getPath() + "_to_" + nugget.getRegistryName().getPath()));
		});

		RecipeMaps.addDustFromSanding(SANDING_DUST);
		SANDING_DUST.forEach((input, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("i").pattern("p").define('p', TagInit.Items.GRIT_PAPERS)
					.define('i', input).unlockedBy("has_item", has(input)).save(consumer, sanding(
							"item/" + input.getRegistryName().getPath() + "_to_" + output.getRegistryName().getPath()));
		});

		RecipeMaps.addArmours(HELMETS, CHESTPLATES, LEGGINGS, BOOTS);
		HELMETS.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("###").pattern("# #").define('#', material)
					.unlockedBy("has_item", has(material))
					.save(consumer, armour("helmets/" + output.getRegistryName().getPath()));
		});
		CHESTPLATES.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("# #").pattern("###").pattern("###").define('#', material)
					.unlockedBy("has_item", has(material))
					.save(consumer, armour("chestplates/" + output.getRegistryName().getPath()));
		});
		LEGGINGS.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("###").pattern("# #").pattern("# #").define('#', material)
					.unlockedBy("has_item", has(material))
					.save(consumer, armour("leggings/" + output.getRegistryName().getPath()));
		});
		BOOTS.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("# #").pattern("# #").define('#', material)
					.unlockedBy("has_item", has(material))
					.save(consumer, armour("boots/" + output.getRegistryName().getPath()));
		});

		RecipeMaps.addTools(AXES, PICKAXES, SHOVELS, HOES, SWORDS);
		AXES.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("##").pattern("#S").pattern(" S").define('#', material)
					.define('S', Items.STICK).unlockedBy("has_item", has(material))
					.save(consumer, tools("axes/" + output.getRegistryName().getPath()));
		});

		PICKAXES.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("###").pattern(" S ").pattern(" S ").define('#', material)
					.define('S', Items.STICK).unlockedBy("has_item", has(material))
					.save(consumer, tools("pickaxes/" + output.getRegistryName().getPath()));
		});

		SHOVELS.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern(" # ").pattern(" S ").pattern(" S ").define('#', material)
					.define('S', Items.STICK).unlockedBy("has_item", has(material))
					.save(consumer, tools("shovels/" + output.getRegistryName().getPath()));
		});

		HOES.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("## ").pattern(" S ").pattern(" S ").define('#', material)
					.define('S', Items.STICK).unlockedBy("has_item", has(material))
					.save(consumer, tools("hoes/" + output.getRegistryName().getPath()));
		});

		SWORDS.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern(" # ").pattern(" # ").pattern(" S ").define('#', material)
					.define('S', Items.STICK).unlockedBy("has_item", has(material))
					.save(consumer, tools("swords/" + output.getRegistryName().getPath()));
		});

		RecipeMaps.addMixing(MIXING_DUST);
		MIXING_DUST.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern(" # ").pattern(" D ").pattern(" B ")
					.define('D', ItemInit.REFINING_DUST.get()).define('#', material)
					.define('B', ItemInit.MIXING_BOWL.get()).unlockedBy("has_item", has(material))
					.save(consumer, mixing_bowl("refined_dusts/" + output.getRegistryName().getPath()));
		});

		RecipeMaps.addGrinderRecipes(GRINDER_RECIPES, GRINDER_RECIPES_TAG);
		GRINDER_RECIPES.forEach((input, output) -> {
			GrinderRecipeBuilder.recipeBuilder(output.getFirst(), output.getSecond())
					.addIngredient(Ingredient.of(input), input.getCount()).build(consumer);
		});
		GRINDER_RECIPES_TAG.forEach((input, output) -> {
			GrinderRecipeBuilder.recipeBuilder(output.getFirst(), output.getSecond())
					.addIngredient(input.getFirst(), input.getSecond()).build(consumer);
		});

		RecipeMaps.addMixerRecipes(MIXER_RECIPES);
		MIXER_RECIPES.forEach((inputPair, output) -> {
			MixerRecipeBuilder.recipeBuilder(output.getFirst(), output.getSecond())
					.addIngredient(inputPair.getFirst().getFirst(), inputPair.getFirst().getSecond())
					.addSecondaryIngredient(inputPair.getSecond().getFirst(), inputPair.getSecond().getSecond())
					.build(consumer);
		});

	}

	public static ResourceLocation storageBlocks(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "storage_blocks/" + name);
	}

	public static ResourceLocation nuggets(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "nuggets/" + name);
	}

	public static ResourceLocation sanding(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "sanding/" + name);
	}

	public static ResourceLocation armour(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "armours/" + name);
	}

	public static ResourceLocation tools(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "tools/" + name);
	}

	public static ResourceLocation mixing_bowl(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "mixing_bowl/" + name);
	}

}

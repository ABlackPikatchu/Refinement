package com.ablackpikatchu.refinement.data.common;

import java.util.HashMap;
import java.util.function.Consumer;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.TagInit;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class RecipeProvider extends net.minecraft.data.RecipeProvider {

	public static HashMap<Block, Item> SHAPELESS_BLOCK_INGOT = new HashMap<>();
	public static HashMap<Item, Item> SHAPELESS_INGOT_NUGGET = new HashMap<>();

	public static HashMap<Item, Item> SANDING_DUST = new HashMap<>();
	public static HashMap<ITag<Item>, Item> SANDING_DUST_TAG = new HashMap<>();

	public RecipeProvider(DataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		addShapelessBlockIngotEntries(SHAPELESS_BLOCK_INGOT);
		SHAPELESS_BLOCK_INGOT.forEach((block, item) -> {
			ShapelessRecipeBuilder.shapeless(block).requires(item, 9).unlockedBy("has_item", has(item)).save(consumer,
					storageBlocks(item.getRegistryName().getPath() + "_to_" + block.getRegistryName().getPath()));
			ShapelessRecipeBuilder.shapeless(item, 9).requires(block.asItem())
					.unlockedBy("has_item", has(block.asItem())).save(consumer, storageBlocks(
							block.getRegistryName().getPath() + "_to_" + item.getRegistryName().getPath()));
		});

		addShapelessIngotNuggetEntries(SHAPELESS_INGOT_NUGGET);
		SHAPELESS_INGOT_NUGGET.forEach((ingot, nugget) -> {
			ShapelessRecipeBuilder.shapeless(ingot).requires(nugget, 9).unlockedBy("has_item", has(nugget)).save(
					consumer, nuggets(nugget.getRegistryName().getPath() + "_to_" + ingot.getRegistryName().getPath()));
			ShapelessRecipeBuilder.shapeless(nugget, 9).requires(ingot).unlockedBy("has_item", has(ingot)).save(
					consumer, nuggets(ingot.getRegistryName().getPath() + "_to_" + nugget.getRegistryName().getPath()));
		});

		addDustFromSanding(SANDING_DUST, SANDING_DUST_TAG);
		SANDING_DUST.forEach((input, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("i").pattern("p").define('p', TagInit.Items.GRIT_PAPERS)
					.define('i', input).unlockedBy("has_item", has(input)).save(consumer, sanding(
							"item/" + input.getRegistryName().getPath() + "_to_" + output.getRegistryName().getPath()));
		});
		SANDING_DUST_TAG.forEach((input, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("i").pattern("p").define('p', TagInit.Items.GRIT_PAPERS)
					.define('i', input).unlockedBy("has_item", has(input))
					.save(consumer, sanding("tags/" + input.getValues().get(0).getRegistryName().getPath() + "_to_"
							+ output.getRegistryName().getPath()));
		});
	}

	public void addShapelessBlockIngotEntries(HashMap<Block, Item> map) {
		map.put(BlockInit.REFINED_CARBON_BLOCK.get(), ItemInit.REFINED_CARBON_INGOT.get());
		map.put(BlockInit.REFINED_IRON_BLOCK.get(), ItemInit.REFINED_IRON_INGOT.get());
		map.put(BlockInit.REFINED_GOLD_BLOCK.get(), ItemInit.REFINED_GOLD_INGOT.get());
		map.put(BlockInit.REFINED_DIAMOND_BLOCK.get(), ItemInit.REFINED_DIAMOND.get());
		map.put(BlockInit.REFINED_NETHERITE_BLOCK.get(), ItemInit.REFINED_NETHERITE_INGOT.get());
	}

	public void addShapelessIngotNuggetEntries(HashMap<Item, Item> map) {
		map.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_NUGGET.get());
		map.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_NUGGET.get());
	}

	public void addDustFromSanding(HashMap<Item, Item> map, HashMap<ITag<Item>, Item> tagMap) {
		map.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_DUST.get());
		map.put(ItemInit.REFINED_CARBON_INGOT.get(), ItemInit.REFINED_CARBON_DUST.get());
		map.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_DUST.get());
		map.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_DUST.get());
		map.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_DUST.get());
		map.put(ItemInit.PURE_CRYSTAL.get(), ItemInit.REFINING_DUST.get());
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

}

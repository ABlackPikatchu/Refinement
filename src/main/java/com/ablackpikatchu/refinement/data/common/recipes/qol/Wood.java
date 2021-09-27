package com.ablackpikatchu.refinement.data.common.recipes.qol;

import java.util.HashMap;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.util.lists.ItemLists;

import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class Wood {

	public static class Shaped {

		protected static HashMap<ShapedRecipeBuilder, ResourceLocation> recipes = new HashMap<>();

		public static HashMap<ShapedRecipeBuilder, ResourceLocation> getRecipes() {

			for (Item log : ItemLists.LOGS) {
				Item wood = ForgeRegistries.ITEMS.getValue(new ResourceLocation(log.getRegistryName().getNamespace(),
						log.getRegistryName().getPath().replace("log", "wood")));
				Item planks = ForgeRegistries.ITEMS.getValue(new ResourceLocation(log.getRegistryName().getNamespace(),
						log.getRegistryName().getPath().replace("log", "planks")));
				Item stairs = ForgeRegistries.ITEMS.getValue(new ResourceLocation(log.getRegistryName().getNamespace(),
						log.getRegistryName().getPath().replace("log", "stairs")));
				Item slab = ForgeRegistries.ITEMS.getValue(new ResourceLocation(log.getRegistryName().getNamespace(),
						log.getRegistryName().getPath().replace("log", "slab")));
				addOverrideWood(log, wood);
				addOverrideWood(wood, log);
				addOverrideStair(planks, stairs);
				addSlabToPlanks(slab, planks);
			}
			;

			addOverrideWood(Items.WARPED_STEM, Items.STRIPPED_WARPED_STEM);
			addOverrideWood(Items.STRIPPED_WARPED_STEM, Items.WARPED_STEM);
			addOverrideStair(Items.WARPED_PLANKS, Items.WARPED_STAIRS);
			addSlabToPlanks(Items.WARPED_SLAB, Items.WARPED_PLANKS);

			addOverrideWood(Items.CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM);
			addOverrideWood(Items.STRIPPED_CRIMSON_STEM, Items.CRIMSON_STEM);
			addOverrideStair(Items.CRIMSON_PLANKS, Items.CRIMSON_STAIRS);
			addSlabToPlanks(Items.CRIMSON_SLAB, Items.CRIMSON_PLANKS);

			return recipes;
		}

		private static void addOverrideWood(Item input, Item output) {
			recipes.put(new ShapedRecipeBuilder(output, 4).pattern("LL").pattern("LL").define('L', input)
					.unlockedBy("has_item", has(input)), MCName(output.getRegistryName().getPath()));
		}

		private static void addOverrideStair(Item input, Item output) {
			recipes.put(new ShapedRecipeBuilder(output, 8).pattern("L  ").pattern("LL ").pattern("LLL")
					.define('L', input).unlockedBy("has_item", has(input)), MCName(output.getRegistryName().getPath()));
			recipes.put(new ShapedRecipeBuilder(input, 3).pattern("LL").pattern("LL").define('L', output)
					.unlockedBy("has_item", has(output)), name("planks/stairs/" + input.getRegistryName().getPath()));
		}

		private static void addSlabToPlanks(Item input, Item output) {
			recipes.put(new ShapedRecipeBuilder(output, 1).pattern("L").pattern("L").define('L', input)
					.unlockedBy("has_item", has(input)), name("planks/slabs/" + output.getRegistryName().getPath()));
		}

	}

	public static class Shapeless {

		protected static HashMap<ShapelessRecipeBuilder, ResourceLocation> recipes = new HashMap<>();

		public static HashMap<ShapelessRecipeBuilder, ResourceLocation> getRecipes() {

			for (Item log : ItemLists.LOGS) {
				Item strippedLog = ForgeRegistries.ITEMS.getValue(new ResourceLocation(
						log.getRegistryName().getNamespace(), "stripped_" + log.getRegistryName().getPath()));

				Item wood = ForgeRegistries.ITEMS.getValue(new ResourceLocation(log.getRegistryName().getNamespace(),
						log.getRegistryName().getPath().replace("log", "wood")));
				Item strippedWood = ForgeRegistries.ITEMS.getValue(new ResourceLocation(
						wood.getRegistryName().getNamespace(), "stripped_" + wood.getRegistryName().getPath()));

				addStripped(log, strippedLog);
				addStripped(wood, strippedWood);
			};
			
			addStripped(Items.WARPED_STEM, Items.STRIPPED_WARPED_STEM);
			addStripped(Items.CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM);

			return recipes;
		}

		private static void addStripped(Item input, Item output) {
			recipes.put(new ShapelessRecipeBuilder(output, 1).requires(input).requires(Items.FLINT)
					.unlockedBy("has_item", has(input)), name("stripped/" + output.getRegistryName().getPath()));
		}
	}

	private static ResourceLocation name(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "qol/wood/" + name);
	}

	private static ResourceLocation MCName(String name) {
		return new ResourceLocation("minecraft", name);
	}

	protected static InventoryChangeTrigger.Instance has(IItemProvider p_200403_0_) {
		return inventoryTrigger(ItemPredicate.Builder.item().of(p_200403_0_).build());
	}

	protected static InventoryChangeTrigger.Instance inventoryTrigger(ItemPredicate... p_200405_0_) {
		return new InventoryChangeTrigger.Instance(EntityPredicate.AndPredicate.ANY, MinMaxBounds.IntBound.ANY,
				MinMaxBounds.IntBound.ANY, MinMaxBounds.IntBound.ANY, p_200405_0_);
	}

}

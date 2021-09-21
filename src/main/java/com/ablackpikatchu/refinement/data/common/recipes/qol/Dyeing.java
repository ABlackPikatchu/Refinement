package com.ablackpikatchu.refinement.data.common.recipes.qol;

import java.util.HashMap;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.TagInit;
import com.ablackpikatchu.refinement.core.util.lists.ItemLists;

import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class Dyeing {

	public static class Shaped {

		protected static HashMap<ShapedRecipeBuilder, ResourceLocation> recipes = new HashMap<>();

		public static HashMap<ShapedRecipeBuilder, ResourceLocation> getRecipes() {

			for (Item dye : ItemLists.DYES) {
				Item terracotaOutput = ForgeRegistries.ITEMS.getValue(new ResourceLocation(dye.getRegistryName().getNamespace(),
						dye.getRegistryName().getPath().replace("dye", "terracotta")));
				addOverrideTerracotaRecipe(dye, terracotaOutput);
				
				Item glassOutput = ForgeRegistries.ITEMS.getValue(new ResourceLocation(dye.getRegistryName().getNamespace(),
						dye.getRegistryName().getPath().replace("dye", "stained_glass")));
				addOverrideGlassRecipe(dye, glassOutput);
				
				Item glassPaneOutput = ForgeRegistries.ITEMS.getValue(new ResourceLocation(dye.getRegistryName().getNamespace(),
						dye.getRegistryName().getPath().replace("dye", "stained_glass_pane")));
				addOverrideGlassPaneRecipe(dye, glassPaneOutput);
			};

			return recipes;
		}

		private static void addOverrideTerracotaRecipe(Item dye, Item output) {
			recipes.put(
					new ShapedRecipeBuilder(output, 8).pattern("TTT").pattern("TDT").pattern("TTT")
							.define('T', TagInit.Items.TERRACOTTAS).define('D', dye).unlockedBy("has_item", has(dye)),
					MCName(output.getRegistryName().getPath()));
		}
		
		private static void addOverrideGlassRecipe(Item dye, Item output) {
			recipes.put(
					new ShapedRecipeBuilder(output, 8).pattern("GGG").pattern("GDG").pattern("GGG")
							.define('G', TagInit.Items.MC_GLASS).define('D', dye).unlockedBy("has_item", has(dye)),
					MCName(output.getRegistryName().getPath()));
		}
		
		private static void addOverrideGlassPaneRecipe(Item dye, Item output) {
			recipes.put(
					new ShapedRecipeBuilder(output, 8).pattern("GGG").pattern("GDG").pattern("GGG")
							.define('G', TagInit.Items.MC_GLASS_PANE).define('D', dye).unlockedBy("has_item", has(dye)),
					MCName(output.getRegistryName().getPath() + "_from_glass_pane"));
		}
	}

	public static class Shapeless {

		protected static HashMap<ShapelessRecipeBuilder, ResourceLocation> recipes = new HashMap<>();

		public static HashMap<ShapelessRecipeBuilder, ResourceLocation> getRecipes() {

			for (Item dye : ItemLists.DYES) {
				Item output = ForgeRegistries.ITEMS.getValue(new ResourceLocation(dye.getRegistryName().getNamespace(),
						dye.getRegistryName().getPath().replace("dye", "concrete_powder")));
				addConcreteRecipe(dye, output);
				
				Item woolOutput = ForgeRegistries.ITEMS.getValue(new ResourceLocation(dye.getRegistryName().getNamespace(),
						dye.getRegistryName().getPath().replace("dye", "wool")));
				addOverrideWoolRecipe(dye, woolOutput);
			};

			return recipes;
		}

		private static void addConcreteRecipe(Item dye, Item output) {
			recipes.put(
					new ShapelessRecipeBuilder(output, 8).requires(Ingredient.of(TagInit.Items.CONCRETE_POWDERS), 8)
							.requires(dye).unlockedBy("has_item", has(dye)),
					name("concrete_powder/" + output.getRegistryName().getPath()));
		}
		
		private static void addOverrideWoolRecipe(Item dye, Item output) {
			recipes.put(
					new ShapelessRecipeBuilder(output, 1).requires(Ingredient.of(TagInit.Items.WOOL))
							.requires(dye).unlockedBy("has_item", has(dye)),
					MCName(output.getRegistryName().getPath()));
		}
	}

	private static ResourceLocation name(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "qol/dyeing/" + name);
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

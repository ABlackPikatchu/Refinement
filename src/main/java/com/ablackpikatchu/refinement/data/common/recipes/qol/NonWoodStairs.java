package com.ablackpikatchu.refinement.data.common.recipes.qol;

import java.util.HashMap;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.util.lists.ItemLists;

import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class NonWoodStairs {

	public static class Shaped {

		protected static HashMap<ShapedRecipeBuilder, ResourceLocation> recipes = new HashMap<>();

		public static HashMap<ShapedRecipeBuilder, ResourceLocation> getRecipes() {

			for (Item stairs : ItemLists.NON_WOOD_STAIRS) {
				Item unCrafting = ForgeRegistries.ITEMS
						.getValue(new ResourceLocation(stairs.getRegistryName().getNamespace(),
								stairs.getRegistryName().getPath().replace("_stairs", "")));
				if (unCrafting != Items.AIR)
					addOverrideStair(unCrafting, stairs);
			}

			return recipes;
		}

		private static void addOverrideStair(Item input, Item output) {
			recipes.put(new ShapedRecipeBuilder(output, 8).pattern("L  ").pattern("LL ").pattern("LLL")
					.define('L', input).unlockedBy("has_item", has(input)), MCName(output.getRegistryName().getPath()));
			recipes.put(new ShapedRecipeBuilder(input, 3).pattern("LL").pattern("LL").define('L', output)
					.unlockedBy("has_item", has(output)), name("uncrafting/" + input.getRegistryName().getPath()));
		}
	}

	private static ResourceLocation name(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "qol/stairs/" + name);
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

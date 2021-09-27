package com.ablackpikatchu.refinement.data.common.recipes.qol;

import java.util.HashMap;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.util.lists.ItemLists;

import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class NonWoodSlabs {

	public static class Shapeless {

		protected static HashMap<ShapelessRecipeBuilder, ResourceLocation> recipes = new HashMap<>();

		public static HashMap<ShapelessRecipeBuilder, ResourceLocation> getRecipes() {

			for (Item slab : ItemLists.NON_WOOD_SLABS) {
				Item unCrafting = ForgeRegistries.ITEMS.getValue(new ResourceLocation(
						slab.getRegistryName().getNamespace(), slab.getRegistryName().getPath().replace("_slab", "")));
				if (unCrafting != Items.AIR)
					addSlab(slab, unCrafting);
			}

			return recipes;
		}

		private static void addSlab(Item input, Item output) {
			recipes.put(new ShapelessRecipeBuilder(output, 1).requires(input, 2).unlockedBy("has_item", has(input)),
					name("uncrafting/" + output.getRegistryName().getPath()));
		}
	}

	private static ResourceLocation name(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "qol/slabs/" + name);
	}

	@SuppressWarnings("unused")
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

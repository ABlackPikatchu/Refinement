package com.ablackpikatchu.refinement.core.util.helper;

import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

public class DataGenHelper {

	public static class Criterion {

		public static InventoryChangeTrigger.Instance has(IItemProvider item) {
			return inventoryTrigger(ItemPredicate.Builder.item().of(item).build());
		}
		
		public static InventoryChangeTrigger.Instance has(Ingredient ingredient) {
			return inventoryTrigger(ItemPredicate.Builder.item().of(ingredient.getItems()[0].getItem()).build());
		}

		protected static InventoryChangeTrigger.Instance inventoryTrigger(ItemPredicate... p_200405_0_) {
			return new InventoryChangeTrigger.Instance(EntityPredicate.AndPredicate.ANY, MinMaxBounds.IntBound.ANY,
					MinMaxBounds.IntBound.ANY, MinMaxBounds.IntBound.ANY, p_200405_0_);
		}
	}

}

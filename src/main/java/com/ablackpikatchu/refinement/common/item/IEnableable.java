package com.ablackpikatchu.refinement.common.item;

import com.ablackpikatchu.refinement.common.recipe.conditions.EnableableCondition;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

/**
 * Interface for making items enableable through a config
 * @author matyrobbrt
 *
 */
public interface IEnableable {
	boolean isEnabled();
	
	default IConditionSerializer<?> getCondition() {
		return EnableableCondition.Serializer.INSTANCE;
	}
	
	/**
	 * Fills the creative tabs (and JEI) of the item if the item is enabled
	 * @param item the item to fill in the creative tabs
	 * @param tab parse it from the fillItemCategory method
	 * @param items parse it from the fillItemCategory method
	 */
	default void fillCreativeTabs(Item item, ItemGroup tab, NonNullList<ItemStack> items) {
		if (isEnabled()) item.fillItemCategory(tab, items);
	}
}

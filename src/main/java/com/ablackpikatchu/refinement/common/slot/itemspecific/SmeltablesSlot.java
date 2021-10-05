package com.ablackpikatchu.refinement.common.slot.itemspecific;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.world.World;

public class SmeltablesSlot extends Slot {

	public ArrayList<Item> validItems = new ArrayList<>();
	public final World level;

	public SmeltablesSlot(IInventory pContainer, World level, int pIndex, int pX, int pY) {
		super(pContainer, pIndex, pX, pY);
		this.level = level;
		this.level.getRecipeManager().getAllRecipesFor(IRecipeType.SMELTING).forEach(recipe -> {
			recipe.getIngredients().forEach(ingredient -> {
				for (ItemStack item : ingredient.getItems()) {
					if (!validItems.contains(item.getItem()))
						validItems.add(item.getItem());
				}
			});
		});
	}

	@Override
	public boolean mayPlace(ItemStack pStack) {
		return validItems.contains(pStack.getItem());
	}

}

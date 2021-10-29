package com.ablackpikatchu.refinement.api.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public abstract class AbstractRefinementRecipe implements IRecipe<IInventory> {
	
	@Override
	public boolean matches(IInventory pInv, World pLevel) {
		return false;
	}
	
	@Override
	public ItemStack assemble(IInventory pInv) {
		return null;
	}
	
	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return false;
	}

}

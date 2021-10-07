package com.ablackpikatchu.refinement.core.util;

import com.ablackpikatchu.refinement.core.config.ModJsonConfigs;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Conversions {

	public static void convert(PlayerEntity player) {
		ModJsonConfigs.ORE_CONVERSION.getConversionRecipes().forEach(recipe -> {
			if (!recipe.getInput().isEmpty() && recipe.getOutput() != Items.AIR) {
				for (int i = 0; i <= player.inventory.getContainerSize(); ++i) {
					if (recipe.getInput().test(player.inventory.getItem(i))
							&& player.inventory.getItem(i).getItem() != recipe.getOutput()) {
						int count = player.inventory.getItem(i).getCount();
						player.inventory.setItem(i, new ItemStack(recipe.getOutput(), count));
						player.inventory.setChanged();
					}
				}
			}
		});
	}

}

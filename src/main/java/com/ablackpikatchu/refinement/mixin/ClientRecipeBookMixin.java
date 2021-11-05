package com.ablackpikatchu.refinement.mixin;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.Lists;

import net.minecraft.client.util.ClientRecipeBook;
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;

@Mixin(value = ClientRecipeBook.class, priority = 1)
public class ClientRecipeBookMixin {

	/**
	 * Fixing the horrible "Unknown Recipe Category"
	 * @param pRecipe
	 * @param ci
	 */
	@Inject(method = "Lnet/minecraft/client/util/ClientRecipeBook;getCategory(Lnet/minecraft/item/crafting/IRecipe;)Lnet/minecraft/client/util/RecipeBookCategories", 
			at = @At("HEAD"), 
			remap = false, 
			cancellable = true)
	private static void getCategoryMixin(IRecipe<?> pRecipe, CallbackInfoReturnable<RecipeBookCategories> ci) {
		IRecipeType<?> irecipetype = pRecipe.getType();
		ArrayList<IRecipeType<?>> vanillaRecipes = Lists.newArrayList(IRecipeType.BLASTING,
				IRecipeType.CAMPFIRE_COOKING, IRecipeType.CRAFTING, IRecipeType.SMELTING, IRecipeType.SMITHING,
				IRecipeType.SMOKING, IRecipeType.STONECUTTING);
		if (!vanillaRecipes.contains(irecipetype))
			ci.setReturnValue(RecipeBookCategories.UNKNOWN);
	}

}

package com.ablackpikatchu.refinement.data.common.recipes;

import java.util.function.Consumer;

import com.ablackpikatchu.refinement.data.common.recipes.builder.ShapedNoMirrorRecipeBuilder;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.shaped.Pattern;
import com.ablackpikatchu.refinement.resourcecrops.core.CropInit;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Items;

public class ShapedNoMirrorRecipes {
	
	public static void addRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedNoMirrorRecipeBuilder.shaped(Items.OAK_LOG, 16).pattern(new Pattern("L  ", " L ", "  L")).define('L', CropInit.WOOD_ESSENCE.get()).save(consumer);
		ShapedNoMirrorRecipeBuilder.shaped(Items.DARK_OAK_LOG, 16).pattern(new Pattern("  L", " L ", "L  ")).define('L', CropInit.WOOD_ESSENCE.get()).save(consumer);
		ShapedNoMirrorRecipeBuilder.shaped(Items.ACACIA_LOG, 16).pattern(new Pattern(" L ", " L ", " L ")).define('L', CropInit.WOOD_ESSENCE.get()).save(consumer);
		ShapedNoMirrorRecipeBuilder.shaped(Items.BIRCH_LOG, 16).pattern(new Pattern("LLL", "   ", "   ")).define('L', CropInit.WOOD_ESSENCE.get()).save(consumer);
		ShapedNoMirrorRecipeBuilder.shaped(Items.SPRUCE_LOG, 16).pattern(new Pattern("L  ", "L  ", " L ")).define('L', CropInit.WOOD_ESSENCE.get()).save(consumer);
		ShapedNoMirrorRecipeBuilder.shaped(Items.WARPED_STEM, 16).pattern(new Pattern(" L ", "  L", " L ")).define('L', CropInit.WOOD_ESSENCE.get()).save(consumer);
		ShapedNoMirrorRecipeBuilder.shaped(Items.CRIMSON_STEM, 16).pattern(new Pattern(" L ", "  L", "L  ")).define('L', CropInit.WOOD_ESSENCE.get()).save(consumer);
	}

}

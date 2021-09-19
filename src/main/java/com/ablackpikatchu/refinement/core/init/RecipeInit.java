package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.common.recipe.GrinderRecipe;
import com.ablackpikatchu.refinement.common.recipe.MixerRecipe;
import com.ablackpikatchu.refinement.common.recipe.MoldPressRecipe;
import com.ablackpikatchu.refinement.common.recipe.OreUnifyRecipe;
import com.ablackpikatchu.refinement.common.recipe.type.GrinderRecipeType;
import com.ablackpikatchu.refinement.common.recipe.type.MixerRecipeType;
import com.ablackpikatchu.refinement.common.recipe.type.MoldPressRecipeType;
import com.ablackpikatchu.refinement.common.recipe.type.OreUnifyRecipeType;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import net.minecraftforge.event.RegistryEvent.Register;

public class RecipeInit {
	public static final IRecipeType<GrinderRecipe> GRINDER_RECIPE = new GrinderRecipeType();
	public static final IRecipeType<MixerRecipe> MIXER_RECIPE = new MixerRecipeType();
	public static final IRecipeType<MoldPressRecipe> MOLD_PRESS_RECIPE = new MoldPressRecipeType();
	public static final IRecipeType<OreUnifyRecipe> ORE_UNIFY_RECIPE = new OreUnifyRecipeType();
	
	public static void registerRecipes(Register<IRecipeSerializer<?>> event) {
		registerRecipe(event, GRINDER_RECIPE, GrinderRecipe.SERIALIZER);
		registerRecipe(event, MIXER_RECIPE, MixerRecipe.SERIALIZER);
		registerRecipe(event, MOLD_PRESS_RECIPE, MoldPressRecipe.SERIALIZER);
		registerRecipe(event, ORE_UNIFY_RECIPE, OreUnifyRecipe.SERIALIZER);
	}
	
	private static void registerRecipe(Register<IRecipeSerializer<?>> event, IRecipeType<?> type,
			IRecipeSerializer<?> serializer) {
		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(type.toString()), type);
		event.getRegistry().register(serializer);
	}
}

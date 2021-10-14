package com.ablackpikatchu.refinement.common.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.RecipeInit;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class RefinementJEIPlugin implements IModPlugin {

	private static final ResourceLocation PLUGIN_ID = new ResourceLocation(Refinement.MOD_ID, "jei_plugin");

	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_ID;
	}

	@SuppressWarnings("resource")
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();

		registration.addRecipes(getRecipes(manager, RecipeInit.GRINDER_RECIPE), GrinderRecipeCategory.ID);
		registration.addRecipes(getRecipes(manager, RecipeInit.MIXER_RECIPE), MixerRecipeCategory.ID);
		registration.addRecipes(getRecipes(manager, RecipeInit.MOLD_PRESS_RECIPE), MoldPressRecipeCatgory.ID);
		registration.addRecipes(getRecipes(manager, RecipeInit.DNA_SEQUENCER_RECIPE), DNASequencerRecipeCategory.ID);
		registration.addRecipes(getRecipes(manager, RecipeInit.ALLOY_SMELTING_RECIPE), AlloySmelterRecipeCategory.ID);
		
		registration.addRecipes(getRecipes(manager, RecipeInit.ANVIL_RECIPE), AnvilRecipeCategory.ID);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();

		registration.addRecipeCategories(new GrinderRecipeCategory(helper));
		registration.addRecipeCategories(new MixerRecipeCategory(helper));
		registration.addRecipeCategories(new MoldPressRecipeCatgory(helper));
		registration.addRecipeCategories(new DNASequencerRecipeCategory(helper));
		registration.addRecipeCategories(new AlloySmelterRecipeCategory(helper));
		
		registration.addRecipeCategories(new AnvilRecipeCategory(helper));
	}

	private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type) {
		return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type)
				.collect(Collectors.toList());
	}

}

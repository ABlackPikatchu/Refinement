package com.ablackpikatchu.refinement.common.compat.jei;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.recipe.InWorldTierUpgradingRecipe;
import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;

public class InWorldTierUpgradingRecipeCategory implements IRecipeCategory<InWorldTierUpgradingRecipe> {
	
	public static final ResourceLocation ID = new ResourceLocation(Refinement.MOD_ID, ".in_world_tier_upgrading_recipe_category");
	private final IDrawable back;
	private final IDrawable icon;

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	public InWorldTierUpgradingRecipeCategory(IGuiHelper helper) {
		this.back = helper.createDrawable(new ResourceLocation(Refinement.MOD_ID,
				"textures/gui/jei/in_world_tier_upgrading.png"), 0, 0, 121, 71);
		this.icon = helper.createDrawableIngredient(new ItemStack(ItemInit.TierRelated.BETA_TIER_UPGRADER));
	}

	@Override
	public Class<? extends InWorldTierUpgradingRecipe> getRecipeClass() {
		return InWorldTierUpgradingRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("category." + Refinement.MOD_ID + ".in_world_tier_upgrading_recipe").getString();
	}

	@Override
	public IDrawable getBackground() {
		return back;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}
	
	@Override
	public void setIngredients(InWorldTierUpgradingRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, InWorldTierUpgradingRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

		itemStackGroup.init(0, true, 26, 6);
		itemStackGroup.init(1, true, 77, 6);
		itemStackGroup.init(2, false, 51, 46);
		
		itemStackGroup.set(ingredients);
	}

}

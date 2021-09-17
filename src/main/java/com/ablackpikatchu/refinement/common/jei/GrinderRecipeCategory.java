package com.ablackpikatchu.refinement.common.jei;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.tileentity.GrinderScreen;
import com.ablackpikatchu.refinement.common.recipe.GrinderRecipe;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.mojang.blaze3d.matrix.MatrixStack;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class GrinderRecipeCategory implements IRecipeCategory<GrinderRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(Refinement.MOD_ID, ".grinder_recipe_category");
	private final IDrawable back;
	private final IDrawable icon;
	private final IDrawableStatic staticProgressBar;
	private final IDrawableAnimated progressBar;

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	public GrinderRecipeCategory(IGuiHelper helper) {
		this.back = helper.createDrawable(GrinderScreen.GRINDER_JEI_SCREEN, 0, 0, 176, 72);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.GRINDER.get().asItem()));
		this.staticProgressBar = helper.createDrawable(GrinderScreen.GRINDER_JEI_SCREEN, 176, 0, 59, 47);
		this.progressBar = helper.createAnimatedDrawable(staticProgressBar, CommonConfig.GRINDER_DEFAULT_PROCESS_TIME.get(), IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public Class<? extends GrinderRecipe> getRecipeClass() {
		return GrinderRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("category." + Refinement.MOD_ID + ".grinder_recipe").getString();
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
	public void draw(GrinderRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		progressBar.draw(matrixStack, 68, 9);
	}

	@Override
	public void setIngredients(GrinderRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, GrinderRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

		itemStackGroup.init(0, true, 43, 22);
		itemStackGroup.init(1, true, 7, 43);
		itemStackGroup.init(2, false, 134, 22);
		
		itemStackGroup.set(ingredients);
	}

}
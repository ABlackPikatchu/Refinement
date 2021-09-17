package com.ablackpikatchu.refinement.common.jei;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.tileentity.MoldPressScreen;
import com.ablackpikatchu.refinement.common.recipe.MoldPressRecipe;
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

public class MoldPressRecipeCatgory implements IRecipeCategory<MoldPressRecipe> {
	public static final ResourceLocation ID = new ResourceLocation(Refinement.MOD_ID, ".mold_press_category");
	private final IDrawableAnimated progressBar;
	private final IDrawableStatic staticProgressBar;
	private final IDrawable back;
	private final IDrawable icon;

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	public MoldPressRecipeCatgory(IGuiHelper helper) {
		this.back = helper.createDrawable(MoldPressScreen.MOLD_PRESS_JEI_SCREEN, 0, 0, 176, 82);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.MOLD_PRESS.get().asItem()));
		this.staticProgressBar = helper.createDrawable(MoldPressScreen.MOLD_PRESS_JEI_SCREEN, 176, 0, 40, 17);
		this.progressBar = helper.createAnimatedDrawable(staticProgressBar, CommonConfig.MOLD_PRESS_DEFAULT_PROCESS_TIME.get(), IDrawableAnimated.StartDirection.BOTTOM, false);
	}

	@Override
	public Class<? extends MoldPressRecipe> getRecipeClass() {
		return MoldPressRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("category." + Refinement.MOD_ID + ".mold_press_recipe").getString();
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
	public void draw(MoldPressRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		progressBar.draw(matrixStack, 50, 29);
	}

	@Override
	public void setIngredients(MoldPressRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, MoldPressRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

		itemStackGroup.init(0, true, 61, 5);
		itemStackGroup.init(1, true, 61, 52);
		itemStackGroup.init(2, true, 7, 43);
		itemStackGroup.init(3, false, 118, 5);
		
		itemStackGroup.set(ingredients);
	}
}

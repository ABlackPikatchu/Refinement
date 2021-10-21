package com.ablackpikatchu.refinement.common.compat.jei;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.tileentity.MixerScreen;
import com.ablackpikatchu.refinement.common.recipe.MixerRecipe;
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

public class MixerRecipeCategory implements IRecipeCategory<MixerRecipe> {
	public static final ResourceLocation ID = new ResourceLocation(Refinement.MOD_ID, ".mixer_recipe_category");
	private final IDrawableAnimated progressBar;
	private final IDrawableStatic staticProgressBar;
	private final IDrawable back;
	private final IDrawable icon;

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	public MixerRecipeCategory(IGuiHelper helper) {
		this.back = helper.createDrawable(MixerScreen.MIXER_JEI_SCREEN, 0, 0, 176, 77);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.MIXER.get().asItem()));
		this.staticProgressBar = helper.createDrawable(MixerScreen.MIXER_JEI_SCREEN, 14, 94, 82, 24);
		this.progressBar = helper.createAnimatedDrawable(staticProgressBar, CommonConfig.MIXER_DEFAULT_PROCESS_TIME.get(), IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public Class<? extends MixerRecipe> getRecipeClass() {
		return MixerRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("category." + Refinement.MOD_ID + ".mixer_recipe").getString();
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
	public void draw(MixerRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		progressBar.draw(matrixStack, 51, 19);
	}

	@Override
	public void setIngredients(MixerRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, MixerRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

		itemStackGroup.init(0, true, 43, 13);
		itemStackGroup.init(1, true, 43, 42);
		itemStackGroup.init(2, true, 7, 48);
		itemStackGroup.init(3, false, 134, 27);
		
		itemStackGroup.set(ingredients);
	}
}

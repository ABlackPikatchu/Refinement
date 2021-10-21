package com.ablackpikatchu.refinement.common.compat.jei;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.tileentity.AlloySmelterScreen;
import com.ablackpikatchu.refinement.common.recipe.AlloySmeltingRecipe;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;

public class AlloySmelterRecipeCategory implements IRecipeCategory<AlloySmeltingRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(Refinement.MOD_ID, ".alloy_smelter_category");
	private final IDrawableAnimated progressBar;
	private final IDrawableStatic staticProgressBar;
	private final IDrawable back;
	private final IDrawable icon;

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	public AlloySmelterRecipeCategory(IGuiHelper helper) {
		this.back = helper.createDrawable(AlloySmelterScreen.ALLOY_SMELTER_JEI_SCREEN, 0, 0, 176, 77);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.ALLOY_SMELTER_BLOCK.get().asItem()));
		this.staticProgressBar = helper.createDrawable(AlloySmelterScreen.ALLOY_SMELTER_JEI_SCREEN, 185, 4, 24, 17);
		this.progressBar = helper.createAnimatedDrawable(staticProgressBar,
				200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public Class<? extends AlloySmeltingRecipe> getRecipeClass() {
		return AlloySmeltingRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("category." + Refinement.MOD_ID + ".alloy_smelting").getString();
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
	public void draw(AlloySmeltingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		progressBar.draw(matrixStack, 98, 25);
	}

	@Override
	public void setIngredients(AlloySmeltingRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AlloySmeltingRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

		itemStackGroup.init(0, true, 22, 24);
		itemStackGroup.init(1, true, 40, 24);
		itemStackGroup.init(2, true, 58, 24);
		itemStackGroup.init(3, true, 76, 24);
		
		itemStackGroup.init(4, true, 7, 50);
		
		itemStackGroup.init(5, false, 131, 24);

		itemStackGroup.set(ingredients);
	}

}

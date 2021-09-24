package com.ablackpikatchu.refinement.common.jei;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.tileentity.DNASequencerScreen;
import com.ablackpikatchu.refinement.common.recipe.DNASequencerRecipe;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.util.helper.JEIHelper;
import com.ablackpikatchu.refinement.datafixers.util.text.Color;
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

public class DNASequencerRecipeCategory implements IRecipeCategory<DNASequencerRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(Refinement.MOD_ID, ".dna_sequencer_category");
	private final IDrawableAnimated progressBar;
	private final IDrawableStatic staticProgressBar;
	private final IDrawable back;
	private final IDrawable icon;

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	public DNASequencerRecipeCategory(IGuiHelper helper) {
		this.back = helper.createDrawable(DNASequencerScreen.DNA_SEQUENCER_JEI_SCREEN, 0, 0, 176, 77);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.DNA_SEQUENCER.get().asItem()));
		this.staticProgressBar = helper.createDrawable(DNASequencerScreen.DNA_SEQUENCER_JEI_SCREEN, 179, 8, 71, 22);
		this.progressBar = helper.createAnimatedDrawable(staticProgressBar,
				CommonConfig.DNA_SEQUENCER_DEFAULT_PROCESS_TIME.get(), IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public Class<? extends DNASequencerRecipe> getRecipeClass() {
		return DNASequencerRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("category." + Refinement.MOD_ID + ".dna_sequencer").getString();
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
	public void draw(DNASequencerRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		progressBar.draw(matrixStack, 62, 26);
		if (recipe.getSuccessProbability() < 100)
			JEIHelper.renderText(matrixStack, "Success Chance: " + recipe.getSuccessProbability() + "%", 62, 50,
					new Color("dark_blue").getColourInt());
		else
			JEIHelper.renderText(matrixStack, "Success Chance:" + recipe.getSuccessProbability() + "%", 62, 50,
					new Color("dark_blue").getColourInt());
	}

	@Override
	public void setIngredients(DNASequencerRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, DNASequencerRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

		itemStackGroup.init(0, true, 43, 13);
		itemStackGroup.init(1, true, 43, 42);
		itemStackGroup.init(2, true, 7, 48);
		itemStackGroup.init(3, false, 134, 27);

		itemStackGroup.set(ingredients);
	}
}

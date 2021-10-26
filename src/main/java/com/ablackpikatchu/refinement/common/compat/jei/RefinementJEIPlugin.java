package com.ablackpikatchu.refinement.common.compat.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.client.screen.tileentity.AlloySmelterScreen;
import com.ablackpikatchu.refinement.client.screen.tileentity.GrinderScreen;
import com.ablackpikatchu.refinement.common.container.AlloySmelterContainer;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;

@JeiPlugin
public class RefinementJEIPlugin implements IModPlugin {

	private static final ResourceLocation PLUGIN_ID = new ResourceLocation(Refinement.MOD_ID, "jei_plugin");
	
	public static final ResourceLocation VANILLA_CRAFTING = new ResourceLocation("minecraft", "crafting");

	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_ID;
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(BlockInit.SMELTER_BLOCK.get()), VanillaRecipeCategoryUid.FURNACE);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.ALLOY_SMELTER_BLOCK.get()),
				AlloySmelterRecipeCategory.ID);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.GRINDER.get()), GrinderRecipeCategory.ID);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.DNA_SEQUENCER.get()), DNASequencerRecipeCategory.ID);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.MIXER.get()), MixerRecipeCategory.ID);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.MOLD_PRESS.get()), MoldPressRecipeCatgory.ID);
		registration.addRecipeCatalyst(new ItemStack(Blocks.ANVIL), AnvilRecipeCategory.ID);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(GrinderScreen.class, 69, 9, 57, 46, GrinderRecipeCategory.ID);
		registration.addRecipeClickArea(AlloySmelterScreen.class, 98, 25, 23, 15, AlloySmelterRecipeCategory.ID);
		// TODO JEI recipe click areas for all machines
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(AlloySmelterContainer.class, AlloySmelterRecipeCategory.ID, 0, 4, 10, 36);
		// TODO JEI transfer handlers
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
		
		registration.addRecipes(getRecipes(manager, RecipeInit.STORAGE_BIN_UPGRADING), VANILLA_CRAFTING);
		registration.addRecipes(getRecipes(manager, RecipeInit.SHAPED_NO_MIRROR), VANILLA_CRAFTING);
		
		registration.addRecipes(getRecipes(manager, RecipeInit.IN_WORLD_TIER_UPGRADING), InWorldTierUpgradingRecipeCategory.ID);
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
		
		registration.addRecipeCategories(new InWorldTierUpgradingRecipeCategory(helper));
	}

	private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type) {
		return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type)
				.collect(Collectors.toList());
	}

}

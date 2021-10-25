package com.ablackpikatchu.refinement.core.init;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.recipe.AlloySmeltingRecipe;
import com.ablackpikatchu.refinement.common.recipe.AlloySmeltingRecipe.AlloySmeltingRecipeType;
import com.ablackpikatchu.refinement.common.recipe.AnvilRecipe;
import com.ablackpikatchu.refinement.common.recipe.DNASequencerRecipe;
import com.ablackpikatchu.refinement.common.recipe.GrinderRecipe;
import com.ablackpikatchu.refinement.common.recipe.MixerRecipe;
import com.ablackpikatchu.refinement.common.recipe.MoldPressRecipe;
import com.ablackpikatchu.refinement.common.recipe.ShapedNoMirrorRecipe;
import com.ablackpikatchu.refinement.common.recipe.special.StorageBinUpgradingRecipe;
import com.ablackpikatchu.refinement.common.recipe.type.AnvilRecipeType;
import com.ablackpikatchu.refinement.common.recipe.type.DNASequencerRecipeType;
import com.ablackpikatchu.refinement.common.recipe.type.GrinderRecipeType;
import com.ablackpikatchu.refinement.common.recipe.type.MixerRecipeType;
import com.ablackpikatchu.refinement.common.recipe.type.MoldPressRecipeType;
import com.ablackpikatchu.refinement.common.recipe.type.ShapedNoMirrorRecipeType;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;

public class RecipeInit {

	public static final IRecipeType<GrinderRecipe> GRINDER_RECIPE = new GrinderRecipeType();
	public static final IRecipeType<MixerRecipe> MIXER_RECIPE = new MixerRecipeType();
	public static final IRecipeType<MoldPressRecipe> MOLD_PRESS_RECIPE = new MoldPressRecipeType();
	public static final IRecipeType<DNASequencerRecipe> DNA_SEQUENCER_RECIPE = new DNASequencerRecipeType();
	public static final IRecipeType<AlloySmeltingRecipe> ALLOY_SMELTING_RECIPE = new AlloySmeltingRecipeType();
	
	public static final IRecipeType<AnvilRecipe> ANVIL_RECIPE = new AnvilRecipeType();
	public static final IRecipeType<ShapedNoMirrorRecipe> SHAPED_NO_MIRROR = new ShapedNoMirrorRecipeType();
	
	// Special
	public static final IRecipeType<StorageBinUpgradingRecipe> STORAGE_BIN_UPGRADING = new StorageBinUpgradingRecipe.StorageBinUpgradingRecipeType();
	
	public static void registerRecipes(Register<IRecipeSerializer<?>> event) {
		registerRecipe(event, GRINDER_RECIPE, GrinderRecipe.SERIALIZER);
		registerRecipe(event, MIXER_RECIPE, MixerRecipe.SERIALIZER);
		registerRecipe(event, MOLD_PRESS_RECIPE, MoldPressRecipe.SERIALIZER);
		registerRecipe(event, DNA_SEQUENCER_RECIPE, DNASequencerRecipe.SERIALIZER);
		registerRecipe(event, ALLOY_SMELTING_RECIPE, AlloySmeltingRecipe.SERIALIZER);
		
		registerRecipe(event, ANVIL_RECIPE, AnvilRecipe.SERIALIZER);
		registerRecipe(event, SHAPED_NO_MIRROR, ShapedNoMirrorRecipe.SERIALIZER);
		
		registerRecipe(event, STORAGE_BIN_UPGRADING, StorageBinUpgradingRecipe.SERIALIZER);
		
		Refinement.LOGGER.info("Recipes Registered!");
	}
	
	@SuppressWarnings("unused")
	private static <T extends IRecipe<?>> SpecialRecipeSerializer<T> registerSpecialSerializer(RegistryEvent.Register<IRecipeSerializer<?>> event, String name, SpecialRecipeSerializer<T> serializer) {
		serializer.setRegistryName(new ResourceLocation(MOD_ID, name));
		event.getRegistry().register(serializer);
		return serializer;
	}

	private static void registerRecipe(Register<IRecipeSerializer<?>> event, IRecipeType<?> type,
			IRecipeSerializer<?> serializer) {
		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(type.toString()), type);
		event.getRegistry().register(serializer);
	}
}

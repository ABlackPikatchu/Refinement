package com.ablackpikatchu.refinement.data.common.recipes;

import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.TagInit;
import com.ablackpikatchu.refinement.datafixers.util.recipe.Output;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.shaped.KeyIngredient;
import com.ablackpikatchu.refinement.datafixers.util.recipe.vanilla.shaped.Pattern;

import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ShapedRecipes {

	protected static HashMap<ShapedRecipeBuilder, ResourceLocation> shapedRecipes = new HashMap<>();

	public static HashMap<ShapedRecipeBuilder, ResourceLocation> shapedRecipes() {

		newShapedRecipe(new Output(ItemInit.SPEED_UPGRADE.get(), 2), new Pattern("RD#", "PNP", "#DR"),
				machineStuff(itemName(ItemInit.SPEED_UPGRADE.get())),
				new KeyIngredient('#', ItemInit.REFINED_CARBON_INGOT.get()),
				new KeyIngredient('N', ItemInit.REFINED_NETHERITE_INGOT.get()),
				new KeyIngredient('D', ItemInit.REFINED_DIAMOND.get()),
				new KeyIngredient('P', ItemInit.MACHINE_PARTS.get()),
				new KeyIngredient('R', ItemInit.REFINED_IRON_INGOT.get()));

		newShapedRecipe(new Output(ItemInit.MACHINE_FRAME.get(), 1), new Pattern("CRC", "WFW", "CRC"),
				machineStuff(itemName(ItemInit.MACHINE_FRAME.get())),
				new KeyIngredient('C', ItemInit.REFINED_IRON_INGOT.get()),
				new KeyIngredient('R', ItemInit.MACHINE_PARTS.get()), new KeyIngredient('F', Items.FURNACE),
				new KeyIngredient('W', ItemInit.REFINED_GOLD_COGWHEEL.get()));

		newShapedRecipe(new Output(ItemInit.MACHINE_PARTS.get(), 4), new Pattern("CRC", "GDG", "CRC"),
				machineStuff(itemName(ItemInit.MACHINE_PARTS.get())),
				new KeyIngredient('C', ItemInit.REFINED_IRON_COGWHEEL),
				new KeyIngredient('R', ItemInit.REFINED_IRON_INGOT.get()),
				new KeyIngredient('G', ItemInit.REFINED_GOLD_INGOT.get()),
				new KeyIngredient('D', ItemInit.REFINED_DIAMOND.get()));

		newShapedRecipe(new Output(ItemInit.GRINDER.get(), 1), new Pattern("RPR", "DFD", "RPR"),
				machineStuff(itemName(ItemInit.GRINDER.get())), new KeyIngredient('P', ItemInit.MACHINE_PARTS),
				new KeyIngredient('R', ItemInit.REFINED_IRON_INGOT.get()),
				new KeyIngredient('D', ItemInit.REFINED_DIAMOND_COGWHEEL.get()),
				new KeyIngredient('F', ItemInit.MACHINE_FRAME.get()));

		newShapedRecipe(new Output(ItemInit.MIXER.get(), 1), new Pattern("RPR", "DFD", "RMR"),
				machineStuff(itemName(ItemInit.MIXER.get())), new KeyIngredient('P', ItemInit.MACHINE_PARTS),
				new KeyIngredient('R', ItemInit.REFINED_IRON_INGOT.get()),
				new KeyIngredient('D', ItemInit.REFINED_DIAMOND_COGWHEEL.get()),
				new KeyIngredient('F', ItemInit.MACHINE_FRAME.get()),
				new KeyIngredient('M', ItemInit.MIXING_BOWL.get()));

		newShapedRecipe(new Output(ItemInit.MOLD_PRESS.get(), 1), new Pattern("RPR", "DFD", "R#R"),
				machineStuff(itemName(ItemInit.MOLD_PRESS.get())), new KeyIngredient('P', ItemInit.MACHINE_PARTS),
				new KeyIngredient('R', ItemInit.REFINED_IRON_INGOT.get()),
				new KeyIngredient('D', ItemInit.REFINED_IRON_COGWHEEL.get()),
				new KeyIngredient('F', ItemInit.MACHINE_FRAME.get()),
				new KeyIngredient('#', ItemInit.REFINED_DIAMOND.get()));

		newShapedRecipe(new Output(ItemInit.MIXING_BOWL.get(), 1), new Pattern(" / ", "_ _", " _ "),
				tools(itemName(ItemInit.MIXING_BOWL.get())), new KeyIngredient('/', Items.STICK),
				new KeyIngredient('_', Items.OAK_SLAB));

		newShapedRecipe(new Output(ItemInit.GRIT_PAPER.get(), 1), new Pattern("III", "PPP", "   "),
				tools(itemName(ItemInit.GRIT_PAPER.get())), new KeyIngredient('I', ItemInit.GRIT),
				new KeyIngredient('P', Items.PAPER));

		newShapedRecipe(new Output(ItemInit.IRON_GRIT_PAPER.get(), 1), new Pattern("III", "PPP", "   "),
				tools(itemName(ItemInit.IRON_GRIT_PAPER.get())), new KeyIngredient('I', ItemInit.IRON_INFUSED_GRIT),
				new KeyIngredient('P', Items.PAPER));

		newShapedRecipe(new Output(ItemInit.DIAMOND_GRIT_PAPER.get(), 1), new Pattern("III", "PPP", "   "),
				tools(itemName(ItemInit.DIAMOND_GRIT_PAPER.get())),
				new KeyIngredient('I', ItemInit.DIAMOND_INFUSED_GRIT), new KeyIngredient('P', Items.PAPER));

		newShapedRecipe(new Output(ItemInit.NETHERITE_GRIT_PAPER.get(), 1), new Pattern("III", "PPP", "   "),
				tools(itemName(ItemInit.NETHERITE_GRIT_PAPER.get())),
				new KeyIngredient('I', ItemInit.NETHERITE_INFUSED_GRIT), new KeyIngredient('P', Items.PAPER));

		newShapedRecipe(new Output(ItemInit.UNFIRED_COGWHEEL_MOLD.get(), 1), new Pattern("###", "#I#", "###"),
				molds(itemName(ItemInit.UNFIRED_COGWHEEL_MOLD)), new KeyIngredient('#', Items.CLAY_BALL),
				new KeyIngredient('I', TagInit.Items.COGWHEELS));

		newShapedRecipe(new Output(ItemInit.UNFIRED_GEM_MOLD.get(), 1), new Pattern("###", "#I#", "###"),
				molds(itemName(ItemInit.UNFIRED_GEM_MOLD)), new KeyIngredient('#', Items.CLAY_BALL),
				new KeyIngredient('I', TagInit.Items.GEMS));

		newShapedRecipe(new Output(ItemInit.UNFIRED_INGOT_MOLD.get(), 1), new Pattern("###", "#I#", "###"),
				molds(itemName(ItemInit.UNFIRED_INGOT_MOLD)), new KeyIngredient('#', Items.CLAY_BALL),
				new KeyIngredient('I', TagInit.Items.INGOTS));

		newShapedRecipe(new Output(ItemInit.GRIT.get(), 6), new Pattern("SGS", "GSG", " B "),
				mixingBowl(itemName(ItemInit.GRIT)), new KeyIngredient('S', Items.SAND),
				new KeyIngredient('G', Items.GRAVEL), new KeyIngredient('B', ItemInit.MIXING_BOWL));

		newShapedRecipe(new Output(ItemInit.IRON_INFUSED_GRIT.get(), 2), new Pattern(" G ", " R ", " B "),
				mixingBowl(itemName(ItemInit.IRON_INFUSED_GRIT)), new KeyIngredient('G', ItemInit.REFINED_IRON_DUST),
				new KeyIngredient('R', ItemInit.GRIT), new KeyIngredient('B', ItemInit.MIXING_BOWL));

		newShapedRecipe(new Output(ItemInit.DIAMOND_INFUSED_GRIT.get(), 2), new Pattern(" G ", " R ", " B "),
				mixingBowl(itemName(ItemInit.DIAMOND_INFUSED_GRIT)),
				new KeyIngredient('G', ItemInit.REFINED_DIAMOND_DUST), new KeyIngredient('R', ItemInit.GRIT),
				new KeyIngredient('B', ItemInit.MIXING_BOWL));

		newShapedRecipe(new Output(ItemInit.NETHERITE_INFUSED_GRIT.get(), 2), new Pattern(" G ", " R ", " B "),
				mixingBowl(itemName(ItemInit.NETHERITE_INFUSED_GRIT)),
				new KeyIngredient('G', ItemInit.REFINED_NETHERITE_DUST), new KeyIngredient('R', ItemInit.GRIT),
				new KeyIngredient('B', ItemInit.MIXING_BOWL));

		newShapedRecipe(new Output(ItemInit.UNFIRED_REFINED_DIAMOND_COGWHEEL.get(), 1),
				new Pattern("###", " S ", "   "), cogwheels(itemName(ItemInit.UNFIRED_REFINED_DIAMOND_COGWHEEL)),
				new KeyIngredient('#', ItemInit.REFINED_DIAMOND_DUST), new KeyIngredient('S', ItemInit.COGWHEEL_MOLD));

		newShapedRecipe(new Output(ItemInit.UNFIRED_REFINED_IRON_COGWHEEL.get(), 1), new Pattern("###", " S ", "   "),
				cogwheels(itemName(ItemInit.UNFIRED_REFINED_IRON_COGWHEEL)),
				new KeyIngredient('#', ItemInit.REFINED_IRON_DUST), new KeyIngredient('S', ItemInit.COGWHEEL_MOLD));

		newShapedRecipe(new Output(ItemInit.UNFIRED_REFINED_GOLD_COGWHEEL.get(), 1), new Pattern("###", " S ", "   "),
				cogwheels(itemName(ItemInit.UNFIRED_REFINED_GOLD_COGWHEEL)),
				new KeyIngredient('#', ItemInit.REFINED_GOLD_DUST), new KeyIngredient('S', ItemInit.COGWHEEL_MOLD));

		newShapedRecipe(new Output(ItemInit.WOODEN_COGWHEEL.get(), 1), new Pattern("#O#", "O O", "#O#"),
				cogwheels(itemName(ItemInit.WOODEN_COGWHEEL)), new KeyIngredient('#', Items.OAK_PLANKS),
				new KeyIngredient('O', Items.OAK_BUTTON));

		newShapedRecipe(new Output(ItemInit.MINERS_STEW.get(), 1), new Pattern("GDI", "ECR", " B "),
				food(itemName(ItemInit.MINERS_STEW)), new KeyIngredient('G', TagInit.Items.GOLD_INGOT),
				new KeyIngredient('D', TagInit.Items.DIAMOND), new KeyIngredient('I', TagInit.Items.IRON_INGOT),
				new KeyIngredient('E', Items.EMERALD), new KeyIngredient('C', TagInit.Items.COBBLESTONE),
				new KeyIngredient('R', Items.REDSTONE), new KeyIngredient('B', Items.BOWL));

		newShapedRecipe(new Output(ItemInit.UNFIRED_REFINED_CARBON_INGOT.get(), 1), new Pattern(" / ", "/M/", "   "),
				unfired(itemName(ItemInit.UNFIRED_REFINED_CARBON_INGOT)),
				new KeyIngredient('/', ItemInit.REFINED_CARBON_DUST), new KeyIngredient('M', ItemInit.INGOT_MOLD));

		newShapedRecipe(new Output(ItemInit.UNFIRED_REFINED_IRON_INGOT.get(), 1), new Pattern(" / ", "/M/", "   "),
				unfired(itemName(ItemInit.UNFIRED_REFINED_IRON_INGOT)),
				new KeyIngredient('/', ItemInit.REFINED_IRON_DUST), new KeyIngredient('M', ItemInit.INGOT_MOLD));

		newShapedRecipe(new Output(ItemInit.UNFIRED_REFINED_GOLD_INGOT.get(), 1), new Pattern(" / ", "/M/", "   "),
				unfired(itemName(ItemInit.UNFIRED_REFINED_GOLD_INGOT)),
				new KeyIngredient('/', ItemInit.REFINED_GOLD_DUST), new KeyIngredient('M', ItemInit.INGOT_MOLD));

		newShapedRecipe(new Output(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT.get(), 1), new Pattern(" / ", "/M/", "   "),
				unfired(itemName(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT)),
				new KeyIngredient('/', ItemInit.REFINED_NETHERITE_DUST), new KeyIngredient('M', ItemInit.INGOT_MOLD));

		newShapedRecipe(new Output(ItemInit.UNFIRED_REFINED_DIAMOND.get(), 1), new Pattern(" / ", "/M/", "   "),
				unfired(itemName(ItemInit.UNFIRED_REFINED_DIAMOND)),
				new KeyIngredient('/', ItemInit.REFINED_DIAMOND_DUST), new KeyIngredient('M', ItemInit.GEM_MOLD));

		newShapedRecipe(new Output(BlockInit.REFINED_SAPLING.get().asItem(), 1), new Pattern("CCC", "CSC", "CCC"),
				misc(itemName(BlockInit.REFINED_SAPLING.get().asItem())),
				new KeyIngredient('C', BlockInit.REFINED_CARBON_BLOCK.get().asItem()),
				new KeyIngredient('S', TagInit.Items.SAPLINGS));

		shapedRecipes.forEach((recipe, name) -> {
			recipe.unlockedBy("has_item", has(Items.AIR));
		});

		return shapedRecipes;

	}

	/**
	 * Adds a new shaped recipe
	 * 
	 * @param output      the output of the recipe
	 * @param pattern     the pattern of the recipe
	 * @param name        the name of the recipe
	 * @param ingredients the ingredients of the recipe
	 */
	public static void newShapedRecipe(Output output, Pattern pattern, ResourceLocation name,
			@Nonnull KeyIngredient... ingredients) {
		ShapedRecipeBuilder recipe = new ShapedRecipeBuilder(output.getItem(), output.getCount());
		pattern.getShapedRecipePattern(recipe);
		for (KeyIngredient ingredient : ingredients) {
			ingredient.getShapedRecipe(recipe);
		}
		shapedRecipes.put(recipe, name);

	}

	public static ResourceLocation machineStuff(@Nullable String name) {
		return new ResourceLocation(Refinement.MOD_ID, "machine_stuff/" + name);
	}

	public static ResourceLocation mixingBowl(@Nullable String name) {
		return new ResourceLocation(Refinement.MOD_ID, "mixing_bowl/" + name);
	}

	public static ResourceLocation tools(@Nullable String name) {
		return new ResourceLocation(Refinement.MOD_ID, "tools/" + name);
	}

	public static ResourceLocation molds(@Nullable String name) {
		return new ResourceLocation(Refinement.MOD_ID, "molds/" + name);
	}

	public static ResourceLocation cogwheels(@Nullable String name) {
		return new ResourceLocation(Refinement.MOD_ID, "cogwheels/" + name);
	}

	public static ResourceLocation food(@Nullable String name) {
		return new ResourceLocation(Refinement.MOD_ID, "food/" + name);
	}

	public static ResourceLocation unfired(@Nullable String name) {
		return new ResourceLocation(Refinement.MOD_ID, "unfired/" + name);
	}

	public static ResourceLocation misc(@Nullable String name) {
		return new ResourceLocation(Refinement.MOD_ID, "misc/" + name);
	}

	public static String itemName(Item item) {
		return item.getRegistryName().getPath();
	}

	public static String itemName(RegistryObject<Item> item) {
		return item.get().getRegistryName().getPath();
	}

	protected static InventoryChangeTrigger.Instance has(IItemProvider p_200403_0_) {
		return inventoryTrigger(ItemPredicate.Builder.item().of(p_200403_0_).build());
	}

	protected static InventoryChangeTrigger.Instance inventoryTrigger(ItemPredicate... p_200405_0_) {
		return new InventoryChangeTrigger.Instance(EntityPredicate.AndPredicate.ANY, MinMaxBounds.IntBound.ANY,
				MinMaxBounds.IntBound.ANY, MinMaxBounds.IntBound.ANY, p_200405_0_);
	}

}

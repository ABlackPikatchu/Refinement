package com.ablackpikatchu.refinement.data.common.recipes;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.BlockItemInit;
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
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
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
				new KeyIngredient('R', ItemInit.Ingots.SIGNALUM_INGOT));

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

		newShapedRecipe(new Output(BlockItemInit.Machines.GRINDER_ITEM, 1), new Pattern("RPR", "DFD", "RPR"),
				machineStuff(itemName(BlockItemInit.Machines.GRINDER_ITEM)),
				new KeyIngredient('P', ItemInit.MACHINE_PARTS),
				new KeyIngredient('R', ItemInit.REFINED_IRON_INGOT.get()),
				new KeyIngredient('D', ItemInit.REFINED_DIAMOND_COGWHEEL.get()),
				new KeyIngredient('F', ItemInit.MACHINE_FRAME.get()));

		newShapedRecipe(new Output(BlockItemInit.Machines.MIXER_ITEM, 1), new Pattern("RPR", "DFD", "RMR"),
				machineStuff(itemName(BlockItemInit.Machines.MIXER_ITEM)),
				new KeyIngredient('P', ItemInit.MACHINE_PARTS),
				new KeyIngredient('R', ItemInit.REFINED_IRON_INGOT.get()),
				new KeyIngredient('D', ItemInit.REFINED_DIAMOND_COGWHEEL.get()),
				new KeyIngredient('F', ItemInit.MACHINE_FRAME.get()),
				new KeyIngredient('M', ItemInit.MIXING_BOWL.get()));

		newShapedRecipe(new Output(new ResourceLocation(MOD_ID, "smelter"), 1), new Pattern("RRR", "RFR", "RTR"),
				machineStuff("smelter"), new KeyIngredient('R', TagInit.Items.REDSTONE_STORAGE),
				new KeyIngredient('F', Items.FURNACE),
				new KeyIngredient('T', BlockInit.CARBON_GENERATOR_BLOCK.get().asItem()));

		newShapedRecipe(new Output(BlockInit.ALLOY_SMELTER_BLOCK.get().asItem(), 1), new Pattern("CIC", "ISI", "CIC"),
				machineStuff(itemName(BlockInit.ALLOY_SMELTER_BLOCK.get().asItem())),
				new KeyIngredient('C', ItemInit.REFINED_DIAMOND_COGWHEEL.get()),
				new KeyIngredient('I', TagInit.Items.IRON_STORAGE),
				new KeyIngredient('S', BlockInit.SMELTER_BLOCK.get().asItem()));

		newShapedRecipe(new Output(BlockInit.CARBON_GENERATOR_BLOCK.get().asItem(), 1),
				new Pattern("RCR", "RFR", "RTR"),
				machineStuff(itemName(BlockInit.CARBON_GENERATOR_BLOCK.get().asItem())),
				new KeyIngredient('R', TagInit.Items.REDSTONE_STORAGE),
				new KeyIngredient('F', ItemInit.REFINED_CARBON_INGOT.get()),
				new KeyIngredient('T', TagInit.Items.TIN_STORAGE),
				new KeyIngredient('C', TagInit.Items.COPPER_STORAGE));

		newShapedRecipe(new Output(BlockInit.ENERGY_GENERATOR_BLOCK.get().asItem(), 1),
				new Pattern("RCR", "RFR", "RTR"),
				machineStuff(itemName(BlockInit.ENERGY_GENERATOR_BLOCK.get().asItem())),
				new KeyIngredient('R', TagInit.Items.REDSTONE_STORAGE),
				new KeyIngredient('F', ItemInit.REFINED_IRON_COGWHEEL.get()),
				new KeyIngredient('T', TagInit.Items.TIN_STORAGE),
				new KeyIngredient('C', TagInit.Items.SILVER_STORAGE));

		newShapedRecipe(new Output(BlockItemInit.Machines.MOLD_PRESS_ITEM, 1), new Pattern("RPR", "DFD", "R#R"),
				machineStuff(itemName(BlockItemInit.Machines.MOLD_PRESS_ITEM)),
				new KeyIngredient('P', ItemInit.MACHINE_PARTS),
				new KeyIngredient('R', ItemInit.REFINED_IRON_INGOT.get()),
				new KeyIngredient('D', ItemInit.REFINED_IRON_COGWHEEL.get()),
				new KeyIngredient('F', ItemInit.MACHINE_FRAME.get()),
				new KeyIngredient('#', ItemInit.REFINED_DIAMOND.get()));

		newShapedRecipe(new Output(ItemInit.AUTO_EJECT_UPGRADE.get(), 1), new Pattern("DCD", "PNP", "DCD"),
				machineStuff(itemName(ItemInit.AUTO_EJECT_UPGRADE.get())),
				new KeyIngredient('D', ItemInit.REFINED_GOLD_INGOT.get()),
				new KeyIngredient('C', ItemInit.REFINED_CARBON_INGOT),
				new KeyIngredient('P', ItemInit.MACHINE_PARTS.get()),
				new KeyIngredient('N', ItemInit.REFINED_NETHERITE_INGOT));

		newShapedRecipe(new Output(ItemInit.AUTO_IMPORT_UPGRADE.get(), 1), new Pattern("DCD", "PNP", "DCD"),
				machineStuff(itemName(ItemInit.AUTO_IMPORT_UPGRADE.get())),
				new KeyIngredient('D', ItemInit.REFINED_IRON_INGOT.get()),
				new KeyIngredient('C', ItemInit.REFINED_CARBON_INGOT),
				new KeyIngredient('P', ItemInit.MACHINE_PARTS.get()),
				new KeyIngredient('N', ItemInit.REFINED_NETHERITE_INGOT));

		newShapedRecipe(new Output(ItemInit.ENERGY_ABILITY_UPGRADE.get(), 1), new Pattern("ECE", "PNP", "ECE"),
				machineStuff(itemName(ItemInit.ENERGY_ABILITY_UPGRADE.get())),
				new KeyIngredient('E', ItemInit.Ingots.ENDERIUM_INGOT),
				new KeyIngredient('C', ItemInit.REFINED_CARBON_INGOT),
				new KeyIngredient('P', ItemInit.MACHINE_PARTS.get()),
				new KeyIngredient('N', ItemInit.REFINED_NETHERITE_INGOT));

		newShapedRecipe(new Output(ItemInit.FoodItems.MINERS_APPLE, 1), new Pattern("CCC", "FBF", "CCC"),
				food(itemName(ItemInit.FoodItems.MINERS_APPLE)), new KeyIngredient('C', Items.APPLE),
				new KeyIngredient('B', ItemInit.FoodItems.MINERS_BREAD), new KeyIngredient('F', Items.FEATHER));

		newShapedRecipe(new Output(ItemInit.FoodItems.MINERS_CARROT, 2), new Pattern("CCC", " B ", "CCC"),
				food(itemName(ItemInit.FoodItems.MINERS_CARROT)), new KeyIngredient('C', Items.CARROT),
				new KeyIngredient('B', ItemInit.FoodItems.MINERS_BREAD));

		newShapedRecipe(new Output(ItemInit.FoodItems.MINERS_JERKY, 2), new Pattern("CCC", " B ", "CCC"),
				food(itemName(ItemInit.FoodItems.MINERS_JERKY)), new KeyIngredient('C', Items.RABBIT_FOOT),
				new KeyIngredient('B', ItemInit.FoodItems.MINERS_BREAD));

		newShapedRecipe(new Output(ItemInit.FoodItems.MINERS_BREAD, 2), new Pattern("WWW", "W W", "WWW"),
				food(itemName(ItemInit.FoodItems.MINERS_BREAD)), new KeyIngredient('W', Items.WHEAT));

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

		newShapedRecipe(new Output(ItemInit.MATERIALS_STATION_ITEM.get(), 1), new Pattern("BBB", "BDB", "BBB"),
				misc(itemName(ItemInit.MATERIALS_STATION_ITEM.get())), new KeyIngredient('D', Items.CRAFTING_TABLE),
				new KeyIngredient('B', TagInit.Items.EMERALD_STORAGE));

		newShapedRecipe(new Output(ItemInit.REFINED_BONEMEAL.get(), 2), new Pattern("BBB", "BDB", "BBB"),
				misc(itemName(ItemInit.REFINED_BONEMEAL.get())), new KeyIngredient('B', Items.BONE_MEAL),
				new KeyIngredient('D', TagInit.Items.REFINING_DUST));

		newShapedRecipe(new Output(ItemInit.GLUTTONY_BRACELET.get(), 1), new Pattern("CCC", "CDC", "CCC"),
				misc(itemName(ItemInit.GLUTTONY_BRACELET)), new KeyIngredient('C', Items.COCOA_BEANS),
				new KeyIngredient('D', TagInit.Items.DIAMOND));

		newShapedRecipe(new Output(ItemInit.FoodItems.CURING_APPLE, 1), new Pattern("E", "G", "M"),
				food(itemName(ItemInit.FoodItems.CURING_APPLE)), new KeyIngredient('G', Items.GOLDEN_APPLE),
				new KeyIngredient('E', Items.FERMENTED_SPIDER_EYE), new KeyIngredient('M', Items.MILK_BUCKET));

		newShapedRecipe(new Output(ItemInit.FoodItems.GOD_APPLE, 1), new Pattern("SSS", "SAS", "SSS"),
				food(itemName(ItemInit.FoodItems.GOD_APPLE)), new KeyIngredient('A', Items.GOLDEN_APPLE),
				new KeyIngredient('S', Items.NETHER_STAR));

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

		newShapedRecipe(new Output(ItemInit.FoodItems.MINERS_STEW, 1), new Pattern("GDI", "ECR", " B "),
				food(itemName(ItemInit.FoodItems.MINERS_STEW)), new KeyIngredient('G', TagInit.Items.GOLD_INGOT),
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

		addTierUpgraderRecipe(ItemInit.TierRelated.BETA_TIER_UPGRADER, ItemInit.TierRelated.BETA_ALLOY,
				ItemInit.TierRelated.BETA_CIRCUIT, Ingredient.of(ItemTags.PLANKS));
		addTierUpgraderRecipe(ItemInit.TierRelated.GAMMA_TIER_UPGRADER, ItemInit.TierRelated.GAMMA_ALLOY,
				ItemInit.TierRelated.GAMMA_CIRCUIT, Ingredient.of(ItemTags.PLANKS));
		addTierUpgraderRecipe(ItemInit.TierRelated.EPSILON_TIER_UPGRADER, ItemInit.TierRelated.EPSILON_ALLOY,
				ItemInit.TierRelated.EPSILON_CIRCUIT, Ingredient.of(ItemTags.PLANKS));
		addTierUpgraderRecipe(ItemInit.TierRelated.OMEGA_TIER_UPGRADER, ItemInit.TierRelated.OMEGA_ALLOY,
				ItemInit.TierRelated.OMEGA_CIRCUIT, Ingredient.of(ItemTags.PLANKS));
		
		Functions.addAlloyToCircuit(ItemInit.TierRelated.ALPHA_ALLOY, ItemInit.TierRelated.ALPHA_CIRCUIT, Ingredient.of(ItemTags.PLANKS));
		Functions.addAlloyToCircuit(ItemInit.TierRelated.BETA_ALLOY, ItemInit.TierRelated.BETA_CIRCUIT, Ingredient.of(ItemInit.TierRelated.ALPHA_CIRCUIT));
		Functions.addAlloyToCircuit(ItemInit.TierRelated.GAMMA_ALLOY, ItemInit.TierRelated.GAMMA_CIRCUIT, Ingredient.of(ItemInit.TierRelated.BETA_CIRCUIT));
		Functions.addAlloyToCircuit(ItemInit.TierRelated.EPSILON_ALLOY, ItemInit.TierRelated.EPSILON_CIRCUIT, Ingredient.of(ItemInit.TierRelated.GAMMA_CIRCUIT));
		Functions.addAlloyToCircuit(ItemInit.TierRelated.OMEGA_ALLOY, ItemInit.TierRelated.OMEGA_CIRCUIT, Ingredient.of(ItemInit.TierRelated.EPSILON_CIRCUIT));

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

	private static class Functions {

		public static void addAlloyToCircuit(Item alloy, Item outputCircuit, Ingredient previousCircuit) {
			newShapedRecipe(new Output(outputCircuit, 1), new Pattern("APA"), machineStuff(itemName(outputCircuit)),
					new KeyIngredient('A', Ingredient.of(alloy)),
					new KeyIngredient('P', previousCircuit));
		}
	}

	private static void addTierUpgraderRecipe(Item tierUpgrader, Item alloy, Item circuit, Ingredient middleItem) {
		newShapedRecipe(new Output(tierUpgrader, 1), new Pattern("ACA", "AMA", "ACA"),
				machineStuff(itemName(tierUpgrader)), new KeyIngredient('A', Ingredient.of(alloy)),
				new KeyIngredient('C', Ingredient.of(circuit)), new KeyIngredient('M', middleItem));
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

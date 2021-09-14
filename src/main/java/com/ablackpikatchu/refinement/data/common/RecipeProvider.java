package com.ablackpikatchu.refinement.data.common;

import java.util.HashMap;
import java.util.function.Consumer;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.TagInit;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class RecipeProvider extends net.minecraft.data.RecipeProvider {

	public static HashMap<Block, Item> SHAPELESS_BLOCK_INGOT = new HashMap<>();
	public static HashMap<Item, Item> SHAPELESS_INGOT_NUGGET = new HashMap<>();

	public static HashMap<Item, Item> SANDING_DUST = new HashMap<>();

	public static HashMap<Item, Item> HELMETS = new HashMap<>();
	public static HashMap<Item, Item> CHESTPLATES = new HashMap<>();
	public static HashMap<Item, Item> LEGGINGS = new HashMap<>();
	public static HashMap<Item, Item> BOOTS = new HashMap<>();

	public static HashMap<Item, Item> AXES = new HashMap<>();
	public static HashMap<Item, Item> PICKAXES = new HashMap<>();
	public static HashMap<Item, Item> SHOVELS = new HashMap<>();
	public static HashMap<Item, Item> HOES = new HashMap<>();
	public static HashMap<Item, Item> SWORDS = new HashMap<>();

	public RecipeProvider(DataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		addShapelessBlockIngotEntries(SHAPELESS_BLOCK_INGOT);
		SHAPELESS_BLOCK_INGOT.forEach((block, item) -> {
			ShapelessRecipeBuilder.shapeless(block).requires(item, 9).unlockedBy("has_item", has(item)).save(consumer,
					storageBlocks(item.getRegistryName().getPath() + "_to_" + block.getRegistryName().getPath()));
			ShapelessRecipeBuilder.shapeless(item, 9).requires(block.asItem())
					.unlockedBy("has_item", has(block.asItem())).save(consumer, storageBlocks(
							block.getRegistryName().getPath() + "_to_" + item.getRegistryName().getPath()));
		});

		addShapelessIngotNuggetEntries(SHAPELESS_INGOT_NUGGET);
		SHAPELESS_INGOT_NUGGET.forEach((ingot, nugget) -> {
			ShapelessRecipeBuilder.shapeless(ingot).requires(nugget, 9).unlockedBy("has_item", has(nugget)).save(
					consumer, nuggets(nugget.getRegistryName().getPath() + "_to_" + ingot.getRegistryName().getPath()));
			ShapelessRecipeBuilder.shapeless(nugget, 9).requires(ingot).unlockedBy("has_item", has(ingot)).save(
					consumer, nuggets(ingot.getRegistryName().getPath() + "_to_" + nugget.getRegistryName().getPath()));
		});

		addDustFromSanding(SANDING_DUST);
		SANDING_DUST.forEach((input, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("i").pattern("p").define('p', TagInit.Items.GRIT_PAPERS)
					.define('i', input).unlockedBy("has_item", has(input)).save(consumer, sanding(
							"item/" + input.getRegistryName().getPath() + "_to_" + output.getRegistryName().getPath()));
		});

		addArmours(HELMETS, CHESTPLATES, LEGGINGS, BOOTS);
		HELMETS.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("###").pattern("# #").define('#', material)
					.unlockedBy("has_item", has(material))
					.save(consumer, armour("helmets/" + output.getRegistryName().getPath()));
		});
		CHESTPLATES.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("# #").pattern("###").pattern("###").define('#', material)
					.unlockedBy("has_item", has(material))
					.save(consumer, armour("chestplates/" + output.getRegistryName().getPath()));
		});
		LEGGINGS.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("###").pattern("# #").pattern("# #").define('#', material)
					.unlockedBy("has_item", has(material))
					.save(consumer, armour("leggings/" + output.getRegistryName().getPath()));
		});
		BOOTS.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("# #").pattern("# #").define('#', material)
					.unlockedBy("has_item", has(material))
					.save(consumer, armour("boots/" + output.getRegistryName().getPath()));
		});

		addTools(AXES, PICKAXES, SHOVELS, HOES, SWORDS);
		AXES.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("##").pattern("#S").pattern(" S").define('#', material)
					.define('S', Items.STICK).unlockedBy("has_item", has(material))
					.save(consumer, tools("axes/" + output.getRegistryName().getPath()));
		});

		PICKAXES.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("###").pattern(" S ").pattern(" S ").define('#', material)
					.define('S', Items.STICK).unlockedBy("has_item", has(material))
					.save(consumer, tools("pickaxes/" + output.getRegistryName().getPath()));
		});

		SHOVELS.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern(" # ").pattern(" S ").pattern(" S ").define('#', material)
					.define('S', Items.STICK).unlockedBy("has_item", has(material))
					.save(consumer, tools("shovels/" + output.getRegistryName().getPath()));
		});
		
		HOES.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern("## ").pattern(" S ").pattern(" S ").define('#', material)
					.define('S', Items.STICK).unlockedBy("has_item", has(material))
					.save(consumer, tools("shovels/" + output.getRegistryName().getPath()));
		});
		
		SWORDS.forEach((material, output) -> {
			ShapedRecipeBuilder.shaped(output).pattern(" # ").pattern(" # ").pattern(" S ").define('#', material)
					.define('S', Items.STICK).unlockedBy("has_item", has(material))
					.save(consumer, tools("shovels/" + output.getRegistryName().getPath()));
		});
	}

	public void addShapelessBlockIngotEntries(HashMap<Block, Item> map) {
		map.put(BlockInit.REFINED_CARBON_BLOCK.get(), ItemInit.REFINED_CARBON_INGOT.get());
		map.put(BlockInit.REFINED_IRON_BLOCK.get(), ItemInit.REFINED_IRON_INGOT.get());
		map.put(BlockInit.REFINED_GOLD_BLOCK.get(), ItemInit.REFINED_GOLD_INGOT.get());
		map.put(BlockInit.REFINED_DIAMOND_BLOCK.get(), ItemInit.REFINED_DIAMOND.get());
		map.put(BlockInit.REFINED_NETHERITE_BLOCK.get(), ItemInit.REFINED_NETHERITE_INGOT.get());
	}

	public void addShapelessIngotNuggetEntries(HashMap<Item, Item> map) {
		map.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_NUGGET.get());
		map.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_NUGGET.get());
	}

	public void addDustFromSanding(HashMap<Item, Item> map) {
		map.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_DUST.get());
		map.put(ItemInit.REFINED_CARBON_INGOT.get(), ItemInit.REFINED_CARBON_DUST.get());
		map.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_DUST.get());
		map.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_DUST.get());
		map.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_DUST.get());
		map.put(ItemInit.PURE_CRYSTAL.get(), ItemInit.REFINING_DUST.get());
		map.put(Items.IRON_INGOT, ItemInit.IRON_DUST.get());
		map.put(Items.GOLD_INGOT, ItemInit.GOLD_DUST.get());
		map.put(Items.DIAMOND, ItemInit.DIAMOND_DUST.get());
		map.put(Items.NETHERITE_INGOT, ItemInit.NETHERITE_DUST.get());
		map.put(Items.COAL, ItemInit.COAL_DUST.get());
		map.put(Items.CHARCOAL, ItemInit.CHARCOAL_DUST.get());
	}

	public void addArmours(HashMap<Item, Item> helmetMap, HashMap<Item, Item> chestplateMap,
			HashMap<Item, Item> leggingsMap, HashMap<Item, Item> bootsMap) {
		helmetMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_HELMET.get());
		helmetMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_HELMET.get());
		helmetMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_HELMET.get());
		helmetMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_HELMET.get());

		chestplateMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_CHESTPLATE.get());
		chestplateMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_CHESTPLATE.get());
		chestplateMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_CHESTPLATE.get());
		chestplateMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_CHESTPLATE.get());

		leggingsMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_LEGGINGS.get());
		leggingsMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_LEGGINGS.get());
		leggingsMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_LEGGINGS.get());
		leggingsMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_LEGGINGS.get());

		bootsMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_BOOTS.get());
		bootsMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_BOOTS.get());
		bootsMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_BOOTS.get());
		bootsMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_BOOTS.get());
	}

	public void addTools(HashMap<Item, Item> axeMap, HashMap<Item, Item> pickaxeMap, HashMap<Item, Item> shovelMap, HashMap<Item, Item> hoeMap, HashMap<Item, Item> swordMap) {
		axeMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_AXE.get());
		axeMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_AXE.get());
		axeMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_AXE.get());
		axeMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_AXE.get());

		pickaxeMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_PICKAXE.get());
		pickaxeMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_PICKAXE.get());
		pickaxeMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_PICKAXE.get());
		pickaxeMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_PICKAXE.get());
		
		shovelMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_SHOVEL.get());
		shovelMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_SHOVEL.get());
		shovelMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_SHOVEL.get());
		shovelMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_SHOVEL.get());
		
		hoeMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_HOE.get());
		hoeMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_HOE.get());
		hoeMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_HOE.get());
		hoeMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_HOE.get());
		
		swordMap.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_SWORD.get());
		swordMap.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_SWORD.get());
		swordMap.put(ItemInit.REFINED_DIAMOND.get(), ItemInit.REFINED_DIAMOND_SWORD.get());
		swordMap.put(ItemInit.REFINED_NETHERITE_INGOT.get(), ItemInit.REFINED_NETHERITE_SWORD.get());

	}

	public static ResourceLocation storageBlocks(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "storage_blocks/" + name);
	}

	public static ResourceLocation nuggets(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "nuggets/" + name);
	}

	public static ResourceLocation sanding(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "sanding/" + name);
	}

	public static ResourceLocation armour(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "armours/" + name);
	}

	public static ResourceLocation tools(String name) {
		return new ResourceLocation(Refinement.MOD_ID, "tools/" + name);
	}

}

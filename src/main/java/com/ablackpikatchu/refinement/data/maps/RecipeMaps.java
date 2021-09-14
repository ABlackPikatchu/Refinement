package com.ablackpikatchu.refinement.data.maps;

import java.util.HashMap;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.TagInit;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;

public class RecipeMaps {

	public static void addShapelessBlockIngotEntries(HashMap<Block, Item> map) {
		map.put(BlockInit.REFINED_CARBON_BLOCK.get(), ItemInit.REFINED_CARBON_INGOT.get());
		map.put(BlockInit.REFINED_IRON_BLOCK.get(), ItemInit.REFINED_IRON_INGOT.get());
		map.put(BlockInit.REFINED_GOLD_BLOCK.get(), ItemInit.REFINED_GOLD_INGOT.get());
		map.put(BlockInit.REFINED_DIAMOND_BLOCK.get(), ItemInit.REFINED_DIAMOND.get());
		map.put(BlockInit.REFINED_NETHERITE_BLOCK.get(), ItemInit.REFINED_NETHERITE_INGOT.get());
	}

	public static void addShapelessIngotNuggetEntries(HashMap<Item, Item> map) {
		map.put(ItemInit.REFINED_GOLD_INGOT.get(), ItemInit.REFINED_GOLD_NUGGET.get());
		map.put(ItemInit.REFINED_IRON_INGOT.get(), ItemInit.REFINED_IRON_NUGGET.get());
	}

	public static void addDustFromSanding(HashMap<Item, Item> map) {
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

	public static void addArmours(HashMap<Item, Item> helmetMap, HashMap<Item, Item> chestplateMap,
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

	public static void addTools(HashMap<Item, Item> axeMap, HashMap<Item, Item> pickaxeMap,
			HashMap<Item, Item> shovelMap, HashMap<Item, Item> hoeMap, HashMap<Item, Item> swordMap) {
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

	public static void addGrinderRecipes(HashMap<ItemStack, Pair<Item, Integer>> map,
			HashMap<Pair<ITag<Item>, Integer>, Pair<Item, Integer>> tagMap) {
		map.put(new ItemStack(Items.GRAVEL, 16), new Pair<Item, Integer>(Items.SAND, 16));

		tagMap.put(new Pair<ITag<Item>, Integer>(TagInit.Items.COBBLESTONE, 16),
				new Pair<Item, Integer>(Items.GRAVEL, 16));
	}

}

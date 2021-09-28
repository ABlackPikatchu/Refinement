package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class TagInit {

	public static final class Items {

		// Grit Papers
		public static final ITag.INamedTag<Item> GRIT_PAPERS = mod("grit_papers");
		public static final ITag.INamedTag<Item> IRON_GRIT_PAPER = mod("grit_papers/iron");
		public static final ITag.INamedTag<Item> DIAMOND_GRIT_PAPER = mod("grit_papers/diamond");
		public static final ITag.INamedTag<Item> NETHERITE_GRIT_PAPER = mod("grit_papers/netherite");

		// Cogwheels
		public static final ITag.INamedTag<Item> COGWHEELS = mod("cogwheels");
		public static final ITag.INamedTag<Item> WOODEN_COGWHEEL = mod("cogwheels/wooden");
		public static final ITag.INamedTag<Item> REFINED_IRON_COGWHEEL = mod("cogwheels/refined_iron");
		public static final ITag.INamedTag<Item> REFINED_GOLD_COGWHEEL = mod("cogwheels/refined_gold");
		public static final ITag.INamedTag<Item> REFINED_DIAMOND_COGWHEEL = mod("cogwheels/refined_diamond");

		// Grits
		public static final ITag.INamedTag<Item> GRITS = mod("grits");
		public static final ITag.INamedTag<Item> GRIT = mod("grits/grit");
		public static final ITag.INamedTag<Item> IRON_GRIT = mod("grits/iron");
		public static final ITag.INamedTag<Item> DIAMOND_GRIT = mod("grits/diamond");
		public static final ITag.INamedTag<Item> NETHERITE_GRIT = mod("grits/netherite");

		// Molds
		public static final ITag.INamedTag<Item> MOLDS = mod("molds");
		public static final ITag.INamedTag<Item> GEM_MOLD = mod("molds/gem");
		public static final ITag.INamedTag<Item> INGOT_MOLD = mod("molds/ingot");
		public static final ITag.INamedTag<Item> COGWHEEL_MOLD = mod("molds/cogwheel");

		// Unfired Cogwheels
		public static final ITag.INamedTag<Item> UNFIRED_COGWHEELS = mod("unfired_cogwheels");
		public static final ITag.INamedTag<Item> UNFIRED_IRON_COGWHEEL = mod("unfired_cogwheels/refined_iron");
		public static final ITag.INamedTag<Item> UNFIRED_GOLD_COGWHEEL = mod("unfired_cogwheels/refined_gold");
		public static final ITag.INamedTag<Item> UNFIRED_DIAMOND_COGWHEEL = mod("unfired_cogwheels/refined_diamond");

		// Unfired Gems
		public static final ITag.INamedTag<Item> UNFIRED_GEMS = mod("unfired_gems");
		public static final ITag.INamedTag<Item> UNFIRED_DIAMOND = mod("unfired_gems/diamond");

		// Unfired Ingots
		public static final ITag.INamedTag<Item> UNFIRED_INGOTS = mod("unfired_ingots");
		public static final ITag.INamedTag<Item> UNFIRED_IRON_INGOT = mod("unfired_ingots/refined_iron");
		public static final ITag.INamedTag<Item> UNFIRED_GOLD_INGOT = mod("unfired_ingots/refined_gold");
		public static final ITag.INamedTag<Item> UNFIRED_NETHERITE_INGOT = mod("unfired_ingots/refined_netherite");
		public static final ITag.INamedTag<Item> UNFIRED_CARBON_INGOT = mod("unfired_ingots/refined_carbon");

		// Unfired Molds
		public static final ITag.INamedTag<Item> UNFIRED_MOLDS = mod("unfired_molds");
		public static final ITag.INamedTag<Item> UNFIRED_GEM_MOLD = mod("unfired_molds/gem");
		public static final ITag.INamedTag<Item> UNFIRED_INGOT_MOLD = mod("unfired_molds/ingot");
		public static final ITag.INamedTag<Item> UNFIRED_COGWHEEL_MOLD = mod("unfired_molds/cogwheel");
		
		//Boxes
		public static final ITag.INamedTag<Item> FOOD_BOX = mod("boxes/food");
		
		/*
		 * 
		 * Forge
		 * 
		 */

		// Ingot
		public static final ITag.INamedTag<Item> INGOTS = forge("ingots");
		public static final ITag.INamedTag<Item> IRON_INGOT = forge("ingots/iron");
		public static final ITag.INamedTag<Item> GOLD_INGOT = forge("ingots/gold");
		public static final ITag.INamedTag<Item> NETHERITE_INGOT = forge("ingots/netherite");
		public static final ITag.INamedTag<Item> REFINED_IRON_INGOT = forge("ingots/refined_iron");
		public static final ITag.INamedTag<Item> REFINED_GOLD_INGOT = forge("ingots/refined_gold");
		public static final ITag.INamedTag<Item> REFINED_NETHERITE_INGOT = forge("ingots/refined_netherite");
		public static final ITag.INamedTag<Item> REFINED_CARBON_INGOT = forge("ingots/refined_carbon");

		// Dusts
		public static final ITag.INamedTag<Item> DUSTS = forge("dusts");
		public static final ITag.INamedTag<Item> IRON_DUST = forge("dusts/iron");
		public static final ITag.INamedTag<Item> GOLD_DUST = forge("dusts/gold");
		public static final ITag.INamedTag<Item> DIAMOND_DUST = forge("dusts/diamond");
		public static final ITag.INamedTag<Item> NETHERITE_DUST = forge("dusts/netherite");
		public static final ITag.INamedTag<Item> COAL_DUST = forge("dusts/coal");
		public static final ITag.INamedTag<Item> CHARCOAL_DUST = forge("dusts/charcoal");
		public static final ITag.INamedTag<Item> REFINED_IRON_DUST = forge("dusts/refined_iron");
		public static final ITag.INamedTag<Item> REFINED_GOLD_DUST = forge("dusts/refined_gold");
		public static final ITag.INamedTag<Item> REFINED_DIAMOND_DUST = forge("dusts/refined_diamond");
		public static final ITag.INamedTag<Item> REFINED_NETHERITE_DUST = forge("dusts/refined_netherite");
		public static final ITag.INamedTag<Item> REFINED_CARBON_DUST = forge("dusts/refined_carbon");
		public static final ITag.INamedTag<Item> REFINING_DUST = forge("dusts/refining");

		// Gems
		public static final ITag.INamedTag<Item> GEMS = forge("gems");
		public static final ITag.INamedTag<Item> DIAMOND = forge("gems/diamond");
		public static final ITag.INamedTag<Item> REFINED_DIAMOND = forge("gems/refined_diamond");
		public static final ITag.INamedTag<Item> PURE_CRYSTAL = forge("gems/pure_crystal");

		// Blocks
		public static final ITag.INamedTag<Item> COBBLESTONE = forge("cobblestone");
		public static final ITag.INamedTag<Item> ORES = forge("ores");
		public static final ITag.INamedTag<Item> PURE_CRYSTAL_ORE = forge("ores/pure_crystal");
		public static final ITag.INamedTag<Item> BLANK_ORE = forge("ores/blank");

		public static final ITag.INamedTag<Item> STORAGE_BLOCKS = forge("storage_blocks");
		public static final ITag.INamedTag<Item> IRON_STORAGE = forge("storage_blocks/refined_iron");
		public static final ITag.INamedTag<Item> GOLD_STORAGE = forge("storage_blocks/refined_gold");
		public static final ITag.INamedTag<Item> DIAMOND_STORAGE = forge("storage_blocks/refined_diamond");
		public static final ITag.INamedTag<Item> NETHERITE_STORAGE = forge("storage_blocks/refined_netherite");
		public static final ITag.INamedTag<Item> CARBON_STORAGE = forge("storage_blocks/refined_carbon");
		
		//Misc 
		public static final ITag.INamedTag<Item> TERRACOTTAS = forge("terracottas");
		public static final ITag.INamedTag<Item> CONCRETE_POWDERS = forge("concrete_powders");
		public static final ITag.INamedTag<Item> MC_GLASS = forge("minecraft_glass");
		public static final ITag.INamedTag<Item> MC_GLASS_PANE = forge("minecraft_glass_pane");
		public static final ITag.INamedTag<Item> SAPLINGS = minecraft("saplings");
		
		public static final ITag.INamedTag<Item> WOOL = minecraft("wool");

		private static ITag.INamedTag<Item> forge(String path) {
			return ItemTags.bind(new ResourceLocation("forge", path).toString());
		}
		
		private static ITag.INamedTag<Item> minecraft(String path) {
			return ItemTags.bind(new ResourceLocation("minecraft", path).toString());
		}

		private static ITag.INamedTag<Item> mod(String path) {
			return ItemTags.bind(new ResourceLocation(Refinement.MOD_ID, path).toString());
		}
	}

	public static final class Blocks {

		public static final ITag.INamedTag<Block> COBBLESTONE = forge("cobblestone");
		public static final ITag.INamedTag<Block> ORES = forge("ores");
		public static final ITag.INamedTag<Block> PURE_CRYSTAL_ORE = forge("ores/pure_crystal");
		public static final ITag.INamedTag<Block> BLANK_ORE = forge("ores/blank");

		public static final ITag.INamedTag<Block> STORAGE_BLOCKS = forge("storage_blocks");
		public static final ITag.INamedTag<Block> IRON_STORAGE = forge("storage_blocks/refined_iron");
		public static final ITag.INamedTag<Block> GOLD_STORAGE = forge("storage_blocks/refined_gold");
		public static final ITag.INamedTag<Block> DIAMOND_STORAGE = forge("storage_blocks/refined_diamond");
		public static final ITag.INamedTag<Block> NETHERITE_STORAGE = forge("storage_blocks/refined_netherite");
		public static final ITag.INamedTag<Block> CARBON_STORAGE = forge("storage_blocks/refined_carbon");

		private static ITag.INamedTag<Block> forge(String path) {
			return BlockTags.bind(new ResourceLocation("forge", path).toString());
		}

	}

}

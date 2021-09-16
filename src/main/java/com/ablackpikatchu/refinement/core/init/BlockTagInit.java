package com.ablackpikatchu.refinement.core.init;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class BlockTagInit {
	
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

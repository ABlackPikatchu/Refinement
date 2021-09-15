package com.ablackpikatchu.refinement.core.init;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class BlockTagInit {
	
	public static final class Blocks {
		
		public static final ITag.INamedTag<Block> ORES = forge("ores");
		
		
		private static ITag.INamedTag<Block> forge(String path) {
			return BlockTags.bind(new ResourceLocation("forge", path).toString());
		}
		
	}

}

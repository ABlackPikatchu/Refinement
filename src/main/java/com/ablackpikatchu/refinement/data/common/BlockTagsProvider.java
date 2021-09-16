package com.ablackpikatchu.refinement.data.common;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.TagInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsProvider extends net.minecraft.data.BlockTagsProvider {

	public BlockTagsProvider(DataGenerator p_i48256_1_, ExistingFileHelper existingFileHelper) {
		super(p_i48256_1_, Refinement.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void addTags() {
		
		
		//ORES
		tag(TagInit.Blocks.ORES).add(BlockInit.PURE_CRYSTAL_ORE.get());
    	tag(TagInit.Blocks.PURE_CRYSTAL_ORE).add(BlockInit.PURE_CRYSTAL_ORE.get());
    	tag(TagInit.Blocks.ORES).add(BlockInit.BLANK_ORE.get());
    	tag(TagInit.Blocks.BLANK_ORE).add(BlockInit.BLANK_ORE.get());
    	
    	
		//Storage Blocks
		tag(TagInit.Blocks.STORAGE_BLOCKS).add(BlockInit.REFINED_IRON_BLOCK.get());
    	tag(TagInit.Blocks.IRON_STORAGE).add(BlockInit.REFINED_IRON_BLOCK.get());
    	tag(TagInit.Blocks.STORAGE_BLOCKS).add(BlockInit.REFINED_GOLD_BLOCK.get());
    	tag(TagInit.Blocks.GOLD_STORAGE).add(BlockInit.REFINED_GOLD_BLOCK.get());
    	tag(TagInit.Blocks.STORAGE_BLOCKS).add(BlockInit.REFINED_DIAMOND_BLOCK.get());
    	tag(TagInit.Blocks.DIAMOND_STORAGE).add(BlockInit.REFINED_DIAMOND_BLOCK.get());
    	tag(TagInit.Blocks.STORAGE_BLOCKS).add(BlockInit.REFINED_NETHERITE_BLOCK.get());
    	tag(TagInit.Blocks.NETHERITE_STORAGE).add(BlockInit.REFINED_NETHERITE_BLOCK.get());
    	tag(TagInit.Blocks.STORAGE_BLOCKS).add(BlockInit.REFINED_CARBON_BLOCK.get());
    	tag(TagInit.Blocks.CARBON_STORAGE).add(BlockInit.REFINED_CARBON_BLOCK.get());
		
	}

}

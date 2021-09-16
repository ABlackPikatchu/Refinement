package com.ablackpikatchu.refinement.data.common;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.BlockTagInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsProvider extends net.minecraft.data.BlockTagsProvider {

	public BlockTagsProvider(DataGenerator p_i48256_1_, ExistingFileHelper existingFileHelper) {
		super(p_i48256_1_, Refinement.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void addTags() {
		
		
		//ORES
		tag(BlockTagInit.Blocks.ORES).add(BlockInit.PURE_CRYSTAL_ORE.get());
    	tag(BlockTagInit.Blocks.PURE_CRYSTAL_ORE).add(BlockInit.PURE_CRYSTAL_ORE.get());
    	tag(BlockTagInit.Blocks.ORES).add(BlockInit.BLANK_ORE.get());
    	tag(BlockTagInit.Blocks.BLANK_ORE).add(BlockInit.BLANK_ORE.get());
    	
    	
		//Storage Blocks
		tag(BlockTagInit.Blocks.STORAGE_BLOCKS).add(BlockInit.REFINED_IRON_BLOCK.get());
    	tag(BlockTagInit.Blocks.IRON_STORAGE).add(BlockInit.REFINED_IRON_BLOCK.get());
    	tag(BlockTagInit.Blocks.STORAGE_BLOCKS).add(BlockInit.REFINED_GOLD_BLOCK.get());
    	tag(BlockTagInit.Blocks.GOLD_STORAGE).add(BlockInit.REFINED_GOLD_BLOCK.get());
    	tag(BlockTagInit.Blocks.STORAGE_BLOCKS).add(BlockInit.REFINED_DIAMOND_BLOCK.get());
    	tag(BlockTagInit.Blocks.DIAMOND_STORAGE).add(BlockInit.REFINED_DIAMOND_BLOCK.get());
    	tag(BlockTagInit.Blocks.STORAGE_BLOCKS).add(BlockInit.REFINED_NETHERITE_BLOCK.get());
    	tag(BlockTagInit.Blocks.NETHERITE_STORAGE).add(BlockInit.REFINED_NETHERITE_BLOCK.get());
    	tag(BlockTagInit.Blocks.STORAGE_BLOCKS).add(BlockInit.REFINED_CARBON_BLOCK.get());
    	tag(BlockTagInit.Blocks.CARBON_STORAGE).add(BlockInit.REFINED_CARBON_BLOCK.get());
		
	}

}

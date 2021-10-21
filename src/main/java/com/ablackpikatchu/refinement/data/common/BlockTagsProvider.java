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
    	
    	tag(TagInit.Blocks.TIN_ORE).add(BlockInit.TIN_ORE.get());
    	tag(TagInit.Blocks.SILVER_ORE).add(BlockInit.SILVER_ORE.get());
    	tag(TagInit.Blocks.COPPER_ORE).add(BlockInit.COPPER_ORE.get());
    	tag(TagInit.Blocks.LEAD_ORE).add(BlockInit.LEAD_ORE.get());
    	
    	
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
    	
    	tag(TagInit.Blocks.LUMIUM_STORAGE).add(BlockInit.LUMIUM_BLOCK.get());
    	tag(TagInit.Blocks.SIGNALUM_STORAGE).add(BlockInit.SIGNALUM_BLOCK.get());
    	tag(TagInit.Blocks.ENDERIUM_STORAGE).add(BlockInit.ENDERIUM_BLOCK.get());
    	tag(TagInit.Blocks.TIN_STORAGE).add(BlockInit.TIN_BLOCK.get());
    	tag(TagInit.Blocks.SILVER_STORAGE).add(BlockInit.SILVER_BLOCK.get());
    	tag(TagInit.Blocks.LEAD_STORAGE).add(BlockInit.LEAD_BLOCK.get());
    	tag(TagInit.Blocks.COPPER_STORAGE).add(BlockInit.COPPER_BLOCK.get());
		
	}

}

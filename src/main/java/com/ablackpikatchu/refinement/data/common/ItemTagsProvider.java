package com.ablackpikatchu.refinement.data.common;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.ItemTagInit;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsProvider extends net.minecraft.data.ItemTagsProvider {

	public ItemTagsProvider(DataGenerator p_i232552_1_, BlockTagsProvider p_i232552_2_,
			ExistingFileHelper existingFileHelper) {
		super(p_i232552_1_, p_i232552_2_, Refinement.MOD_ID, existingFileHelper);
	}
	
	
	@Override
	protected void addTags() {
		//Ingot
		tag(Tags.Items.INGOTS_IRON).add(net.minecraft.item.Items.IRON_INGOT);
		tag(Tags.Items.INGOTS_GOLD).add(net.minecraft.item.Items.GOLD_INGOT);
		tag(Tags.Items.INGOTS_NETHERITE).add(net.minecraft.item.Items.NETHERITE_INGOT);
		tag(ItemTagInit.Items.REFINED_IRON_INGOT).add(ItemInit.REFINED_IRON_INGOT.get());
		tag(ItemTagInit.Items.INGOTS).add(ItemInit.REFINED_IRON_INGOT.get());
		tag(ItemTagInit.Items.REFINED_GOLD_INGOT).add(ItemInit.REFINED_GOLD_INGOT.get());
		tag(ItemTagInit.Items.INGOTS).add(ItemInit.REFINED_GOLD_INGOT.get());
		tag(ItemTagInit.Items.REFINED_NETHERITE_INGOT).add(ItemInit.REFINED_NETHERITE_INGOT.get());
		tag(ItemTagInit.Items.INGOTS).add(ItemInit.REFINED_NETHERITE_INGOT.get());
		tag(ItemTagInit.Items.REFINED_CARBON_INGOT).add(ItemInit.REFINED_CARBON_INGOT.get());
		tag(ItemTagInit.Items.INGOTS).add(ItemInit.REFINED_CARBON_INGOT.get());
		
		//Dust
		tag(ItemTagInit.Items.DIAMOND_DUST).add(ItemInit.DIAMOND_DUST.get());
		tag(ItemTagInit.Items.CHARCOAL_DUST).add(ItemInit.CHARCOAL_DUST.get());
		tag(ItemTagInit.Items.COAL_DUST).add(ItemInit.COAL_DUST.get());
		tag(ItemTagInit.Items.IRON_DUST).add(ItemInit.IRON_DUST.get());
		tag(ItemTagInit.Items.GOLD_DUST).add(ItemInit.GOLD_DUST.get());
		tag(ItemTagInit.Items.NETHERITE_DUST).add(ItemInit.NETHERITE_DUST.get());
		tag(ItemTagInit.Items.REFINED_IRON_DUST).add(ItemInit.REFINED_IRON_DUST.get());
		tag(ItemTagInit.Items.DUSTS).add(ItemInit.REFINED_IRON_DUST.get());
		tag(ItemTagInit.Items.REFINED_GOLD_DUST).add(ItemInit.REFINED_GOLD_DUST.get());
		tag(ItemTagInit.Items.DUSTS).add(ItemInit.REFINED_GOLD_DUST.get());
		tag(ItemTagInit.Items.REFINED_DIAMOND_DUST).add(ItemInit.REFINED_DIAMOND_DUST.get());
		tag(ItemTagInit.Items.DUSTS).add(ItemInit.REFINED_DIAMOND_DUST.get());
		tag(ItemTagInit.Items.REFINED_NETHERITE_DUST).add(ItemInit.REFINED_NETHERITE_DUST.get());
		tag(ItemTagInit.Items.DUSTS).add(ItemInit.REFINED_NETHERITE_DUST.get());
		tag(ItemTagInit.Items.REFINED_CARBON_DUST).add(ItemInit.REFINED_CARBON_DUST.get());
		tag(ItemTagInit.Items.DUSTS).add(ItemInit.REFINED_CARBON_DUST.get());
		tag(ItemTagInit.Items.REFINING_DUST).add(ItemInit.REFINING_DUST.get());
		tag(ItemTagInit.Items.DUSTS).add(ItemInit.REFINING_DUST.get());
		
		//Gems
		tag(Tags.Items.GEMS_DIAMOND).add(net.minecraft.item.Items.DIAMOND);
		tag(ItemTagInit.Items.REFINED_DIAMOND).add(ItemInit.REFINED_DIAMOND.get());
		tag(ItemTagInit.Items.GEMS).add(ItemInit.REFINED_DIAMOND.get());
		tag(ItemTagInit.Items.PURE_CRYSTAL).add(ItemInit.PURE_CRYSTAL.get());
		tag(ItemTagInit.Items.GEMS).add(ItemInit.PURE_CRYSTAL.get());
		
		//Grit Papers
		tag(ItemTagInit.Items.GRIT_PAPERS).add(ItemInit.IRON_GRIT_PAPER.get());
		tag(ItemTagInit.Items.IRON_GRIT_PAPER).add(ItemInit.IRON_GRIT_PAPER.get());
		tag(ItemTagInit.Items.GRIT_PAPERS).add(ItemInit.DIAMOND_GRIT_PAPER.get());
		tag(ItemTagInit.Items.DIAMOND_GRIT_PAPER).add(ItemInit.DIAMOND_GRIT_PAPER.get());
		tag(ItemTagInit.Items.GRIT_PAPERS).add(ItemInit.NETHERITE_GRIT_PAPER.get());
		tag(ItemTagInit.Items.NETHERITE_GRIT_PAPER).add(ItemInit.NETHERITE_GRIT_PAPER.get());
		
		//Cogwheels
		tag(ItemTagInit.Items.COGWHEELS).add(ItemInit.WOODEN_COGWHEEL.get());
		tag(ItemTagInit.Items.WOODEN_COGWHEEL).add(ItemInit.WOODEN_COGWHEEL.get());
		tag(ItemTagInit.Items.COGWHEELS).add(ItemInit.REFINED_IRON_COGWHEEL.get());
		tag(ItemTagInit.Items.REFINED_IRON_COGWHEEL).add(ItemInit.REFINED_IRON_COGWHEEL.get());
		tag(ItemTagInit.Items.COGWHEELS).add(ItemInit.REFINED_GOLD_COGWHEEL.get());
		tag(ItemTagInit.Items.REFINED_GOLD_COGWHEEL).add(ItemInit.REFINED_GOLD_COGWHEEL.get());
		tag(ItemTagInit.Items.COGWHEELS).add(ItemInit.REFINED_DIAMOND_COGWHEEL.get());
		tag(ItemTagInit.Items.REFINED_DIAMOND_COGWHEEL).add(ItemInit.REFINED_DIAMOND_COGWHEEL.get());
		
		//Grits
		tag(ItemTagInit.Items.GRITS).add(ItemInit.GRIT.get());
		tag(ItemTagInit.Items.GRIT).add(ItemInit.GRIT.get());
		tag(ItemTagInit.Items.GRITS).add(ItemInit.IRON_INFUSED_GRIT.get());
		tag(ItemTagInit.Items.IRON_GRIT).add(ItemInit.IRON_INFUSED_GRIT.get());
		tag(ItemTagInit.Items.GRITS).add(ItemInit.DIAMOND_INFUSED_GRIT.get());
		tag(ItemTagInit.Items.DIAMOND_GRIT).add(ItemInit.DIAMOND_INFUSED_GRIT.get());
		tag(ItemTagInit.Items.GRITS).add(ItemInit.NETHERITE_INFUSED_GRIT.get());
		tag(ItemTagInit.Items.NETHERITE_GRIT).add(ItemInit.NETHERITE_INFUSED_GRIT.get());
		
		//Molds
		tag(ItemTagInit.Items.INGOT_MOLD).add(ItemInit.INGOT_MOLD.get());
		tag(ItemTagInit.Items.MOLDS).add(ItemInit.INGOT_MOLD.get());
		tag(ItemTagInit.Items.GEM_MOLD).add(ItemInit.GEM_MOLD.get());
		tag(ItemTagInit.Items.MOLDS).add(ItemInit.GEM_MOLD.get());
		tag(ItemTagInit.Items.COGWHEEL_MOLD).add(ItemInit.COGWHEEL_MOLD.get());
		tag(ItemTagInit.Items.MOLDS).add(ItemInit.COGWHEEL_MOLD.get());
		
		//Unfired Cogwheels
		tag(ItemTagInit.Items.UNFIRED_COGWHEELS).add(ItemInit.UNFIRED_REFINED_IRON_COGWHEEL.get());
		tag(ItemTagInit.Items.UNFIRED_IRON_COGWHEEL).add(ItemInit.UNFIRED_REFINED_IRON_COGWHEEL.get());
		tag(ItemTagInit.Items.UNFIRED_COGWHEELS).add(ItemInit.UNFIRED_REFINED_GOLD_COGWHEEL.get());
		tag(ItemTagInit.Items.UNFIRED_GOLD_COGWHEEL).add(ItemInit.UNFIRED_REFINED_GOLD_COGWHEEL.get());
		tag(ItemTagInit.Items.UNFIRED_COGWHEELS).add(ItemInit.UNFIRED_REFINED_DIAMOND_COGWHEEL.get());
		tag(ItemTagInit.Items.UNFIRED_DIAMOND_COGWHEEL).add(ItemInit.UNFIRED_REFINED_DIAMOND_COGWHEEL.get());
		
		//Unfired Gems
    	tag(ItemTagInit.Items.UNFIRED_GEMS).add(ItemInit.REFINED_DIAMOND.get());
    	tag(ItemTagInit.Items.UNFIRED_DIAMOND).add(ItemInit.REFINED_DIAMOND.get());
    	
    	//Unfired Ingots
    	tag(ItemTagInit.Items.UNFIRED_INGOTS).add(ItemInit.UNFIRED_REFINED_IRON_INGOT.get());
    	tag(ItemTagInit.Items.UNFIRED_IRON_INGOT).add(ItemInit.UNFIRED_REFINED_IRON_INGOT.get());
    	tag(ItemTagInit.Items.UNFIRED_INGOTS).add(ItemInit.UNFIRED_REFINED_GOLD_INGOT.get());
    	tag(ItemTagInit.Items.UNFIRED_GOLD_INGOT).add(ItemInit.UNFIRED_REFINED_GOLD_INGOT.get());
    	tag(ItemTagInit.Items.UNFIRED_INGOTS).add(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT.get());
    	tag(ItemTagInit.Items.UNFIRED_NETHERITE_INGOT).add(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT.get());
    	tag(ItemTagInit.Items.UNFIRED_INGOTS).add(ItemInit.UNFIRED_REFINED_CARBON_INGOT.get());
    	tag(ItemTagInit.Items.UNFIRED_NETHERITE_INGOT).add(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT.get());
    	
    	//Unfired Molds
    	tag(ItemTagInit.Items.UNFIRED_MOLDS).add(ItemInit.UNFIRED_INGOT_MOLD.get());
    	tag(ItemTagInit.Items.UNFIRED_INGOT_MOLD).add(ItemInit.UNFIRED_INGOT_MOLD.get());
    	tag(ItemTagInit.Items.UNFIRED_MOLDS).add(ItemInit.UNFIRED_GEM_MOLD.get());
    	tag(ItemTagInit.Items.UNFIRED_GEM_MOLD).add(ItemInit.UNFIRED_GEM_MOLD.get());
    	tag(ItemTagInit.Items.UNFIRED_MOLDS).add(ItemInit.UNFIRED_COGWHEEL_MOLD.get());
    	tag(ItemTagInit.Items.UNFIRED_COGWHEEL_MOLD).add(ItemInit.UNFIRED_COGWHEEL_MOLD.get());
    	
    	//Ores
    	tag(ItemTagInit.Items.ORES).add(ItemInit.PURE_CRYSTAL_ORE.get());
    	tag(ItemTagInit.Items.PURE_CRYSTAL_ORE).add(ItemInit.PURE_CRYSTAL_ORE.get());
    	tag(ItemTagInit.Items.ORES).add(ItemInit.BLANK_ORE.get());
    	tag(ItemTagInit.Items.BLANK_ORE).add(ItemInit.BLANK_ORE.get());
    	
    	//Storage Blocks
    	tag(ItemTagInit.Items.STORAGE_BLOCKS).add(ItemInit.REFINED_IRON_BLOCK.get());
    	tag(ItemTagInit.Items.IRON_STORAGE).add(ItemInit.REFINED_IRON_BLOCK.get());
    	tag(ItemTagInit.Items.STORAGE_BLOCKS).add(ItemInit.REFINED_GOLD_BLOCK.get());
    	tag(ItemTagInit.Items.GOLD_STORAGE).add(ItemInit.REFINED_GOLD_BLOCK.get());
    	tag(ItemTagInit.Items.STORAGE_BLOCKS).add(ItemInit.REFINED_DIAMOND_BLOCK.get());
    	tag(ItemTagInit.Items.DIAMOND_STORAGE).add(ItemInit.REFINED_DIAMOND_BLOCK.get());
    	tag(ItemTagInit.Items.STORAGE_BLOCKS).add(ItemInit.REFINED_NETHERITE_BLOCK.get());
    	tag(ItemTagInit.Items.NETHERITE_STORAGE).add(ItemInit.REFINED_NETHERITE_BLOCK.get());
    	tag(ItemTagInit.Items.STORAGE_BLOCKS).add(ItemInit.REFINED_CARBON_BLOCK.get());
    	tag(ItemTagInit.Items.CARBON_STORAGE).add(ItemInit.REFINED_CARBON_BLOCK.get());
		
		
	}

}

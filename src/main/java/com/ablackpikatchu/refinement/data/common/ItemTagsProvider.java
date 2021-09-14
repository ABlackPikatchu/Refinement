package com.ablackpikatchu.refinement.data.common;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.TagInit;

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
		
		//Dust
		tag(TagInit.Items.DIAMOND_DUST).add(ItemInit.DIAMOND_DUST.get());
		tag(TagInit.Items.CHARCOAL_DUST).add(ItemInit.CHARCOAL_DUST.get());
		tag(TagInit.Items.COAL_DUST).add(ItemInit.COAL_DUST.get());
	}

}

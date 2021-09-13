package com.ablackpikatchu.refinement.data.common;

import com.ablackpikatchu.refinement.Refinement;

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
		tag(Tags.Items.INGOTS_IRON).add(net.minecraft.item.Items.IRON_INGOT);
	}

}

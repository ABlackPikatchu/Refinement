package com.ablackpikatchu.refinement.data.common;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsProvider extends net.minecraft.data.BlockTagsProvider {

	public BlockTagsProvider(DataGenerator p_i48256_1_, ExistingFileHelper existingFileHelper) {
		super(p_i48256_1_, Refinement.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void addTags() {
		
	}

}

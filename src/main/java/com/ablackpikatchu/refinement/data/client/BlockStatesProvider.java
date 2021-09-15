package com.ablackpikatchu.refinement.data.client;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStatesProvider extends BlockStateProvider {

	ExistingFileHelper existingFileHelper;
	BlockModelProvider blockModelProvider;
	
	public BlockStatesProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Refinement.MOD_ID, existingFileHelper);
		this.existingFileHelper = existingFileHelper;
	}
	
	@Override
	protected void registerStatesAndModels() {
		ModelFile blankOre = new ModelFile.UncheckedModelFile(modLoc("block/blank_ore"));
		simpleBlock(BlockInit.BLANK_ORE.get(), blankOre);
	}

}

package com.ablackpikatchu.refinement.data.client;

import java.util.HashMap;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.data.maps.LootTableMaps;
import com.ablackpikatchu.refinement.resourcecrops.common.ModCrop;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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

		HashMap<Block, IItemProvider> cropBlocks = new HashMap<>();
		LootTableMaps.addCropLoot(cropBlocks);
		cropBlocks.forEach((block, resource) -> {
			cropBlock(block);
		});
		
		simpleBlock(BlockInit.REFINED_LEAVES.get());
		logBlock(BlockInit.REFINED_LOG.get());
		logBlock(BlockInit.REFINED_STRIPPED_LOG.get());
		cross(BlockInit.REFINED_SAPLING.get());

	}

	private void cross(Block block) {
		getVariantBuilder(block).partialState().setModels(new ConfiguredModel(crossModel(block, blockTexture(block))));
	}

	public void cropBlock(Block block) {
		getVariantBuilder(block).partialState().with(ModCrop.AGE, 0).modelForState()
				.modelFile(models().cross(name(block) + "_0", cropModel(block, 0))).addModel().partialState()
				.with(ModCrop.AGE, 1).modelForState().modelFile(models().cross(name(block) + "_1", cropModel(block, 1)))
				.addModel().partialState().with(ModCrop.AGE, 2).modelForState()
				.modelFile(models().cross(name(block) + "_2", cropModel(block, 2))).addModel().partialState()
				.with(ModCrop.AGE, 3).modelForState().modelFile(models().cross(name(block) + "_3", cropModel(block, 3)))
				.addModel().partialState().with(ModCrop.AGE, 4).modelForState()
				.modelFile(models().cross(name(block) + "_4", cropModel(block, 4))).addModel().partialState()
				.with(ModCrop.AGE, 5).modelForState().modelFile(models().cross(name(block) + "_5", cropModel(block, 5)))
				.addModel().partialState().with(ModCrop.AGE, 6).modelForState()
				.modelFile(models().cross(name(block) + "_6", cropModel(block, 6))).addModel().partialState()
				.with(ModCrop.AGE, 7).modelForState().modelFile(models().cross(name(block) + "_7", cropModel(block, 7)))
				.addModel();
	}

	public String name(Block block) {
		return block.getRegistryName().getPath();
	}
	
	public ModelFile crossModel(Block block, ResourceLocation name) {
        return models().cross(name(block), name);
    }

	public ResourceLocation cropModel(Block block, int age) {
		return new ResourceLocation(Refinement.MOD_ID, "blocks/crops/" + name(block) + "/stage_" + age);
	}
	
	public void logBlock(Block block) {
        axisBlock((RotatedPillarBlock) block, blockTexture(block), extend(blockTexture(block), "_top"));
    }
	
	public ResourceLocation blockTexture(Block block) {
        ResourceLocation name = block.getRegistryName();
        return new ResourceLocation(name.getNamespace(), "blocks" + "/" + name.getPath());
    }
	
	private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }

}

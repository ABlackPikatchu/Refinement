package com.ablackpikatchu.refinement.data.client;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

import java.util.HashMap;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.MachineBlock;
import com.ablackpikatchu.refinement.common.block.StorageBinBlock;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.data.maps.LootTableMaps;
import com.ablackpikatchu.refinement.resourcecrops.common.ModCrop;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import static net.minecraft.util.Direction.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder.PartialBlockstate;
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

		cubeAll(BlockInit.COPPER_BLOCK.get());

		machineBlock(BlockInit.SMELTER_BLOCK.get(), "machines/smelter", "machines/smelter_lit");
		machineBlock(BlockInit.GRINDER.get(), "machines/grinder", "machines/grinder_lit");
		machineBlock(BlockInit.MIXER.get(), "machines/mixer", "machines/mixer_lit");
		
		storageBin(BlockInit.ALPHA_STORAGE_BIN_BLOCK.get(), "storage_bins/alpha_front", "storage_bins/alpha_front_locked", "storage_bins/alpha_top");
		storageBin(BlockInit.BETA_STORAGE_BIN_BLOCK, "storage_bins/beta_front", "storage_bins/beta_front_locked", "storage_bins/beta_top");
		storageBin(BlockInit.GAMMA_STORAGE_BIN_BLOCK, "storage_bins/gamma_front", "storage_bins/gamma_front_locked", "storage_bins/gamma_top");
		storageBin(BlockInit.EPSILON_STORAGE_BIN_BLOCK, "storage_bins/epsilon_front", "storage_bins/epsilon_front_locked", "storage_bins/epsilon_top");
		storageBin(BlockInit.OMEGA_STORAGE_BIN_BLOCK, "storage_bins/omega_front", "storage_bins/omega_front_locked", "storage_bins/omega_top");

	}

	private void cross(Block block) {
		getVariantBuilder(block).partialState().setModels(new ConfiguredModel(crossModel(block, blockTexture(block))));
	}

	public void cropBlock(Block block) {
		getVariantBuilder(block).partialState().with(ModCrop.AGE, 0).modelForState()
				.modelFile(models().cross("block/crop/" + name(block) + "_0", cropModel(block, 0))).addModel()
				.partialState().with(ModCrop.AGE, 1).modelForState()
				.modelFile(models().cross("block/crop/" + name(block) + "_1", cropModel(block, 1))).addModel()
				.partialState().with(ModCrop.AGE, 2).modelForState()
				.modelFile(models().cross("block/crop/" + name(block) + "_2", cropModel(block, 2))).addModel()
				.partialState().with(ModCrop.AGE, 3).modelForState()
				.modelFile(models().cross("block/crop/" + name(block) + "_3", cropModel(block, 3))).addModel()
				.partialState().with(ModCrop.AGE, 4).modelForState()
				.modelFile(models().cross("block/crop/" + name(block) + "_4", cropModel(block, 4))).addModel()
				.partialState().with(ModCrop.AGE, 5).modelForState()
				.modelFile(models().cross("block/crop/" + name(block) + "_5", cropModel(block, 5))).addModel()
				.partialState().with(ModCrop.AGE, 6).modelForState()
				.modelFile(models().cross("block/crop/" + name(block) + "_6", cropModel(block, 6))).addModel()
				.partialState().with(ModCrop.AGE, 7).modelForState()
				.modelFile(models().cross("block/crop/" + name(block) + "_7", cropModel(block, 7))).addModel();
	}

	public static final Direction[] ROTATABLE_DIRECTIONS = new Direction[] {
			NORTH, SOUTH, EAST, WEST
	};

	public void machineBlock(Block block, String normalTexture, String litTexture) {
		PartialBlockstate state = getVariantBuilder(block).partialState();
		for (Direction direction : ROTATABLE_DIRECTIONS) {
			int rotationY = 0;
			if (direction == Direction.EAST)
				rotationY = 90;
			if (direction == Direction.SOUTH)
				rotationY = 180;
			if (direction == Direction.WEST)
				rotationY = 270;

			state.with(MachineBlock.LIT, false).with(MachineBlock.FACING, direction).modelForState()
					.modelFile(models().orientable("block/machine/" + name(block),
							new ResourceLocation(MOD_ID, "blocks/machines/machine_side"),
							new ResourceLocation(MOD_ID, "blocks/" + normalTexture),
							new ResourceLocation(MOD_ID, "blocks/machines/machine_side")))
					.rotationY(rotationY).addModel();

			state.with(MachineBlock.LIT, true).with(MachineBlock.FACING, direction).modelForState()
					.modelFile(models().orientable("block/machine/" + name(block) + "_lit",
							new ResourceLocation(MOD_ID, "blocks/machines/machine_side"),
							new ResourceLocation(MOD_ID, "blocks/" + litTexture),
							new ResourceLocation(MOD_ID, "blocks/machines/machine_side")))
					.rotationY(rotationY).addModel();
		}
		
		itemModels().withExistingParent(block.asItem().getRegistryName().toString(),
				new ResourceLocation(MOD_ID, "block/machine/" + name(block)));
	}
	
	public void storageBin(Block block, String normalFront, String lockedFront, String top) {
		PartialBlockstate state = getVariantBuilder(block).partialState();
		for (Direction direction : ROTATABLE_DIRECTIONS) {
			int rotationY = 0;
			if (direction == Direction.EAST)
				rotationY = 90;
			if (direction == Direction.SOUTH)
				rotationY = 180;
			if (direction == Direction.WEST)
				rotationY = 270;
			
			state.with(StorageBinBlock.LOCKED, false).with(StorageBinBlock.FACING, direction).modelForState()
			.modelFile(models().orientable("block/storage_bin/" + name(block),
					new ResourceLocation(MOD_ID, "blocks/machines/machine_side"),
					new ResourceLocation(MOD_ID, "blocks/" + normalFront),
					new ResourceLocation(MOD_ID, "blocks/" + top)))
			.rotationY(rotationY).addModel();
			
			state.with(StorageBinBlock.LOCKED, true).with(StorageBinBlock.FACING, direction).modelForState()
			.modelFile(models().orientable("block/machine/" + name(block) + "_locked",
					new ResourceLocation(MOD_ID, "blocks/machines/machine_side"),
					new ResourceLocation(MOD_ID, "blocks/" + lockedFront),
					new ResourceLocation(MOD_ID, "blocks/" + top)))
			.rotationY(rotationY).addModel();
		}
		
		itemModels().withExistingParent(block.asItem().getRegistryName().toString(),
				new ResourceLocation(MOD_ID, "block/storage_bin/" + name(block)));
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

	@Override
	public ResourceLocation blockTexture(Block block) {
		ResourceLocation name = block.getRegistryName();
		return new ResourceLocation(name.getNamespace(), "blocks" + "/" + name.getPath());
	}

	private ResourceLocation extend(ResourceLocation rl, String suffix) {
		return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
	}

}

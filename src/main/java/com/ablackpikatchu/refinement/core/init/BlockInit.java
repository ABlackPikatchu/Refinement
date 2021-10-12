package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.*;
import com.ablackpikatchu.refinement.common.block.machine.CarbonGeneratorBlock;
import com.ablackpikatchu.refinement.common.block.machine.DNASequencerBlock;
import com.ablackpikatchu.refinement.common.block.machine.EnergyGeneratorBlock;
import com.ablackpikatchu.refinement.common.block.machine.GrinderBlock;
import com.ablackpikatchu.refinement.common.block.machine.MixerBlock;
import com.ablackpikatchu.refinement.common.block.machine.MoldPressBlock;
import com.ablackpikatchu.refinement.common.block.machine.SmelterBlock;
import com.ablackpikatchu.refinement.common.block.machine.VaccumulatorBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			Refinement.MOD_ID);

	public static final RegistryObject<Block> REFINED_IRON_BLOCK = BLOCKS
			.register("refined_iron_block",
					() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(5f)
							.harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)
							.requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> REFINED_GOLD_BLOCK = BLOCKS.register("refined_gold_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.GOLD).strength(3f).harvestLevel(2)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> REFINED_DIAMOND_BLOCK = BLOCKS
			.register("refined_diamond_block",
					() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.DIAMOND).strength(5f)
							.harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)
							.requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> REFINED_NETHERITE_BLOCK = BLOCKS.register("refined_netherite_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(50f)
					.harvestLevel(3).harvestTool(ToolType.PICKAXE).sound(SoundType.NETHERITE_BLOCK)
					.requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MACHINE_FRAME = BLOCKS.register("machine_frame",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(5f)
					.harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).noOcclusion()
					.requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> REFINED_CARBON_BLOCK = BLOCKS
			.register("refined_carbon_block",
					() -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(5f)
							.harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)
							.requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> PURE_CRYSTAL_ORE = BLOCKS.register("pure_crystal_ore",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(5.0F, 6.0F)
					.sound(SoundType.STONE)));

	public static final RegistryObject<Block> BLANK_ORE = BLOCKS.register("blank_ore", () -> new BlankOreBlock());

	// TE Blocks
	public static final RegistryObject<Block> GRINDER = BLOCKS.register("grinder", () -> new GrinderBlock());
	public static final RegistryObject<Block> MIXER = BLOCKS.register("mixer", () -> new MixerBlock());
	public static final RegistryObject<Block> MOLD_PRESS = BLOCKS.register("mold_press", () -> new MoldPressBlock());
	public static final RegistryObject<Block> DNA_SEQUENCER = BLOCKS.register("dna_sequencer",
			() -> new DNASequencerBlock());

	public static final RegistryObject<Block> VACCUMULATOR = BLOCKS.register("vaccumulator",
			() -> new VaccumulatorBlock());

	public static final RegistryObject<Block> CARBON_GENERATOR_BLOCK = BLOCKS.register("carbon_generator",
			() -> new CarbonGeneratorBlock());

	public static final RegistryObject<Block> ENERGY_GENERATOR_BLOCK = BLOCKS.register("energy_generator",
			() -> new EnergyGeneratorBlock());
	
	public static final RegistryObject<Block> SMELTER_BLOCK = BLOCKS.register("smelter",
			() -> new SmelterBlock());
	
	public static final RegistryObject<Block> ENERGY_CABLE_BLOCK = BLOCKS.register("energy_cable", EnergyCableBlock::new);
	
	public static final RegistryObject<Block> RESOURCE_STATUE_BLOCK = BLOCKS.register("resource_statue", () -> new ResourceStatueBlock());

	// POIs
	public static final RegistryObject<Block> MATERIALS_STATION = BLOCKS.register("materials_station",
			() -> new Block(AbstractBlock.Properties.copy(Blocks.SMITHING_TABLE)));

	// Trees
	public static final RegistryObject<Block> REFINED_LOG = BLOCKS.register("refined_log",
			() -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD)
					.strength(2f, 10f).harvestLevel(0).harvestTool(ToolType.AXE)));

	public static final RegistryObject<Block> REFINED_STRIPPED_LOG = BLOCKS.register("refined_stripped_log",
			() -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD)
					.strength(2f, 3f).harvestLevel(0).harvestTool(ToolType.AXE)));

	public static final RegistryObject<Block> REFINED_LEAVES = BLOCKS.register("refined_leaves",
			() -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2f, 1f)
					.randomTicks().noOcclusion()));

	public static final RegistryObject<Block> REFINED_SAPLING = BLOCKS.register("refined_sapling",
			() -> new RefinedSaplingBlock(TreeInit.REFINED, AbstractBlock.Properties.of(Material.LEAVES)
					.sound(SoundType.GRASS).strength(0f).randomTicks().noOcclusion()));
}

package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.BlankOreBlock;
import com.ablackpikatchu.refinement.common.block.EnergyCableBlock;
import com.ablackpikatchu.refinement.common.block.RefinedSaplingBlock;
import com.ablackpikatchu.refinement.common.block.ResourceStatueBlock;
import com.ablackpikatchu.refinement.common.block.StorageBinBlock;
import com.ablackpikatchu.refinement.common.block.machine.AlloySmelterBlock;
import com.ablackpikatchu.refinement.common.block.machine.CarbonGeneratorBlock;
import com.ablackpikatchu.refinement.common.block.machine.DNASequencerBlock;
import com.ablackpikatchu.refinement.common.block.machine.EnergyGeneratorBlock;
import com.ablackpikatchu.refinement.common.block.machine.GrinderBlock;
import com.ablackpikatchu.refinement.common.block.machine.MixerBlock;
import com.ablackpikatchu.refinement.common.block.machine.MoldPressBlock;
import com.ablackpikatchu.refinement.common.block.machine.SmelterBlock;
import com.ablackpikatchu.refinement.common.block.machine.VaccumulatorBlock;
import com.ablackpikatchu.refinement.common.te.tier.Tier;
import com.ablackpikatchu.refinement.core.anotation.registries.HoldsRegstries;
import com.ablackpikatchu.refinement.core.anotation.registries.RegisterBlock;

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

@HoldsRegstries
public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			Refinement.MOD_ID);

	public static final RegistryObject<Block> REFINED_IRON_BLOCK = BLOCKS
			.register("refined_iron_block",
					() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(5f)
							.harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)
							.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> TIN_BLOCK = BLOCKS
			.register("tin_block",
					() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(5f)
							.harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)
							.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS
			.register("silver_block",
					() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(5f)
							.harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)
							.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> COPPER_BLOCK = BLOCKS
			.register("copper_block",
					() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(5f)
							.harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)
							.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> REFINED_GOLD_BLOCK = BLOCKS.register("refined_gold_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.GOLD).strength(3f).harvestLevel(2)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> LUMIUM_BLOCK = BLOCKS.register("lumium_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.GOLD).strength(3f).harvestLevel(2)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> LEAD_BLOCK = BLOCKS.register("lead_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.GOLD).strength(3f).harvestLevel(2)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> REFINED_DIAMOND_BLOCK = BLOCKS
			.register("refined_diamond_block",
					() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.DIAMOND).strength(5f)
							.harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)
							.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> SIGNALUM_BLOCK = BLOCKS
			.register("signalum_block",
					() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.DIAMOND).strength(5f)
							.harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)
							.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> REFINED_NETHERITE_BLOCK = BLOCKS.register("refined_netherite_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(50f)
					.harvestLevel(3).harvestTool(ToolType.PICKAXE).sound(SoundType.NETHERITE_BLOCK)
					.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> ENDERIUM_BLOCK = BLOCKS.register("enderium_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(45f)
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
	public static final RegistryObject<Block> TIN_ORE = oreBlock("tin_ore");
	public static final RegistryObject<Block> SILVER_ORE = oreBlock("silver_ore");
	public static final RegistryObject<Block> COPPER_ORE = oreBlock("copper_ore");
	public static final RegistryObject<Block> LEAD_ORE = oreBlock("lead_ore");

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
	
	public static final RegistryObject<Block> ALLOY_SMELTER_BLOCK = BLOCKS.register("alloy_smelter", AlloySmelterBlock::new);
	
	public static final RegistryObject<Block> ENERGY_CABLE_BLOCK = BLOCKS.register("energy_cable", EnergyCableBlock::new);
	
	public static final RegistryObject<Block> ALPHA_STORAGE_BIN_BLOCK = BLOCKS.register("alpha_storage_bin", () -> new StorageBinBlock(Tier.ALPHA, 4096));
	public static final RegistryObject<Block> BETA_STORAGE_BIN_BLOCK = BLOCKS.register("beta_storage_bin", () -> new StorageBinBlock(Tier.BETA, 8192));
	
	@RegisterBlock(registryName = "gamma_storage_bin")
	public static final Block GAMMA_STORAGE_BIN_BLOCK = new StorageBinBlock(Tier.GAMMA, 16384);
	@RegisterBlock(registryName = "epsilon_storage_bin")
	public static final Block EPSILON_STORAGE_BIN_BLOCK = new StorageBinBlock(Tier.EPSILON, 65536);
	@RegisterBlock(registryName = "omega_storage_bin")
	public static final Block OMEGA_STORAGE_BIN_BLOCK = new StorageBinBlock(Tier.OMEGA, 131072);
	
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
	
	private static RegistryObject<Block> oreBlock(String registryName) {
		return BLOCKS.register(registryName, () -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_ORE)));
	}
}

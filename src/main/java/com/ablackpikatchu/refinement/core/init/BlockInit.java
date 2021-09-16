package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.BlankOreBlock;
import com.ablackpikatchu.refinement.common.block.GrinderBlock;
import com.ablackpikatchu.refinement.common.block.MixerBlock;
import com.ablackpikatchu.refinement.common.block.MoldPressBlock;
import com.ablackpikatchu.refinement.common.block.VaccumulatorBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
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

	public static final RegistryObject<Block> VACCUMULATOR = BLOCKS.register("vaccumulator",
			() -> new VaccumulatorBlock());
}

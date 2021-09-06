package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;

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

	public static final RegistryObject<Block> REFINED_IRON_BLOCK = BLOCKS.register("refined_iron_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(5f)
					.harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)));
	public static final RegistryObject<Block> REFINED_GOLD_BLOCK = BLOCKS.register("refined_gold_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.GOLD).strength(3f).harvestLevel(2)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)));
	public static final RegistryObject<Block> REFINED_DIAMOND_BLOCK = BLOCKS.register("refined_diamond_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.DIAMOND).strength(5f)
					.harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)));
	public static final RegistryObject<Block> REFINED_NETHERITE_BLOCK = BLOCKS.register("refined_netherite_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(50f)
					.harvestLevel(3).harvestTool(ToolType.PICKAXE).sound(SoundType.NETHERITE_BLOCK)));

}

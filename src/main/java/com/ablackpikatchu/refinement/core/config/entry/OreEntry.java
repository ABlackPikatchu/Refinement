package com.ablackpikatchu.refinement.core.config.entry;

import com.google.gson.annotations.Expose;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;

import net.minecraftforge.registries.ForgeRegistries;

public class OreEntry {

	@Expose
	public String block;
	@Expose
	public int maxVeinSize;
	@Expose
	public int minHeight;
	@Expose
	public int maxHeight;
	@Expose
	public Type fillerType;

	public OreEntry(String block, int maxVeinSize, int minHeight, int maxHeight, Type fillerType) {
		this.block = block;
		this.maxVeinSize = maxVeinSize;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.fillerType = fillerType;
	}

	public OreEntry(Block block, int maxVeinSize, int minHeight, int maxHeight, Type fillerType) {
		this.block = block.getRegistryName().toString();
		this.maxVeinSize = maxVeinSize;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.fillerType = fillerType;
	}

	public Block getBlock() {
		return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(this.block));
	}

	public RuleTest getFillerType() {

		switch (this.fillerType) {
		case NATURAL_STONE:
			return OreFeatureConfig.FillerBlockType.NATURAL_STONE;
		case NETHERRACK:
			return OreFeatureConfig.FillerBlockType.NETHERRACK;
		case NETHER_ORE_REPLACEABLES:
			return OreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES;
		}

		return OreFeatureConfig.FillerBlockType.NATURAL_STONE;
	}

	public enum Type {
		NATURAL_STONE, NETHERRACK, NETHER_ORE_REPLACEABLES
	}

}

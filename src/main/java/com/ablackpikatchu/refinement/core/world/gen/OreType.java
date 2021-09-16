package com.ablackpikatchu.refinement.core.world.gen;

import com.ablackpikatchu.refinement.core.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum OreType {

	PURE(Lazy.of(BlockInit.PURE_CRYSTAL_ORE), 6, 4, 20),
    BLANK(Lazy.of(BlockInit.BLANK_ORE), 2, 5, 25);

	private final Lazy<Block> block;
	private final int maxVeinSize;
	private final int minHeight;
	private final int maxHeight;

	OreType(Lazy<Block> block, int maxVeinSize, int minHeight, int maxHeight) {
		this.block = block;
		this.maxVeinSize = maxVeinSize;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}

	public Lazy<Block> getBlock() {
		return block;
	}

	public int getMaxVeinSize() {
		return maxVeinSize;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}
	
	public static OreType get(Block block) {
		for (OreType ore : values()) {
			if(block == ore.block) {
				return ore;
			}
		}
		return null;
	}
}

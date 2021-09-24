package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.crop.ModCrop;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CropInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			Refinement.MOD_ID);

	public static final RegistryObject<Block> IRON_CROP = BLOCKS.register("iron_crop",
			() -> new ModCrop(AbstractBlock.Properties.copy(Blocks.WHEAT), mod("iron_seeds"),
					mod("iron_essence"), false));
	
	private static ResourceLocation mod(String name) {
		return new ResourceLocation(Refinement.MOD_ID, name);
	}

}

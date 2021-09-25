package com.ablackpikatchu.refinement.core.init;

import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT_RESOURCE_CROPS;
import static com.ablackpikatchu.refinement.core.init.ItemInit.ITEMS;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.ModCrop;
import com.ablackpikatchu.refinement.common.item.ModSeeds;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CropInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			Refinement.MOD_ID);

	// Crops
	public static final RegistryObject<Block> IRON_CROP = BLOCKS.register("iron_crop",
			() -> new ModCrop(AbstractBlock.Properties.copy(Blocks.WHEAT), mod("iron_seeds"), mod("iron_essence"),
					false));

	public static final RegistryObject<Block> COAL_CROP = BLOCKS.register("coal_crop",
			() -> new ModCrop(AbstractBlock.Properties.copy(Blocks.WHEAT), mod("coal_seeds"), mod("coal_essence"),
					false));

	// Seeds
	public static final RegistryObject<BlockItem> IRON_SEEDS = ITEMS.register("iron_seeds",
			() -> new ModSeeds(CropInit.IRON_CROP.get(), new Item.Properties().tab(REFINEMENT_RESOURCE_CROPS)));
	public static final RegistryObject<BlockItem> COAL_SEEDS = ITEMS.register("coal_seeds",
			() -> new ModSeeds(CropInit.COAL_CROP.get(), new Item.Properties().tab(REFINEMENT_RESOURCE_CROPS)));

	private static ResourceLocation mod(String name) {
		return new ResourceLocation(Refinement.MOD_ID, name);
	}

}

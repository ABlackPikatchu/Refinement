package com.ablackpikatchu.refinement.core.init;

import static com.ablackpikatchu.refinement.core.init.ItemInit.ITEMS;
import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT_RESOURCE_CROPS;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.resourcecrops.common.ModCrop;
import com.ablackpikatchu.refinement.resourcecrops.common.ModEssence;
import com.ablackpikatchu.refinement.resourcecrops.common.ModSeeds;

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

	public static final RegistryObject<Block> GOLD_CROP = BLOCKS.register("gold_crop",
			() -> new ModCrop(AbstractBlock.Properties.copy(Blocks.WHEAT), mod("gold_seeds"), mod("gold_essence"),
					false));

	// Seeds
	public static final RegistryObject<BlockItem> IRON_SEEDS = ITEMS.register("iron_seeds",
			() -> new ModSeeds(CropInit.IRON_CROP.get(), new Item.Properties().tab(REFINEMENT_RESOURCE_CROPS)));
	public static final RegistryObject<BlockItem> COAL_SEEDS = ITEMS.register("coal_seeds",
			() -> new ModSeeds(CropInit.COAL_CROP.get(), new Item.Properties().tab(REFINEMENT_RESOURCE_CROPS)));
	public static final RegistryObject<BlockItem> GOLD_SEEDS = ITEMS.register("gold_seeds",
			() -> new ModSeeds(CropInit.GOLD_CROP.get(), new Item.Properties().tab(REFINEMENT_RESOURCE_CROPS)));

	// Essences
	public static final RegistryObject<Item> IRON_ESSENCE = ITEMS.register("iron_essence",
			() -> new ModEssence(new Item.Properties().tab(REFINEMENT_RESOURCE_CROPS)));
	public static final RegistryObject<Item> COAL_ESSENCE = ITEMS.register("coal_essence",
			() -> new ModEssence(new Item.Properties().tab(REFINEMENT_RESOURCE_CROPS)));
	public static final RegistryObject<Item> GOLD_ESSENCE = ITEMS.register("gold_essence",
			() -> new ModEssence(new Item.Properties().tab(REFINEMENT_RESOURCE_CROPS)));
	public static final RegistryObject<Item> NATURE_ESSENCE = ITEMS.register("nature_essence",
			() -> new ModEssence(new Item.Properties().tab(REFINEMENT_RESOURCE_CROPS)));

	private static ResourceLocation mod(String name) {
		return new ResourceLocation(Refinement.MOD_ID, name);
	}

}

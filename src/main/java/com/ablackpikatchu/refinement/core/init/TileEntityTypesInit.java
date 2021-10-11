package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.te.machine.*;
import com.ablackpikatchu.refinement.common.te.misc_tes.CableTileEntity;
import com.ablackpikatchu.refinement.common.te.misc_tes.ResourceStatueTileEntity;
import com.ablackpikatchu.refinement.common.te.misc_tes.VaccumulatorTileEntity;

import net.minecraft.tileentity.TileEntityType;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypesInit {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, Refinement.MOD_ID);

	public static final RegistryObject<TileEntityType<GrinderTileEntity>> GRINDER_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("grinder", () -> TileEntityType.Builder
					.of(GrinderTileEntity::new, BlockInit.GRINDER.get()).build(null));
	public static final RegistryObject<TileEntityType<MixerTileEntity>> MIXER_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("mixer", () -> TileEntityType.Builder
					.of(MixerTileEntity::new, BlockInit.MIXER.get()).build(null));
	public static final RegistryObject<TileEntityType<MoldPressTileEntity>> MOLD_PRESS_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("mold_press", () -> TileEntityType.Builder
					.of(MoldPressTileEntity::new, BlockInit.MOLD_PRESS.get()).build(null));
	public static final RegistryObject<TileEntityType<DNASequencerTileEntity>> DNA_SEQUENCER_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("dna_sequencer", () -> TileEntityType.Builder
					.of(DNASequencerTileEntity::new, BlockInit.DNA_SEQUENCER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<VaccumulatorTileEntity>> VACCUMULATOR_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("vaccumulator", () -> TileEntityType.Builder
					.of(VaccumulatorTileEntity::new, BlockInit.VACCUMULATOR.get()).build(null));
	
	public static final RegistryObject<TileEntityType<CarbonGeneratorTileEntity>> CARBON_GENERATOR_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("carbon_generator", () -> TileEntityType.Builder
					.of(CarbonGeneratorTileEntity::new, BlockInit.CARBON_GENERATOR_BLOCK.get()).build(null));

	public static final RegistryObject<TileEntityType<EnergyGeneratorTileEntity>> ENERGY_GENERATOR_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("energy_generator", () -> TileEntityType.Builder
					.of(EnergyGeneratorTileEntity::new, BlockInit.ENERGY_GENERATOR_BLOCK.get()).build(null));
	
	public static final RegistryObject<TileEntityType<SmelterTileEntity>> SMELTER_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("smelter", () -> TileEntityType.Builder
					.of(SmelterTileEntity::new, BlockInit.SMELTER_BLOCK.get()).build(null));
	
	public static final RegistryObject<TileEntityType<ResourceStatueTileEntity>> RESOURCE_STATUE_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("resource_statue", () -> TileEntityType.Builder
					.of(ResourceStatueTileEntity::new, BlockInit.RESOURCE_STATUE_BLOCK.get()).build(null));
	
	public static final RegistryObject<TileEntityType<CableTileEntity>> CABLE_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("cable", () -> TileEntityType.Builder
					.of(CableTileEntity::new, BlockInit.CABLE_BLOCK.get()).build(null));

}

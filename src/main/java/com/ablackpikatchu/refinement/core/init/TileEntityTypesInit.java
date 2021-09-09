package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.te.machine.GrinderTileEntity;
import com.ablackpikatchu.refinement.common.te.machine.MixerTileEntity;

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

}

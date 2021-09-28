package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.world.feature.RefinedTreeFeature;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureInit {
	
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
			Refinement.MOD_ID);
	
	public static final RegistryObject<RefinedTreeFeature> EXAMPLE_TREE = FEATURES.register("refined_tree",
			() -> new RefinedTreeFeature(NoFeatureConfig.CODEC));

}

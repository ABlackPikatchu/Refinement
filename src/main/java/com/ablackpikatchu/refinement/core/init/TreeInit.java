package com.ablackpikatchu.refinement.core.init;

import java.util.Random;

import com.ablackpikatchu.refinement.core.world.TreeSpawner;

import net.minecraft.world.gen.feature.Feature;

@SuppressWarnings("rawtypes")
public class TreeInit {

	public static final TreeSpawner REFINED = new TreeSpawner() {

		@Override
		protected Feature getFeature(Random rand) {
			return FeatureInit.EXAMPLE_TREE.get();
		}
	};

}

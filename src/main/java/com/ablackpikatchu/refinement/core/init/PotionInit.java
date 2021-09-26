package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.effects.GhostlyShape;
import com.ablackpikatchu.refinement.common.effects.NegateFall;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {

	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS,
			Refinement.MOD_ID);

	public static final RegistryObject<NegateFall> NEGATE_FALL = EFFECTS.register("negate_fall", NegateFall::new);
	public static final RegistryObject<GhostlyShape> GHOSTLY_SHAPE = EFFECTS.register("ghostly_shape",
			GhostlyShape::new);

}

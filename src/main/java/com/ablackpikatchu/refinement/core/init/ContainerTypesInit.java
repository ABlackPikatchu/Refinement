package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.container.GrinderContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypesInit {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, Refinement.MOD_ID);
	
	public static final RegistryObject<ContainerType<GrinderContainer>> GRINDER_CONTAINER_TYPE = CONTAINER_TYPES
			.register("grinder", () -> IForgeContainerType.create(GrinderContainer::new));
}

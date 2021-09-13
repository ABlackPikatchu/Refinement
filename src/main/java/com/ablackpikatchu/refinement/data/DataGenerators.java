package com.ablackpikatchu.refinement.data;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.data.client.ItemModelProvider;
import com.ablackpikatchu.refinement.data.common.BlockTagsProvider;
import com.ablackpikatchu.refinement.data.common.ItemTagsProvider;
import com.ablackpikatchu.refinement.data.common.RecipeProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD)
public class DataGenerators {
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		
		gen.addProvider(new ItemModelProvider(gen, existingFileHelper));
		
		BlockTagsProvider blockTags = new BlockTagsProvider(gen, existingFileHelper);
		gen.addProvider(new ItemTagsProvider(gen, blockTags, existingFileHelper));
		gen.addProvider(new RecipeProvider(gen));
	}

}

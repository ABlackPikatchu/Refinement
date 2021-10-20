package com.ablackpikatchu.refinement.data.client;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.IItemProvider;

import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {

	public ItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Refinement.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		for (String blockItem : BLOCK_ITEMS) {
			withExistingParent(blockItem, modLoc("block/" + blockItem));
		}
		
		builder(ItemInit.LUMIUM_INGOT.get(), "ingots/lumium");
		builder(ItemInit.ENDERIUM_INGOT.get(), "ingots/enderium");
		builder(ItemInit.SILVER_INGOT.get(), "ingots/silver");
		builder(ItemInit.SIGNALUM_INGOT.get(), "ingots/signalum");
		builder(ItemInit.COPPER_INGOT.get(), "ingots/copper");
		
		builder(ItemInit.COPPER_DUST.get(), "dusts/copper_dust");
		
		for (String item : ITEMS) {
			builder(itemGenerated, item);
		}
	}
	
	ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
	
	private ItemModelBuilder builder(IItemProvider item, String name) {
        return getBuilder(item.asItem().getRegistryName().toString()).parent(itemGenerated).texture("layer0", "items/" + name);
    }
	
    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "items/" + name);
    }
    
    public static String[] BLOCK_ITEMS = {
    		"refined_log", "refined_stripped_log", "refined_leaves", "refined_sapling",
    		"copper_block"
    };
    
    public static String[] ITEMS = {
    		
    };

}

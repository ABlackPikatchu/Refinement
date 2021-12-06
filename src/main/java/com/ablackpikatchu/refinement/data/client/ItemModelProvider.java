package com.ablackpikatchu.refinement.data.client;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.block.Block;
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
		
		for (Block block : BLOCK_ITEMS_2) {
			withExistingParent(block.asItem().getRegistryName().getPath(), modLoc("block/" + block.getRegistryName().getPath()));
		}
		
		builder(ItemInit.Ingots.LUMIUM_INGOT, "ingots/lumium");
		builder(ItemInit.Ingots.ENDERIUM_INGOT, "ingots/enderium");
		builder(ItemInit.Ingots.SILVER_INGOT, "ingots/silver");
		builder(ItemInit.Ingots.SIGNALUM_INGOT, "ingots/signalum");
		builder(ItemInit.Ingots.COPPER_INGOT, "ingots/copper");
		
		builder(ItemInit.Ingots.COPPER_DUST, "dusts/copper_dust");
		
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
    
    public static final String[] BLOCK_ITEMS = {
    		"refined_log", "refined_stripped_log", "refined_leaves", "refined_sapling",
    		"copper_block", "enderium_block"
    };
    
    public static final Block[] BLOCK_ITEMS_2 = {
    		BlockInit.SIGNALUM_BLOCK.get()
    };
     
    public static final String[] ITEMS = {
    		
    };

}

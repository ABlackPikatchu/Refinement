package com.ablackpikatchu.refinement.data.client;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.data.DataGenerator;
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
		
		ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
		
		for (String item : ITEMS) {
			builder(itemGenerated, item);
		}
	}
	
    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "items/" + name);
    }
    
    public static String[] BLOCK_ITEMS = {
    		"grinder"
    };
    
    public static String[] ITEMS = {
    		
    };

}

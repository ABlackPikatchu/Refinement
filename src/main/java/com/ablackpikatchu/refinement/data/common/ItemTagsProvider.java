package com.ablackpikatchu.refinement.data.common;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.BlockItemInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import static com.ablackpikatchu.refinement.core.init.ItemInit.*;
import com.ablackpikatchu.refinement.core.init.TagInit;
import com.ablackpikatchu.refinement.core.util.lists.ItemLists;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ItemTagsProvider extends net.minecraft.data.ItemTagsProvider {

	public ItemTagsProvider(DataGenerator p_i232552_1_, BlockTagsProvider p_i232552_2_,
			ExistingFileHelper existingFileHelper) {
		super(p_i232552_1_, p_i232552_2_, Refinement.MOD_ID, existingFileHelper);
	}
	
	
	@Override
	protected void addTags() {
		//Ingot
		tag(Tags.Items.INGOTS_IRON).add(net.minecraft.item.Items.IRON_INGOT);
		tag(Tags.Items.INGOTS_GOLD).add(net.minecraft.item.Items.GOLD_INGOT);
		tag(Tags.Items.INGOTS_NETHERITE).add(net.minecraft.item.Items.NETHERITE_INGOT);
		tag(TagInit.Items.REFINED_IRON_INGOT).add(ItemInit.REFINED_IRON_INGOT.get());
		tag(TagInit.Items.INGOTS).add(ItemInit.REFINED_IRON_INGOT.get());
		tag(TagInit.Items.REFINED_GOLD_INGOT).add(ItemInit.REFINED_GOLD_INGOT.get());
		tag(TagInit.Items.INGOTS).add(ItemInit.REFINED_GOLD_INGOT.get());
		tag(TagInit.Items.REFINED_NETHERITE_INGOT).add(ItemInit.REFINED_NETHERITE_INGOT.get());
		tag(TagInit.Items.INGOTS).add(ItemInit.REFINED_NETHERITE_INGOT.get());
		tag(TagInit.Items.REFINED_CARBON_INGOT).add(ItemInit.REFINED_CARBON_INGOT.get());
		tag(TagInit.Items.INGOTS).add(ItemInit.REFINED_CARBON_INGOT.get());
		
		tag(TagInit.Items.LUMIUM_INGOT).add(ItemInit.Ingots.LUMIUM_INGOT);
		tag(TagInit.Items.SIGNALUM_INGOT).add(ItemInit.Ingots.SIGNALUM_INGOT);
		tag(TagInit.Items.ENDERIUM_INGOT).add(ItemInit.Ingots.ENDERIUM_INGOT);
		tag(TagInit.Items.TIN_INGOT).add(ItemInit.Ingots.TIN_INGOT);
		tag(TagInit.Items.SILVER_INGOT).add(ItemInit.Ingots.SILVER_INGOT);
		tag(TagInit.Items.LEAD_INGOT).add(ItemInit.Ingots.LEAD_INGOT);
		tag(TagInit.Items.COPPER_INGOT).add(ItemInit.Ingots.COPPER_INGOT);
		
		//Dust
		tag(TagInit.Items.DIAMOND_DUST).add(ItemInit.DIAMOND_DUST.get());
		tag(TagInit.Items.CHARCOAL_DUST).add(ItemInit.CHARCOAL_DUST.get());
		tag(TagInit.Items.COAL_DUST).add(ItemInit.COAL_DUST.get());
		tag(TagInit.Items.IRON_DUST).add(ItemInit.IRON_DUST.get());
		tag(TagInit.Items.GOLD_DUST).add(ItemInit.GOLD_DUST.get());
		tag(TagInit.Items.NETHERITE_DUST).add(ItemInit.NETHERITE_DUST.get());
		tag(TagInit.Items.REFINED_IRON_DUST).add(ItemInit.REFINED_IRON_DUST.get());
		tag(TagInit.Items.DUSTS).add(ItemInit.REFINED_IRON_DUST.get());
		tag(TagInit.Items.REFINED_GOLD_DUST).add(ItemInit.REFINED_GOLD_DUST.get());
		tag(TagInit.Items.DUSTS).add(ItemInit.REFINED_GOLD_DUST.get());
		tag(TagInit.Items.REFINED_DIAMOND_DUST).add(ItemInit.REFINED_DIAMOND_DUST.get());
		tag(TagInit.Items.DUSTS).add(ItemInit.REFINED_DIAMOND_DUST.get());
		tag(TagInit.Items.REFINED_NETHERITE_DUST).add(ItemInit.REFINED_NETHERITE_DUST.get());
		tag(TagInit.Items.DUSTS).add(ItemInit.REFINED_NETHERITE_DUST.get());
		tag(TagInit.Items.REFINED_CARBON_DUST).add(ItemInit.REFINED_CARBON_DUST.get());
		tag(TagInit.Items.DUSTS).add(ItemInit.REFINED_CARBON_DUST.get());
		tag(TagInit.Items.REFINING_DUST).add(ItemInit.REFINING_DUST.get());
		tag(TagInit.Items.DUSTS).add(ItemInit.REFINING_DUST.get());
		
		tag(TagInit.Items.TIN_DUST).add(ItemInit.Ingots.TIN_DUST);
		tag(TagInit.Items.SILVER_DUST).add(ItemInit.Ingots.SILVER_DUST);
		tag(TagInit.Items.LEAD_DUST).add(ItemInit.Ingots.LEAD_DUST);
		tag(TagInit.Items.COPPER_DUST).add(ItemInit.Ingots.COPPER_DUST);
		
		//Gems
		tag(Tags.Items.GEMS_DIAMOND).add(net.minecraft.item.Items.DIAMOND);
		tag(TagInit.Items.REFINED_DIAMOND).add(ItemInit.REFINED_DIAMOND.get());
		tag(TagInit.Items.GEMS).add(ItemInit.REFINED_DIAMOND.get());
		tag(TagInit.Items.PURE_CRYSTAL).add(ItemInit.PURE_CRYSTAL.get());
		tag(TagInit.Items.GEMS).add(ItemInit.PURE_CRYSTAL.get());
		
		//Grit Papers
		tag(TagInit.Items.GRIT_PAPERS).add(ItemInit.IRON_GRIT_PAPER.get());
		tag(TagInit.Items.IRON_GRIT_PAPER).add(ItemInit.IRON_GRIT_PAPER.get());
		tag(TagInit.Items.GRIT_PAPERS).add(ItemInit.DIAMOND_GRIT_PAPER.get());
		tag(TagInit.Items.DIAMOND_GRIT_PAPER).add(ItemInit.DIAMOND_GRIT_PAPER.get());
		tag(TagInit.Items.GRIT_PAPERS).add(ItemInit.NETHERITE_GRIT_PAPER.get());
		tag(TagInit.Items.NETHERITE_GRIT_PAPER).add(ItemInit.NETHERITE_GRIT_PAPER.get());
		
		//Cogwheels
		tag(TagInit.Items.COGWHEELS).add(ItemInit.WOODEN_COGWHEEL.get());
		tag(TagInit.Items.WOODEN_COGWHEEL).add(ItemInit.WOODEN_COGWHEEL.get());
		tag(TagInit.Items.COGWHEELS).add(ItemInit.REFINED_IRON_COGWHEEL.get());
		tag(TagInit.Items.REFINED_IRON_COGWHEEL).add(ItemInit.REFINED_IRON_COGWHEEL.get());
		tag(TagInit.Items.COGWHEELS).add(ItemInit.REFINED_GOLD_COGWHEEL.get());
		tag(TagInit.Items.REFINED_GOLD_COGWHEEL).add(ItemInit.REFINED_GOLD_COGWHEEL.get());
		tag(TagInit.Items.COGWHEELS).add(ItemInit.REFINED_DIAMOND_COGWHEEL.get());
		tag(TagInit.Items.REFINED_DIAMOND_COGWHEEL).add(ItemInit.REFINED_DIAMOND_COGWHEEL.get());
		
		//Grits
		tag(TagInit.Items.GRITS).add(ItemInit.GRIT.get());
		tag(TagInit.Items.GRIT).add(ItemInit.GRIT.get());
		tag(TagInit.Items.GRITS).add(ItemInit.IRON_INFUSED_GRIT.get());
		tag(TagInit.Items.IRON_GRIT).add(ItemInit.IRON_INFUSED_GRIT.get());
		tag(TagInit.Items.GRITS).add(ItemInit.DIAMOND_INFUSED_GRIT.get());
		tag(TagInit.Items.DIAMOND_GRIT).add(ItemInit.DIAMOND_INFUSED_GRIT.get());
		tag(TagInit.Items.GRITS).add(ItemInit.NETHERITE_INFUSED_GRIT.get());
		tag(TagInit.Items.NETHERITE_GRIT).add(ItemInit.NETHERITE_INFUSED_GRIT.get());
		
		//Molds
		tag(TagInit.Items.INGOT_MOLD).add(ItemInit.INGOT_MOLD.get());
		tag(TagInit.Items.MOLDS).add(ItemInit.INGOT_MOLD.get());
		tag(TagInit.Items.GEM_MOLD).add(ItemInit.GEM_MOLD.get());
		tag(TagInit.Items.MOLDS).add(ItemInit.GEM_MOLD.get());
		tag(TagInit.Items.COGWHEEL_MOLD).add(ItemInit.COGWHEEL_MOLD.get());
		tag(TagInit.Items.MOLDS).add(ItemInit.COGWHEEL_MOLD.get());
		
		//Unfired Cogwheels
		tag(TagInit.Items.UNFIRED_COGWHEELS).add(ItemInit.UNFIRED_REFINED_IRON_COGWHEEL.get());
		tag(TagInit.Items.UNFIRED_IRON_COGWHEEL).add(ItemInit.UNFIRED_REFINED_IRON_COGWHEEL.get());
		tag(TagInit.Items.UNFIRED_COGWHEELS).add(ItemInit.UNFIRED_REFINED_GOLD_COGWHEEL.get());
		tag(TagInit.Items.UNFIRED_GOLD_COGWHEEL).add(ItemInit.UNFIRED_REFINED_GOLD_COGWHEEL.get());
		tag(TagInit.Items.UNFIRED_COGWHEELS).add(ItemInit.UNFIRED_REFINED_DIAMOND_COGWHEEL.get());
		tag(TagInit.Items.UNFIRED_DIAMOND_COGWHEEL).add(ItemInit.UNFIRED_REFINED_DIAMOND_COGWHEEL.get());
		
		//Unfired Gems
    	tag(TagInit.Items.UNFIRED_GEMS).add(ItemInit.REFINED_DIAMOND.get());
    	tag(TagInit.Items.UNFIRED_DIAMOND).add(ItemInit.REFINED_DIAMOND.get());
    	
    	//Unfired Ingots
    	tag(TagInit.Items.UNFIRED_INGOTS).add(ItemInit.UNFIRED_REFINED_IRON_INGOT.get());
    	tag(TagInit.Items.UNFIRED_IRON_INGOT).add(ItemInit.UNFIRED_REFINED_IRON_INGOT.get());
    	tag(TagInit.Items.UNFIRED_INGOTS).add(ItemInit.UNFIRED_REFINED_GOLD_INGOT.get());
    	tag(TagInit.Items.UNFIRED_GOLD_INGOT).add(ItemInit.UNFIRED_REFINED_GOLD_INGOT.get());
    	tag(TagInit.Items.UNFIRED_INGOTS).add(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT.get());
    	tag(TagInit.Items.UNFIRED_NETHERITE_INGOT).add(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT.get());
    	tag(TagInit.Items.UNFIRED_INGOTS).add(ItemInit.UNFIRED_REFINED_CARBON_INGOT.get());
    	tag(TagInit.Items.UNFIRED_NETHERITE_INGOT).add(ItemInit.UNFIRED_REFINED_NETHERITE_INGOT.get());
    	
    	//Unfired Molds
    	tag(TagInit.Items.UNFIRED_MOLDS).add(ItemInit.UNFIRED_INGOT_MOLD.get());
    	tag(TagInit.Items.UNFIRED_INGOT_MOLD).add(ItemInit.UNFIRED_INGOT_MOLD.get());
    	tag(TagInit.Items.UNFIRED_MOLDS).add(ItemInit.UNFIRED_GEM_MOLD.get());
    	tag(TagInit.Items.UNFIRED_GEM_MOLD).add(ItemInit.UNFIRED_GEM_MOLD.get());
    	tag(TagInit.Items.UNFIRED_MOLDS).add(ItemInit.UNFIRED_COGWHEEL_MOLD.get());
    	tag(TagInit.Items.UNFIRED_COGWHEEL_MOLD).add(ItemInit.UNFIRED_COGWHEEL_MOLD.get());
    	
    	//Ores
    	tag(TagInit.Items.ORES).add(ItemInit.PURE_CRYSTAL_ORE.get());
    	tag(TagInit.Items.PURE_CRYSTAL_ORE).add(ItemInit.PURE_CRYSTAL_ORE.get());
    	tag(TagInit.Items.ORES).add(BlockItemInit.BLANK_ORE_ITEM);
    	tag(TagInit.Items.BLANK_ORE).add(BlockItemInit.BLANK_ORE_ITEM);
    	
    	tag(TagInit.Items.TIN_ORE).add(BlockInit.TIN_ORE.get().asItem());
    	tag(TagInit.Items.SILVER_ORE).add(BlockInit.SILVER_ORE.get().asItem());
    	tag(TagInit.Items.COPPER_ORE).add(BlockInit.COPPER_ORE.get().asItem());
    	tag(TagInit.Items.LEAD_ORE).add(BlockInit.LEAD_ORE.get().asItem());
    	
    	//Storage Blocks
    	tag(TagInit.Items.STORAGE_BLOCKS).add(ItemInit.REFINED_IRON_BLOCK.get());
    	tag(TagInit.Items.REFINED_IRON_STORAGE).add(ItemInit.REFINED_IRON_BLOCK.get());
    	tag(TagInit.Items.STORAGE_BLOCKS).add(ItemInit.REFINED_GOLD_BLOCK.get());
    	tag(TagInit.Items.GOLD_STORAGE).add(ItemInit.REFINED_GOLD_BLOCK.get());
    	tag(TagInit.Items.STORAGE_BLOCKS).add(ItemInit.REFINED_DIAMOND_BLOCK.get());
    	tag(TagInit.Items.DIAMOND_STORAGE).add(ItemInit.REFINED_DIAMOND_BLOCK.get());
    	tag(TagInit.Items.STORAGE_BLOCKS).add(ItemInit.REFINED_NETHERITE_BLOCK.get());
    	tag(TagInit.Items.NETHERITE_STORAGE).add(ItemInit.REFINED_NETHERITE_BLOCK.get());
    	tag(TagInit.Items.STORAGE_BLOCKS).add(ItemInit.REFINED_CARBON_BLOCK.get());
    	tag(TagInit.Items.CARBON_STORAGE).add(ItemInit.REFINED_CARBON_BLOCK.get());
    	
    	//MC Dyes
    	for (Item dye : ItemLists.DYES) {
			Item terracota = ForgeRegistries.ITEMS.getValue(new ResourceLocation(dye.getRegistryName().getNamespace(),
					dye.getRegistryName().getPath().replace("dye", "terracotta")));
			tag(TagInit.Items.TERRACOTTAS).add(terracota);
			
			Item concretePowder = ForgeRegistries.ITEMS.getValue(new ResourceLocation(dye.getRegistryName().getNamespace(),
					dye.getRegistryName().getPath().replace("dye", "concrete_powder")));
			tag(TagInit.Items.CONCRETE_POWDERS).add(concretePowder);
			
			Item glass = ForgeRegistries.ITEMS.getValue(new ResourceLocation(dye.getRegistryName().getNamespace(),
					dye.getRegistryName().getPath().replace("dye", "stained_glass")));
			tag(TagInit.Items.MC_GLASS).add(glass);
			
			Item glassPane = ForgeRegistries.ITEMS.getValue(new ResourceLocation(dye.getRegistryName().getNamespace(),
					dye.getRegistryName().getPath().replace("dye", "stained_glass_pane")));
			tag(TagInit.Items.MC_GLASS_PANE).add(glassPane);
		};
		tag(TagInit.Items.TERRACOTTAS).add(Items.TERRACOTTA);
		tag(TagInit.Items.MC_GLASS).add(Items.GLASS);
		tag(TagInit.Items.MC_GLASS_PANE).add(Items.GLASS_PANE);
    	
		tag(TagInit.Items.COPPER_STORAGE).add(BlockInit.COPPER_BLOCK.get().asItem());
		tag(TagInit.Items.TIN_STORAGE).add(BlockInit.TIN_BLOCK.get().asItem());
		tag(TagInit.Items.SILVER_STORAGE).add(BlockInit.SILVER_BLOCK.get().asItem());
		tag(TagInit.Items.LEAD_STORAGE).add(BlockInit.LEAD_BLOCK.get().asItem());
		
		tag(TagInit.Items.LUMIUM_STORAGE).add(BlockInit.LUMIUM_BLOCK.get().asItem());
		tag(TagInit.Items.SIGNALUM_STORAGE).add(BlockInit.SIGNALUM_BLOCK.get().asItem());
		tag(TagInit.Items.ENDERIUM_STORAGE).add(BlockInit.ENDERIUM_BLOCK.get().asItem());
		
		tag(com.ablackpikatchu.refinement.core.init.TagInit.Items.MAGNET_BLACKLISTED).add(Items.DRAGON_EGG);
		tag(TagInit.Items.VACUUMULATOR_BLACKLISTED).addTag(TagInit.Items.MAGNET_BLACKLISTED);
	}

}

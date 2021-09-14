package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class TagInit {

	public static final class Items {
		
		public static final ITag.INamedTag<Item> GRIT_PAPERS = mod("grit_papers");
		
		
		//Ingot
		public static final ITag.INamedTag<Item> IRON_INGOT = forge("ingots/iron");
		public static final ITag.INamedTag<Item> GOLD_INGOT = forge("ingots/gold");
		public static final ITag.INamedTag<Item> NETHERITE_INGOT = forge("ingots/netherite");
		
		//Dusts
		public static final ITag.INamedTag<Item> IRON_DUST = forge("dusts/iron");
		public static final ITag.INamedTag<Item> GOLD_DUST = forge("dusts/gold");
		public static final ITag.INamedTag<Item> DIAMOND_DUST = forge("dusts/diamond");
		public static final ITag.INamedTag<Item> NETHERITE_DUST = forge("dusts/netherite");
		public static final ITag.INamedTag<Item> COAL_DUST = forge("dusts/coal");
		public static final ITag.INamedTag<Item> CHARCOAL_DUST = forge("dusts/charcoal");
		
		//Gems
		public static final ITag.INamedTag<Item> DIAMOND = forge("gems/diamond");
		
		//Forge
		public static final ITag.INamedTag<Item> COBBLESTONE = forge("cobblestone");

		private static ITag.INamedTag<Item> forge(String path) {
			return ItemTags.bind(new ResourceLocation("forge", path).toString());
		}


		private static ITag.INamedTag<Item> mod(String path) {
			return ItemTags.bind(new ResourceLocation(Refinement.MOD_ID, path).toString());
		}
	}

}

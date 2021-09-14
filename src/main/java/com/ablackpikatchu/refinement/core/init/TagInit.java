package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class TagInit {

	public static final class Items {
		
		public static final INamedTag<Item> GRIT_PAPERS = mod("grit_papers");
		
		
		//Ingot
		public static final INamedTag<Item> IRON_INGOT = forge("ingots/iron");
		public static final INamedTag<Item> GOLD_INGOT = forge("ingots/gold");
		public static final INamedTag<Item> NETHERITE_INGOT = forge("ingots/netherite");
		
		//Dusts
		public static final INamedTag<Item> IRON_DUST = forge("dusts/iron");
		public static final INamedTag<Item> GOLD_DUST = forge("dusts/gold");
		public static final INamedTag<Item> DIAMOND_DUST = forge("dusts/diamond");
		public static final INamedTag<Item> NETHERITE_DUST = forge("dusts/netherite");
		public static final INamedTag<Item> COAL_DUST = forge("dusts/coal");
		public static final INamedTag<Item> CHARCOAL_DUST = forge("dusts/charcoal");
		
		//Gems
		public static final INamedTag<Item> DIAMOND = forge("gems/diamond");

		private static INamedTag<Item> forge(String path) {
			return ItemTags.bind(new ResourceLocation("forge", path).toString());
		}


		private static INamedTag<Item> mod(String path) {
			return ItemTags.bind(new ResourceLocation(Refinement.MOD_ID, path).toString());
		}
	}

}

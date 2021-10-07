package com.ablackpikatchu.refinement.core.config.entry;

import com.google.gson.annotations.Expose;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class OreConversionEntry {

	@Expose
	public String input;
	@Expose
	public String output;

	public OreConversionEntry(String input, String output) {
		this.input = input;
		this.output = output;
	}

	public Ingredient getInput() {
		if (this.input.startsWith("#")) {
			ITag.INamedTag<Item> tag = ItemTags.bind(this.input.substring(1));
			return Ingredient.of(tag);
		} else
			return Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.input)));
	}

	public Item getOutput() {
		return ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.output));
	}

}

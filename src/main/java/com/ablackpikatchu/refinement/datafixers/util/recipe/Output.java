package com.ablackpikatchu.refinement.datafixers.util.recipe;

import net.minecraft.item.Item;

public class Output {

	private final Item item;
	private final int count;
	
	public Output(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public int getCount() {
		return this.count;
	}
	
}

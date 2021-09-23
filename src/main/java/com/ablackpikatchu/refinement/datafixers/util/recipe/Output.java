package com.ablackpikatchu.refinement.datafixers.util.recipe;

import net.minecraft.item.Item;

/**
 * Class for defining outputs of recipes
 * @author matyrobbrt
 *
 */
public class Output {

	private final Item item;
	private final int count;
	
	/**
	 * Creates a new recipe output
	 * @param item the item output
	 * @param count the count of the output
	 */
	public Output(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	
	/**
	 * Gets the output item
	 * @return the output item
	 */
	public Item getItem() {
		return this.item;
	}
	
	/**
	 * Gets the output count
	 * @return the output count
	 */
	public int getCount() {
		return this.count;
	}
	
}

package com.ablackpikatchu.refinement.datafixers.util.recipe;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

/**
 * Class for defining tag inputs of recipe
 * @author matyrobbrt
 *
 */
public class TagInput {
	
	private final ITag<Item> tag;
	private final int count;

	/**
	 * Creates a new recipe tag input
	 * @param tag the tag of the input
	 * @param count the count of the input
	 */
	public TagInput(ITag<Item> tag, int count) {
		this.tag = tag;
		this.count = count;
	}

	/**
	 * Gets the tag of the input
	 * @return the tag of the input
	 */
	public ITag<Item> getTag() {
		return this.tag;
	}

	/**
	 * Gets the count of the input
	 * @return the count of the input
	 */
	public int getCount() {
		return this.count;
	}
}

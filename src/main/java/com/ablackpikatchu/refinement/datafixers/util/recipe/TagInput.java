package com.ablackpikatchu.refinement.datafixers.util.recipe;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

public class TagInput {
	private final ITag<Item> tag;
	private final int count;

	public TagInput(ITag<Item> tag, int count) {
		this.tag = tag;
		this.count = count;
	}

	public ITag<Item> getTag() {
		return this.tag;
	}

	public int getCount() {
		return this.count;
	}
}

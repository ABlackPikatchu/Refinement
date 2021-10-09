package com.ablackpikatchu.refinement.data.patchouli.builder.type;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.data.patchouli.builder.page.IPatchouliPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class PatchouliEntry {

	public String category;
	public String fileName;
	public String displayName;
	public Item icon;

	public String turnin;
	
	public ArrayList<IPatchouliPage> pages = new ArrayList<>();

	public PatchouliEntry(String category, String displayName, IItemProvider icon) {
		this.category = category;
		this.fileName = displayName.toLowerCase().replace(' ', '_');
		this.displayName = displayName;
		this.icon = icon.asItem();
	}
	
	public PatchouliEntry(PatchouliCategory category, String displayName, IItemProvider icon) {
		this.category = category.fileName;
		this.fileName = displayName.toLowerCase().replace(' ', '_');
		this.displayName = displayName;
		this.icon = icon.asItem();
	}

	public PatchouliEntry addPage(IPatchouliPage page) {
		this.pages.add(page);
		return this;
	}
	
	public PatchouliEntry setTurnin(String turnin) {
		this.turnin = turnin;
		return this;
	}

	public JsonObject serialize() {
		JsonObject object = new JsonObject();

		object.addProperty("name", this.displayName);
		object.addProperty("category", new ResourceLocation(Refinement.MOD_ID, this.category).toString());

		if (this.icon != null)
			object.addProperty("icon", this.icon.getRegistryName().toString());

		JsonArray pagesElement = new JsonArray();
		this.pages.forEach(page -> pagesElement.add(page.serialize()));
		object.add("pages", pagesElement);
		
		addProperty(object, "turnin", this.turnin);
		
		return object;
	}
	
	private void addProperty(JsonObject object, String property, String value) {
		if (value != null)
			object.addProperty(property, value);
	}

}

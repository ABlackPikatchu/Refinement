package com.ablackpikatchu.refinement.data.patchouli.builder;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.util.IItemProvider;

public class PatchouliCatgory {

	public String name;
	public String fileName;
	public String description;
	public IItemProvider icon;

	public PatchouliCatgory(String name, String fileName, String description, @Nullable IItemProvider icon) {
		this.name = name;
		this.fileName = fileName;
		this.description = description;
		this.icon = icon;
	}

	public JsonElement serialize() {
		JsonObject object = new JsonObject();

		object.addProperty("name", this.name);
		object.addProperty("description", this.description);
		if (this.icon != null)
			object.addProperty("icon", this.icon.asItem().getRegistryName().toString());

		return object;
	}

}

package com.ablackpikatchu.refinement.data.patchouli.builder.type;

import javax.annotation.Nonnull;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class PatchouliCategory {

	public String name;
	public String fileName;
	public String description;
	public IItemProvider icon;
	
	public ResourceLocation textureIcon;

	public PatchouliCategory(String name, String fileName, String description, @Nonnull IItemProvider icon) {
		this.name = name;
		this.fileName = fileName;
		this.description = description;
		this.icon = icon;
	}
	
	public PatchouliCategory setIcon(ResourceLocation textureIcon) {
		this.icon = null;
		this.textureIcon = textureIcon;
		return this;
	}

	public JsonElement serialize() {
		JsonObject object = new JsonObject();

		object.addProperty("name", this.name);
		object.addProperty("description", this.description);
		
		if (this.icon != null)
			object.addProperty("icon", this.icon.asItem().getRegistryName().toString());
		
		if (this.textureIcon != null)
			object.addProperty("icon", this.textureIcon.toString());

		return object;
	}

}

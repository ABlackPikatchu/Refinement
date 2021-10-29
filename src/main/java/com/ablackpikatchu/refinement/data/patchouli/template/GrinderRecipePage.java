package com.ablackpikatchu.refinement.data.patchouli.template;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.api.datagen.patchouli.page.IPatchouliPage;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.util.ResourceLocation;

public class GrinderRecipePage implements IPatchouliPage {
	
	public ResourceLocation recipe;
	public String text;
	public String header;
	
	public GrinderRecipePage(ResourceLocation recipe) {
		this.recipe = recipe;
	}
	
	public GrinderRecipePage setText(String text) {
		this.text = text;
		return this;
	}
	
	public GrinderRecipePage setHeader(String header) {
		this.header = header;
		return this;
	}

	@Override
	public String getType() {
		return Refinement.MOD_ID + ":grinder_recipe";
	}

	@Override
	public JsonElement serialize() {
		JsonObject object = new JsonObject();
		addType(object);
		addProperty(object, "recipe", this.recipe.toString());
		addProperty(object, "text", this.text);
		addProperty(object, "header", this.header);
		return object;
	}

}

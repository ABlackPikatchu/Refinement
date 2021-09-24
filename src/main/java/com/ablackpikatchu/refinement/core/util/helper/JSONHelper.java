package com.ablackpikatchu.refinement.core.util.helper;

import com.ablackpikatchu.refinement.datafixers.util.json.JSONInput;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JSONUtils;

public class JSONHelper {

	public static JSONInput getInputWithCount(JsonObject json, String name) {
		final JsonElement element = JSONUtils.isArrayNode(json, name) ? JSONUtils.getAsJsonArray(json, name)
				: JSONUtils.getAsJsonObject(json, name);

		final Ingredient input = Ingredient.fromJson(element);

		JsonObject object = JSONUtils.getAsJsonObject(json, name);
		int actualCount = 1;
		if (object.has("count")) {
			int count = object.get("count").getAsInt();
			if (count > 1) {
				actualCount = count;
				for (ItemStack stack : input.getItems()) {
					stack.setCount(count);
				}
			}
		}

		return new JSONInput(input, actualCount);

	}

}

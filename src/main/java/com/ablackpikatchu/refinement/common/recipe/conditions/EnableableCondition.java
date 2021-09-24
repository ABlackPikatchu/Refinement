package com.ablackpikatchu.refinement.common.recipe.conditions;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.item.IEnableable;
import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class EnableableCondition implements ICondition {

	public static final ResourceLocation ID = new ResourceLocation(Refinement.MOD_ID, "item_enabled");
	private final ResourceLocation item;

	public EnableableCondition(ResourceLocation item) {
		this.item = item;
	}

	@Override
	public ResourceLocation getID() {
		return ID;
	}

	@Override
	public boolean test() {
		Item item = ForgeRegistries.ITEMS.getValue(this.item);
		if (item == Items.AIR)
			return false;
		if (item instanceof IEnableable) {
			return ((IEnableable) item).isEnabled();
		} else {
			return true;
		}
	}

	public static class Serializer implements IConditionSerializer<EnableableCondition> {

		public static final Serializer INSTANCE = new Serializer();

		@Override
		public void write(JsonObject json, EnableableCondition value) {
			json.addProperty("item", value.item.toString());
		}

		@Override
		public EnableableCondition read(JsonObject json) {
			return new EnableableCondition(new ResourceLocation(JSONUtils.getAsString(json, "item")));
		}

		@Override
		public ResourceLocation getID() {
			return EnableableCondition.ID;
		}
	}
}

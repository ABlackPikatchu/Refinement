package com.ablackpikatchu.refinement.common.recipe.conditions;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.google.gson.JsonObject;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CropsEnabledCondition implements ICondition {

	public static final ResourceLocation ID = new ResourceLocation(Refinement.MOD_ID, "crops_enabled");
	private final boolean value;
	
	public CropsEnabledCondition(boolean value) {
		this.value = value;
	}
	
	@Override
	public ResourceLocation getID() {
		return ID;
	}

	@Override
	public boolean test() {
		return CommonConfig.CROPS_ENABLED.get() == value;
	}
	
	 public static class Serializer implements IConditionSerializer<CropsEnabledCondition> {
		 
	        public static final Serializer INSTANCE = new Serializer();

	        @Override
	        public CropsEnabledCondition read(JsonObject json) {
	            return new CropsEnabledCondition(json.get("value").getAsBoolean());
	        }

	        @Override
	        public ResourceLocation getID() {
	            return CropsEnabledCondition.ID;
	        }

			@Override
			public void write(JsonObject json, CropsEnabledCondition cond) {
				json.addProperty("value", cond.value);
			}
	    }

}

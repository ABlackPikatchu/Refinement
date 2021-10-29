package com.ablackpikatchu.refinement.core.config.json;

import java.util.LinkedList;
import java.util.List;

import com.ablackpikatchu.refinement.api.config.json.JsonConfig;
import com.ablackpikatchu.refinement.core.config.entry.OreConversionEntry;
import com.google.gson.annotations.Expose;

import net.minecraft.item.Items;

public class OreConversionConfig extends JsonConfig {

	@Expose
	private List<OreConversionEntry> CONVERSIONS;

	@Override
	public String getName() {
		return "ore_unify_config";
	}

	@Override
	protected void reset() {
		this.CONVERSIONS = new LinkedList<>();

		this.CONVERSIONS.add(new OreConversionEntry("#forge:ores/gold", Items.GOLD_ORE.getRegistryName().toString()));
		this.CONVERSIONS.add(new OreConversionEntry(Items.BUBBLE_CORAL.getRegistryName().toString(),
				Items.BRAIN_CORAL.getRegistryName().toString()));
	}

	public List<OreConversionEntry> getConversionRecipes() {
		return this.CONVERSIONS;
	}

}

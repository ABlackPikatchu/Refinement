package com.ablackpikatchu.refinement.core.config.json;

import com.ablackpikatchu.refinement.api.config.json.JsonConfig;
import com.ablackpikatchu.refinement.core.config.entry.ToolSettingsEntry;
import com.google.gson.annotations.Expose;

public class ToolsConfig extends JsonConfig {
	
	@Expose
	private ToolSettingsEntry REFINED_IRON;
	@Expose
	private ToolSettingsEntry REFINED_GOLD;
	@Expose
	private ToolSettingsEntry REFINED_DIAMOND;
	@Expose
	private ToolSettingsEntry REFINED_NETHERITE;

	@Override
	public String getName() {
		return "tools_config";
	}

	@Override
	protected void reset() {
		this.REFINED_IRON = new ToolSettingsEntry(2, 250, 6.0f, 2.0f, 14);
		this.REFINED_GOLD = new ToolSettingsEntry(0, 48, 12.0f, 0.0f, 22);
		this.REFINED_DIAMOND = new ToolSettingsEntry(3, 1832, 8.0f, 3.0f, 10);
		this.REFINED_NETHERITE = new ToolSettingsEntry(4, 2532, 9.0f, 4.0f, 15);
	}

	public ToolSettingsEntry getRefinedIronValues() {
		return this.REFINED_IRON;
	}

	public ToolSettingsEntry getRefinedGoldValues() {
		return this.REFINED_GOLD;
	}

	public ToolSettingsEntry getRefinedDiamondValues() {
		return this.REFINED_DIAMOND;
	}

	public ToolSettingsEntry getRefinedNetheriteValues() {
		return this.REFINED_NETHERITE;
	}
	
}

package com.ablackpikatchu.refinement.core.config.json;

import com.ablackpikatchu.refinement.core.config.entry.ArmourSettingsEntry;
import com.google.gson.annotations.Expose;

public class ArmourConfig extends JsonConfig {

	@Expose
	private ArmourSettingsEntry REFINED_IRON;
	@Expose
	private ArmourSettingsEntry REFINED_GOLD;
	@Expose
	private ArmourSettingsEntry REFINED_DIAMOND;
	@Expose
	private ArmourSettingsEntry REFINED_NETHERITE;

	@Expose
	private int[] baseDurability;

	@Override
	public String getName() {
		return "armour_config";
	}

	@Override
	protected void reset() {
		this.REFINED_IRON = new ArmourSettingsEntry(16, new int[] {
				3, 6, 7, 3
		}, 9, 1.0f, 0.1f);

		this.REFINED_GOLD = new ArmourSettingsEntry(8, new int[] {
				1, 3, 5, 2
		}, 26, 1.0F, 0.1f);

		this.REFINED_DIAMOND = new ArmourSettingsEntry(34, new int[] {
				4, 7, 9, 4
		}, 11, 3.0F, 0.1f);

		this.REFINED_NETHERITE = new ArmourSettingsEntry(38, new int[] {
				4, 7, 9, 4
		}, 16, 4.0F, 0.2f);

		this.baseDurability = new int[] {
				128, 144, 160, 112
		};
	}

	public ArmourSettingsEntry getRefinedIronValues() {
		return this.REFINED_IRON;
	}

	public ArmourSettingsEntry getRefinedGoldValues() {
		return this.REFINED_GOLD;
	}

	public ArmourSettingsEntry getRefinedDiamondValues() {
		return this.REFINED_DIAMOND;
	}

	public ArmourSettingsEntry getRefinedNetheriteValues() {
		return this.REFINED_NETHERITE;
	}
	
	public int[] getBaseDurability() {
		return this.baseDurability;
	}

}

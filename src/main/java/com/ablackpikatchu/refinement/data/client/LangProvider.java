package com.ablackpikatchu.refinement.data.client;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.PotionInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangProvider extends LanguageProvider {
	
	public LangProvider(DataGenerator gen) {
		super(gen, Refinement.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		
		//Blocks
		add(BlockInit.BLANK_ORE.get(), "Blank Ore");
		add(BlockInit.GRINDER.get(), "Grinder");
		add(BlockInit.MACHINE_FRAME.get(), "Machine Frame");
		add(BlockInit.MIXER.get(), "Mixer");
		add(BlockInit.MOLD_PRESS.get(), "Mold Press");
		add(BlockInit.PURE_CRYSTAL_ORE.get(), "Pure Crystal Ore");
		add(BlockInit.REFINED_CARBON_BLOCK.get(), "Refined Carbon Block");
		add(BlockInit.REFINED_DIAMOND_BLOCK.get(), "Refined Diamond Block");
		add(BlockInit.REFINED_GOLD_BLOCK.get(), "Refined Gold Block");
		add(BlockInit.REFINED_IRON_BLOCK.get(), "Refined Iron Block");
		add(BlockInit.REFINED_NETHERITE_BLOCK.get(), "Refined Netherite Block");
		add(BlockInit.VACCUMULATOR.get(), "Vacuumulator");
		
		//Effects
		add(PotionInit.NEGATE_FALL.get(), "Negate Fall Damage");
		
		//Death Messages
		add("death.attack.minersStewDamage", "%1$s choked on precious metals");
		add("death.attack.minersStewDamage.player", "%1$s's lack of air defeated them while fighting %2$s");
		
		//Tooltips
		addTooltips("hold_shift", "Hold \u00A7eSHIFT\u00A7r for more information!");
	}
	
	private void addTooltips(String key, String value) {
		this.add("tooltip." + Refinement.MOD_ID + "." + key, value);
	}

}

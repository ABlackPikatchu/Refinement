package com.ablackpikatchu.refinement.client;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class RefinementLang {
	
	public static ITextComponent getComponent(String key) {
		return new TranslationTextComponent("component." + Refinement.MOD_ID + "." + key);
	}

}

package com.ablackpikatchu.refinement.core.util;

import java.util.List;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ToolTipUtils {

	public ToolTipUtils() {
		throw new IllegalAccessError("Util class!");
	}

	/**
	 * Renders shift tooltips for an item
	 * @param tooltip
	 * @param tooltipNames the lang entry/entries for the tooltip text
	 */
	public static void renderShiftTooltip(List<ITextComponent> tooltip, String[] tooltipNames) {

		if (Screen.hasShiftDown()) {
			for (String tooltipName : tooltipNames) {
				TranslationTextComponent tooltipText = new TranslationTextComponent(
						"tooltip." + Refinement.MOD_ID + "." + tooltipName);
				tooltip.add(tooltipText);
			}
		} else {

			tooltip.add(new TranslationTextComponent(
					"tooltip." + Refinement.MOD_ID + "." + "hold_shift"));
		}

	}

}

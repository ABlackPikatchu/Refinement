package com.ablackpikatchu.refinement.core.util.text;

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
	 * 
	 * @param tooltip
	 * @param tooltipNames the lang entry/entries for the tooltip text
	 * @param colour       the colour of the text (if 0, the text will not be coloured)
	 */
	public static void renderShiftTooltips(List<ITextComponent> tooltip, String[] tooltipNames, int colour) {

		if (Screen.hasShiftDown()) {
			for (String tooltipName : tooltipNames) {
				TranslationTextComponent tooltipText = new TranslationTextComponent(
						"tooltip." + Refinement.MOD_ID + "." + tooltipName);
				if (colour != 0)
					tooltipText = TextFormattingUtils.colouredTranslationTextComponent(tooltipText, colour);
				tooltip.add(tooltipText);
			}
		} else {

			tooltip.add(new TranslationTextComponent("tooltip." + Refinement.MOD_ID + "." + "hold_shift"));
		}

	}

	/**
	 * Renders normal tooltips for an item
	 * 
	 * @param tooltip
	 * @param tooltipNames the lang entry/entries for the tooltip text
	 * @param colour       the colour of the text (if 0, the text will not be coloured)
	 */
	public static void renderTooltips(List<ITextComponent> tooltip, String[] tooltipNames, int colour) {

		for (String tooltipName : tooltipNames) {
			TranslationTextComponent tooltipText = new TranslationTextComponent(
					"tooltip." + Refinement.MOD_ID + "." + tooltipName);
			if (colour != 0)
				tooltipText = TextFormattingUtils.colouredTranslationTextComponent(tooltipText, colour);
			tooltip.add(tooltipText);
		}

	}

}

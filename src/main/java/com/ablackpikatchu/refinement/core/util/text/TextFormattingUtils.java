package com.ablackpikatchu.refinement.core.util.text;

import net.minecraft.util.text.Color;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;

public class TextFormattingUtils {

	public TextFormattingUtils() {
		throw new IllegalAccessError("Util class!");
	}

	public static String capitalizeWord(String str) {
		String words[] = str.split("\\s");
		String capitalizeWord = "";
		for (String w : words) {
			String first = w.substring(0, 1);
			String afterfirst = w.substring(1);
			capitalizeWord += first.toUpperCase() + afterfirst + " ";
		}
		return capitalizeWord.trim();
	}

	// String Text
	public static StringTextComponent colouredStringTextComponent(StringTextComponent text, int colour) {
		return (StringTextComponent) text.setStyle(Style.EMPTY.withColor(Color.fromRgb(colour)));
	}

	public static StringTextComponent boldStringTextComponent(StringTextComponent text, boolean apply) {
		return (StringTextComponent) text.setStyle(Style.EMPTY.withBold(apply));
	}

	public static StringTextComponent italicStringTextComponent(StringTextComponent text, boolean apply) {
		return (StringTextComponent) text.setStyle(Style.EMPTY.withItalic(apply));
	}

	public static StringTextComponent underlineStringTextComponent(StringTextComponent text, boolean apply) {
		return (StringTextComponent) text.setStyle(Style.EMPTY.setUnderlined(apply));
	}

	public static StringTextComponent strikethroughStringTextComponent(StringTextComponent text, boolean apply) {
		return (StringTextComponent) text.setStyle(Style.EMPTY.setStrikethrough(apply));
	}

	// Translation Text
	public static TranslationTextComponent colouredTranslationTextComponent(TranslationTextComponent text, int colour) {
		return (TranslationTextComponent) text.setStyle(Style.EMPTY.withColor(Color.fromRgb(colour)));
	}

	public static TranslationTextComponent boldTranslationTextComponent(TranslationTextComponent text, boolean apply) {
		return (TranslationTextComponent) text.setStyle(Style.EMPTY.withBold(apply));
	}

	public static TranslationTextComponent italicTranslationTextComponent(TranslationTextComponent text,
			boolean apply) {
		return (TranslationTextComponent) text.setStyle(Style.EMPTY.withItalic(apply));
	}

	public static TranslationTextComponent underlineTranslationTextComponent(TranslationTextComponent text,
			boolean apply) {
		return (TranslationTextComponent) text.setStyle(Style.EMPTY.setUnderlined(apply));
	}

	public static TranslationTextComponent strikethroughTranslationTextComponent(TranslationTextComponent text,
			boolean apply) {
		return (TranslationTextComponent) text.setStyle(Style.EMPTY.setStrikethrough(apply));
	}

}

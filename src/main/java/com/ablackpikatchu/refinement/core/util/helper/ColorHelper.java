package com.ablackpikatchu.refinement.core.util.helper;

public class ColorHelper {
	
	/**
	 * Converts a {@link java.awt.Color} to a HEX value
	 * @param awt the colour to convert
	 * @return
	 */
	public static String toHex(java.awt.Color awt) {
		String buf = Integer.toHexString(awt.getRGB());
		return "#"+buf.substring(buf.length()-6);
	}

}

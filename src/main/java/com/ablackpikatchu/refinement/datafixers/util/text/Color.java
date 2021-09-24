package com.ablackpikatchu.refinement.datafixers.util.text;

import java.util.Map;

import com.google.common.collect.ImmutableMap.Builder;

/**
 * Colour handling class
 * 
 * @author matyrobbrt
 *
 */
public class Color {

	private final int colourInt;

	public static final Map<String, Integer> COLOURS = (new Builder<String, Integer>()).put("red", 0xff0000)
			.put("dark_blue", 0x2d59dc).build();

	/**
	 * Creates a new colour based on its hex
	 * 
	 * @param colourInt the hex value of the colour
	 */
	public Color(int colourInt) {
		this.colourInt = colourInt;
	}

	/**
	 * Creates a new colour with a name from the {@link #COLOURS} map. If that name
	 * doesn't exist in the map, the colour will be white (#ffffff)
	 * 
	 * @param name
	 */
	public Color(String name) {
		if (COLOURS.containsKey(name.toLowerCase()))
			this.colourInt = COLOURS.get(name.toLowerCase());
		else
			this.colourInt = 0xffffff;
	}

	/**
	 * Returns the hex int value of a colour
	 * 
	 * @return
	 */
	public int getColourInt() {
		return this.colourInt;
	}

}

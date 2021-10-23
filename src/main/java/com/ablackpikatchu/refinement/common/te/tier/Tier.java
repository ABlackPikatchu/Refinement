package com.ablackpikatchu.refinement.common.te.tier;

import java.awt.Color;

public enum Tier {
	
	ALPHA(Color.GRAY), BETA(Color.CYAN);
	
	private Color color;
	
	private Tier(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
}

package com.ablackpikatchu.refinement.common.te.tier;

import java.awt.Color;

public class Tier {
	
	public static final Tier ALPHA = new Tier(Color.GRAY); 
	public static final Tier BETA = new Tier(Color.CYAN);
	public static final Tier GAMMA = new Tier(Color.RED);
	public static final Tier EPSILON = new Tier(Color.BLUE);
	public static final Tier OMEGA = new Tier(new Color(0xAA00AA));
	
	private Color color;
	
	private Tier(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public Tier nextTier() {
		if (this == ALPHA)
			return BETA;
		if (this == BETA)
			return GAMMA;
		if (this == GAMMA)
			return EPSILON;
		if (this == EPSILON)
			return OMEGA;
		return null;
	}
	
	public Tier previousTier() {
		if (this == BETA)
			return ALPHA;
		if (this == GAMMA)
			return BETA;
		if (this == EPSILON)
			return GAMMA;
		if (this == OMEGA)
			return EPSILON;
		return null;
	}
	
}

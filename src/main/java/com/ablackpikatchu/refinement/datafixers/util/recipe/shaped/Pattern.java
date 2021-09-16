package com.ablackpikatchu.refinement.datafixers.util.recipe.shaped;

import net.minecraft.data.ShapedRecipeBuilder;

public class Pattern {

	private final String top;
	private final String middle;
	private final String bottom;
	
	public Pattern(String top, String middle, String bottom) {
		this.top = top;
		this.middle = middle;
		this.bottom = bottom;
	}
	
	public String getTop() {
		return this.top;
	}
	
	public String getMiddle() {
		return this.middle;
	}
	
	public String getBottom() {
		return this.bottom;
	}
	
	public void getShapedRecipePattern(ShapedRecipeBuilder recipe) {
		recipe.pattern(this.top).pattern(this.middle).pattern(this.bottom);
	}
}

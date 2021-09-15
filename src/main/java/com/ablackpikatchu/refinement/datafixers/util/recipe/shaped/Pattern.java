package com.ablackpikatchu.refinement.datafixers.util.recipe.shaped;

public class Pattern {

	private final String first;
	private final String second;
	private final String third;
	
	public Pattern(String first, String second, String third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public String getFirst() {
		return this.first;
	}
	
	public String getSecond() {
		return this.second;
	}
	
	public String getThird() {
		return this.third;
	}
}

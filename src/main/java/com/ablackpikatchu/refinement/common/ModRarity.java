package com.ablackpikatchu.refinement.common;

import static net.minecraft.util.text.TextFormatting.*;

import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;

import static net.minecraft.item.Rarity.*;

public class ModRarity {

	public static final Rarity DARK_RED_RARITY = create("DARK_RED", DARK_RED);
	public static final Rarity DARK_PURPLE_RARITY = create("DARK_PURPLE", DARK_PURPLE);
	public static final Rarity GOLD_RARITY = create("GOLD_PURPLE", GOLD);
	public static final Rarity GREEN_RARITY = create("GREEN", TextFormatting.GREEN);
	
	public static final Rarity ALPHA_RARITY = create("ALPHA", GRAY);
	public static final Rarity BETA_RARITY = create("BETA", TextFormatting.AQUA);
	public static final Rarity GAMMA_RARITY = create("GAMMA", RED);
	public static final Rarity EPSILON_RARITY = create("EPSILON", BLUE);
	public static final Rarity OMEGA_RARITY = create("OMEGA", TextFormatting.DARK_PURPLE);
	
}

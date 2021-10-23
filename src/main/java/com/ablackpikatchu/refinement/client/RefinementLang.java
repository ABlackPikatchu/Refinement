package com.ablackpikatchu.refinement.client;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.block.Block;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraftforge.fml.RegistryObject;

public class RefinementLang {

	private RefinementLang() {
	}

	public static final ITextComponent COMPATIBLE_MACHINES_COMPONENT = getComponent("compatible_machines");

	public static ITextComponent getComponent(String key) {
		return new TranslationTextComponent("component." + Refinement.MOD_ID + "." + key);
	}

	public static String getBlockName(Block block) {
		return new TranslationTextComponent(block.asItem().getDefaultInstance().getDescriptionId()).getString();
	}
	
	public static String getBlockName(RegistryObject<Block> block) {
		return new TranslationTextComponent(block.get().asItem().getDefaultInstance().getDescriptionId()).getString();
	}

}

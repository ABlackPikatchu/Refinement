package com.ablackpikatchu.refinement.datafixers.util.keybinds;

import net.minecraft.client.settings.KeyBinding;

public class ModKeyBinding {

	private KeyBinding key;
	private final String name;
	private final int value;
	
	public ModKeyBinding(KeyBinding key, String name, int value) {
		this.key = key;
		this.name = name;
		this.value = value;
	}
	
	public KeyBinding getKey() {
		return this.key;
	}
	
	public void setKey(KeyBinding newKey) {
		this.key = newKey;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getValue() {
		return this.value;
	}
	
}

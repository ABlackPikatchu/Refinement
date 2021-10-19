package com.ablackpikatchu.refinement.common.te.security;

import net.minecraft.nbt.CompoundNBT;

public enum SecurityType {

	PUBLIC("public"), PRIVATE("private");
	
	private String name;
	
	private SecurityType(String name) {
		this.name = name;
	}

	public static final String NBT_NAME = "SecurityType";

	public static void toNBT(SecurityType security, CompoundNBT nbt) {
		switch (security) {
		case PUBLIC:
			nbt.putString(NBT_NAME, "public");
		case PRIVATE:
			nbt.putString(NBT_NAME, "private");
		}
	}

	public static SecurityType fromNBT(CompoundNBT nbt) {
		String type = nbt.getString(NBT_NAME);
		switch (type.toLowerCase()) {
		case "public":
			return PUBLIC;
		case "private":
			return PRIVATE;
		}
		return SecurityType.PUBLIC;
	}

	public boolean isPrivate() {
		return this == PRIVATE;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static SecurityType byName(String name) {
		for (SecurityType type : values()) {
			if (type.getName() == name)
				return type;
		}
		return null;
	}

	public SecurityType next() {
		int index = this.ordinal() + 1;
		if (index >= values().length)
			index = 0;
		return values()[index];
	}

}

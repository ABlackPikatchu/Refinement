package com.ablackpikatchu.refinement.core.util.helper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import net.minecraftforge.registries.ForgeRegistries;

public class NBTHelper {
	
	public static BlockPos getBlockPos(ItemStack stack, String key) {
		CompoundNBT nbt = getTagCompound(stack).getCompound(key);
		boolean hasData = nbt.getDouble("x") != 0 && nbt.getDouble("y") != 0 && nbt.getDouble("z") != 0;
		return hasData ? new BlockPos(nbt.getDouble("x"), nbt.getDouble("y"), nbt.getDouble("z")) : null;
	}
	
	public static void setBlockPos(ItemStack stack, String key, BlockPos value) {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putDouble("x", value.getX());
		nbt.putDouble("y", value.getY());
		nbt.putDouble("z", value.getZ());
		getTagCompound(stack).put(key, nbt);
	}
	
	public static boolean getBoolean(ItemStack stack, String key) {
		return stack.hasTag() && getTagCompound(stack).getBoolean(key);
	}

	public static void flipBoolean(ItemStack stack, String key) {
		setBoolean(stack, key, !getBoolean(stack, key));
	}

	public static void setBoolean(ItemStack stack, String key, boolean value) {
		getTagCompound(stack).putBoolean(key, value);
	}

	public static void validateCompound(ItemStack stack) {
		if (!stack.hasTag()) {
			CompoundNBT tag = new CompoundNBT();
			stack.setTag(tag);
		}
	}

	public static void setString(ItemStack stack, String key, String value) {
		getTagCompound(stack).putString(key, value);
	}

	public static String getString(ItemStack stack, String key) {
		return stack.hasTag() ? getTagCompound(stack).getString(key) : "";
	}

	public static CompoundNBT getTagCompound(ItemStack stack) {
		validateCompound(stack);
		return stack.getTag();
	}

	public static void setInt(ItemStack stack, String key, int value) {
		getTagCompound(stack).putInt(key, value);
	}

	public static int getInt(ItemStack stack, String key) {
		return stack.hasTag() ? getTagCompound(stack).getInt(key) : 0;
	}

	public static double getDouble(ItemStack stack, String key) {
		return stack.hasTag() ? getTagCompound(stack).getDouble(key) : 0.0;
	}

	public static void setDouble(ItemStack stack, String key, double value) {
		getTagCompound(stack).putDouble(key, value);
	}

	public static double getFloat(ItemStack stack, String key) {
		return stack.hasTag() ? getTagCompound(stack).getFloat(key) : 0.0F;
	}

	public static void setFloat(ItemStack stack, String key, float value) {
		getTagCompound(stack).putFloat(key, value);
	}

	public static Item getItem(ItemStack stack, String key) {
		return ForgeRegistries.ITEMS.getValue(new ResourceLocation(getString(stack, key)));
	}

	public static void setItem(ItemStack stack, String key, IItemProvider item) {
		setString(stack, key, item.asItem().getRegistryName().toString());
	}
}

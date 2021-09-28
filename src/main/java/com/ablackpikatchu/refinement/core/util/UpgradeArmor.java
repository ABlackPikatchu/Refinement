package com.ablackpikatchu.refinement.core.util;

import java.util.List;
import java.util.Random;

import com.ablackpikatchu.refinement.common.item.ModUpgradableArmor;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.item.ItemStack;

import net.minecraftforge.event.AnvilUpdateEvent;

public class UpgradeArmor {

	static Random rand = new Random();

	public static void upgradeEffect(ItemStack stack, AnvilUpdateEvent event) {
		if (stack.getItem() instanceof ModUpgradableArmor) {
			if (!NBTHelper.getBoolean(stack, ModUpgradableArmor.effectRolled)) {
				List<String> configEffects = CommonConfig.ARMOUR_UPGRADING_EFFECTS.get();
				ItemStack output = stack.copy();
				NBTHelper.setBoolean(output, ModUpgradableArmor.effectRolled, true);
				NBTHelper.setString(output, ModUpgradableArmor.storedEffect,
						configEffects.get(rand.nextInt(configEffects.size())));
				NBTHelper.setBoolean(output, ModUpgradableArmor.inAnvil, true);
				event.setOutput(output);
				event.setMaterialCost(1);
				event.setCost(1);
			}
		}
	}

	public static void upgradeAbility(ItemStack stack, AnvilUpdateEvent event) {
		if (stack.getItem() instanceof ModUpgradableArmor) {
			if (!NBTHelper.getBoolean(stack, ModUpgradableArmor.abilityRolled)) {
				ItemStack output = stack.copy();
				NBTHelper.setBoolean(output, ModUpgradableArmor.abilityRolled, true);
				NBTHelper.setString(output, ModUpgradableArmor.storedAbility, ModUpgradableArmor.flightAbility);
				NBTHelper.setBoolean(output, ModUpgradableArmor.inAnvil, true);
				event.setOutput(output);
				event.setMaterialCost(1);
				event.setCost(1);
			}
		}
	}

}

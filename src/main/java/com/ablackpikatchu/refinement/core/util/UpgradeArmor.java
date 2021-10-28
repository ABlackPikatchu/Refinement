package com.ablackpikatchu.refinement.core.util;

import java.util.List;
import java.util.Random;

import com.ablackpikatchu.refinement.common.item.RefinementUpgradableArmor;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.item.ItemStack;

import net.minecraftforge.event.AnvilUpdateEvent;

public class UpgradeArmor {

	static Random rand = new Random();

	public static void upgradeEffect(ItemStack stack, AnvilUpdateEvent event) {
		if (stack.getItem() instanceof RefinementUpgradableArmor) {
			if (!NBTHelper.getBoolean(stack, RefinementUpgradableArmor.effectRolled)) {
				List<String> configEffects = CommonConfig.ARMOUR_UPGRADING_EFFECTS.get();
				ItemStack output = stack.copy();
				NBTHelper.setBoolean(output, RefinementUpgradableArmor.effectRolled, true);
				NBTHelper.setString(output, RefinementUpgradableArmor.storedEffect,
						configEffects.get(rand.nextInt(configEffects.size())));
				NBTHelper.setBoolean(output, RefinementUpgradableArmor.inAnvil, true);
				event.setOutput(output);
				event.setMaterialCost(1);
				event.setCost(1);
			}
		}
	}

	public static void upgradeAbility(ItemStack stack, AnvilUpdateEvent event) {
		if (stack.getItem() instanceof RefinementUpgradableArmor) {
			if (!NBTHelper.getBoolean(stack, RefinementUpgradableArmor.abilityRolled)) {
				ItemStack output = stack.copy();
				NBTHelper.setBoolean(output, RefinementUpgradableArmor.abilityRolled, true);
				NBTHelper.setString(output, RefinementUpgradableArmor.storedAbility, RefinementUpgradableArmor.flightAbility);
				NBTHelper.setBoolean(output, RefinementUpgradableArmor.inAnvil, true);
				event.setOutput(output);
				event.setMaterialCost(1);
				event.setCost(1);
			}
		}
	}

}

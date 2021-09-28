package com.ablackpikatchu.refinement.common.events;

import java.util.Random;

import com.ablackpikatchu.refinement.common.item.ArmorUpgrader;
import com.ablackpikatchu.refinement.common.item.ModUpgradableArmor;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.util.UpgradeArmor;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnvilEvents {

	static Random rand = new Random();

	@SubscribeEvent
	public static void armourUpgradingStarted(AnvilUpdateEvent event) {
		ItemStack right = event.getRight();
		ItemStack left = event.getLeft();

		if (right.getItem() == ItemInit.ARMOR_UPGRADER.get()) {
			if (NBTHelper.getString(right, ArmorUpgrader.type) == ArmorUpgrader.potionType)
				UpgradeArmor.upgradeEffect(left, event);
			else if (NBTHelper.getBoolean(right, ArmorUpgrader.rolled) && NBTHelper.getString(right, ArmorUpgrader.type) != ArmorUpgrader.potionType)
				UpgradeArmor.upgradeAbility(left, event);
		}
	}

	@SubscribeEvent
	public static void armourUpgradingFinished(AnvilRepairEvent event) {
		if (event.getItemResult().getItem() instanceof ModUpgradableArmor) {
			NBTHelper.setBoolean(event.getItemResult(), ModUpgradableArmor.inAnvil, false);
		}
	}

	@SubscribeEvent
	public static void armourUpgraderCrafting(AnvilUpdateEvent event) {
		ItemStack left = event.getLeft();
		ItemStack right = event.getRight();

		if (left.getItem() == ItemInit.ARMOR_UPGRADER.get() && !NBTHelper.getBoolean(left, ArmorUpgrader.rolled)) {
			if (right.getItem() == Items.POTION) {
				event.setMaterialCost(1);
				event.setCost(22);
				ItemStack output = left.copy();
				NBTHelper.setBoolean(output, ArmorUpgrader.rolled, true);
				NBTHelper.setString(output, ArmorUpgrader.type, ArmorUpgrader.potionType);
				event.setOutput(output);
			} else if (right.getItem() == Items.DRAGON_EGG) {
				event.setMaterialCost(1);
				event.setCost(22);
				ItemStack output = left.copy();
				NBTHelper.setBoolean(output, ArmorUpgrader.rolled, true);
				NBTHelper.setString(output, ArmorUpgrader.type, ArmorUpgrader.abilityType);
				event.setOutput(output);
			}
		}
	}

}

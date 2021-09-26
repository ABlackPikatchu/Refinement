package com.ablackpikatchu.refinement.common.events;

import java.util.Random;

import com.ablackpikatchu.refinement.common.item.ModUpgradableArmor;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.util.UpgradeArmor;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.item.ItemStack;

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
			UpgradeArmor.upgradeEffect(left, event);
		}
	}

	@SubscribeEvent
	public static void armourUpgradingFinished(AnvilRepairEvent event) {
		if (event.getItemResult().getItem() instanceof ModUpgradableArmor) {
			NBTHelper.setBoolean(event.getItemResult(), ModUpgradableArmor.inAnvil, false);
		}
	}

}

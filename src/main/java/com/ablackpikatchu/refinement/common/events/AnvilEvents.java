package com.ablackpikatchu.refinement.common.events;

import java.util.Random;

import com.ablackpikatchu.refinement.common.item.ArmorUpgrader;
import com.ablackpikatchu.refinement.common.item.ModUpgradableArmor;
import com.ablackpikatchu.refinement.common.recipe.AnvilRecipe;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.util.UpgradeArmor;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;

import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnvilEvents {

	static Random rand = new Random();

	@SubscribeEvent(priority = EventPriority.HIGHEST)
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

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void armourUpgradingFinished(AnvilRepairEvent event) {
		if (event.getItemResult().getItem() instanceof ModUpgradableArmor) {
			NBTHelper.setBoolean(event.getItemResult(), ModUpgradableArmor.inAnvil, false);
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
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
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void handleAnvilRecipes(AnvilUpdateEvent event) {
		for (final IRecipe<?> recipe : event.getPlayer().level.getRecipeManager().getAllRecipesFor(RecipeInit.ANVIL_RECIPE)) {
			final AnvilRecipe anvilRecipe = (AnvilRecipe) recipe;
			final ItemStack left = event.getLeft();
			final ItemStack right = event.getRight();
			if (anvilRecipe.isValid(left, right)) {
				event.setCost(anvilRecipe.getXPRequired());
				event.setMaterialCost(anvilRecipe.getRightCount());
				ItemStack output = recipe.getResultItem().copy();
				event.setOutput(output);
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void finishAnvilRecipes(AnvilRepairEvent event) {
		for (final IRecipe<?> recipe : event.getPlayer().level.getRecipeManager().getAllRecipesFor(RecipeInit.ANVIL_RECIPE)) {
			final AnvilRecipe anvilRecipe = (AnvilRecipe) recipe;
			final ItemStack left = event.getItemInput();
			final ItemStack right = event.getIngredientInput();
			final PlayerEntity player = event.getPlayer();
			if (anvilRecipe.isValid(left, right)) {
				final ItemStack returnLeft = left.copy();
				returnLeft.shrink(anvilRecipe.getLeftCount());
				if (!player.addItem(returnLeft)) player.drop(returnLeft, false);
			}
		}
	}

}

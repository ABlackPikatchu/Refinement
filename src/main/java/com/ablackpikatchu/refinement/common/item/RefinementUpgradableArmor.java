package com.ablackpikatchu.refinement.common.item;

import java.util.List;

import com.ablackpikatchu.refinement.core.init.PotionInit;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.ablackpikatchu.refinement.core.util.helper.PlayerHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import net.minecraftforge.registries.ForgeRegistries;

public class RefinementUpgradableArmor extends ArmorItem {

	public static String effectRolled = "EffectRolled";
	public static String storedEffect = "effect";

	public static String abilityRolled = "AbilityRolled";
	public static String storedAbility = "ability";
	public static String flightAbility = "flight";

	public static String inAnvil = "inAnvil";

	public RefinementUpgradableArmor(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
		super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
	}

	private Effect getEffect(ItemStack stack) {
		String effectString = NBTHelper.getString(stack, storedEffect);
		if (effectString == "")
			return null;
		return ForgeRegistries.POTIONS.getValue(new ResourceLocation(effectString));
	}

	private String getAbility(ItemStack stack) {
		return NBTHelper.getString(stack, storedAbility);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (getEffect(stack) != null)
			PlayerHelper.applyTickEffect(player, getEffect(stack));
		if (getAbility(stack) != "") {
			PlayerHelper.applyTickEffect(player, PotionInit.FLIGHT.get());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltip, ITooltipFlag p_77624_4_) {

		if (!NBTHelper.getBoolean(stack, inAnvil)) {
			String effect = NBTHelper.getString(stack, storedEffect);
			if (effect != "")
				tooltip.add(new StringTextComponent("Adds effect \u00A76" + effect));

			String ability = getAbility(stack);
			if (ability != "")
				tooltip.add(new StringTextComponent("Gives ability \u00A74" + ability));
		}
	}

}

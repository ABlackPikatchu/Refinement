package com.ablackpikatchu.refinement.common.item;

import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT_ARMOUR;

import java.util.List;

import com.ablackpikatchu.refinement.common.material.ModArmorMaterial;
import com.ablackpikatchu.refinement.core.init.PotionInit;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import net.minecraftforge.registries.ForgeRegistries;

public class ModUpgradableArmor extends ArmorItem {

	public static String effectRolled = "EffectRolled";
	public static String storedEffect = "effect";

	public static String abilityRolled = "AbilityRolled";
	public static String storedAbility = "ability";
	public static String flightAbilityType = "flight";

	public static String inAnvil = "inAnvil";

	public ModUpgradableArmor() {
		super(ModArmorMaterial.REFINEDNETHERITEARMOR, EquipmentSlotType.CHEST,
				new Item.Properties().tab(REFINEMENT_ARMOUR));
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
			player.addEffect(new EffectInstance(getEffect(stack), 100));
		if (getAbility(stack) != "") {
			if (getAbility(stack) == flightAbilityType)
				player.addEffect(new EffectInstance(PotionInit.FLIGHT.get(), 100));
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

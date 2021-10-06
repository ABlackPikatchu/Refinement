package com.ablackpikatchu.refinement.common.item;

import java.util.List;

import com.ablackpikatchu.refinement.core.config.ModJsonConfigs;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup;
import com.ablackpikatchu.refinement.core.util.MathUtils;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.ablackpikatchu.refinement.datafixers.util.Triple;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import net.minecraftforge.registries.ForgeRegistries;

public class ResourceStatueItem extends BlockItem {

	public static final String RESOURCE_PRODUCED_NBT_STRING = "resource";
	public static final String MAX_PRODUCED_NBT_STRING = "maxProduce";
	public static final String PRODUCED_NBT_STRING = "produced";

	public ResourceStatueItem() {
		super(BlockInit.RESOURCE_STATUE_BLOCK.get(),
				new Item.Properties().tab(RefinementItemGroup.REFINEMENT).stacksTo(1).rarity(Rarity.EPIC));
	}

	public static Item getResourceProduced(ItemStack stack) {
		return ForgeRegistries.ITEMS
				.getValue(new ResourceLocation(NBTHelper.getString(stack, RESOURCE_PRODUCED_NBT_STRING)));
	}

	public static int getMaxProduce(ItemStack stack) {
		return NBTHelper.getInt(stack, MAX_PRODUCED_NBT_STRING);
	}

	public static int getProduced(ItemStack stack) {
		return NBTHelper.getInt(stack, PRODUCED_NBT_STRING);
	}

	public static void setResourceProduced(ItemStack stack, String resource) {
		NBTHelper.setString(stack, RESOURCE_PRODUCED_NBT_STRING, resource);
	}

	public static void setMaxProduce(ItemStack stack, int maxProduce) {
		NBTHelper.setInt(stack, MAX_PRODUCED_NBT_STRING, maxProduce);
	}

	public static void setProduced(ItemStack stack, int produced) {
		NBTHelper.setInt(stack, PRODUCED_NBT_STRING, produced);
	}

	@Override
	public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		if (getResourceProduced(pStack) == Items.AIR) {
			Triple<Item, Integer, Integer> randomLoot = ModJsonConfigs.RESOURCE_STATUE.getRandomLoot();
			setResourceProduced(pStack, randomLoot.getA().getRegistryName().toString());
			setMaxProduce(pStack, MathUtils.getRandomNumber(randomLoot.getB(), randomLoot.getC()));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, World pLevel, List<ITextComponent> tooltip, ITooltipFlag pFlag) {
		if (getResourceProduced(stack) != Items.AIR && NBTHelper.getString(stack, RESOURCE_PRODUCED_NBT_STRING) != "") {
			String producedResourceName = getResourceProduced(stack).getDefaultInstance().getDisplayName().getString();
			
			producedResourceName = producedResourceName.replace('[', ' ');
			producedResourceName = producedResourceName.replace(']', ' ');
			
			tooltip.add(new StringTextComponent(
					"Produced Item:\u00A76" + producedResourceName));
			tooltip.add(new StringTextComponent("Maximum Items Produced: \u00A75" + getMaxProduce(stack)));
			tooltip.add(new StringTextComponent(
					"Remaining Items To Produce: \u00A7c" + (getMaxProduce(stack) - getProduced(stack))));
		} else {
			tooltip.add(new StringTextComponent("Hold in inventory in order to roll!"));
		}
	}

}

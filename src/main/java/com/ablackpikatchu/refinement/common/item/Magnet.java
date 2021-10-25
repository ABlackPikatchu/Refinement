package com.ablackpikatchu.refinement.common.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementToolsWeaponsGroup;
import com.ablackpikatchu.refinement.core.util.IEnableable;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class Magnet extends Item implements IEnableable {

	public Magnet() {
		super(new Item.Properties().defaultDurability(2048).tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS));
	}

	@Override
	public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
		ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);

		if (!isEnabled())
			return ActionResult.fail(stack);

		NBTHelper.flipBoolean(stack, "Enabled");
		p_77659_2_.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F,
				NBTHelper.getBoolean(stack, "Enabled") ? 0.5F : 1.0F);

		return super.use(p_77659_1_, p_77659_2_, p_77659_3_);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
		if (!isEnabled())
			return;

		if (entity instanceof PlayerEntity && NBTHelper.getBoolean(stack, "Enabled")) {

			PlayerEntity player = (PlayerEntity) entity;
			double range = CommonConfig.MAGNET_RANGE.get();
			ArrayList<ItemEntity> rangeItems = (ArrayList<ItemEntity>) world.getEntitiesOfClass(ItemEntity.class,
					entity.getBoundingBox().inflate(range));
			for (ItemEntity item : rangeItems) {

				if (!item.isAlive())
					continue;

				if (item.getThrower() != null && item.getThrower().equals(entity.getUUID()) && item.hasPickUpDelay())
					continue;

				if (!world.isClientSide()) {
					item.setNoPickUpDelay();
					int damage = item.getItem().getCount();
					Boolean shouldDamage = true;
					if (item.distanceTo(entity) < 1.5F)
						shouldDamage = false;
					item.setPos(entity.getX(), entity.getY(), entity.getZ());
					if (shouldDamage)
						if (!player.isCreative())
							stack.hurt(damage, new Random(0), (ServerPlayerEntity) player);
				}
			}

			List<ExperienceOrbEntity> xp = world.getEntitiesOfClass(ExperienceOrbEntity.class,
					entity.getBoundingBox().inflate(range));
			for (ExperienceOrbEntity orb : xp) {
				if (!world.isClientSide()) {
					orb.setPos(entity.getX(), entity.getY(), entity.getZ());
				}
			}
		}

	}

	@Override
	public boolean isFoil(ItemStack p_77636_1_) {
		return NBTHelper.getBoolean(p_77636_1_, "Enabled");
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

		if (enchantment.isAllowedOnBooks()) {
			return false;
		}

		if (enchantment.isCompatibleWith(Enchantments.MENDING)) {
			return false;
		}

		return false;
	}

	@Override
	public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> items) {
		if (isEnabled()) super.fillItemCategory(tab, items);
	}

	@Override
	public boolean isEnabled() {
		return CommonConfig.MAGNET_ENABLED.get();
	}

}

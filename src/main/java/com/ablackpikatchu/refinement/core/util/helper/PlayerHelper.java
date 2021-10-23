package com.ablackpikatchu.refinement.core.util.helper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class PlayerHelper {

	public static boolean hasItem(PlayerEntity player, Item item) {
		for (int i = 0; i <= player.inventory.getContainerSize(); ++i) {
			if (player.inventory.getItem(i).getItem() == item)
				return true;
		}
		return false;
	}

	public static ItemStack getFirstStackMatchingItem(PlayerEntity player, Item item) {
		if (hasItem(player, item)) {
			for (int i = 0; i <= player.inventory.getContainerSize(); ++i) {
				if (player.inventory.getItem(i).getItem() == item)
					return player.inventory.getItem(i);
			}
		} else
			return ItemStack.EMPTY;
		return ItemStack.EMPTY;
	}

	public static boolean canFitItem(PlayerEntity player, ItemStack item) {

		for (int i = 0; i <= player.inventory.getContainerSize(); i++) {
			if (player.inventory.canPlaceItem(i, item))
				return true;
		}

		return false;
	}

	public static void applyTickEffect(PlayerEntity player, Effect effect) {
		if (!player.hasEffect(effect)) {
			player.addEffect(new EffectInstance(effect, 320));
		} else {
			if (player.getEffect(effect).getDuration() <= 200)
				player.addEffect(new EffectInstance(effect, 320));
		}
	}
	
	public static void updatePlayerInventory (PlayerEntity player) {
        if (player instanceof ServerPlayerEntity)
            ((ServerPlayerEntity) player).refreshContainer(player.inventoryMenu);
    }
	
	public static BlockRayTraceResult getPlayerPOVHitResult(World pLevel, PlayerEntity pPlayer, RayTraceContext.FluidMode pFluidMode) {
	      float f = pPlayer.xRot;
	      float f1 = pPlayer.yRot;
	      Vector3d vector3d = pPlayer.getEyePosition(1.0F);
	      float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
	      float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
	      float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
	      float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
	      float f6 = f3 * f4;
	      float f7 = f2 * f4;
	      double d0 = pPlayer.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();;
	      Vector3d vector3d1 = vector3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
	      return pLevel.clip(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.OUTLINE, pFluidMode, pPlayer));
	   }

}

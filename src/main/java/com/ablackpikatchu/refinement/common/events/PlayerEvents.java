package com.ablackpikatchu.refinement.common.events;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.security.ISecurableTile;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.util.helper.PlayerHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Refinement.MOD_ID)
public class PlayerEvents {

	@SubscribeEvent
	public static void handleGluttonyBracelet(LivingEntityUseItemEvent.Tick event) {
		if (event.getEntityLiving() instanceof PlayerEntity) {

			final PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			final ItemStack using = event.getItem();

			if (using.isEdible() && using.getUseDuration() <= 1000
					&& PlayerHelper.hasItem(player, ItemInit.GLUTTONY_BRACELET.get())) {
				ItemStack bracelet = PlayerHelper.getFirstStackMatchingItem(player, ItemInit.GLUTTONY_BRACELET.get());
				event.setDuration(1);
				bracelet.setDamageValue(bracelet.getDamageValue() + 1);
				if (bracelet.getDamageValue() >= bracelet.getMaxDamage())
					bracelet.shrink(1);
			}
		}
	}

	@SubscribeEvent
	public static void onMine(PlayerEvent.BreakSpeed event) {
		if (event.getEntityLiving().level.isClientSide())
			return;
		if (event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			TileEntity tile = event.getEntity().level.getBlockEntity(event.getPos());
			if (tile != null && tile instanceof ISecurableTile) {
				ISecurableTile securableTile = (ISecurableTile) tile;
				if (securableTile != null && securableTile.getSecurity().isPrivate())
					if (!player.getUUID().equals(securableTile.getOwnerUUID()))
						event.setCanceled(true);
			}
		}
	}
}

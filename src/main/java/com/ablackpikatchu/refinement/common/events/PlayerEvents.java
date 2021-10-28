package com.ablackpikatchu.refinement.common.events;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.inventory.StorageBinHandler;
import com.ablackpikatchu.refinement.common.item.blockitem.StorageBinBlockItem;
import com.ablackpikatchu.refinement.common.te.misc_tes.StorageBinTileEntity;
import com.ablackpikatchu.refinement.common.te.security.ISecurableTile;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.ablackpikatchu.refinement.core.util.helper.PlayerHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
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
			if (tile instanceof ISecurableTile) {
				ISecurableTile securableTile = (ISecurableTile) tile;
				if (securableTile.getSecurity().isPrivate() && !player.getUUID().equals(securableTile.getOwnerUUID()))
					event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerPickup(EntityItemPickupEvent event) {
		PlayerEntity player = event.getPlayer();
		for (int i = 0; i <= player.inventory.getContainerSize() - 1; i++) {
			ItemStack playerStack = player.inventory.getItem(i);
			
			if (playerStack.getItem() instanceof StorageBinBlockItem && NBTHelper.getBoolean(playerStack, "RefillEnabled")) {
				StorageBinHandler handler = StorageBinTileEntity.handlerFromNbt(playerStack.getOrCreateTag());
				ItemStack inserted = handler.insertItem(0, event.getItem().getItem(), false);
				event.getItem().getItem().setCount(inserted.getCount());
				event.getItem().setItem(inserted);
				StorageBinTileEntity.handlerToNbt(handler, playerStack.getOrCreateTag());
			}
		}
	}
}

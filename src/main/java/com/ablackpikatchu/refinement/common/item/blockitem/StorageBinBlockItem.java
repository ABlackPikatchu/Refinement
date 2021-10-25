package com.ablackpikatchu.refinement.common.item.blockitem;

import com.ablackpikatchu.refinement.common.block.StorageBinBlock;
import com.ablackpikatchu.refinement.common.inventory.StorageBinHandler;
import com.ablackpikatchu.refinement.common.te.misc_tes.StorageBinTileEntity;
import com.ablackpikatchu.refinement.core.util.helper.ItemStackHelper;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class StorageBinBlockItem extends BlockItem {

	public StorageBinBlockItem(Block pBlock, Properties pProperties) {
		super(pBlock, pProperties);
	}
	
	public int getStackLimit() {
		if (this.getBlock() instanceof StorageBinBlock)
			return ((StorageBinBlock) this.getBlock()).getStackLimit();
		return 0;
	}

	@Override
	public void inventoryTick(ItemStack stack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		if (pEntity instanceof PlayerEntity && stack.hasTag()) {
			PlayerEntity player = (PlayerEntity) pEntity;
			StorageBinHandler handler = StorageBinTileEntity.handlerFromNbt(stack.getOrCreateTag());
			if (NBTHelper.getBoolean(stack, "RefillEnabled") && !handler.isEmpty()
					&& ItemStackHelper.areStacksTheSame(handler.getStackInSlot(0), player.inventory.getSelected())) {
				ItemStack item = handler.takeItemsFromSlot(0, 1);

				if (!item.isEmpty()) {
					if (!player.inventory.add(player.inventory.selected, item)) {
						handler.insertItem(0, item, false);
					}
					StorageBinTileEntity.handlerToNbt(handler, stack.getOrCreateTag());
				}
			}
		}
		super.inventoryTick(stack, pLevel, pEntity, pItemSlot, pIsSelected);
	}

	@Override
	public ActionResultType useOn(ItemUseContext pContext) {
		/**
		if (pContext.getItemInHand().hasTag()) {
			StorageBinHandler handler = StorageBinTileEntity.handlerFromNbt(pContext.getItemInHand().getOrCreateTag());
			boolean execute = true;
			if (handler.isLocked(0)) {
				execute = handler.getStoredItemCount() - 1 > 0;
			}
			if (execute && NBTHelper.getBoolean(pContext.getItemInHand(), "RefillEnabled") && !handler.isEmpty()
					&& pContext.getPlayer().isShiftKeyDown()) {
				handler.getStackInSlot(0).useOn(pContext);
				StorageBinTileEntity.handlerToNbt(handler, pContext.getItemInHand().getOrCreateTag());
				return ActionResultType.SUCCESS;
			}
		}
		**/
		return super.useOn(pContext);
	}

}

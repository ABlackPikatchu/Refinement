package com.ablackpikatchu.refinement.common.item;

import com.ablackpikatchu.refinement.common.te.upgrade.IUpgradableTile;
import com.ablackpikatchu.refinement.common.te.upgrade.Upgrade;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;

public class UpgradeItem extends Item {

	public final Upgrade upgradeType;

	public UpgradeItem(Properties properties, Upgrade upgradeType) {
		super(properties);
		this.upgradeType = upgradeType;
	}

	@Override
	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext pContext) {
		if (pContext.getPlayer().isShiftKeyDown())
			return ActionResultType.PASS;

		TileEntity tile = pContext.getLevel().getBlockEntity(pContext.getClickedPos());
		if (tile instanceof IUpgradableTile) {
			IUpgradableTile upgradableTile = (IUpgradableTile) tile;
			int upgradeSlot = upgradableTile.getSlotForUpgrade(upgradeType);
			ItemStack oneItemStack = this.getDefaultInstance().copy();
			oneItemStack.setCount(1);
			if (upgradeSlot != -1
					&& TileEntityHelper.canPlaceItemInStack(((IInventory) tile).getItem(upgradeSlot), oneItemStack)) {
				int oldCount = 0;
				if (!((IInventory) tile).getItem(upgradeSlot).isEmpty())
					oldCount = ((IInventory) tile).getItem(upgradeSlot).getCount();
				ItemStack newStack = pContext.getItemInHand().copy();
				newStack.setCount(oldCount + 1);
				((IInventory) tile).setItem(upgradeSlot, newStack);
				pContext.getItemInHand().shrink(1);
				return ActionResultType.SUCCESS;
			}
		}

		return super.useOn(pContext);
	}

}

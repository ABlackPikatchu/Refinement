package com.ablackpikatchu.refinement.common.container;

import java.util.Objects;

import com.ablackpikatchu.refinement.common.slot.OutputSlot;
import com.ablackpikatchu.refinement.common.slot.itemspecific.CarbonSlot;
import com.ablackpikatchu.refinement.common.slot.itemspecific.UpgradeSlot;
import com.ablackpikatchu.refinement.common.te.machine.GrinderTileEntity;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.util.FunctionalIntReferenceHolder;
import com.ablackpikatchu.refinement.core.util.enums.Upgrades;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GrinderContainer extends MachineContainer<GrinderTileEntity> {

	private final IWorldPosCallable canInteractWithCallable;
	public FunctionalIntReferenceHolder currentWaitTime;
	public FunctionalIntReferenceHolder maxWaitTime;
	
	public GrinderContainer(final int windowId, final PlayerInventory playerInv,
			final GrinderTileEntity te) {
		super(ContainerTypesInit.GRINDER_CONTAINER_TYPE.get(), windowId, te);
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

		// Tile Entity
		this.addSlot(new Slot((IInventory) te, 0, 44, 23)); //Input
		this.addSlot(new OutputSlot((IInventory) te, 1, 135, 23)); //Output
		this.addSlot(new CarbonSlot(te, 2, 8, 44)); //Coal
		this.addSlot(new UpgradeSlot((IInventory) te, 3, 197, 113, Upgrades.SPEED)); //Speed Upgrade
		this.addSlot(new UpgradeSlot((IInventory) te, 4, 179, 113, Upgrades.AUTO_EJECT)); // Auto eject upgrade
		this.addSlot(new UpgradeSlot(te, 5, 197, 95, Upgrades.AUTO_IMPORT)); // Auto import upgrade

		// Main Player Inventory
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 159 - (4 - row) * 18 - 10));
			}
		}

		// Player Hotbar
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInv, col, 8 + col * 18, 135));
		}

		this.addDataSlot(currentWaitTime = new FunctionalIntReferenceHolder(() -> this.te.currentWaitTime,
				value -> this.te.currentWaitTime = value));
		this.addDataSlot(maxWaitTime = new FunctionalIntReferenceHolder(() -> this.te.maxWaitTime,
				value -> this.te.maxWaitTime = value));
	}

	public GrinderContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static GrinderTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof GrinderTileEntity) {
			return (GrinderTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return stillValid(canInteractWithCallable, p_75145_1_, BlockInit.GRINDER.get());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < GrinderTileEntity.slots
					&& !this.moveItemStackTo(stack1, GrinderTileEntity.slots, this.slots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if (!this.moveItemStackTo(stack1, 0, GrinderTileEntity.slots, false)) {
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return stack;
	}
	
	@Override
	protected boolean moveItemStackTo(ItemStack pStack, int pStartIndex, int pEndIndex, boolean pReverseDirection) {
		boolean flag = false;
	      int i = pStartIndex;
	      if (pReverseDirection) {
	         i = pEndIndex - 1;
	      }

	      if (pStack.isStackable()) {
	         while(!pStack.isEmpty()) {
	            if (pReverseDirection) {
	               if (i < pStartIndex) {
	                  break;
	               }
	            } else if (i >= pEndIndex) {
	               break;
	            }

	            Slot slot = this.slots.get(i);
	            ItemStack itemstack = slot.getItem();
	            if (!itemstack.isEmpty() && consideredTheSameItem(pStack, itemstack)) {
	               int j = itemstack.getCount() + pStack.getCount();
	               int maxSize = slot.getMaxStackSize();
	               if (j <= maxSize) {
	                  pStack.setCount(0);
	                  itemstack.setCount(j);
	                  slot.setChanged();
	                  flag = true;
	               } else if (itemstack.getCount() < maxSize) {
	                  pStack.shrink(maxSize - itemstack.getCount());
	                  itemstack.setCount(maxSize);
	                  slot.setChanged();
	                  flag = true;
	               }
	            }

	            if (pReverseDirection) {
	               --i;
	            } else {
	               ++i;
	            }
	         }
	      }

	      if (!pStack.isEmpty()) {
	         if (pReverseDirection) {
	            i = pEndIndex - 1;
	         } else {
	            i = pStartIndex;
	         }

	         while(true) {
	            if (pReverseDirection) {
	               if (i < pStartIndex) {
	                  break;
	               }
	            } else if (i >= pEndIndex) {
	               break;
	            }

	            Slot slot1 = this.slots.get(i);
	            ItemStack itemstack1 = slot1.getItem();
	            if (itemstack1.isEmpty() && slot1.mayPlace(pStack)) {
	               if (pStack.getCount() > slot1.getMaxStackSize()) {
	                  slot1.set(pStack.split(slot1.getMaxStackSize()));
	               } else {
	                  slot1.set(pStack.split(pStack.getCount()));
	               }

	               slot1.setChanged();
	               flag = true;
	               break;
	            }

	            if (pReverseDirection) {
	               --i;
	            } else {
	               ++i;
	            }
	         }
	      }

	      return flag;
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgressionScaled() {
		return this.currentWaitTime.get() != 0 && this.maxWaitTime.get() != 0
				? this.currentWaitTime.get() * 59 / this.maxWaitTime.get()
				: 0;
	}
}
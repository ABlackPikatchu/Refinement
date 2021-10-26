package com.ablackpikatchu.refinement.common.inventory;

import javax.annotation.Nonnull;

import com.ablackpikatchu.refinement.core.util.helper.ItemStackHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;

import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class StorageBinHandler extends ItemStackHandler {

	public int[] lockedSlots;
	protected int stackLimit;

	public StorageBinHandler(int stackLimit) {
		super(1);
		this.stackLimit = stackLimit;
		lockedSlots = new int[1];
	}

	public void setStackLimit(int stackLimit) {
		this.stackLimit = stackLimit;
	}

	public boolean isEmpty() {
		if (getStackInSlot(0).getItem() == Items.AIR)
			return true;
		return getStackInSlot(0).isEmpty();
	}
	
	public boolean canFit(int count) {
		return this.stackLimit >= this.getStoredItemCount() + count;
	}
	
	@Override
	public boolean isItemValid(int slot, ItemStack stack) {
		return com.ablackpikatchu.refinement.core.util.helper.ItemStackHelper.areStacksTheSame(stack, getStackInSlot(slot));
	}

	@Override
	public void onContentsChanged(int slot) {
	}
	
	@Override
	public int getSlotLimit(int slot) {
		return this.stackLimit;
	}

	@Override
	public int getStackLimit(int slot, ItemStack stack) {
		return this.stackLimit;
	}
	
	public int getStoredItemCount() {
		return getStackInSlot(0).getCount();
	}
	
	public void setStoredItemCount(int count) {
		getStackInSlot(0).setCount(count);
		if (getStackInSlot(0).getCount() <= 0)
			setStackInSlot(0, ItemStack.EMPTY);
	}
	
	public int getRemainingCapacity() {
		return getStackLimit(0, null) - getStackInSlot(0).getCount();
	}
	
	public void copyFromOther(StorageBinHandler otherHandler) {
		setStackInSlot(0, otherHandler.getStackInSlot(0));
		setStackLimit(otherHandler.getSlotLimit(0));
		this.lockedSlots = otherHandler.lockedSlots;
	}
	
	@Nonnull
	public ItemStack takeItemsFromSlot(int slot, int count) {
		if (isEmpty())
			return ItemStack.EMPTY;

		ItemStack stack = getStackInSlot(0).copy();
		stack.setCount(Math.min(count, getStoredItemCount()));

		if (count >= getStoredItemCount() && isLocked(0)) {
			setStoredItemCount(getStoredItemCount() - stack.getCount() + 1);
			stack.shrink(1);
		} else
			setStoredItemCount(getStoredItemCount() - stack.getCount());
		
		onContentsChanged(0);

		return stack;
	}

	@Override
	@Nonnull
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (amount == 0)
			return ItemStack.EMPTY;

		boolean isLocked = isLocked(slot);

		validateSlotIndex(slot);

		ItemStack existing = this.stacks.get(slot);

		if (existing.isEmpty())
			return ItemStack.EMPTY;

		int toExtract = Math.min(amount, this.stackLimit);
		// attempting to extract equal to or greater than what is present
		if (existing.getCount() <= toExtract) {
			if (!simulate) {
				// leave one behind
				if (isLocked)
					this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, 1));
				else// dont
					this.stacks.set(slot, ItemStack.EMPTY);
				onContentsChanged(slot);
			}

			if (isLocked) {
				// return nothing
				if (existing.getCount() == 1)
					return ItemStack.EMPTY;
				else
					return ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - 1);
			} else

				return existing;
			// there is more than requested
		} else {
			if (!simulate) {
				this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
				onContentsChanged(slot);
			}
			return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
		}
	}

	public NonNullList<ItemStack> getContents() {
		return stacks;
	}

	public void lockSlot(int slot) {
		lockedSlots[slot] = 1 - lockedSlots[slot];
	}

	public boolean isLocked(int slot) {
		if (lockedSlots.length >= slot + 1)
			return lockedSlots[slot] == 1;
		return false;
	}

	@Override
	public CompoundNBT serializeNBT() {
		ListNBT nbtTagList = new ListNBT();
		for (int i = 0; i < getContents().size(); i++) {
			if (!getContents().get(i).isEmpty()) {
				int realCount = Math.min(this.stackLimit, getContents().get(i).getCount());
				CompoundNBT itemTag = new CompoundNBT();
				itemTag.putInt("Slot", i);
				getContents().get(i).save(itemTag);
				itemTag.putInt("ExtendedCount", realCount);
				nbtTagList.add(itemTag);
			}
		}
		CompoundNBT nbt = new CompoundNBT();
		nbt.put("Items", nbtTagList);
		nbt.putIntArray("LockedSlots", lockedSlots);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		ListNBT tagList = nbt.getList("Items", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < tagList.size(); i++) {
			CompoundNBT itemTags = tagList.getCompound(i);
			int slot = itemTags.getInt("Slot");

			if (slot >= 0 && slot < stacks.size()) {
				if (itemTags.contains("StackList", Constants.NBT.TAG_LIST)) {
					ItemStack stack = ItemStack.EMPTY;
					ListNBT stackTagList = itemTags.getList("StackList", Constants.NBT.TAG_COMPOUND);
					for (int j = 0; j < stackTagList.size(); j++) {
						CompoundNBT itemTag = stackTagList.getCompound(j);
						ItemStack temp = ItemStack.of(itemTag);
						if (!temp.isEmpty()) {
							if (stack.isEmpty())
								stack = temp;
							else
								stack.grow(temp.getCount());
						}
					}
					if (!stack.isEmpty()) {
						int count = stack.getCount();
						count = Math.min(count, getStackLimit(slot, stack));
						stack.setCount(count);

						stacks.set(slot, stack);
					}
				} else {
					ItemStack stack = ItemStack.of(itemTags);
					if (itemTags.contains("ExtendedCount", Constants.NBT.TAG_INT)) {
						stack.setCount(itemTags.getInt("ExtendedCount"));
					}
					stacks.set(slot, stack);
				}
			}
		}
		lockedSlots = nbt.getIntArray("LockedSlots");
		onLoad();
	}

	@Nonnull
	@Override
	public ItemStack getStackInSlot(int slot) {
		return super.getStackInSlot(slot);
	}

	public boolean canPlayerUse(PlayerEntity player) {
		return false;
	}

	public int calcRedstone() {
		int numStacks = 0;
		float f = 0F;

		for (int slot = 0; slot < this.getSlots(); slot++) {
			ItemStack stack = this.getStackInSlot(slot);

			if (!stack.isEmpty()) {
				f += (float) stack.getCount() / (float) this.getStackLimit(slot, stack);
				numStacks++;
			}
		}

		f /= this.getSlots();
		return MathHelper.floor(f * 14F) + (numStacks > 0 ? 1 : 0);
	}

	public boolean canItemBeStored(ItemStack stack) {
		return ItemStackHelper.areStacksTheSame(stack, getStackInSlot(0));
	}

}

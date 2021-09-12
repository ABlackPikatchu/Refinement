package com.ablackpikatchu.refinement.common.te;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.core.util.MCMathUtils;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public abstract class LockableSidedInventoryTileEntity extends LockableTileEntity implements ISidedInventory {
	protected NonNullList<ItemStack> items;
	private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP,
			Direction.DOWN, Direction.NORTH);
	@Nullable
	protected ResourceLocation lootTable;
	protected long lootTableSeed;

	protected LockableSidedInventoryTileEntity(TileEntityType<?> typeIn, int inventorySize) {
		super(typeIn);
		this.items = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
	}

	@Override
	public int getContainerSize() {
		return items.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : items) {
			if (!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int index) {
		if (index < 0 || index >= items.size()) {
			return ItemStack.EMPTY;
		}
		return items.get(index);
	}

	@Override
	public ItemStack removeItem(int index, int count) {
		return ItemStackHelper.removeItem(items, index, count);
	}

	@Override
	public ItemStack removeItemNoUpdate(int index) {
		return ItemStackHelper.takeItem(items, index);
	}

	@Override
	public void setItem(int index, ItemStack stack) {
		items.set(index, stack);
		if (stack.getCount() > getMaxStackSize()) {
			stack.setCount(getMaxStackSize());
		}
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		return level != null && level.getBlockEntity(worldPosition) == this
				&& MCMathUtils.distanceSq(player, worldPosition) <= 64;
	}

	@Override
	public void clearContent() {
		items.clear();
	}

	@Override
	public void load(BlockState stateIn, CompoundNBT tags) {
		super.load(stateIn, tags);
		this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(tags, items);
		for (int i = 0; i <= getContainerSize() - 1; ++i) {
			if (items.get(i) != null)
			this.setItem(i, items.get(i));
		}
	}

	@Override
	public CompoundNBT save(CompoundNBT tags) {
		super.save(tags);
		NonNullList<ItemStack> saveItems = NonNullList.create();
		for (int i = 0; i <= getContainerSize() - 1; ++i) {
			saveItems.add(i, getItem(i));
		}
		ItemStackHelper.saveAllItems(tags, saveItems);
		return tags;
	}
	
	public NonNullList<ItemStack> getAllItems() {
		return this.items;
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT tags = getUpdateTag();
		ItemStackHelper.saveAllItems(tags, items);
		return new SUpdateTileEntityPacket(worldPosition, 1, tags);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
		super.onDataPacket(net, packet);
		ItemStackHelper.loadAllItems(packet.getTag(), items);
	}

	@Nullable
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (!this.remove && side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (side == Direction.UP)
				return handlers[0].cast();
			if (side == Direction.DOWN)
				return handlers[1].cast();
			return handlers[2].cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		for (LazyOptional<? extends IItemHandler> handler : handlers) {
			handler.invalidate();
		}
	}

	protected boolean trySaveLootTable(CompoundNBT p_184282_1_) {
		if (this.lootTable == null) {
			return false;
		} else {
			p_184282_1_.putString("LootTable", this.lootTable.toString());
			if (this.lootTableSeed != 0L) {
				p_184282_1_.putLong("LootTableSeed", this.lootTableSeed);
			}

			return true;
		}
	}

	protected abstract NonNullList<ItemStack> getItems();

	protected abstract void setItems(NonNullList<ItemStack> p_199721_1_);

	protected boolean tryLoadLootTable(CompoundNBT p_184283_1_) {
		if (p_184283_1_.contains("LootTable", 8)) {
			this.lootTable = new ResourceLocation(p_184283_1_.getString("LootTable"));
			this.lootTableSeed = p_184283_1_.getLong("LootTableSeed");
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unused")
	public boolean hasRoomForOutputItem(ItemStack stack) {
		for (int i : getOutputSlots()) {
			ItemStack output = getItem(i);
			if (TileEntityHelper.canPlaceItemInStack(output, stack)) {
				return true;
			}
		}
		return false;
	}

	protected void storeResultItem(ItemStack stack) {
		// Merge the item into any output slot it can fit in
		for (int i : getOutputSlots()) {
			ItemStack output = getItem(i);
			if (TileEntityHelper.canPlaceItemInStack(output, stack)) {
				if (output.isEmpty()) {
					setItem(i, stack);
				} else {
					output.setCount(output.getCount() + stack.getCount());
				}
				return;
			}
		}
	}

	protected abstract int[] getOutputSlots();
}

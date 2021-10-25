package com.ablackpikatchu.refinement.common.te.misc_tes;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.common.inventory.IItemHandlerInventory;
import com.ablackpikatchu.refinement.common.inventory.StorageBinHandler;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.network.RefinementNetwork;
import com.ablackpikatchu.refinement.core.network.message.UpdateBinMessage;
import com.ablackpikatchu.refinement.core.util.helper.PlayerHelper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class StorageBinTileEntity extends TileEntity implements ITickableTileEntity, IItemHandlerInventory<StorageBinHandler>, IInventory, ISidedInventory {

	private final StorageBinHandler itemHandler = createHandler();
	private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
	private int stackLimit = 1024;

	private long lastClickTime;
	private UUID lastClickUUID;

	public StorageBinTileEntity(TileEntityType<?> p_i48289_1_) {
		super(p_i48289_1_);
	}

	public StorageBinTileEntity(int stackLimit) {
		this(TileEntityTypesInit.STORAGE_BIN_TILE_ENTITY_TYPE.get());
		this.stackLimit = stackLimit;
		this.itemHandler.setStackLimit(stackLimit);
	}

	public StorageBinTileEntity() {
		this(TileEntityTypesInit.STORAGE_BIN_TILE_ENTITY_TYPE.get());
	}

	private StorageBinHandler createHandler() {
		return new StorageBinHandler(1024) {
			@Override
			public void onContentsChanged(int slot) {
				StorageBinTileEntity.this.setChanged();
			}
		};
	}

	@Nonnull
	public ItemStack takeItemsFromSlot(int slot, int count) {
		if (itemHandler.isEmpty())
			return ItemStack.EMPTY;

		ItemStack stack = itemHandler.getStackInSlot(0).copy();
		stack.setCount(Math.min(count, itemHandler.getStoredItemCount()));

		if (count >= itemHandler.getStoredItemCount() && itemHandler.isLocked(0)) {
			itemHandler.setStoredItemCount(itemHandler.getStoredItemCount() - stack.getCount() + 1);
			stack.shrink(1);
		} else
			itemHandler.setStoredItemCount(itemHandler.getStoredItemCount() - stack.getCount());

		if (level != null) {
			level.updateNeighborsAt(worldPosition, getBlockState().getBlock());
			level.updateNeighborsAt(worldPosition.below(), getBlockState().getBlock());
		}
		
		itemHandler.onContentsChanged(0);

		return stack;
	}

	@Override
	public StorageBinHandler getItemHandler() {
		return this.itemHandler;
	}

	public int getStoredItemStackSize() {
		return itemHandler.getStoredItemCount();
	}

	public int getStackLimit() {
		return stackLimit;
	}

	public int putItemsIntoSlot(int slot, @Nonnull ItemStack stack, int count) {

		if (itemHandler.isEmpty()) {
			ItemStack stack2 = stack.copy();
			stack2.setCount(1);
			itemHandler.setStackInSlot(0, stack2);
			stack.shrink(1);
		}

		if (!itemHandler.canItemBeStored(stack))
			return 0;

		int countAdded = Math.min(count, stack.getCount());
		if (itemHandler.getRemainingCapacity() < countAdded)
			countAdded = Math.min(countAdded, itemHandler.getRemainingCapacity());

		itemHandler.setStoredItemCount(itemHandler.getStoredItemCount() + countAdded);
		stack.shrink(countAdded);
		
		setChanged();

		return countAdded;
	}

	public int interactPutCurrentItemIntoSlot(int slot, PlayerEntity player) {
		int count = 0;
		ItemStack playerStack = player.inventory.getSelected();
		if (!playerStack.isEmpty())
			count = putItemsIntoSlot(slot, playerStack, playerStack.getCount());

		return count;
	}

	public int interactPutCurrentInventoryIntoSlot(int slot, PlayerEntity player) {

		int count = 0;
		if (!itemHandler.isEmpty()) {
			for (int i = 0, n = player.inventory.getContainerSize(); i < n; i++) {
				ItemStack subStack = player.inventory.getItem(i);
				if (!subStack.isEmpty()) {
					int subCount = putItemsIntoSlot(slot, subStack, subStack.getCount());
					if (subCount > 0 && subStack.getCount() == 0)
						player.inventory.setItem(i, ItemStack.EMPTY);

					count += subCount;
				}
			}
		}

		if (count > 0)
			PlayerHelper.updatePlayerInventory(player);

		return count;
	}

	public int interactPutItemsIntoSlot(int slot, PlayerEntity player) {
		int count;
		if (level.getGameTime() - lastClickTime < 10 && player.getUUID().equals(lastClickUUID))
			count = interactPutCurrentInventoryIntoSlot(slot, player);
		else
			count = interactPutCurrentItemIntoSlot(slot, player);

		lastClickTime = level.getGameTime();
		lastClickUUID = player.getUUID();

		return count;
	}

	public ItemStack extractItem(int count) {
		return itemHandler.extractItem(0, count, false);
	}

	public void addItem(ItemStack stack) {
		itemHandler.insertItem(0, stack, false);
	}

	public ItemStack getItemStored() {
		return itemHandler.getStackInSlot(0).copy();
	}

	@Override
	public void tick() {
		if (this.level.isClientSide())
			clientTick();
		else
			serverTick();
	}

	public void serverTick() {
		// itemHandler.insertItem(0, new ItemStack(ItemInit.CHARCOAL_DUST.get()),
		// false);
	}

	public void clientTick() {
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		itemHandler.deserializeNBT(nbt.getCompound("inv"));
		stackLimit = nbt.getInt("StackLimit");
		itemHandler.setStackLimit(stackLimit);
		setChanged();
		super.load(state, nbt);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		compound.put("inv", itemHandler.serializeNBT());
		compound.putInt("StackLimit", stackLimit);
		return super.save(compound);
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT tags = new CompoundNBT();
		save(tags);
		return new SUpdateTileEntityPacket(getBlockPos(), -1, tags);
	}

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = super.getUpdateTag();
		nbt.put("inv", itemHandler.serializeNBT());
		nbt.putInt("StackLimit", stackLimit);
		return nbt;
	}

	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
		load(state, tag);
		if (getLevel() != null && !getLevel().isClientSide())
			syncClient(itemHandler.getStackInSlot(0), itemHandler.getStoredItemCount(), getStackLimit());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
		super.onDataPacket(net, packet);
		CompoundNBT tag = packet.getTag();
		// itemHandler.deserializeNBT(tag.getCompound("inv"));
		// itemStored = ItemStack.of(tag.getCompound("ItemStored"));
		handleUpdateTag(getBlockState(), tag);
	}
	
	public void storeDataToItem(ItemStack stack) {
		CompoundNBT nbt = new CompoundNBT();
		save(nbt);
		stack.setTag(nbt);
	}
	
	public void loadFromItem(ItemStack stack) {
		if (!stack.hasTag())
			return;
		CompoundNBT nbt = stack.getOrCreateTag();
		itemHandler.deserializeNBT(nbt.getCompound("inv"));
		stackLimit = nbt.getInt("StackLimit");
	}
	
	public static StorageBinHandler handlerFromNbt(CompoundNBT nbt) {
		int stackLimit = nbt.getInt("StackLimit");
		StorageBinHandler binInventory = new StorageBinHandler(stackLimit);
		binInventory.deserializeNBT(nbt.getCompound("inv"));
		return binInventory;
	}
	
	public static void handlerToNbt(StorageBinHandler handler, CompoundNBT nbt) {
		nbt.put("inv", handler.serializeNBT());
		nbt.putInt("StackLimit", handler.getSlotLimit(0));
	}
	
	@Override
	public void setChanged() {
		if (level != null && !level.isClientSide() && level.getBlockEntity(worldPosition) != null)
			syncClient(itemHandler.getStackInSlot(0), itemHandler.getStoredItemCount(), getStackLimit());
		super.setChanged();
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return handler.cast();
		}

		return super.getCapability(cap, side);
	}

	protected void syncClient(ItemStack item, int count, int stackLimit) {
		if (level == null)
			return;
		if (level.isClientSide())
			return;

		PacketDistributor.TargetPoint point = new PacketDistributor.TargetPoint(worldPosition.getX(),
				worldPosition.getY(), worldPosition.getZ(), 500, level.dimension());
		RefinementNetwork.STORAGE_BIN_CHANNEL.send(PacketDistributor.NEAR.with(() -> point),
				new UpdateBinMessage(worldPosition, item, count, stackLimit));
	}
	
	@Override
	public World getLevel() {
		return this.level;
	}

	@OnlyIn(Dist.CLIENT)
	public void clientUpdate(final ItemStack item, final int count, final int stackLimit) {
		if (!level.isClientSide())
			return;

		StorageBinTileEntity.this.clientUpdateAsync(item, count, stackLimit);
	}

	@OnlyIn(Dist.CLIENT)
	public void clientUpdateAsync(ItemStack item, int count, int stackLimit) {
		itemHandler.setStackInSlot(0, item);
		itemHandler.setStoredItemCount(count);
		itemHandler.setStackLimit(stackLimit);
		this.stackLimit = stackLimit;
	}

	@Override
	public void clearContent() {
		itemHandler.getContents().clear();
	}

	@Override
	public int getContainerSize() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return itemHandler.isEmpty();
	}

	@Override
	public ItemStack getItem(int pIndex) {
		return itemHandler.getStackInSlot(pIndex);
	}

	@Override
	public ItemStack removeItem(int pIndex, int pCount) {
		return itemHandler.extractItem(pIndex, pCount, false);
	}

	@Override
	public ItemStack removeItemNoUpdate(int pIndex) {
		return itemHandler.extractItem(pIndex, itemHandler.getStackInSlot(pIndex).getCount(), false);
	}

	@Override
	public void setItem(int pIndex, ItemStack pStack) {
		itemHandler.setStackInSlot(pIndex, pStack);
	}

	@Override
	public boolean stillValid(PlayerEntity pPlayer) {
		return false;
	}

	@Override
	public int[] getSlotsForFace(Direction pSide) {
		return new int[] {0};
	}

	@Override
	public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, Direction pDirection) {
		return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
		return true;
	}

}

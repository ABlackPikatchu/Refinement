package com.ablackpikatchu.refinement.api.te;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.common.item.AutoEjectUpgrade;
import com.ablackpikatchu.refinement.common.item.AutoImportUpgrade;
import com.ablackpikatchu.refinement.common.te.security.SecurityType;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.util.MCMathUtils;
import com.ablackpikatchu.refinement.core.util.energy.ModEnergyStorage;
import com.ablackpikatchu.refinement.core.util.helper.InventoryHelper;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.INameable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public abstract class SidedInventoryTileEntity extends LockableTileEntity
implements ISidedInventory, ITickableTileEntity, IInventory, INamedContainerProvider, INameable {

	public int currentWaitTime;
	public int maxWaitTime;
	public int usedCarbon;
	public IInventory iInventory = this;
	protected NonNullList<ItemStack> items;
	protected final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP,
			Direction.DOWN, Direction.NORTH);
	public final SidedInvWrapper inventory = new SidedInvWrapper(this, null);
	@Nullable
	protected ResourceLocation lootTable;
	protected long lootTableSeed;

	public SecurityType security;
	public UUID ownerUUID;

	protected SidedInventoryTileEntity(TileEntityType<?> typeIn, int inventorySize) {
		super(typeIn);
		items = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
	}

	@Override
	public int getContainerSize() { return items.size(); }

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : items) {
			if (!stack.isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public ItemStack getItem(int index) {
		if (index < 0 || index >= items.size())
			return ItemStack.EMPTY;
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
	public void tick() {
		if (!level.isClientSide()) {
			serverTick();
		} else {
			clientTick();
		}
	}

	public void serverTick() {

	}

	public void clientTick() {

	}

	@Override
	public void load(BlockState stateIn, CompoundNBT tags) {
		super.load(stateIn, tags);
		items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(tags, items);
		for (int i = 0; i <= getContainerSize() - 1; ++i) {
			this.setItem(i, items.get(i));
		}
		currentWaitTime = tags.getInt("CurrentWaitTime");
		if (tags.hasUUID("owner")) {
			setOwner(tags.getUUID("owner"));
		}
		security = SecurityType.byName(tags.getString("SecurityType"));
	}

	@Override
	public CompoundNBT save(CompoundNBT tags) {
		super.save(tags);
		NonNullList<ItemStack> saveItems = NonNullList.create();
		for (int i = 0; i <= getContainerSize() - 1; ++i) {
			saveItems.add(i, getItem(i));
		}
		ItemStackHelper.saveAllItems(tags, saveItems);
		tags.putInt("CurrentWaitTime", currentWaitTime);
		if (ownerUUID != null) {
			tags.putUUID("owner", ownerUUID);
		}
		if (security != null) {
			tags.putString("SecurityType", security.getName());
		}
		return tags;
	}

	public void loadItems() {
		NonNullList<ItemStack> newItems = NonNullList.create();
		for (int i = 0; i <= getContainerSize() - 1; ++i) {
			newItems.add(i, getItem(i));
		}
		items = newItems;
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
		super.onDataPacket(net, packet);
		loadItems();
		ItemStackHelper.loadAllItems(packet.getTag(), items);
	}

	public NonNullList<ItemStack> getAllItems() { return items; }

	@Nullable
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (!remove && side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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
		dropContents();
		super.setRemoved();
		for (LazyOptional<? extends IItemHandler> handler : handlers) {
			handler.invalidate();
		}
	}

	protected abstract NonNullList<ItemStack> getItems();

	protected abstract void setItems(NonNullList<ItemStack> p_199721_1_);

	public boolean hasRoomForOutputItem(ItemStack stack) {
		for (int i : getOutputSlots()) {
			ItemStack output = getItem(i);
			if (TileEntityHelper.canPlaceItemInStack(output, stack))
				return true;
		}
		return false;
	}

	protected void storeResultItem(ItemStack stack) {
		// Merge the item into any output slot it can fit in
		for (int i : getOutputSlots()) {
			try {
				ItemStack output = ((IInventory) this).getItem(i);
				if (getItems() != null && TileEntityHelper.canPlaceItemInStack(output, stack)) {
					if (output.isEmpty()) {
						((IInventory) this).setItem(i, stack);
					} else {
						output.setCount(output.getCount() + stack.getCount());
					}
					return;
				} else if (getOutputSlots().length == 1)
					return;
			} catch (Exception e) {

			}
		}
	}

	protected abstract int[] getOutputSlots();

	/**
	 * Drops the contents of the tile entity
	 */
	public void dropContents() {
		getAllItems().forEach(item -> {
			ItemEntity itemEntity = new ItemEntity(level, worldPosition.getX(), worldPosition.getY(),
					worldPosition.getZ(), item);
			level.addFreshEntity(itemEntity);
		});
	}

	public void setOwner(UUID playerUUID) { ownerUUID = playerUUID; }

	public void setSecurity(SecurityType security) { this.security = security; }

	public boolean isPrivate() { return security == SecurityType.PRIVATE; }

	public int getCurrentProgress() { return currentWaitTime; }

	public void handleSpeedUpgrades(int speedUpgradeSlot, int defaultSpeed, int decreasedSpeed) {
		if (this.getItem(speedUpgradeSlot).getItem() == ItemInit.SPEED_UPGRADE.get()) {
			maxWaitTime = defaultSpeed - (decreasedSpeed * this.getItem(speedUpgradeSlot).getCount());
			usedCarbon = this.getItem(speedUpgradeSlot).getCount() / 2 + 1;
		} else {
			maxWaitTime = defaultSpeed;
			usedCarbon = 1;
		}
	}

	public void handleEnergySpeedUpgrades(int speedUpgradeSlot, int defaultSpeed, int decreasedSpeed,
			ModEnergyStorage energyStorage, int defaultEnergy, int extraConsumedEnergyPerUpgrade) {
		if (this.getItem(speedUpgradeSlot).getItem() == ItemInit.SPEED_UPGRADE.get()) {
			maxWaitTime = defaultSpeed - (decreasedSpeed * this.getItem(speedUpgradeSlot).getCount());
			energyStorage.energyUsed = this.getItem(speedUpgradeSlot).getCount() * extraConsumedEnergyPerUpgrade
					+ defaultEnergy;
		} else {
			energyStorage.energyUsed = defaultEnergy;
			maxWaitTime = defaultSpeed;
		}
	}

	/**
	 * Gets the all the recipes of type
	 *
	 * @param <R>
	 * @param recipeType the {@link IRecipeType} to check for
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <R extends IRecipe<IInventory>> List<R> getRecipes(IRecipeType<?> recipeType) {
		List<R> recipes = new ArrayList<>();
		if (recipeType instanceof IRecipeType<?>) {
			recipes = level.getRecipeManager().getAllRecipesFor((IRecipeType<R>) recipeType);
		}
		return recipes;
	}

	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn, World world) {
		return world != null ? world.getRecipeManager().getRecipes().stream()
				.filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}

	/**
	 * Handles auto ejecting
	 *
	 * @param autoEjectupgradeSlot auto-eject upgrade slot
	 * @param outputSlots          output slots (a.k.a slots that can be ejected)
	 */
	public void handleAutoEject(int autoEjectupgradeSlot, int... outputSlots) {
		for (int outputSlot : outputSlots) {
			Direction direction = Direction
					.byName(NBTHelper.getString(getItem(autoEjectupgradeSlot), AutoEjectUpgrade.DIRECTION_PROPERTY));
			if (direction == null)
				return;

			if (level.getBlockEntity(worldPosition.relative(direction, 1)) != null) {
				TileEntity targetTile = level.getBlockEntity(worldPosition.relative(direction, 1));
				targetTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction.getOpposite())
				.map(handler -> {
					for (int i = 0; i <= handler.getSlots() - 1; i++) {
						if (!getItem(outputSlot).isEmpty()) {
							if (handler.insertItem(i, getItem(outputSlot), true) != getItem(outputSlot)) {
								setItem(outputSlot, handler.insertItem(i, getItem(outputSlot), false));
							}
						}
					}
					return true;
				});
			}
		}
	}

	/**
	 * Handles auto importing
	 *
	 * @param recipeType            the recipe the machine accepts
	 * @param autoImportUpgradeSlot auto-import upgrade slot
	 * @param inputSlots            input slots (a.k.a slots that can be imported
	 *                              into)
	 */
	public void handleAutoImport(@Nullable IRecipeType<?> recipeType, int autoImportUpgradeSlot, int... inputSlots) {
		for (int inputSlot : inputSlots) {
			Direction direction = Direction
					.byName(NBTHelper.getString(getItem(autoImportUpgradeSlot), AutoImportUpgrade.DIRECTION_PROPERTY));
			if (direction == null)
				return;

			if (level.getBlockEntity(worldPosition.relative(direction, 1)) != null) {
				if (this.getItem(autoImportUpgradeSlot).getItem() == ItemInit.AUTO_IMPORT_UPGRADE.get()) {
					TileEntity targetTile = level.getBlockEntity(worldPosition.relative(direction, 1));
					targetTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction.getOpposite())
					.map(handler -> {
						for (int i = 0; i <= handler.getSlots() - 1; i++) {
							if (recipeType != null) {
								if (this.getValidInputs(recipeType)
										.contains(handler.getStackInSlot(i).getItem())) {
									if (inventory.insertItem(inputSlot, handler.getStackInSlot(i),
											true) != handler.getStackInSlot(i)) {
										if (!getItem(inputSlot).isEmpty()) {
											handler.extractItem(i, handler.getSlotLimit(i) - inventory
													.insertItem(inputSlot, handler.getStackInSlot(i), false)
													.getCount(), false);
										} else {
											setItem(inputSlot, handler.extractItem(i,
													inventory.getSlotLimit(inputSlot), false));
										}
									}
									TileEntityHelper.updateTE(this);
									TileEntityHelper.updateTE(
											level.getBlockEntity(worldPosition.relative(direction, 1)));
									break;
								}
							} else {
								if (inventory.insertItem(inputSlot, handler.getStackInSlot(i), true) != handler
										.getStackInSlot(i)) {
									handler.extractItem(inventory
											.insertItem(inputSlot, handler.getStackInSlot(i), false).getCount(),
											i, false);
								}
								TileEntityHelper.updateTE(this);
								TileEntityHelper
								.updateTE(level.getBlockEntity(worldPosition.relative(direction, 1)));
								break;
							}
						}
						return true;
					});
				}
			}
		}
	}

	/**
	 * Handles Fuel Auto Importing
	 *
	 * @param autoImportUpgradeSlot the auto-import upgrade slot
	 * @param fuelSlot              the fuel slot
	 */
	public void handleFuelAutoImport(int autoImportUpgradeSlot, int fuelSlot) {
		Direction direction = Direction
				.byName(NBTHelper.getString(getItem(autoImportUpgradeSlot), AutoImportUpgrade.DIRECTION_PROPERTY));
		if (direction == null)
			return;
		if (HopperTileEntity.getContainerAt(level, worldPosition.relative(direction, 1)) != null) {
			if (this.getItem(autoImportUpgradeSlot).getItem() == ItemInit.AUTO_IMPORT_UPGRADE.get()) {
				IInventory autoImportContainer = HopperTileEntity.getContainerAt(level,
						worldPosition.relative(direction, 1));
				if (!autoImportContainer.isEmpty()) {
					for (int i = 0; i <= autoImportContainer.getContainerSize() - 1; i++) {
						if (autoImportContainer.getItem(i).getItem() == ItemInit.REFINED_CARBON_INGOT.get()) {
							ItemStack pStack = autoImportContainer.getItem(i);
							ItemStack itemstack = this.getItem(fuelSlot);
							if (itemstack.isEmpty()) {
								ItemStack copy = pStack.copy();
								copy.setCount(1);
								this.setItem(fuelSlot, copy);
								pStack.shrink(1);
							} else if (InventoryHelper.canMergeItems(itemstack, pStack)) {
								int x = pStack.getMaxStackSize() - itemstack.getCount();
								int j = Math.min(pStack.getCount(), x);
								pStack.shrink(j);
								itemstack.grow(j);
							} else {
								InventoryHelper.mergeOneByOne(itemstack, pStack);
							}
							this.setChanged();
							autoImportContainer.setChanged();
							TileEntityHelper.updateTE(this);
							if (level.getBlockEntity(worldPosition.relative(direction, 1)) != null) {
								TileEntityHelper.updateTE(level.getBlockEntity(worldPosition.relative(direction, 1)));
							}
							break;
						}
					}
				}
			}
		}
	}

	public ArrayList<Item> getValidInputs(IRecipeType<?> recipeType) {
		ArrayList<Item> validInputs = new ArrayList<>();
		this.getRecipes(recipeType).forEach(recipe -> {
			recipe.getIngredients().forEach(ingredient -> {
				for (ItemStack item : ingredient.getItems()) {
					if (!validInputs.contains(item.getItem()) && item.getItem() != ItemInit.REFINED_CARBON_INGOT.get()
							&& !item.isEmpty()) {
						validInputs.add(item.getItem());
					}
				}
			});
		});
		return validInputs;
	}

	public void sendOutPower(ModEnergyStorage energyStorage, Direction... directions) {
		AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
		if (capacity.get() > 0) {
			for (Direction direction : directions) {
				TileEntity te = level.getBlockEntity(worldPosition.relative(direction));
				if (te != null) {
					boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite())
							.map(handler -> {
								if (handler.canReceive()) {
									int received = handler.receiveEnergy(
											Math.min(capacity.get(), energyStorage.getMaxExtract()), false);
									capacity.addAndGet(-received);
									energyStorage.consumeEnergy(received);
									TileEntityHelper.updateTE(this);
									TileEntityHelper.updateTE(te);
									return capacity.get() > 0;
								} else
									return true;
							}).orElse(true);
					if (!doContinue)
						return;
				}
			}
		}
	}

	public void advanceProgress() {
		currentWaitTime++;
	}

	public void regressProgress() {
		currentWaitTime--;
		if (currentWaitTime < 0) {
			currentWaitTime = 0;
		}
	}

	public boolean progressComplete() {
		return currentWaitTime >= maxWaitTime;
	}
}

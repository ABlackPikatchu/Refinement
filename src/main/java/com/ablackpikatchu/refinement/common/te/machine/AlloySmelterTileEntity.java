package com.ablackpikatchu.refinement.common.te.machine;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.machine.AlloySmelterBlock;
import com.ablackpikatchu.refinement.common.container.AlloySmelterContainer;
import com.ablackpikatchu.refinement.common.item.AutoImportUpgrade;
import com.ablackpikatchu.refinement.common.recipe.AlloySmeltingRecipe;
import com.ablackpikatchu.refinement.common.te.MachineTileEntity;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.energy.ModEnergyStorage;
import com.ablackpikatchu.refinement.core.util.helper.InventoryHelper;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;
import com.google.common.collect.Lists;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraftforge.common.util.Constants;

public class AlloySmelterTileEntity extends MachineTileEntity implements ITickableTileEntity {

	List<ItemStack> allItems = null;
	private ITextComponent customName;

	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

	public final static int slots = 10;

	private static final int[] SLOTS_FOR_UP = new int[] {
			0, 1, 2, 3
	};
	private static final int[] SLOTS_FOR_DOWN = new int[] {
			4
	};
	private static final int[] SLOTS_FOR_SIDES = new int[] {
			0, 1, 2, 3, 5
	};

	private int tickSinceLastStore;

	public AlloySmelterTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, slots);
		this.energyStorage = createEnergy();
	}

	public AlloySmelterTileEntity() {
		this(TileEntityTypesInit.ALLOY_SMELTER_TILE_ENTITY_TYPE.get());
	}

	private ModEnergyStorage createEnergy() {
		return new ModEnergyStorage(100000, (CommonConfig.ALLOY_SMELTER_DEFAULT_ENERGY_USAGE.get()
				+ CommonConfig.ALLOY_SMELTER_ENERGY_USAGE_PER_SPEED_UPGRADE.get() * 8) / 4 * 5) {
			@Override
			protected void onEnergyChanged() {
				boolean newHasPower = hasEnoughPowerToWork();
				if (newHasPower != hasPower) {
					hasPower = newHasPower;
				}
				setChanged();
			}
		};
	}
	
	@Override
	public void handleAutoImport(@Nullable IRecipeType<?> recipeType, int autoImportUpgradeSlot, int... inputSlots) {
		for (int inputSlot : inputSlots) {
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
							if (recipeType != null) {
								if (this.getValidInputs(recipeType)
										.contains(autoImportContainer.getItem(i).getItem())) {
									InventoryHelper.tryMoveInItem(autoImportContainer, (IInventory) this,
											autoImportContainer.getItem(i), inputSlot, null);
									InventoryHelper.tryMoveInItem(autoImportContainer, (IInventory) this,
											autoImportContainer.getItem(i), inputSlot, null);
									TileEntityHelper.updateTE(this);
									TileEntityHelper
											.updateTE(this.level.getBlockEntity(worldPosition.relative(direction, 1)));
									break;
								}
							} else {
								InventoryHelper.tryMoveInItem(autoImportContainer, (IInventory) this,
										autoImportContainer.getItem(i), inputSlot, null);
								InventoryHelper.tryMoveInItem(autoImportContainer, (IInventory) this,
										autoImportContainer.getItem(i), inputSlot, null);
								TileEntityHelper.updateTE(this);
								TileEntityHelper
										.updateTE(this.level.getBlockEntity(worldPosition.relative(direction, 1)));
								break;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public ArrayList<Item> getValidInputs(IRecipeType<?> recipeType) {
		ArrayList<Item> validInputs = new ArrayList<>();
			getRecipes(RecipeInit.ALLOY_SMELTING_RECIPE).forEach(recipe -> {
				//AlloySmeltingRecipe alloySmeltingRecipe = (AlloySmeltingRecipe) recipe;
				recipe.getIngredients().forEach(ingredient -> {
					if (!ingredient.isEmpty()) {
						for (ItemStack itemStack : ingredient.getItems()) {
						Item item = itemStack.getItem();
						if (item != ItemInit.REFINED_CARBON_INGOT.get() && !validInputs.contains(item))
							validInputs.add(item);
						}
					}
				});
			});
		return validInputs;
	}

	@Override
	public void tick() {
		if (!this.level.isClientSide()) {
			TileEntityHelper.setStateProperty(this, AlloySmelterBlock.LIT, false);

			handleEnergyAbilityUpgrade(9);
			handleAutoEject(7, 4);
			if (!hasValidRecipe())
				handleAutoImport(RecipeInit.ALLOY_SMELTING_RECIPE, 8, 0, 1, 2, 3);
			if (!usingEnergy)
				handleFuelAutoImport(8, 5);

			if (tickSinceLastStore < 20)
				tickSinceLastStore++;

			if (getRecipe() != null) {
				if (!usingEnergy)
					handleSpeedUpgrades(6, getRecipe().getProcessTime(),
							CommonConfig.ALLOY_SMELTER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE.get());
				else
					handleEnergySpeedUpgrades(6, getRecipe().getProcessTime(),
							CommonConfig.ALLOY_SMELTER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE.get(), energyStorage,
							CommonConfig.ALLOY_SMELTER_DEFAULT_ENERGY_USAGE.get(),
							CommonConfig.ALLOY_SMELTER_ENERGY_USAGE_PER_SPEED_UPGRADE.get());
				if (canWork()) {
					if (this.currentWaitTime >= this.maxWaitTime) {
						TileEntityHelper.updateTE(this);
						finishRecipe();
					} else {
						TileEntityHelper.setStateProperty(this, AlloySmelterBlock.LIT, true);
						advanceProgress();
					}
				}
			}
		}
	}

	private void finishRecipe() {
		if (tickSinceLastStore >= 20) {
			if (TileEntityHelper.canPlaceItemInStack(this.getItem(4), getRecipe().getResultItem())) {
				consumeFuel();
				getRecipe().consumeIngredients(iInventory);
				int oldCount = 0;
				if (this.getItem(4) != ItemStack.EMPTY)
					oldCount = this.getItem(4).getCount();
				this.setItem(4, new ItemStack(getRecipe().getResultItem().getItem(),
						getRecipe().getResultItem().getCount() + oldCount));
				this.currentWaitTime = 0;
				tickSinceLastStore = 0;
				TileEntityHelper.updateTE(this);
			}
		}
	}

	@Nullable
	protected AlloySmeltingRecipe getRecipe() {
		if (level == null)
			return null;
		return level.getRecipeManager().getRecipeFor(RecipeInit.ALLOY_SMELTING_RECIPE, this, level).orElse(null);
	}

	protected boolean hasValidRecipe() {
		return getRecipe() != null;
	}

	@Override
	public int getContainerSize() {
		return slots;
	}

	@Override
	protected int[] getOutputSlots() {
		return SLOTS_FOR_DOWN;
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + Refinement.MOD_ID + ".grinder");
	}

	public void setCustomName(ITextComponent name) {
		this.customName = name;
	}

	public ITextComponent getName() {
		return this.customName != null ? this.customName : this.getDefaultName();
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	@Nullable
	public ITextComponent getCustomName() {
		return this.customName;
	}

	@Override
	protected Container createMenu(final int windowID, final PlayerInventory playerInv) {
		return new AlloySmelterContainer(windowID, playerInv, this);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		if (this.customName != null) {
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
		}
		compound.putInt("TickSinceLastStore", tickSinceLastStore);
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		if (nbt.contains("CustomName", Constants.NBT.TAG_STRING)) {
			this.customName = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
		}
		tickSinceLastStore = nbt.getInt("TickSinceLastStore");
	}

	@Override
	public int[] getSlotsForFace(Direction pSide) {
		if (pSide == Direction.UP)
			return SLOTS_FOR_UP;
		else if (pSide == Direction.DOWN)
			return SLOTS_FOR_DOWN;
		else
			return SLOTS_FOR_SIDES;
	}

	@Override
	public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, Direction pDirection) {
		if (pDirection == Direction.DOWN)
			return false;
		else {
			if (hasValidRecipe())
				return false;
			if (pDirection == Direction.UP && Lists.newArrayList(0, 1, 2, 3).contains(pIndex))
				return true;
			else if (pIndex == 5) {
				if (!usingEnergy)
					return Lists.newArrayList(0, 1, 2, 3, 5).contains(pIndex);
				else
					return Lists.newArrayList(0, 1, 2, 3).contains(pIndex);
			}
		}
		return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
		return pIndex == 4 && pDirection == Direction.DOWN;
	}

	@Override
	public int getFuelSlot() {
		return 5;
	}

}
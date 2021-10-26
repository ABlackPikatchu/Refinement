package com.ablackpikatchu.refinement.common.te.machine;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.machine.GrinderBlock;
import com.ablackpikatchu.refinement.common.container.GrinderContainer;
import com.ablackpikatchu.refinement.common.recipe.GrinderRecipe;
import com.ablackpikatchu.refinement.common.te.MachineTileEntity;
import com.ablackpikatchu.refinement.common.te.security.ISecurableTile;
import com.ablackpikatchu.refinement.common.te.security.SecurityType;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.energy.ModEnergyStorage;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraftforge.common.util.Constants;

public class GrinderTileEntity extends MachineTileEntity implements ITickableTileEntity, ISecurableTile {

	List<ItemStack> allItems = null;
	private ITextComponent customName;
	public static int slots = 7;
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
	private static final int[] SLOTS_FOR_UP = new int[] {
			0
	};
	private static final int[] SLOTS_FOR_DOWN = new int[] {
			1
	};
	private static final int[] SLOTS_FOR_SIDES = new int[] {
			2
	};

	public GrinderTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, slots);
		this.energyStorage = createEnergy();
	}

	private ModEnergyStorage createEnergy() {
		return new ModEnergyStorage(100000, (CommonConfig.GRINDER_DEFAULT_ENERGY_USAGE.get()
				+ CommonConfig.GRINDER_ENERGY_USAGE_PER_SPEED_UPGRADE.get() * 8) / 4 * 5, 0) {
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

	public GrinderTileEntity() {
		this(TileEntityTypesInit.GRINDER_TILE_ENTITY_TYPE.get());
	}

	@Override
	public void tick() {
		if (!this.level.isClientSide()) {
			handleEnergyAbilityUpgrade(6);
			//handleAutoEject(4, 1);
			handleNewAutoEject(4, 1);
			
			if (!usingEnergy)
				handleFuelAutoImport(5, 2);

			handleAutoImport(RecipeInit.GRINDER_RECIPE, 5, 0);

			if (!usingEnergy)
				handleSpeedUpgrades(3, CommonConfig.GRINDER_DEFAULT_PROCESS_TIME.get(),
						CommonConfig.GRINDER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE.get());
			else
				handleEnergySpeedUpgrades(3, CommonConfig.GRINDER_DEFAULT_PROCESS_TIME.get(),
						CommonConfig.GRINDER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE.get(), energyStorage,
						CommonConfig.GRINDER_DEFAULT_ENERGY_USAGE.get(),
						CommonConfig.GRINDER_ENERGY_USAGE_PER_SPEED_UPGRADE.get());

			this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(GrinderBlock.LIT, false));
			if (getRecipe(this.getItem(0)) != null) {
				IRecipe<?> recipe = getRecipe(this.getItem(0));
				final GrinderRecipe grinderRecipe = (GrinderRecipe) recipe;
				if (canWork()) {
					if (this.currentWaitTime >= this.maxWaitTime) {
						TileEntityHelper.updateTE(this);
						if (TileEntityHelper.canPlaceItemInStack(this.getItem(1), recipe.getResultItem())) {
							consumeFuel();
							grinderRecipe.finishRecipe(iInventory);
							this.currentWaitTime = 0;
							TileEntityHelper.updateTE(this);
						}
					} else {
						this.currentWaitTime++;
						if (usingEnergy)
							energyStorage.useEnergy();
						this.setChanged();
						this.level.setBlockAndUpdate(this.getBlockPos(),
								this.getBlockState().setValue(GrinderBlock.LIT, true));
					}
				}
			} else
				regressProgress();
		}
	}

	@Nullable
	private GrinderRecipe getRecipe(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}

		Set<IRecipe<?>> recipes = findRecipesByType(RecipeInit.GRINDER_RECIPE, this.level);
		for (IRecipe<?> iRecipe : recipes) {
			GrinderRecipe recipe = (GrinderRecipe) iRecipe;
			if (recipe.matches(this, this.level)) {
				return recipe;
			}
		}

		return null;
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

	@Override
	public void setCustomName(ITextComponent name) {
		this.customName = name;
	}

	@Override
	public ITextComponent getName() {
		return this.customName != null ? this.customName : this.getDefaultName();
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	@Override
	@Nullable
	public ITextComponent getCustomName() {
		return this.customName;
	}

	@Override
	protected Container createMenu(final int windowID, final PlayerInventory playerInv) {
		return new GrinderContainer(windowID, playerInv, this);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		if (this.customName != null) {
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
		}
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		if (nbt.contains("CustomName", Constants.NBT.TAG_STRING)) {
			this.customName = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
		}
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.DOWN)
			return SLOTS_FOR_DOWN;
		else if (side == Direction.UP)
			return SLOTS_FOR_UP;
		else
			return SLOTS_FOR_SIDES;
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction side) {
		if (index == 1)
			return false;
		else if (side == Direction.DOWN && index == 0)
			return false;
		else if (index == 2) {
			if (stack.getItem() == ItemInit.REFINED_CARBON_INGOT.get())
				return true;
			else
				return false;
		} else if (index == 3)
			return false;
		else
			return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction side) {
		if (index != 1)
			return false;
		else
			return true;
	}

	@Override
	public SecurityType getSecurity() {
		return this.security;
	}

	@Override
	public void setSecurity(SecurityType security) {
		this.security = security;
		TileEntityHelper.updateTE(this);
	}

	@Override
	public UUID getOwnerUUID() {
		return this.ownerUUID;
	}

	@Override
	public int getFuelSlot() {
		return 2;
	}

}

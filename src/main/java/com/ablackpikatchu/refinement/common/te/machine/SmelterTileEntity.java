package com.ablackpikatchu.refinement.common.te.machine;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.api.te.SidedInventoryTileEntity;
import com.ablackpikatchu.refinement.common.block.machine.SmelterBlock;
import com.ablackpikatchu.refinement.common.container.SmelterContainer;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.energy.ModEnergyStorage;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class SmelterTileEntity extends SidedInventoryTileEntity {

	public final ModEnergyStorage energyStorage = createEnergy();
	public final static int slots = 5;
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

	private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);
	
	private boolean hasPower = false;

	public SmelterTileEntity(TileEntityType<?> p_i48285_1_) {
		super(p_i48285_1_, slots);
	}

	public SmelterTileEntity() {
		this(TileEntityTypesInit.SMELTER_TILE_ENTITY_TYPE.get());
	}

	private boolean hasEnoughPowerToWork() {
		return energyStorage.getEnergyStored() >= energyStorage.energyUsed;
	}

	private ModEnergyStorage createEnergy() {
		return new ModEnergyStorage(50000, (CommonConfig.SMELTER_DEFAULT_ENERGY_USAGE.get()
                + CommonConfig.SMELTER_ENERGY_USAGE_PER_SPEED_UPGRADE.get() * 8) / 4 * 5) {
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
	public void serverTick() {
		TileEntityHelper.setStateProperty(this, SmelterBlock.LIT, false);
		handleAutoEject(3, 1);
		handleAutoImport(null, 4, 0);
		handleEnergySpeedUpgrades(2, CommonConfig.SMELTER_DEFAULT_PROCESS_TIME.get(),
                CommonConfig.SMELTER_TIME_DECREASED_BY_EACH_SPEED_UPGRADE.get(), energyStorage,
                CommonConfig.SMELTER_DEFAULT_ENERGY_USAGE.get(),
                CommonConfig.SMELTER_ENERGY_USAGE_PER_SPEED_UPGRADE.get());
		getRecipes(IRecipeType.SMELTING).forEach(recipe -> {
			FurnaceRecipe furnaceRecipe = (FurnaceRecipe) recipe;
			if (hasEnoughPowerToWork()) {
				if (furnaceRecipe.matches(this.iInventory, this.level)) {
					if (progressComplete()) {
						if (TileEntityHelper.canPlaceItemInStack(this.getItem(1), recipe.getResultItem())) {
							int oldCount = 0;
							if (this.getItem(1) != ItemStack.EMPTY)
								oldCount = this.getItem(1).getCount();
							this.setItem(1, new ItemStack(recipe.getResultItem().getItem(), 1 + oldCount));
							this.getItem(0).shrink(1);
							energyStorage.useEnergy();
							this.currentWaitTime = 0;
							TileEntityHelper.updateTE(this);
						}
					} else {
						this.currentWaitTime++;
						energyStorage.useEnergy();
						TileEntityHelper.setStateProperty(this, SmelterBlock.LIT, true);
					}
				}
			} else
				regressProgress();
		});
		
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + Refinement.MOD_ID + ".smelter");
	}

	@Override
	protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
		return new SmelterContainer(p_213906_1_, p_213906_2_, this);
	}

	@Override
	public void load(BlockState stateIn, CompoundNBT tags) {
		energyStorage.setEnergy(tags.getInt("energyStored"));
		super.load(stateIn, tags);
	}

	@Override
	public CompoundNBT save(CompoundNBT tags) {
		tags.putInt("energyStored", energyStorage.getEnergyStored());
		super.save(tags);
		return tags;
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		energy.invalidate();
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityEnergy.ENERGY) {
			return energy.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public int[] getSlotsForFace(Direction p_180463_1_) {
		return new int[] {
				0
		};
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack p_180462_2_, Direction p_180462_3_) {
		return slot == 0;
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction p_180461_3_) {
		return slot == 1;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> p_199721_1_) {
		this.items = p_199721_1_;
	}

	@Override
	protected int[] getOutputSlots() {
		return new int[] {
				1
		};
	}

}

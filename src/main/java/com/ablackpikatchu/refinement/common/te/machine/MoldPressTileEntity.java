package com.ablackpikatchu.refinement.common.te.machine;

import java.util.List;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.api.te.SidedInventoryTileEntity;
import com.ablackpikatchu.refinement.common.block.machine.MoldPressBlock;
import com.ablackpikatchu.refinement.common.container.MoldPressContainer;
import com.ablackpikatchu.refinement.common.recipe.MoldPressRecipe;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;
import com.ablackpikatchu.refinement.core.util.lists.ItemLists;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraftforge.common.util.Constants;

public class MoldPressTileEntity extends SidedInventoryTileEntity implements ITickableTileEntity {

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
			3
	};

	public MoldPressTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, slots);
	}

	public MoldPressTileEntity() {
		this(TileEntityTypesInit.MOLD_PRESS_TILE_ENTITY_TYPE.get());
	}

	@Override
	public void tick() {
		if (!this.level.isClientSide()) {
			handleSpeedUpgrades(4, CommonConfig.MOLD_PRESS_DEFAULT_PROCESS_TIME.get(),
					CommonConfig.MOLD_PRESS_TIME_DECREASED_BY_EACH_SPEED_UPGRADE.get());
			handleAutoEject(5, 1);
			handleFuelAutoImport(6, 3);
			handleAutoImport(RecipeInit.MOLD_PRESS_RECIPE, 6, 0);
			this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(MoldPressBlock.LIT, false));
			if (getRecipe() != null && this.getItem(3).getItem() == ItemInit.REFINED_CARBON_INGOT.get()
					&& this.getItem(3).getCount() >= this.usedCarbon) {
				final MoldPressRecipe recipe = getRecipe();
				if (this.currentWaitTime >= this.maxWaitTime) {
					TileEntityHelper.updateTE(this);
					if (TileEntityHelper.canPlaceItemInStack(this.getItem(1), recipe.getResultItem())) {
						recipe.consumeInput(iInventory);
						this.getItem(3).shrink(this.usedCarbon);
						int oldCount = 0;
						if (this.getItem(1) != ItemStack.EMPTY)
							oldCount = this.getItem(1).getCount();
						this.setItem(1, new ItemStack(recipe.getResultItem().getItem(),
								recipe.getResultItem().getCount() + oldCount));
						this.currentWaitTime = 0;
						this.level.setBlockAndUpdate(this.getBlockPos(),
								this.getBlockState().setValue(MoldPressBlock.LIT, true));
						TileEntityHelper.updateTE(this);
					}
				} else {
					this.currentWaitTime++;
					this.setChanged();
					this.level.setBlockAndUpdate(this.getBlockPos(),
							this.getBlockState().setValue(MoldPressBlock.LIT, true));
				}
			}
		}
	}

	@Nullable
	protected MoldPressRecipe getRecipe() {
		if (level == null)
			return null;
		return level.getRecipeManager().getRecipeFor(RecipeInit.MOLD_PRESS_RECIPE, this, level).orElse(null);
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
	protected void setItems(NonNullList<ItemStack> p_199721_1_) {
		this.items = p_199721_1_;

	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + Refinement.MOD_ID + ".mold_press");
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
		return new MoldPressContainer(windowID, playerInv, this);
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
		if (side == null)
			return new int[] {0, 3};
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
		else if (index == 3) {
			if (stack.getItem() == ItemInit.REFINED_CARBON_INGOT.get())
				return true;
			else
				return false;
		} else if (index == 4)
			return false;
		else if (index == 2)
			return ItemLists.isMold(stack.getItem());
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

}

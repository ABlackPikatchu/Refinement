package com.ablackpikatchu.refinement.common.te.machine;

import java.util.List;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.GrinderBlock;
import com.ablackpikatchu.refinement.common.container.GrinderContainer;
import com.ablackpikatchu.refinement.common.recipe.GrinderRecipe;
import com.ablackpikatchu.refinement.common.te.LockableSidedInventoryTileEntity;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.RecipeInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
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

public class GrinderTileEntity extends LockableSidedInventoryTileEntity implements ITickableTileEntity {
	List<ItemStack> allItems = null;
	private ITextComponent customName;
	public static int slots = 3;
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
	public int currentWaitTime;
	public int maxWaitTime = CommonConfig.GRINDER_DEFAULT_PROCESS_TIME.get();
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 1 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 2 };

	public GrinderTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, slots);
	}

	public GrinderTileEntity() {
		this(TileEntityTypesInit.GRINDER_TILE_ENTITY_TYPE.get());
	}

	@Override
	public void tick() {
		if (!this.level.isClientSide()) {
			this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(GrinderBlock.LIT, false));
			for (final IRecipe<?> recipe : this.level.getRecipeManager().getAllRecipesFor(RecipeInit.GRINDER_RECIPE)) {
				final GrinderRecipe grinderRecipe = (GrinderRecipe) recipe;
				if (grinderRecipe.isValid(this.getItem(0))
						&& this.getItem(2).getItem() == ItemInit.REFINED_CARBON_INGOT.get()) {
					if (this.currentWaitTime == this.maxWaitTime) {
						TileEntityHelper.updateTE(this);
						if (TileEntityHelper.canPlaceItemInStack(this.getItem(1), recipe.getResultItem())) {
							for (ItemStack recipeInput : grinderRecipe.getIngredients().get(0).getItems()) {
								this.getItem(0).shrink(recipeInput.getCount());
							}
							this.getItem(2).shrink(1);
							int oldCount = 0;
							if (this.getItem(1) != ItemStack.EMPTY)
								oldCount = this.getItem(1).getCount();
							this.setItem(1, new ItemStack(recipe.getResultItem().getItem(),
									recipe.getResultItem().getCount() + oldCount));
							this.currentWaitTime = 0;
							TileEntityHelper.updateTE(this);
						}
					} else {
						this.currentWaitTime++;
						this.setChanged();
						this.level.setBlockAndUpdate(this.getBlockPos(),
								this.getBlockState().setValue(GrinderBlock.LIT, true));
					}
				}
			}
		}
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
		return new GrinderContainer(windowID, playerInv, this);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		if (this.customName != null) {
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
		}
		compound.putInt("CurrentWaitTime", this.currentWaitTime);
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		if (nbt.contains("CustomName", Constants.NBT.TAG_STRING)) {
			this.customName = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
		}
		this.currentWaitTime = nbt.getInt("CurrentWaitTime");
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
		} else
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

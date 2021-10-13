package com.ablackpikatchu.refinement.common.te.misc_tes;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.container.VaccumulatorContainer;
import com.ablackpikatchu.refinement.common.te.SidedInventoryTileEntity;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

public class VaccumulatorTileEntity extends SidedInventoryTileEntity implements ITickableTileEntity {

	List<ItemStack> allItems = null;
	private ITextComponent customName;
	public static int slots = 36;
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

	private final static int[] SLOTS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
			22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35 };

	public VaccumulatorTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, slots);
	}

	public VaccumulatorTileEntity() {
		this(TileEntityTypesInit.VACCUMULATOR_TILE_ENTITY_TYPE.get());
	}

	@Override
	public void tick() {
		if (!this.level.isClientSide()) {
			ArrayList<ItemEntity> rangeItems = (ArrayList<ItemEntity>) this.level.getEntitiesOfClass(ItemEntity.class,
					new AxisAlignedBB(this.worldPosition).inflate(CommonConfig.VACCUMULATOR_RANGE.get()));
			for (ItemEntity itemEntity : rangeItems) {
				ItemStack item = itemEntity.getItem();
				if (this.hasRoomForOutputItem(item)) {
					this.storeResultItem(item);
					itemEntity.kill();
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
		return SLOTS;
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
		return new TranslationTextComponent("container." + Refinement.MOD_ID + ".vaccumulator");
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
		return new VaccumulatorContainer(windowID, playerInv, this);
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
		return SLOTS;
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction side) {
		return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction side) {
		return true;
	}

}

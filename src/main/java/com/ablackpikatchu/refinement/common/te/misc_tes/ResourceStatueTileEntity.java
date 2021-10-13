package com.ablackpikatchu.refinement.common.te.misc_tes;

import java.util.List;

import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.te.SidedInventoryTileEntity;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.helper.InventoryHelper;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraftforge.registries.ForgeRegistries;

public class ResourceStatueTileEntity extends SidedInventoryTileEntity {

	List<ItemStack> allItems = null;
	private ITextComponent customName;
	public static int slots = 1;
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

	public Item producedItem;
	public int maxProduce;
	public int producedNumber;

	private final static int[] SLOTS = {
			0
	};

	public ResourceStatueTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, slots);
		this.maxWaitTime = CommonConfig.RESOURCE_STATUES_PRODUCE_TIME.get();
	}

	public ResourceStatueTileEntity() {
		this(TileEntityTypesInit.RESOURCE_STATUE_TILE_ENTITY_TYPE.get());
	}

	public void setProducedItem(Item item) {
		this.producedItem = item;
	}

	public void setMaxProduce(int maxProduce) {
		this.maxProduce = maxProduce;
	}

	public void setProduced(int produced) {
		this.producedNumber = produced;
	}

	@Override
	public void serverTick() {
		handleAutoEject();
		if (this.producedItem == Items.AIR)
			return;
		if (this.producedNumber >= this.maxProduce)
			return;
		if (progressComplete()) {
			this.storeResultItem(new ItemStack(this.producedItem));
			this.producedNumber++;
			this.currentWaitTime = 0;
		} else
			advanceProgress();
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = super.getUpdateTag();
		nbt.putString("producedItem", this.producedItem.getRegistryName().toString());
		return nbt;
	}
	
	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
		load(state, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
		super.onDataPacket(net, packet);
		CompoundNBT tag = packet.getTag();
		handleUpdateTag(getBlockState(), tag);
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
		return new TranslationTextComponent("container." + Refinement.MOD_ID + ".resource_statue");
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
		return null;
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		compound.putString("producedItem", this.producedItem.getRegistryName().toString());
		compound.putInt("maxProduce", this.maxProduce);
		compound.putInt("produced", this.producedNumber);
		super.save(compound);
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		this.producedItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(nbt.getString("producedItem")));
		this.maxProduce = nbt.getInt("maxProduce");
		this.producedNumber = nbt.getInt("produced");
		super.load(state, nbt);
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

	public void handleAutoEject() {
		Direction direction = Direction.DOWN;
		if (HopperTileEntity.getContainerAt(level, worldPosition.relative(direction, 1)) != null) {
			if (!this.getItem(0).isEmpty()) {
				IInventory autoEjectContainer = HopperTileEntity.getContainerAt(level,
						worldPosition.relative(direction, 1));
				ItemStack output = this.getItem(0);
				for (int i = 0; i <= autoEjectContainer.getContainerSize() - 1; i++) {
					if (TileEntityHelper.canPlaceItemInStack(autoEjectContainer.getItem(i), output)) {
						InventoryHelper.tryMoveInItem((IInventory) this, autoEjectContainer, output, i,
								direction.getOpposite());
						TileEntityHelper.updateTE(this);
						TileEntityHelper.updateTE(this.level.getBlockEntity(worldPosition.relative(direction, 1)));
						break;
					}
				}
			}
		}
	}

}

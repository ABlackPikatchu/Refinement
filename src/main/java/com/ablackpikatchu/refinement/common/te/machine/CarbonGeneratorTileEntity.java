package com.ablackpikatchu.refinement.common.te.machine;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.machine.CarbonGeneratorBlock;
import com.ablackpikatchu.refinement.common.block.machine.SmelterBlock;
import com.ablackpikatchu.refinement.common.container.CarbonGeneratorContainer;
import com.ablackpikatchu.refinement.common.te.LockableSidedInventoryTileEntity;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.energy.ModEnergyStorage;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class CarbonGeneratorTileEntity extends LockableSidedInventoryTileEntity implements ITickableTileEntity {

    public final ModEnergyStorage energyStorage = createEnergy();
    public final static int slots = 3;
    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private boolean hasPower = false;

    public CarbonGeneratorTileEntity(TileEntityType<?> p_i48285_1_) {
        super(p_i48285_1_, slots);
    }

    public CarbonGeneratorTileEntity() {
        this(TileEntityTypesInit.CARBON_GENERATOR_TILE_ENTITY_TYPE.get());
    }

    private boolean hasEnoughPowerToWork() {
        return energyStorage.getEnergyStored() >= energyStorage.energyUsed;
    }

    private ModEnergyStorage createEnergy() {
        return new ModEnergyStorage(100000, (CommonConfig.CARBON_GENERATOR_DEFAULT_ENERGY_USAGE.get()
                + CommonConfig.CARBON_GENERATOR_ENERGY_USAGE_PER_SPEED_UPGRADE.get() * 8) / 4 * 5) {
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
    public void tick() {
        if (!this.level.isClientSide()) {
        	TileEntityHelper.setStateProperty(this, SmelterBlock.LIT, false);
            handleAutoEject(2, 0);
            handleEnergySpeedUpgrades(1, CommonConfig.CARBON_GENERATOR_DEFAULT_PROCESS_TIME.get(),
                    CommonConfig.CARBON_GENERATOR_TIME_DECREASED_BY_EACH_SPEED_UPGRADE.get(), energyStorage,
                    CommonConfig.CARBON_GENERATOR_DEFAULT_ENERGY_USAGE.get(),
                    CommonConfig.CARBON_GENERATOR_ENERGY_USAGE_PER_SPEED_UPGRADE.get());
            if (hasEnoughPowerToWork()) {
                if (this.currentWaitTime >= this.maxWaitTime) {
                    if (TileEntityHelper.canPlaceItemInStack(this.getItem(0),
                            new ItemStack(ItemInit.REFINED_CARBON_INGOT.get()))) {
                        int oldCount = 0;
                        if (this.getItem(0) != ItemStack.EMPTY)
                            oldCount = this.getItem(0).getCount();
                        this.setItem(0, new ItemStack(ItemInit.REFINED_CARBON_INGOT.get(), 1 + oldCount));
                        this.currentWaitTime = 0;
                        TileEntityHelper.updateTE(this);
                    }
                } else {
                    currentWaitTime++;
                    this.energyStorage.useEnergy();
                    TileEntityHelper.setStateProperty(this, CarbonGeneratorBlock.LIT, true);
                }
            } else regressProgress();
        }
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Refinement.MOD_ID + ".carbon_generator");
    }

    @Override
    protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
        return new CarbonGeneratorContainer(p_213906_1_, p_213906_2_, this);
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
        return new int[]{
                0
        };
    }

    @Override
    public boolean canPlaceItemThroughFace(int p_180462_1_, ItemStack p_180462_2_, Direction p_180462_3_) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction p_180461_3_) {
        return slot == 0;
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
    public SUpdateTileEntityPacket getUpdatePacket() {
        return super.getUpdatePacket();
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        super.onDataPacket(net, packet);
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[]{
                0
        };
    }

}

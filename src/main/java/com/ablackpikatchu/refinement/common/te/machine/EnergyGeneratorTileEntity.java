package com.ablackpikatchu.refinement.common.te.machine;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.machine.EnergyGeneratorBlock;
import com.ablackpikatchu.refinement.common.container.EnergyGeneratorContainer;
import com.ablackpikatchu.refinement.common.te.SidedInventoryTileEntity;
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
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnergyGeneratorTileEntity extends SidedInventoryTileEntity {

    public final ModEnergyStorage energyStorage = createEnergy();
    public final static int slots = 2;
    public int maxWaitTime = 200;
    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private boolean hasPower = false;

    public EnergyGeneratorTileEntity(TileEntityType<?> p_i48285_1_) {
        super(p_i48285_1_, slots);
    }

    public EnergyGeneratorTileEntity() {
        this(TileEntityTypesInit.ENERGY_GENERATOR_TILE_ENTITY_TYPE.get());
    }

    private boolean hasEnoughPowerToWork() {
        return energyStorage.getEnergyStored() >= energyStorage.energyUsed;
    }

    private ModEnergyStorage createEnergy() {
        return new ModEnergyStorage(100000, 0, CommonConfig.ENERGY_GENERATOR_ENERGY_MADE.get() * 3) {
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
    	TileEntityHelper.setStateProperty(this, EnergyGeneratorBlock.LIT, false);
        handleNewAutoImport(null, 1, 0);
        energyStorage.energyMade = CommonConfig.ENERGY_GENERATOR_ENERGY_MADE.get();
        this.maxWaitTime = CommonConfig.ENERGY_GENERATOR_FUEL_LASTING.get();
        if (this.getItem(0).getItem() == ItemInit.REFINED_CARBON_INGOT.get() && energyStorage.canMakeEnergy()) {
            if (this.currentWaitTime >= this.maxWaitTime) {
                this.getItem(0).shrink(1);
                this.currentWaitTime = 0;
            } else {
            	TileEntityHelper.setStateProperty(this, EnergyGeneratorBlock.LIT, true);
                advanceProgress();
                energyStorage.makeEnergy();
            }
        }
        sendOutPower(energyStorage, Direction.values());
        super.serverTick();
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Refinement.MOD_ID + ".energy_generator");
    }

    @Override
    protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
        return new EnergyGeneratorContainer(p_213906_1_, p_213906_2_, this);
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
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction p_180462_3_) {
        if (slot == 0) return stack.getItem() == ItemInit.REFINED_CARBON_INGOT.get();
        else return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack p_180461_2_, Direction p_180461_3_) {
        return false;
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

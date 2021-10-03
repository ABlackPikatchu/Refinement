package com.ablackpikatchu.refinement.common.container;

import com.ablackpikatchu.refinement.common.slot.OutputSlot;
import com.ablackpikatchu.refinement.common.slot.itemspecific.UpgradeSlot;
import com.ablackpikatchu.refinement.common.te.machine.EnergyGeneratorTileEntity;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.ContainerTypesInit;
import com.ablackpikatchu.refinement.core.util.FunctionalIntReferenceHolder;
import com.ablackpikatchu.refinement.core.util.enums.Upgrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

public class EnergyGeneratorContainer extends Container {

    public final EnergyGeneratorTileEntity te;
    private final IWorldPosCallable canInteractWithCallable;
    public FunctionalIntReferenceHolder currentEnergy;
    public FunctionalIntReferenceHolder currentWaitTime;
    public FunctionalIntReferenceHolder maxWaitTime;
    public FunctionalIntReferenceHolder energyMade;

    public EnergyGeneratorContainer(final int windowId, final PlayerInventory playerInv,
                                    final EnergyGeneratorTileEntity te) {
        super(ContainerTypesInit.ENERGY_GENERATOR_CONTAINER_TYPE.get(), windowId);
        this.te = te;
        this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

        // Tile Entity
        this.addSlot(new Slot((IInventory) te, 0, 26, 35));
        this.addSlot(new UpgradeSlot(te, 1, 197, 102, Upgrades.AUTO_IMPORT)); // Auto Eject Upgrade

        // Main Player Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // Player Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }

        this.addDataSlot(currentEnergy = new FunctionalIntReferenceHolder(() -> this.te.energyStorage.getEnergyStored(),
                value -> this.te.energyStorage.setEnergy(value)));
        this.addDataSlot(currentWaitTime = new FunctionalIntReferenceHolder(() -> this.te.currentWaitTime,
                value -> this.te.currentWaitTime = value));
        this.addDataSlot(maxWaitTime = new FunctionalIntReferenceHolder(() -> this.te.maxWaitTime,
                value -> this.te.maxWaitTime = value));
        this.addDataSlot(energyMade = new FunctionalIntReferenceHolder(() -> this.te.energyStorage.energyMade,
                value -> this.te.energyStorage.energyMade = value));
    }

    public EnergyGeneratorContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }

    private static EnergyGeneratorTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
        Objects.requireNonNull(data, "Packet Buffer cannot be null.");
        final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
        if (te instanceof EnergyGeneratorTileEntity) {
            return (EnergyGeneratorTileEntity) te;
        }
        throw new IllegalStateException("Tile Entity Is Not Correct");
    }

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_) {
        return stillValid(canInteractWithCallable, p_75145_1_, BlockInit.ENERGY_GENERATOR_BLOCK.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < EnergyGeneratorTileEntity.slots
                    && !this.moveItemStackTo(stack1, EnergyGeneratorTileEntity.slots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, EnergyGeneratorTileEntity.slots, false)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return stack;
    }

    @OnlyIn(Dist.CLIENT)
    public int getEnergyScaled() {
        return this.currentEnergy.get() != 0 && this.te.energyStorage.getMaxEnergyStored() != 0
                ? this.currentEnergy.get() * 76 / this.te.energyStorage.getMaxEnergyStored()
                : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getProgressionScaled() {
        return this.currentWaitTime.get() != 0 && this.maxWaitTime.get() != 0
                ? this.currentWaitTime.get() * 77 / this.maxWaitTime.get()
                : 0;
    }

}

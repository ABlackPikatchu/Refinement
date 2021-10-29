package com.ablackpikatchu.refinement.common.te.misc_tes;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.EnergyTransmitterBlock;
import com.ablackpikatchu.refinement.common.container.EnergyTransmitterContainer;
import com.ablackpikatchu.refinement.common.inventory.IItemHandlerInventory;
import com.ablackpikatchu.refinement.core.config.CommonConfig;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.energy.ModEnergyStorage;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;
import com.ablackpikatchu.refinement.core.util.helper.WorldHelper;
import com.google.common.collect.Lists;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.ItemStackHandler;

public class EnergyTransmitterTileEntity extends TileEntity implements ITickableTileEntity,
		IItemHandlerInventory<EnergyTransmitterTileEntity.ItemHandler>, INamedContainerProvider {

	public final ModEnergyStorage energyStorage = createEnergy();
	private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

	private final ItemHandler itemHandler = createHandler();

	private boolean isTransferring;

	public float redParticle;
	public float greenParticle;
	public float blueParticle;
	public float alphaParticle;

	public EnergyTransmitterTileEntity() {
		super(TileEntityTypesInit.ENERGY_TRANSMITTER_TILE_ENTITY_TYPE.get());
	}

	public boolean isTransferring() { return isTransferring; }

	private ItemHandler createHandler() {
		return new ItemHandler(9) {

			@Override
			protected void onContentsChanged(int slot) {
				TileEntityHelper.updateTE(EnergyTransmitterTileEntity.this);
				super.onContentsChanged(slot);
			}

			@Override
			public boolean isItemValid(int slot, ItemStack stack) {
				return stack.getItem() == ItemInit.TRANSMITTER_CARD;
			}
		};
	}

	private ModEnergyStorage createEnergy() {
		return new ModEnergyStorage(1000000, 10000) {

			@Override
			protected void onEnergyChanged() {
				TileEntityHelper.updateTE(EnergyTransmitterTileEntity.this);
				super.onEnergyChanged();
			}
		};
	}

	public void resetParticleColour() {
		Random rand = new Random();
		alphaParticle = rand.nextFloat() * 255;
		redParticle = rand.nextFloat() * 255;
		greenParticle = rand.nextFloat() * 255;
		blueParticle = rand.nextFloat() * 255;
		setChanged();
	}

	@Override
	public CompoundNBT save(CompoundNBT pCompound) {
		pCompound.put("energy", energyStorage.serializeNBT());
		pCompound.put("inv", itemHandler.serializeNBT());

		CompoundNBT particleData = new CompoundNBT();

		particleData.putFloat("red", redParticle);
		particleData.putFloat("green", greenParticle);
		particleData.putFloat("blue", blueParticle);
		particleData.putFloat("alpha", alphaParticle);

		pCompound.put("particleData", particleData);

		return super.save(pCompound);
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		energyStorage.deserializeNBT(nbt.getCompound("energy"));
		itemHandler.deserializeNBT(nbt.getCompound("inv"));

		CompoundNBT particleData = nbt.getCompound("particleData");
		redParticle = particleData.getFloat("red");
		greenParticle = particleData.getFloat("green");
		blueParticle = particleData.getFloat("blue");
		alphaParticle = particleData.getFloat("alpha");
		super.load(state, nbt);
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT tags = new CompoundNBT();
		save(tags);
		return new SUpdateTileEntityPacket(getBlockPos(), -1, tags);
	}

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = super.getUpdateTag();
		nbt.put("inv", itemHandler.serializeNBT());
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
	public void tick() {
		if (!level.isClientSide()) {
			serverTick();
		} else {
			clientTick();
		}
	}

	public boolean hasEnoughPowerToWork() {
		return energyStorage.getEnergyStored() >= energyStorage.energyUsed;
	}

	public List<BlockPos> getTargets() {
		List<BlockPos> targets = new LinkedList<>();
		for (int i = 0; i <= itemHandler.getSlots() - 1; i++) {
			ItemStack stack = itemHandler.getStackInSlot(i);
			BlockPos tgtPos = NBTHelper.getBlockPos(stack, "linkedPos");
			if (tgtPos != null && WorldHelper.getTileEntity(level, tgtPos) != null
					&& WorldHelper.getTileEntity(level, tgtPos) instanceof EnergyReceiverTileEntity) {
				targets.add(tgtPos);
			}
		}
		return targets;
	}

	public void serverTick() {
		isTransferring = false;
		energyStorage.energyUsed = itemHandler.fullSlots()
				* CommonConfig.ENERGY_TRANSMITTER_ENERGY_USED_PER_OPERATION.get();
		for (int i = 0; i <= itemHandler.getSlots() - 1; i++) {
			ItemStack stack = itemHandler.getStackInSlot(i);
			BlockPos tgtPos = NBTHelper.getBlockPos(stack, "linkedPos");
			if (tgtPos != null && WorldHelper.getTileEntity(level, tgtPos) instanceof EnergyReceiverTileEntity) {
				TileEntity tgt = WorldHelper.getTileEntity(level, tgtPos);
				tgt.getCapability(CapabilityEnergy.ENERGY).ifPresent(tgtEnergy -> {
					if (tgtEnergy.canReceive() && tgtEnergy.receiveEnergy(energyStorage.getMaxExtract(), true) != 0
							&& hasEnoughPowerToWork()) {
						energyStorage.extractEnergy(tgtEnergy.receiveEnergy(energyStorage.getMaxExtract(), false),
								false);
						energyStorage.useEnergy();
						isTransferring = true;
					}
				});
			}
		}
	}
	
	public void clientTick() {
		isTransferring = false;
		for (int i = 0; i <= itemHandler.getSlots() - 1; i++) {
			ItemStack stack = itemHandler.getStackInSlot(i);
			BlockPos tgtPos = NBTHelper.getBlockPos(stack, "linkedPos");
			if (tgtPos != null && WorldHelper.getTileEntity(level, tgtPos) instanceof EnergyReceiverTileEntity) {
				TileEntity tgt = WorldHelper.getTileEntity(level, tgtPos);
				tgt.getCapability(CapabilityEnergy.ENERGY).ifPresent(tgtEnergy -> {
					isTransferring = tgtEnergy.canReceive() && (tgtEnergy.receiveEnergy(energyStorage.getMaxExtract(), true) != 0);
				});
			}
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		List<Direction> acceptedSides = Lists.newArrayList(null,
				level.getBlockState(worldPosition).getValue(EnergyTransmitterBlock.FACING).getOpposite());
		if (cap == CapabilityEnergy.ENERGY && acceptedSides.contains(side))
			return energy.cast();
		return super.getCapability(cap, side);
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player) {
		return new EnergyTransmitterContainer(windowId, playerInv, this);
	}

	@Override
	public ItemHandler getItemHandler() { return itemHandler; }

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container." + Refinement.MOD_ID + ".energy_transmitter");
	}

	public static class ItemHandler extends ItemStackHandler {

		public ItemHandler(int i) {
			super(i);
		}

		public int fullSlots() {
			int s = 0;
			for (int i = 0; i <= getSlots() - 1; i++) {
				if (!getStackInSlot(i).isEmpty()) {
					s++;
				}
			}
			return s;
		}

	}

}

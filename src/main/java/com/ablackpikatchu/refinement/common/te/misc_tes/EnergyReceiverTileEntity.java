package com.ablackpikatchu.refinement.common.te.misc_tes;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ablackpikatchu.refinement.common.block.EnergyReceiverBlock;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.energy.ModEnergyStorage;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;
import com.google.common.collect.Lists;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyReceiverTileEntity extends TileEntity
implements ITickableTileEntity {

	public final ModEnergyStorage energyStorage = createEnergy();
	private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);


	public EnergyReceiverTileEntity() {
		super(TileEntityTypesInit.ENERGY_RECEIVER_TILE_ENTITY_TYPE.get());
	}
	private ModEnergyStorage createEnergy() {
		return new ModEnergyStorage(1000000, 10000) {
			@Override
			protected void onEnergyChanged() {
				TileEntityHelper.updateTE(EnergyReceiverTileEntity.this);
				super.onEnergyChanged();
			}
		};
	}

	@Override
	public CompoundNBT save(CompoundNBT pCompound) {
		pCompound.put("energy", energyStorage.serializeNBT());
		return super.save(pCompound);
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		energyStorage.deserializeNBT(nbt.getCompound("energy"));
		super.load(state, nbt);
	}

	@Override
	public void tick() {
		if (!level.isClientSide()) {
			serverTick();
		}
	}

	public void serverTick() {
		sendOutPower(level.getBlockState(worldPosition).getValue(EnergyReceiverBlock.FACING).getOpposite());
	}
	
	public void sendOutPower(Direction... directions) {
		AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
		if (capacity.get() > 0) {
			for (Direction direction : directions) {
				TileEntity te = level.getBlockEntity(worldPosition.relative(direction));
				if (te != null) {
					boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite())
							.map(handler -> {
								if (handler.canReceive()) {
									int received = handler.receiveEnergy(
											Math.min(capacity.get(), energyStorage.getMaxExtract()), false);
									capacity.addAndGet(-received);
									energyStorage.consumeEnergy(received);
									TileEntityHelper.updateTE(this);
									TileEntityHelper.updateTE(te);
									return capacity.get() > 0;
								} else
									return true;
							}).orElse(true);
					if (!doContinue)
						return;
				}
			}
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		List<Direction> acceptedSides = Lists.newArrayList(null, level.getBlockState(worldPosition).getValue(EnergyReceiverBlock.FACING).getOpposite());
		if (cap == CapabilityEnergy.ENERGY && acceptedSides.contains(side))
			return energy.cast();
		return super.getCapability(cap, side);
	}

}

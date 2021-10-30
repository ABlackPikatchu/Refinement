package com.ablackpikatchu.refinement.common.energy_cable;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.LongArrayNBT;
import net.minecraft.util.math.BlockPos;

public class EnergyCableNetwork {

	public final String id;
	private BlockPos originPos;
	private List<BlockPos> otherCables = new LinkedList<>();

	public EnergyCableNetwork(String id, BlockPos originPos) {
		this.id = id;
		this.originPos = originPos;
	}

	public String getId() { return this.id; }

	public void setOriginPos(BlockPos originPos) { this.originPos = originPos; }

	public void addCable(BlockPos pos) {
		this.otherCables.add(pos);
	}

	public CompoundNBT writeToNbt(CompoundNBT tag) {
		tag.putString("id", id);
		tag.putLong("origin", originPos.asLong());

		List<Long> otherCablesLong = new LinkedList<>();
		otherCables.forEach(cable -> otherCablesLong.add(cable.asLong()));
		tag.put("otherCables", new LongArrayNBT(otherCablesLong));

		return tag;
	}

	public static EnergyCableNetwork readFromNbt(CompoundNBT tag) {
		String id = tag.getString("id");
		long originPos = tag.getLong("origin");
		EnergyCableNetwork network = new EnergyCableNetwork(id, BlockPos.of(originPos));

		if (tag.contains("otherCables")) {
			for (long cablePos : tag.getLongArray("otherCables")) {
				network.addCable(BlockPos.of(cablePos));
			}
		}

		return network;
	}

}

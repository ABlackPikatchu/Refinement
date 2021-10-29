package com.ablackpikatchu.refinement.core.network.message.to_server;

import com.ablackpikatchu.refinement.api.network.message.IRefinementMessage;
import com.ablackpikatchu.refinement.common.inventory.StorageBinHandler;
import com.ablackpikatchu.refinement.common.item.blockitem.StorageBinBlockItem;
import com.ablackpikatchu.refinement.common.te.misc_tes.StorageBinTileEntity;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.ablackpikatchu.refinement.core.util.helper.PlayerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

import net.minecraftforge.fml.network.NetworkEvent.Context;

public class ToggleBinAutoRefillMessage implements IRefinementMessage {

	public int playerSlot;

	public ToggleBinAutoRefillMessage(int playerSlot) {
		this.playerSlot = playerSlot;
	}

	public static ToggleBinAutoRefillMessage decode(PacketBuffer buffer) {
		return new ToggleBinAutoRefillMessage(buffer.readInt());
	}

	@Override
	public void handle(Context context) {
		if (context.getSender() != null) {
			ItemStack stack = context.getSender().inventory.getItem(playerSlot);
			if (!stack.hasTag() && stack.getItem() instanceof StorageBinBlockItem) {
				StorageBinHandler newHandler = new StorageBinHandler(((StorageBinBlockItem) stack.getItem()).getStackLimit());
				StorageBinTileEntity.handlerToNbt(newHandler, stack.getOrCreateTag());
			}
			NBTHelper.flipBoolean(stack, "RefillEnabled");
			context.getSender().inventory.setChanged();
			PlayerHelper.updatePlayerInventory(context.getSender());
		}
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeInt(playerSlot);
	}

}

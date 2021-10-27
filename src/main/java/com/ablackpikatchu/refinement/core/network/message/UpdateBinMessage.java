package com.ablackpikatchu.refinement.core.network.message;

import com.ablackpikatchu.refinement.common.te.misc_tes.StorageBinTileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class UpdateBinMessage implements IRefinementMessage {
	
	private BlockPos pos;
    private ItemStack item;
    private int count;
    private int stackLimit;

    private boolean failed;

    public UpdateBinMessage(BlockPos pos, ItemStack item, int count, int stackLimit) {
        this.pos = pos;
        this.item = item;
        this.count = count;
        this.failed = false;
        this.stackLimit = stackLimit;
    }

    private UpdateBinMessage(boolean failed) {
        this.failed = failed;
    }

    public static UpdateBinMessage decode(PacketBuffer buf) {
        try {
            BlockPos pos = buf.readBlockPos();
            ItemStack item = buf.readItem();
            int count = buf.readInt();
            int stackLimit = buf.readInt();
            return new UpdateBinMessage(pos, item, count, stackLimit);
        }
        catch (IndexOutOfBoundsException e) {
            return new UpdateBinMessage(true);
        }
    }

    @Override
    public void encode(PacketBuffer buf) {
        buf.writeBlockPos(pos);
        buf.writeItem(item);
        buf.writeInt(count);
        buf.writeInt(stackLimit);
    }

    @SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
    private void handleClient(UpdateBinMessage msg, NetworkEvent.Context ctx) {
        if (!msg.failed) {
            World world = Minecraft.getInstance().level;
            if (world != null) {
                TileEntity tileEntity = world.getBlockEntity(msg.pos);
                if (tileEntity instanceof StorageBinTileEntity) {
                    ((StorageBinTileEntity) tileEntity).clientUpdate(msg.item, msg.count, msg.stackLimit);
                }
            }
        }
    }

	@Override
	public void handle(Context ctx) {
		ctx.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleClient(this, ctx)));
    	ctx.setPacketHandled(true);
	}
	
}

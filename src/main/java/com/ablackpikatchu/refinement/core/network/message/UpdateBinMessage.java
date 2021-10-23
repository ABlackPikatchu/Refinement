package com.ablackpikatchu.refinement.core.network.message;

import java.util.function.Supplier;

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

public class UpdateBinMessage {
	
	private BlockPos pos;
    private ItemStack item;
    private int count;
    private int stackLimit;

    private boolean failed;

    public UpdateBinMessage (BlockPos pos, ItemStack item, int count, int stackLimit) {
        this.pos = pos;
        this.item = item;
        this.count = count;
        this.failed = false;
        this.stackLimit = stackLimit;
    }

    private UpdateBinMessage (boolean failed) {
        this.failed = failed;
    }

    public static UpdateBinMessage decode (PacketBuffer buf) {
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

    public static void encode (UpdateBinMessage msg, PacketBuffer buf) {
        buf.writeBlockPos(msg.pos);
        buf.writeItem(msg.item);
        buf.writeInt(msg.count);
        buf.writeInt(msg.stackLimit);
    }

    public static void handle(UpdateBinMessage msg, Supplier<NetworkEvent.Context> ctx) {
    	ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleClient(msg, ctx.get())));
    	ctx.get().setPacketHandled(true);
       
    }

    @SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
    private static void handleClient(UpdateBinMessage msg, NetworkEvent.Context ctx) {
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
	
}

package com.ablackpikatchu.refinement.common.events;

import java.util.List;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.block.crop.IronCrop;
import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.google.common.collect.Lists;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Refinement.MOD_ID, bus = Bus.MOD)
public class PlayerEvents {

	/**
	@SuppressWarnings("static-access")
	public static void onInteract(PlayerInteractEvent.RightClickBlock event) {
		
		World level = event.getEntity().level;
		BlockPos pos = event.getPos();
		BlockState block = level.getBlockState(pos);
		BlockState state = block.getBlockState();

		if (block.getBlock() == BlockInit.IRON_CROP.get() && block.getValue(IronCrop.AGE) == 7) {
			List<ItemStack> drops = Lists.newArrayList();
			if (level instanceof ServerWorld)
				block.getBlock().getDrops(drops, level, pos, state, 0);;
			for (ItemStack drop : drops) {
				ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getX(), drop);
				level.addFreshEntity(item);
			}
		}
	}
	*/

}

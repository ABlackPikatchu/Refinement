package com.ablackpikatchu.refinement.api.event;

import com.ablackpikatchu.refinement.common.te.tier.ITieredTile;
import com.ablackpikatchu.refinement.core.util.helper.WorldHelper;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * Called before an {@link ITieredTile} changes its tier! If the event is
 * cancelled the tier is not changed!
 * 
 * @author matyrobbrt
 *
 */
@Cancelable
public class TileTierChangeEvent extends RefinementEvent {

	private final World level;
	private final BlockPos pos;

	private TileTierChangeEvent(World level, BlockPos pos) {
		this.level = level;
		this.pos = pos;
	}

	public BlockPos getTilePosition() { return this.pos; }

	public World getLevel() { return this.level; }

	public ITieredTile getTieredTileEntity() {
		TileEntity tile = WorldHelper.getTileEntity(level, pos);
		if (tile == null) { return null; }
		if (tile instanceof ITieredTile) { return (ITieredTile) tile; }
		return null;
	}

	@Override
	public boolean isCancelable() { return true; }
	
	/**
	 * If the return is true, the event was cancelled
	 * @param vacuumulator
	 * @param item
	 * @return
	 */
	public static boolean onTileTierChange(World level, BlockPos pos) {
		return MinecraftForge.EVENT_BUS.post(new TileTierChangeEvent(level, pos));
	}

}

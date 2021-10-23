package com.ablackpikatchu.refinement.core.util.helper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class WorldHelper {
	
	private WorldHelper() {
	}

	/**
     * Gets a tile entity if the location is loaded
     *
     * @param clazz        Class type of the TileEntity we expect to be in the position
     * @param world        world
     * @param pos          position
     * @param logWrongType Whether or not an error should be logged if a tile of a different type is found at the position
     *
     * @return tile entity if found, null if either not found or not loaded, or of the wrong type
     */
	@Nullable
    public static <T extends TileEntity> T getTileEntity(@Nonnull Class<T> clazz, @Nullable IBlockReader world, @Nonnull BlockPos pos, boolean logWrongType) {
        TileEntity tile = getTileEntity(world, pos);
        if (tile == null) {
            return null;
        }
        if (clazz.isInstance(tile)) {
            return clazz.cast(tile);
        } else if (logWrongType) {
           Refinement.LOGGER.warn("Unexpected TileEntity class at {}, expected {}, but found: {}", pos, clazz, tile.getClass());
        }
        return null;
    }
	
	/**
     * Gets a tile entity if the location is loaded
     *
     * @param world world
     * @param pos   position
     *
     * @return tile entity if found, null if either not found or not loaded
     */
	@Nullable
	public static TileEntity getTileEntity(@Nullable IBlockReader world, @Nonnull BlockPos pos) {
        if (!isBlockLoaded(world, pos)) {
            return null;
        }
        return world.getBlockEntity(pos);
    }
	
	 /**
     * Checks if a position is in bounds of the world, and is loaded
     *
     * @param world world
     * @param pos   position
     *
     * @return True if the position is loaded or the given world is of a superclass of IWorldReader that does not have a concept of being loaded.
     */
	@SuppressWarnings("deprecation")
	public static boolean isBlockLoaded(@Nullable IBlockReader world, @Nonnull BlockPos pos) {
        if (world == null || !World.isInWorldBounds(pos)) {
            return false;
        } else if (world instanceof IWorldReader) {
            return ((IWorldReader) world).hasChunkAt(pos);
        }
        return true;
    }
	
}

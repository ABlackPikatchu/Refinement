package com.ablackpikatchu.refinement.common.block;

import com.ablackpikatchu.refinement.common.item.ResourceStatueItem;
import com.ablackpikatchu.refinement.common.te.misc_tes.ResourceStatueTileEntity;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;
import com.ablackpikatchu.refinement.core.util.helper.TileEntityHelper;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ResourceStatueBlock extends Block {

	public ResourceStatueBlock() {
		super(AbstractBlock.Properties.copy(Blocks.IRON_DOOR));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.RESOURCE_STATUE_TILE_ENTITY_TYPE.get().create();
	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		TileEntity tile = worldIn.getBlockEntity(pos);
		if (tile instanceof ResourceStatueTileEntity) {
			ResourceStatueTileEntity statue = (ResourceStatueTileEntity) tile;
			ItemStack stack = new ItemStack(getBlock());
			
			ResourceStatueItem.setResourceProduced(stack, statue.producedItem.getRegistryName().toString());
			ResourceStatueItem.setMaxProduce(stack, statue.maxProduce);
			ResourceStatueItem.setProduced(stack, statue.producedNumber);
			
			ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
            itemEntity.setDefaultPickUpDelay();
            worldIn.addFreshEntity(itemEntity);
		}
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			worldIn.removeBlockEntity(pos);
		}
	}
	
	@Override
	public void setPlacedBy(World pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
		super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
		TileEntity tile = pLevel.getBlockEntity(pPos);
		if (tile instanceof ResourceStatueTileEntity) {
			ResourceStatueTileEntity statue = (ResourceStatueTileEntity) tile;
			statue.setProducedItem(ResourceStatueItem.getResourceProduced(pStack));
			statue.setMaxProduce(ResourceStatueItem.getMaxProduce(pStack));
			statue.setProduced(ResourceStatueItem.getProduced(pStack));
			TileEntityHelper.updateTE(tile);
		}
	}

}

package com.ablackpikatchu.refinement.common.block.machine;

import com.ablackpikatchu.refinement.client.RefinementLang;
import com.ablackpikatchu.refinement.common.te.machine.GrinderTileEntity;
import com.ablackpikatchu.refinement.common.te.security.SecurityType;
import com.ablackpikatchu.refinement.core.init.TileEntityTypesInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import net.minecraftforge.fml.network.NetworkHooks;

public class GrinderBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BooleanProperty.create("lit");

	public GrinderBlock() {
		super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(10f).sound(SoundType.METAL)
				.harvestLevel(4).lightLevel(state -> Boolean.TRUE.equals(state.getValue(LIT)) ? 7 : 0));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, LIT);
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.GRINDER_TILE_ENTITY_TYPE.get().create();
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (!worldIn.isClientSide()) {
			TileEntity te = worldIn.getBlockEntity(pos);
			if (te instanceof GrinderTileEntity) {
				GrinderTileEntity grinder = (GrinderTileEntity) te;
				if (!grinder.isPrivate() || player.isCreative() || player.hasPermissions(2))
					NetworkHooks.openGui((ServerPlayerEntity) player, grinder, pos);
				else {
					if (player.getUUID().equals(grinder.ownerUUID))
						NetworkHooks.openGui((ServerPlayerEntity) player, grinder, pos);
					else
						player.sendMessage(RefinementLang.getComponent("ownership_error"), player.getUUID());
				}
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player) {
		TileEntity tile = world.getBlockEntity(pos);
		if (tile instanceof GrinderTileEntity) {
			GrinderTileEntity grinder = (GrinderTileEntity) tile;
			if (!grinder.isPrivate())
				return true;
			else
				return player.getUUID().equals(grinder.ownerUUID);
		} else {
			return true;
		}
	}
	
	@Override
	public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
		return Container.getRedstoneSignalFromBlockEntity(worldIn.getBlockEntity(pos));
	}

	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getBlockEntity(pos);
		if (tile instanceof GrinderTileEntity) {
			GrinderTileEntity grinder = (GrinderTileEntity) tile;
			if (stack.hasCustomHoverName())
				grinder.setCustomName(stack.getDisplayName());
			grinder.setOwner(placer.getUUID());
			grinder.setSecurity(SecurityType.PUBLIC);
		}
	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			worldIn.removeBlockEntity(pos);
		}
	}

}

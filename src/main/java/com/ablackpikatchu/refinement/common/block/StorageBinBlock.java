package com.ablackpikatchu.refinement.common.block;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.ablackpikatchu.refinement.client.RefinementLang;
import com.ablackpikatchu.refinement.common.inventory.StorageBinHandler;
import com.ablackpikatchu.refinement.common.te.misc_tes.StorageBinTileEntity;
import com.ablackpikatchu.refinement.core.util.helper.PlayerHelper;
import com.ablackpikatchu.refinement.core.util.helper.WorldHelper;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootParameters;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class StorageBinBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public final int stackLimit;

	public StorageBinBlock(int stackLimit) {
		super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(5.5f)
				.sound(SoundType.METAL).harvestLevel(3).noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
		this.stackLimit = stackLimit;
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
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
		return new StorageBinTileEntity(stackLimit);
	}

	@Override
	public void attack(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn) {
		RayTraceResult rayTraceResult = PlayerHelper.getPlayerPOVHitResult(worldIn, playerIn,
				RayTraceContext.FluidMode.ANY);
		if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
			BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) rayTraceResult;
			Direction direction = blockRayTraceResult.getDirection();
			if (!worldIn.isClientSide() && worldIn.getBlockEntity(pos) instanceof StorageBinTileEntity
					&& state.getValue(FACING) == direction) {
				StorageBinTileEntity binTile = (StorageBinTileEntity) worldIn.getBlockEntity(pos);

				ItemStack item;
				if (playerIn.isShiftKeyDown())
					item = binTile.takeItemsFromSlot(0, binTile.getItemStored().getMaxStackSize());
				else
					item = binTile.takeItemsFromSlot(0, 1);

				binTile.setChanged();

				if (!item.isEmpty()) {
					if (!playerIn.inventory.add(item)) {
						dropItemStack(worldIn, pos.relative(direction), playerIn, item);
						worldIn.sendBlockUpdated(pos, state, state, 3);
					} else
						worldIn.playSound(null, pos.getX() + .5f, pos.getY() + .5f, pos.getZ() + .5f,
								SoundEvents.ITEM_PICKUP, SoundCategory.PLAYERS, .2f,
								((worldIn.random.nextFloat() - worldIn.random.nextFloat()) * .7f + 1) * 2);
				}
			}
		}
	}

	@Override
	public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult pHit) {
		if (!level.isClientSide() && !player.isShiftKeyDown()) {
			if (level.getBlockEntity(pos) instanceof StorageBinTileEntity
					&& pHit.getDirection() == state.getValue(FACING)) {
				StorageBinTileEntity binTile = (StorageBinTileEntity) level.getBlockEntity(pos);
				ItemStack item = player.getItemInHand(hand);
				if (hand == Hand.OFF_HAND)
					return ActionResultType.PASS;

				binTile.interactPutItemsIntoSlot(0, player);

				if (item.isEmpty())
					player.setItemInHand(hand, item);

			} else
				return ActionResultType.PASS;
		}

		return ActionResultType.SUCCESS;
	}

	@Override
	public void setPlacedBy(World pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
		if (!pLevel.isClientSide()
				&& WorldHelper.getTileEntity(StorageBinTileEntity.class, pLevel, pPos, false) != null)
			WorldHelper.getTileEntity(StorageBinTileEntity.class, pLevel, pPos, false).loadFromItem(pStack);
	}

	@Override
	public List<ItemStack> getDrops(BlockState pState, net.minecraft.loot.LootContext.Builder pBuilder) {
		List<ItemStack> drops = new ArrayList<>();
		ItemStack stack = new ItemStack(this.asItem());
		if (pBuilder.getParameter(LootParameters.BLOCK_ENTITY) instanceof StorageBinTileEntity) {
			((StorageBinTileEntity) pBuilder.getParameter(LootParameters.BLOCK_ENTITY)).storeDataToItem(stack);
		}
		drops.add(stack);
		return drops;
	}

	public boolean creativeCanBreakBlock(BlockState state, World world, BlockPos pos, PlayerEntity player) {
		RayTraceResult rayTraceResult = PlayerHelper.getPlayerPOVHitResult(world, player,
				RayTraceContext.FluidMode.ANY);
		if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
			BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) rayTraceResult;
			return blockRayTraceResult.getDirection() != state.getValue(FACING);
		}
		return true;
	}

	private void dropItemStack(World world, BlockPos pos, PlayerEntity player, @Nonnull ItemStack stack) {
		ItemEntity entity = new ItemEntity(world, pos.getX() + .5f, pos.getY() + .3f, pos.getZ() + .5f, stack);
		Vector3d motion = entity.getDeltaMovement();
		entity.push(-motion.x, -motion.y, -motion.z);
		world.addFreshEntity(entity);
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
		return Container.getRedstoneSignalFromBlockEntity(worldIn.getBlockEntity(pos));
	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			worldIn.removeBlockEntity(pos);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, IBlockReader pLevel, List<ITextComponent> pTooltip,
			ITooltipFlag pFlag) {
		if (stack.hasTag()) {
			StorageBinHandler binHandler = StorageBinTileEntity.handlerFromNbt(stack.getOrCreateTag());
			ItemStack item = binHandler.getStackInSlot(0);
			int stackLimit = binHandler.getSlotLimit(0);
			int count = binHandler.getStoredItemCount();

			pTooltip.add(new StringTextComponent(RefinementLang.getComponent("stored_item").getString().replace("%i",
					new TranslationTextComponent(item.getDescriptionId()).getString())));
			
			pTooltip.add(new StringTextComponent(RefinementLang.getComponent("count").getString().replace("%c",
					"" + count)));

			pTooltip.add(new StringTextComponent(
					RefinementLang.getComponent("stack_limit").getString().replace("%l", "" + stackLimit)));
		} else {
			pTooltip.add(new StringTextComponent(
					RefinementLang.getComponent("stack_limit").getString().replace("%l", "" + stackLimit)));
		}
		super.appendHoverText(stack, pLevel, pTooltip, pFlag);
	}

}

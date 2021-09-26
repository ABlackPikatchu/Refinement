package com.ablackpikatchu.refinement.resourcecrops.common;

import java.util.Random;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.data.maps.LootTableMaps;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import net.minecraftforge.registries.ForgeRegistries;

/**
 * Class for creating resource crops. For registering their block states,
 * models, and loot tables see {@link LootTableMaps#addCropLoot}
 * 
 * @author matyrobbrt
 *
 */
public class ModCrop extends CropsBlock {

	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] { Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };

	private final ResourceLocation seed;
	private final ResourceLocation resource;
	private final boolean canBonemeal;
	private final ResourceLocation bonemealItem;

	/**
	 * Creates a new resource crop (for registering its block states, models, and
	 * loot tables see {@link LootTableMaps#addCropLoot})
	 * 
	 * @param properties  the properties of the block (usually: <b>
	 *                    AbstractBlock.Properties.copy(Blocks.WHEAT)</b>)
	 * @param seed        the registry name of the seed
	 * @param resource    the registry name of the resource produced
	 * @param canBonemeal if it can be bonemealed using vanilla bonemeal (note:
	 *                    using this method means that the crop will be bonemealable
	 *                    using the {@link ItemInit#REFINED_BONEMEAL} item)
	 */
	public ModCrop(Properties properties, ResourceLocation seed, ResourceLocation resource, boolean canBonemeal) {
		super(properties);
		this.seed = seed;
		this.resource = resource;
		this.canBonemeal = canBonemeal;
		this.bonemealItem = new ResourceLocation(Refinement.MOD_ID, "refined_bonemeal");
	}

	/**
	 * Creates a new resource crop, with the same block properties as
	 * {@link Blocks#WHEAT} (for registering its block states, models, and loot
	 * tables see {@link LootTableMaps#addCropLoot})
	 * 
	 * @param seed        the registry name of the seed
	 * @param resource    the registry name of the resource produced
	 * @param canBonemeal if it can be bonemealed using vanilla bonemeal (note:
	 *                    using this method means that the crop will be bonemealable
	 *                    using the {@link ItemInit#REFINED_BONEMEAL} item)
	 */
	public ModCrop(ResourceLocation seed, ResourceLocation resource, boolean canBonemeal) {
		super(AbstractBlock.Properties.copy(Blocks.WHEAT));
		this.seed = seed;
		this.resource = resource;
		this.canBonemeal = canBonemeal;
		this.bonemealItem = new ResourceLocation(Refinement.MOD_ID, "refined_bonemeal");
	}

	/**
	 * Creates a new resource crop (for registering its block states, models, and
	 * loot tables see {@link LootTableMaps#addCropLoot})
	 * 
	 * @param properties   the properties of the block (usually: <b>
	 *                     AbstractBlock.Properties.copy(Blocks.WHEAT)</b>)
	 * @param seed         the registry name of the seed
	 * @param resource     the registry name of the resource produced
	 * @param canBonemeal  if it can be bonemealed using vanilla bonemeal
	 * @param bonemealItem the registry name of item you can bonemeal the crop with
	 *                     (in order to disable the bonemeal functionality set this
	 *                     field to an unobtainable item like {@link Items#BEDROCK})
	 */
	public ModCrop(Properties properties, ResourceLocation seed, ResourceLocation resource, boolean canBonemeal,
			ResourceLocation bonemealItem) {
		super(properties);
		this.seed = seed;
		this.resource = resource;
		this.canBonemeal = canBonemeal;
		this.bonemealItem = bonemealItem;
	}

	public Item getSeedItem() {
		return ForgeRegistries.ITEMS.getValue(this.seed);
	}

	public Item getResourceItem() {
		return ForgeRegistries.ITEMS.getValue(this.resource);
	}

	public Item getBonemealItem() {
		return ForgeRegistries.ITEMS.getValue(this.bonemealItem);
	}

	@Override
	protected IItemProvider getBaseSeedId() {
		return this.getSeedItem();
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
	}

	@Override
	public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult result) {

		if (this.getAge(state) == 7) {
			ItemStack drops = new ItemStack(this.getResourceItem());
			level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), drops));
			level.setBlockAndUpdate(pos, state.setValue(this.getAgeProperty(), 0));
			return ActionResultType.SUCCESS;
		}

		ItemStack stack = player.getItemInHand(hand);

		if (stack.getItem() == this.getBonemealItem() && level instanceof ServerWorld) {
			this.performBonemeal((ServerWorld) level, new Random(), pos, state);
			stack.shrink(1);
			return ActionResultType.CONSUME;
		}

		return ActionResultType.FAIL;
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_,
			boolean p_176473_4_) {
		return this.canBonemeal;
	}

}

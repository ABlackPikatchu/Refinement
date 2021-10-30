package com.ablackpikatchu.refinement.client.render.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ablackpikatchu.refinement.common.energy_cable.EnergyCableBlock;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.TransformationMatrix;
import net.minecraft.util.math.vector.Vector3f;

import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;
import net.minecraftforge.client.model.pipeline.TRSRTransformer;
import net.minecraftforge.common.model.TransformationHelper;

public class EnergyCableBakedModel implements IBakedModel {

	private static final Map<Direction, TransformationMatrix> SIDE_TRANSFORMS = new EnumMap<>(Direction.class);
	private final IBakedModel core;
	private final IBakedModel extension;
	private final IBakedModel straight;
	private final Map<CableState, List<BakedQuad>> cache = new HashMap<>();

	public EnergyCableBakedModel(IBakedModel core, IBakedModel extension, IBakedModel straight) {
		this.core = core;
		this.extension = extension;
		this.straight = straight;
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
		return getQuads(state, side, rand, EmptyModelData.INSTANCE);
	}

	@Nonnull
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand,
			@Nonnull IModelData extraData) {
		CableState pipeState = new CableState(state, side, rand);

		return cache.computeIfAbsent(pipeState, this::createQuads);
	}

	private List<BakedQuad> createQuads(CableState state) {
		List<BakedQuad> quads = new ArrayList<>();

		if (state.getState() != null) {
			boolean north = state.getState().getValue(EnergyCableBlock.NORTH);
			boolean east = state.getState().getValue(EnergyCableBlock.EAST);
			boolean south = state.getState().getValue(EnergyCableBlock.SOUTH);
			boolean west = state.getState().getValue(EnergyCableBlock.WEST);
			boolean up = state.getState().getValue(EnergyCableBlock.UP);
			boolean down = state.getState().getValue(EnergyCableBlock.DOWN);

			if (north && south && !east && !west && !up && !down) {
                quads.addAll(straight.getQuads(state.getState(), state.getSide(), state.getRand(), EmptyModelData.INSTANCE));
            } else if (!north && !south && east && west && !up && !down) {
                quads.addAll(getTransformedQuads(straight, Direction.EAST, state));
            } else if (!north && !south && !east && !west && up && down) {
                quads.addAll(getTransformedQuads(straight, Direction.UP, state));
            } else if (!north && !south && !east && !west && !up && !down) {
                quads.addAll(core.getQuads(state.getState(), state.getSide(), state.getRand(), EmptyModelData.INSTANCE));
            } else {
                quads.addAll(core.getQuads(state.getState(), state.getSide(), state.getRand(), EmptyModelData.INSTANCE));

                if (north) {
                    quads.addAll(extension.getQuads(state.getState(), state.getSide(), state.getRand(), EmptyModelData.INSTANCE));
                }

                if (east) {
                    quads.addAll(getTransformedQuads(extension, Direction.EAST, state));
                }

                if (south) {
                    quads.addAll(getTransformedQuads(extension, Direction.SOUTH, state));
                }

                if (west) {
                    quads.addAll(getTransformedQuads(extension, Direction.WEST, state));
                }

                if (up) {
                    quads.addAll(getTransformedQuads(extension, Direction.UP, state));
                }

                if (down) {
                    quads.addAll(getTransformedQuads(extension, Direction.DOWN, state));
                }
            }
		}

		return quads;
	}

	private static List<BakedQuad> getTransformedQuads(IBakedModel model, Direction facing, CableState state) {
		TransformationMatrix transformation = SIDE_TRANSFORMS.computeIfAbsent(facing, face -> {
			Quaternion quaternion;
			if (face == Direction.UP) {
				quaternion = TransformationHelper.quatFromXYZ(new Vector3f(90, 0, 0), true);
			} else if (face == Direction.DOWN) {
				quaternion = TransformationHelper.quatFromXYZ(new Vector3f(270, 0, 0), true);
			} else {
				double r = Math.PI * (360 - face.getOpposite().get2DDataValue() * 90) / 180d;

				quaternion = TransformationHelper.quatFromXYZ(new Vector3f(0, (float) r, 0), false);
			}

			return new TransformationMatrix(null, quaternion, null, null).blockCenterToCorner();
		});

		ImmutableList.Builder<BakedQuad> quads = ImmutableList.builder();
		Direction side = state.getSide();

		if (side != null && side.get2DDataValue() > -1) {
			int faceOffset = 4 + Direction.NORTH.get2DDataValue() - facing.get2DDataValue();
			side = Direction.from2DDataValue((side.get2DDataValue() + faceOffset) % 4);
		}

		for (BakedQuad quad : model.getQuads(state.getState(), side, state.getRand(), EmptyModelData.INSTANCE)) {
			BakedQuadBuilder builder = new BakedQuadBuilder(quad.getSprite());
			TRSRTransformer transformer = new TRSRTransformer(builder, transformation);

			quad.pipe(transformer);

			quads.add(builder.build());
		}

		return quads.build();
	}

	@Override
	public boolean useAmbientOcclusion() {
		return core.useAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return core.isGui3d();
	}

	@Override
	public boolean usesBlockLight() {
		return true;
	}

	@Override
	public boolean isCustomRenderer() {
		return core.isCustomRenderer();
	}

	@Override
	@SuppressWarnings("deprecation")
	public TextureAtlasSprite getParticleIcon() {
		return core.getParticleIcon();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return core.getOverrides();
	}

	public static class CableState {
		@Nullable
		private final BlockState state;
		@Nullable
		private final Direction side;
		private final Random rand;

		public CableState(@Nullable BlockState state, Direction side, Random rand) {
            this.state = state;
            this.side = side;
            this.rand = rand;
        }

		@Nullable
		public BlockState getState() {
			return state;
		}

		public Direction getSide() {
			return side;
		}

		public Random getRand() {
			return rand;
		}
	}

}

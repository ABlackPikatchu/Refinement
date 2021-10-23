package com.ablackpikatchu.refinement.client.render.ter;

import javax.annotation.Nonnull;

import com.ablackpikatchu.refinement.common.block.StorageBinBlock;
import com.ablackpikatchu.refinement.common.te.misc_tes.StorageBinTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.IProfiler;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class StorageBinTER extends TileEntityRenderer<StorageBinTileEntity> {

	private Minecraft mc = Minecraft.getInstance();

	public StorageBinTER(TileEntityRendererDispatcher p_i226006_1_) {
		super(p_i226006_1_);
	}

	@Override
	public void render(StorageBinTileEntity tile, float partialTick, MatrixStack pMatrixStack,
			IRenderTypeBuffer pBuffer, int pCombinedLight, int pCombinedOverlay) {
		if (tile.getLevel() != null) {
			IProfiler profiler = tile.getLevel().getProfiler();
			profiler.push("storageBin");
			render(tile, partialTick, pMatrixStack, pBuffer, pCombinedLight, pCombinedOverlay, profiler);
			profiler.pop();
		}
	}

	public Matrix3f getNormalLight() {
		Vector3f normal = new Vector3f(1, 1, 1);
		normal.normalize();
		return new Matrix3f(new Quaternion(normal, 0, true));
	}

	public void render(StorageBinTileEntity bin, float pPartialTicks, MatrixStack pMatrixStack,
			IRenderTypeBuffer pBuffer, int pCombinedLight, int pCombinedOverlay, IProfiler profiler) {
		ItemStack item = bin.getItemStored();
		if (item != null && !item.isEmpty()) {
			try {
				Direction facing = bin.getBlockState().getValue(StorageBinBlock.FACING);

				int lightLevel = getLightLevel(bin.getLevel(), bin.getBlockPos());

				pMatrixStack.pushPose();
				pMatrixStack.last().normal().load(getNormalLight());
				pMatrixStack.translate(0, 0.02, 0);

				switch (facing) {
				case NORTH:
					pMatrixStack.translate(0.73, 0.83, -0.0001);
					pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180));
					break;
				case SOUTH:
					pMatrixStack.translate(0.27, 0.83, 1.0001);
					break;
				case WEST:
					pMatrixStack.translate(-0.0001, 0.83, 0.27);
					pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(-90));
					break;
				case EAST:
					pMatrixStack.translate(1.0001, 0.83, 0.73);
					pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
					break;
				default:
					break;
				}

				float scale = 0.03125F;
				float scaler = 0.9F;
				pMatrixStack.scale(scale * scaler, scale * scaler, 0.0001F);
				pMatrixStack.translate(8, -8, 8);
				pMatrixStack.scale(16, 16, 16);

				Minecraft.getInstance().getItemRenderer().renderStatic(item, TransformType.GUI, lightLevel,
						pCombinedOverlay, pMatrixStack, pBuffer);

				pMatrixStack.popPose();
				
				String countString = "\u00A7f" + bin.getStoredItemStackSize();
				if (bin.getStoredItemStackSize() > (bin.getStackLimit() / 100 * 20))
					countString = "\u00A72" + bin.getStoredItemStackSize();
				if (bin.getStoredItemStackSize() > (bin.getStackLimit() / 100 * 50))
					countString = "\u00A7b" + bin.getStoredItemStackSize();
				if (bin.getStoredItemStackSize() > (bin.getStackLimit() / 100 * 70))
					countString = "\u00A7e" + bin.getStoredItemStackSize();
				if (bin.getStoredItemStackSize() >= bin.getStackLimit())
					countString = "\u00A74" + bin.getStoredItemStackSize();
				
				renderCount(pMatrixStack, pBuffer, pCombinedLight, lightLevel,
						new StringTextComponent(countString + "\u00A7f / \u00A76" + bin.getStackLimit()), facing,
						0.2f);
			} catch (NullPointerException e) {

			}
		}
	}

	@SuppressWarnings("incomplete-switch")
	private void renderCount(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light,
			int overlayLight, ITextComponent text, Direction side, float maxScale) {
		matrix.pushPose();
		matrix.translate(0, -0.3725, 0);
		switch (side) {
		case SOUTH:
			matrix.translate(0, 1, 0.0001);
			matrix.mulPose(Vector3f.XP.rotationDegrees(90));
			break;
		case NORTH:
			matrix.translate(1, 1, 0.9999);
			matrix.mulPose(Vector3f.YP.rotationDegrees(180));
			matrix.mulPose(Vector3f.XP.rotationDegrees(90));
			break;
		case EAST:
			matrix.translate(0.0001, 1, 1);
			matrix.mulPose(Vector3f.YP.rotationDegrees(90));
			matrix.mulPose(Vector3f.XP.rotationDegrees(90));
			break;
		case WEST:
			matrix.translate(0.9999, 1, 0);
			matrix.mulPose(Vector3f.YP.rotationDegrees(-90));
			matrix.mulPose(Vector3f.XP.rotationDegrees(90));
			break;
		}

		float displayWidth = 1;
		float displayHeight = 1;
		matrix.translate(displayWidth / 2, 1, displayHeight / 2);
		matrix.mulPose(Vector3f.XP.rotationDegrees(-90));

		FontRenderer font = this.renderer.getFont();

		int requiredWidth = Math.max(font.width(text), 1);
		int requiredHeight = font.lineHeight + 2;
		float scaler = 0.4F;
		float scaleX = displayWidth / requiredWidth;
		float scale = scaleX * scaler;
		if (maxScale > 0) {
			scale = Math.min(scale, maxScale);
		}

		matrix.scale(scale, -scale, scale);
		int realHeight = (int) Math.floor(displayHeight / scale);
		int realWidth = (int) Math.floor(displayWidth / scale);
		int offsetX = (realWidth - requiredWidth) / 2;
		int offsetY = (realHeight - requiredHeight) / 2;
		font.drawInBatch(text, offsetX - realWidth / 2, 1 + offsetY - realHeight / 2, overlayLight, false,
				matrix.last().pose(), renderer, false, 0, light);
		matrix.popPose();
	}

	private int getLightLevel(World world, BlockPos pos) {
		int bLight = world.getBrightness(LightType.BLOCK, pos);
		int sLight = world.getBrightness(LightType.SKY, pos);
		return LightTexture.pack(bLight, sLight);
	}

}

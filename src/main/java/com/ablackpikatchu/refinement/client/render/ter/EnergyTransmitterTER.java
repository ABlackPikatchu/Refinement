package com.ablackpikatchu.refinement.client.render.ter;

import java.awt.Color;

import com.ablackpikatchu.refinement.common.te.misc_tes.EnergyTransmitterTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;

public class EnergyTransmitterTER extends TileEntityRenderer<EnergyTransmitterTileEntity> {

	public EnergyTransmitterTER(TileEntityRendererDispatcher p_i226006_1_) {
		super(p_i226006_1_);
	}

	@Override
	public void render(EnergyTransmitterTileEntity transmitter, float pPartialTicks, MatrixStack matrixStack,
			IRenderTypeBuffer pBuffer, int pCombinedLight, int pCombinedOverlay) {
		IVertexBuilder buffer = pBuffer.getBuffer(RenderType.lines());

		matrixStack.pushPose();

		Matrix4f matrix4f = matrixStack.last().pose();

		BlockPos transmitterPos = transmitter.getBlockPos();

		transmitter.getTargets().forEach(target -> {
			double zPos = target.getZ() - transmitterPos.getZ() + .5;
			double yPos = target.getY() - transmitterPos.getY() + .5;
			double xPos = target.getX() - transmitterPos.getX() + .9;
			drawLine(matrix4f, buffer, Color.RED, new Vector3d(.1, .5, .5), new Vector3d(xPos, yPos, zPos));
		});

		matrixStack.popPose();
	}

	/**
	 * Draw a coloured line from a starting vertex to an end vertex
	 * 
	 * @param matrixPos    the current transformation matrix
	 * @param renderBuffer the vertex builder used to draw the line
	 * @param startVertex
	 * @param endVertex
	 */
	private static void drawLine(Matrix4f matrixPos, IVertexBuilder renderBuffer, Color color, Vector3d startVertex,
			Vector3d endVertex) {
		renderBuffer.vertex(matrixPos, (float) startVertex.x, (float) startVertex.y, (float) startVertex.z)
				.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()) // there is also a version
																							// for floats (0 -> 1)
				.endVertex();
		renderBuffer.vertex(matrixPos, (float) endVertex.x, (float) endVertex.y, (float) endVertex.z)
				.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()) // there is also a version
																							// for floats (0 -> 1)
				.endVertex();
	}

}

package com.ablackpikatchu.refinement.common.particle;

import java.util.Locale;

import com.ablackpikatchu.refinement.core.init.ParticleTypesInit;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ColouredParticle extends SpriteTexturedParticle {

	private double posX, posY, posZ;

	public ColouredParticle(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn, ColouredParticleData data, IAnimatedSprite sprite) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

		xd = xSpeedIn;
		yd = ySpeedIn;
		zd = zSpeedIn;
		posX = xCoordIn;
		posY = yCoordIn;
		posZ = zCoordIn;
		quadSize = 0.1f * (random.nextFloat() * 0.2f + 1.7f);
		float f = (float) Math.random() * 0.4F + 0.6F;
		rCol = ((float) (Math.random() * 0.2F) + 0.8F) * data.getRed() * f;
		gCol = ((float) (Math.random() * 0.2F) + 0.8F) * data.getGreen() * f;
		bCol = ((float) (Math.random() * 0.2F) + 0.8F) * data.getBlue() * f;
		lifetime = (int) (Math.random() * 10.0d) + 40;
	}

	@Override
	public void tick() {
		xo = posX;
		yo = posY;
		zo = posZ;
		if (age++ >= lifetime) {
			this.remove();
		} else {
			float f = age / (float) lifetime;
			float f1 = -f + f * f * 2.0F;
			float f2 = 1.0F - f1;
			posX = posX + xd * f2;
			posY = posY + yd * f2 + (0.2F - f);
			posZ = posZ + zd * f2;
		}
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<ColouredParticleData> {
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite spriteIn) {
			spriteSet = spriteIn;
		}

		@Override
		public Particle createParticle(ColouredParticleData typeIn, ClientWorld worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			ColouredParticle particle = new ColouredParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, typeIn,
					spriteSet);
			particle.pickSprite(spriteSet);
			return particle;
		}
	}

	public static class ColouredParticleData implements IParticleData {
		@SuppressWarnings("deprecation")
		public static final IParticleData.IDeserializer<ColouredParticleData> DESERIALIZER = new IParticleData.IDeserializer<ColouredParticleData>() {
			@Override
			public ColouredParticleData fromCommand(ParticleType<ColouredParticleData> particleTypeIn,
					StringReader reader) throws CommandSyntaxException {
				reader.expect(' ');
				float red = (float) reader.readDouble();
				reader.expect(' ');
				float green = (float) reader.readDouble();
				reader.expect(' ');
				float blue = (float) reader.readDouble();
				reader.expect(' ');
				float alpha = (float) reader.readDouble();
				return new ColouredParticleData(red, green, blue, alpha);
			}

			@Override
			public ColouredParticleData fromNetwork(ParticleType<ColouredParticleData> particleTypeIn, PacketBuffer buffer) {
				return new ColouredParticleData(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(),
						buffer.readFloat());
			}
		};

		private final float red;
		private final float green;
		private final float blue;
		private final float alpha;

		public ColouredParticleData(float redIn, float greenIn, float blueIn, float alphaIn) {
			red = redIn;
			green = greenIn;
			blue = blueIn;
			alpha = MathHelper.clamp(alphaIn, 0.01f, 4.0f);
		}

		@Override
		public void writeToNetwork(PacketBuffer buffer) {
			buffer.writeFloat(red);
			buffer.writeFloat(green);
			buffer.writeFloat(blue);
			buffer.writeFloat(alpha);
		}

		@SuppressWarnings("deprecation")
		@Override
		public String writeToString() {
			return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f", Registry.PARTICLE_TYPE.getKey(this.getType()),
					red, green, blue, alpha);
		}

		@Override
		public ParticleType<ColouredParticleData> getType() {
			return ParticleTypesInit.COLOURED_PARTICLE.get();
		}

		@OnlyIn(Dist.CLIENT)
		public float getRed() {
			return red;
		}

		@OnlyIn(Dist.CLIENT)
		public float getGreen() {
			return green;
		}

		@OnlyIn(Dist.CLIENT)
		public float getBlue() {
			return blue;
		}

		@OnlyIn(Dist.CLIENT)
		public float getAlpha() {
			return alpha;
		}
	}

	public static class ColouredParticleType extends ParticleType<ColouredParticleData> {

		public ColouredParticleType() {
			super(false, ColouredParticleData.DESERIALIZER);
		}

		@Override
		public Codec<ColouredParticleData> codec() {
			return Codec.unit(new ColouredParticleData(0, 0, 0, 0));
		}
		
	}

}

package supercoder79.ecotones.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class SapDripParticle extends SpriteBillboardParticle {
    protected SapDripParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
        this.gravityStrength = 0;
        this.field_28786 = 0.999F;
        this.maxAge = 40;

        this.scale = world.getRandom().nextFloat() * 0.2f + 0.3f;

        this.colorRed = 1;
        this.colorGreen = 0.75F;
        this.colorBlue = 0.2F;

        this.velocityY = 0;
        this.velocityX = 0;
        this.velocityZ = 0;
    }

    public float getSize(float tickDelta) {
        float f = (((float)this.age + tickDelta) * 2) / (float)this.maxAge;

        return this.scale * Math.min(1, f * f);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age == 20) {
            this.velocityY = -0.1;
            this.gravityStrength = 0.75F;
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            SapDripParticle particle = new SapDripParticle(clientWorld, d, e, f);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}

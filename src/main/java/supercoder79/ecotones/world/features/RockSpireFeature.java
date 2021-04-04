package supercoder79.ecotones.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.BitSet;
import java.util.Random;

public class RockSpireFeature extends Feature<DefaultFeatureConfig> {
    public RockSpireFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getPos();
        Random random = context.getRandom();

        PerlinNoiseSampler sampler = new PerlinNoiseSampler(new ChunkRandom(world.getSeed()));

        BlockState down = world.getBlockState(pos.down());
        if (!(down.isOf(Blocks.GRASS_BLOCK) || down.isOf(Blocks.STONE))) {
            return false;
        }

        // 5-9
        int height = 5 + random.nextInt(5);
        int radius = height / 3; // 2-3, 5x5 - 7x7
        int centerY = world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX(), pos.getZ());

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX() + x, pos.getZ() + z) < centerY) {
                    return false;
                }
            }
        }

        BlockPos.Mutable mutable = pos.mutableCopy();

        BitSet current = new BitSet((radius * 2 + 1) * (radius * 2 + 1));
        BitSet next = new BitSet(current.size());

        // Fill current array
        current.set(0, current.size() - 1);

        double radx = 1.0;
        double raddec = 1.0 / height;

        for (int y = 0; y <= height; y++) {
            next.clear();
            int gY = y + pos.getY();

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    int idx = ((x + radius) * (radius * 2 + 1)) + (z + radius);

                    double dx = x / (double) radius;
                    double dz = z / (double) radius;

                    double gX = x + pos.getX();
                    double gZ = z + pos.getZ();

                    double noise = sampler.sample(gX / 4.0, gY / 3.0, gZ / 4.0) * 0.3;
                    noise += (random.nextDouble() - random.nextDouble()) * 0.3;

                    if (current.get(idx) && dx * dx + dz * dz <= radx + noise) {
                        world.setBlockState(mutable.set(pos, x, y, z), Blocks.STONE.getDefaultState(), 3);
                        next.set(idx);
                    }
                }
            }

            current.and(next);
            radx -= raddec;
        }

        return true;
    }
}

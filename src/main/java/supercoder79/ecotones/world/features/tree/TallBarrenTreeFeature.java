package supercoder79.ecotones.world.features.tree;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import supercoder79.ecotones.world.features.FeatureHelper;
import supercoder79.ecotones.world.features.config.SimpleTreeFeatureConfig;

import java.util.Random;

public class TallBarrenTreeFeature extends Feature<SimpleTreeFeatureConfig> {
    public TallBarrenTreeFeature(Codec<SimpleTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<SimpleTreeFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getPos();
        Random random = context.getRandom();
        SimpleTreeFeatureConfig config = context.getConfig();
        ChunkGenerator generator = context.getGenerator();

        if (world.getBlockState(pos.down()) != Blocks.GRASS_BLOCK.getDefaultState()) {
            return false;
        }

        int height = 10 + random.nextInt(8);
        double leafStart = 0.4 + random.nextDouble() * 0.2;

        for (int y = 0; y < height; y++) {
            world.setBlockState(pos.up(y), config.woodState, 3);

            double progress = (double) y / height;

            if (progress >= leafStart && y % 2 == 0) {
                generateLeafLayer(pos, y, random, context);
            }

            if (y >= height - 2) {
                generateLeafLayer(pos, y, random, context);
            }
        }

        generateLeafLayer(pos, height, random, context);
        for (Direction direction : FeatureHelper.HORIZONTAL) {
            FeatureHelper.placeLeaves(context, pos.up(height + 1).offset(direction));
        }

        return true;
    }

    private static void generateLeafLayer(BlockPos pos, int y, Random random, FeatureContext<SimpleTreeFeatureConfig> context) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos local = pos.add(x, y, z);

                if (Math.abs(x) == 1 && Math.abs(z) == 1) {
                    if (random.nextInt(2) == 0) {
                        FeatureHelper.placeLeaves(context, local);
                    }
                } else {
                    if (random.nextDouble() > 0.2) {
                        FeatureHelper.placeLeaves(context, local);
                        if (random.nextDouble() < 0.3) {
                            Direction direction = FeatureHelper.randomHorizontal(random);
                            FeatureHelper.placeLeaves(context, local.offset(direction));
                        }
                    }
                }
            }
        }
    }
}

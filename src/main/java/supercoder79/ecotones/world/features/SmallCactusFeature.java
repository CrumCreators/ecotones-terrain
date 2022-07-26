package supercoder79.ecotones.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.util.FeatureContext;
import supercoder79.ecotones.blocks.HeadBlocks;
import supercoder79.ecotones.world.features.config.SmallCactusFeatureConfig;

;


public class SmallCactusFeature extends EcotonesFeature<SmallCactusFeatureConfig> {
    public SmallCactusFeature(Codec<SmallCactusFeatureConfig> configCodec) {
        super(configCodec);
    }

    public static final TagKey<Block> GROUND = TagKey.of(Registry.BLOCK_KEY, new Identifier("ecotones", "ground"));

    @Override
    public boolean generate(FeatureContext<SmallCactusFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random random = context.getRandom();
        ChunkGenerator generator = context.getGenerator();
        SmallCactusFeatureConfig config = context.getConfig();


        int count = config.count.get(random);
        int spread = config.spread.get(random);

        BlockPos.Mutable mutable = pos.mutableCopy();
        for (int i = 0; i < count; i++) {
            int dx = random.nextInt(spread) - random.nextInt(spread) + pos.getX();
            int dz = random.nextInt(spread) - random.nextInt(spread) + pos.getZ();
            int y = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, dx, dz);

            mutable.set(dx, y, dz);


            if (world.getBlockState(mutable).getMaterial().isReplaceable() && world.getBlockState(mutable.down()).isIn(GROUND)) {
                world.setBlockState(mutable, HeadBlocks.SMALL_CACTUS.getDefaultState(), 3);
            }
        }
        return true;
    }
}

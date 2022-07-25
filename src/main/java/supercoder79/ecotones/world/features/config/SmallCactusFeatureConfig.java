package supercoder79.ecotones.world.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public class SmallCactusFeatureConfig implements FeatureConfig {
    public static final Codec<SmallCactusFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            IntProvider.VALUE_CODEC.fieldOf("count").forGetter(c -> c.count),
            IntProvider.VALUE_CODEC.fieldOf("spread").forGetter(c -> c.spread)
    ).apply(instance, SmallCactusFeatureConfig::new));

    public final IntProvider count;
    public final IntProvider spread;

    public SmallCactusFeatureConfig(IntProvider count, IntProvider spread) {
        this.count = count;
        this.spread = spread;
    }
}

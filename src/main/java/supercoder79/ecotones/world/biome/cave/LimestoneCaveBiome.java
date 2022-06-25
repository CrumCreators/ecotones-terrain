package supercoder79.ecotones.world.biome.cave;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import supercoder79.ecotones.api.CaveBiome;
import supercoder79.ecotones.world.features.EcotonesConfiguredFeature;

public class LimestoneCaveBiome extends CaveBiome {
    public static LimestoneCaveBiome INSTANCE;

    public static void init() {
        INSTANCE = new LimestoneCaveBiome();
    }

    protected LimestoneCaveBiome() {
        //Changed to Polished Basalt
        this.addFeature(EcotonesConfiguredFeature.wrap(Feature.ORE,
                        new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, Blocks.POLISHED_BASALT.getDefaultState(), 33))
                    .uniformRange(YOffset.fixed(0), YOffset.fixed(48))
                    .spreadHorizontally()
                    .repeat(15).vanilla());
    }
}

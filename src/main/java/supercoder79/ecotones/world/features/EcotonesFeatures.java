package supercoder79.ecotones.world.features;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import supercoder79.ecotones.api.TreeGenerationConfig;
import supercoder79.ecotones.world.features.config.OakTreeFeatureConfig;
import supercoder79.ecotones.world.features.config.RockFeatureConfig;
import supercoder79.ecotones.world.features.config.SimpleTreeFeatureConfig;
import supercoder79.ecotones.world.features.entity.BeehiveFeature;
import supercoder79.ecotones.world.features.entity.DuckNestFeature;
import supercoder79.ecotones.world.features.tree.*;

public final class EcotonesFeatures {
    public static Feature<DefaultFeatureConfig> DESERTIFY_SOIL;
    public static Feature<DefaultFeatureConfig> CACTI;
    public static Feature<SimpleTreeFeatureConfig> SHRUB;
    public static Feature<TreeFeatureConfig> JUNGLE_PALM_TREE;
    public static Feature<DefaultFeatureConfig> SUGARCANE;
    public static Feature<TreeGenerationConfig> SMALL_ACACIA;
    public static Feature<TreeFeatureConfig> BANANA_TREE;
    public static Feature<SimpleTreeFeatureConfig> SMALL_SPRUCE;
    public static Feature<SimpleTreeFeatureConfig> BIG_SHRUB;
    public static Feature<SimpleTreeFeatureConfig> POPLAR_TREE;
    public static Feature<DefaultFeatureConfig> DRAINAGE;
    public static Feature<TreeGenerationConfig> BRANCHING_OAK;
    public static Feature<TreeGenerationConfig> IMPROVED_BIRCH;
    public static Feature<TreeGenerationConfig> BRANCHING_ACACIA;
    public static Feature<SimpleTreeFeatureConfig> WIDE_SHRUB;
    public static Feature<SimpleTreeFeatureConfig> ASPEN_TREE;
    public static Feature<DefaultFeatureConfig> PLACE_WATER;
    public static Feature<DefaultFeatureConfig> FARMLAND;
    public static Feature<DefaultFeatureConfig> BEEHIVES;
    public static Feature<TreeGenerationConfig> MANGROVE_TREE;
    public static Feature<TreeGenerationConfig> BRANCHING_DARK_OAK;
    public static Feature<RockFeatureConfig> ROCK;
    public static Feature<SimpleTreeFeatureConfig> DEAD_TREE;
    public static Feature<DefaultFeatureConfig> PODZOL;
    public static Feature<TreeGenerationConfig> MAPLE_TREE;
    public static Feature<DefaultFeatureConfig> BLUEBERRY_BUSH;
    public static Feature<SimpleTreeFeatureConfig> BARREN_PINE;
    public static Feature<DefaultFeatureConfig> DUCK_NEST;
    public static Feature<DefaultFeatureConfig> PUMPKIN_FARM;
    public static Feature<DefaultFeatureConfig> ROSEMARY;
    public static Feature<OakTreeFeatureConfig> STRAIGHT_OAK;
    public static Feature<SimpleTreeFeatureConfig> BARREN_TREE;

    public static void init() {
        // TODO: cleanup
        DESERTIFY_SOIL = Registry.register(Registry.FEATURE, new Identifier("ecotones", "desertify"), new DesertifySoilFeature(DefaultFeatureConfig.CODEC));
        CACTI = Registry.register(Registry.FEATURE, new Identifier("ecotones", "cacti"), new PlaceCactiFeature(DefaultFeatureConfig.CODEC));
        SHRUB = Registry.register(Registry.FEATURE, new Identifier("ecotones", "shrub"), new ShrubFeature(SimpleTreeFeatureConfig.CODEC));
        JUNGLE_PALM_TREE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "palm_tree"), new PalmTreeFeature(TreeFeatureConfig.CODEC, Blocks.JUNGLE_WOOD.getDefaultState()));
        SUGARCANE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "sugarcane"), new SugarCaneFeature(DefaultFeatureConfig.CODEC));
        SMALL_ACACIA = Registry.register(Registry.FEATURE, new Identifier("ecotones", "small_acacia"), new SmallAcaciaTreeFeature(TreeGenerationConfig.CODEC));
        BANANA_TREE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "banana_tree"), new BananaTreeFeature(TreeFeatureConfig.CODEC));
        SMALL_SPRUCE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "small_spruce"), new SmallSpruceTreeFeature());
        BIG_SHRUB = Registry.register(Registry.FEATURE, new Identifier("ecotones", "big_shrub"), new BigShrubFeature(SimpleTreeFeatureConfig.CODEC));
        POPLAR_TREE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "poplar_tree"), new PoplarTreeFeature(SimpleTreeFeatureConfig.CODEC));
        DRAINAGE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "drainage"), new DrainageDecorationFeature(DefaultFeatureConfig.CODEC));
        BRANCHING_OAK = Registry.register(Registry.FEATURE, new Identifier("ecotones", "branching_oak"), new BranchingOakTreeFeature(TreeGenerationConfig.CODEC));
        IMPROVED_BIRCH = Registry.register(Registry.FEATURE, new Identifier("ecotones", "improved_birch"), new ImprovedBirchTreeFeature(TreeGenerationConfig.CODEC));
        BRANCHING_ACACIA = Registry.register(Registry.FEATURE, new Identifier("ecotones", "branching_acacia"), new BranchingAcaciaTreeFeature(TreeGenerationConfig.CODEC));
        WIDE_SHRUB = Registry.register(Registry.FEATURE, new Identifier("ecotones", "wide_shrub"), new WideShrubFeature(SimpleTreeFeatureConfig.CODEC));
        ASPEN_TREE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "aspen_tree"), new AspenTreeFeature(SimpleTreeFeatureConfig.CODEC));
        PLACE_WATER = Registry.register(Registry.FEATURE, new Identifier("ecotones", "place_water"), new PlaceWaterFeature(DefaultFeatureConfig.CODEC));
        FARMLAND = Registry.register(Registry.FEATURE, new Identifier("ecotones", "farmland"), new FarmlandPatchFeature(DefaultFeatureConfig.CODEC));
        BEEHIVES = Registry.register(Registry.FEATURE, new Identifier("ecotones", "beehives"), new BeehiveFeature(DefaultFeatureConfig.CODEC));
        MANGROVE_TREE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "mangrove_tree"), new MangroveTreeFeature(TreeGenerationConfig.CODEC));
        BRANCHING_DARK_OAK = Registry.register(Registry.FEATURE, new Identifier("ecotones", "branching_dark_oak"), new BranchingDarkOakTreeFeature(TreeGenerationConfig.CODEC));
        ROCK = Registry.register(Registry.FEATURE, new Identifier("ecotones", "rock"), new RockFeature(RockFeatureConfig.CODEC));
        DEAD_TREE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "dead_tree"), new DeadTreeFeature(SimpleTreeFeatureConfig.CODEC));
        PODZOL = Registry.register(Registry.FEATURE, new Identifier("ecotones", "podzol"), new PodzolPatchFeature(DefaultFeatureConfig.CODEC));
        MAPLE_TREE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "maple_tree"), new MapleTreeFeature(TreeGenerationConfig.CODEC));
        BLUEBERRY_BUSH = Registry.register(Registry.FEATURE, new Identifier("ecotones", "blueberry_bush"), new BlueberryBushFeature(DefaultFeatureConfig.CODEC));
        BARREN_PINE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "barren_pine"), new BarrenPineTreeFeature(SimpleTreeFeatureConfig.CODEC));
        DUCK_NEST = Registry.register(Registry.FEATURE, new Identifier("ecotones", "duck_nest"), new DuckNestFeature(DefaultFeatureConfig.CODEC));
        PUMPKIN_FARM = Registry.register(Registry.FEATURE, new Identifier("ecotones", "pumpkin_farm"), new PumpkinFarmFeature(DefaultFeatureConfig.CODEC));
        ROSEMARY = Registry.register(Registry.FEATURE, new Identifier("ecotones", "rosemary"), new RosemaryFeature(DefaultFeatureConfig.CODEC));
        STRAIGHT_OAK = Registry.register(Registry.FEATURE, new Identifier("ecotones", "straight_oak"), new StraightOakTreeFeature(OakTreeFeatureConfig.CODEC));
        BARREN_TREE = Registry.register(Registry.FEATURE, new Identifier("ecotones", "barren_tree"), new BarrenTreeFeature(SimpleTreeFeatureConfig.CODEC));
    }
}

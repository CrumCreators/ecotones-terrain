package supercoder79.ecotones;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import supercoder79.ecotones.gen.DataGen;
import supercoder79.ecotones.util.*;
import supercoder79.ecotones.util.deco.BlockDecorations;
import supercoder79.ecotones.util.vein.OreVeins;
import supercoder79.ecotones.world.EcotonesWorldType;
import supercoder79.ecotones.world.biome.EcotonesBiomeBuilder;
import supercoder79.ecotones.world.biome.EcotonesBiomes;
import supercoder79.ecotones.world.data.EcotonesData;
import supercoder79.ecotones.world.decorator.EcotonesDecorators;
import supercoder79.ecotones.world.edge.EcotonesEdgeDecorations;
import supercoder79.ecotones.world.features.EcotonesFeatures;
import supercoder79.ecotones.world.features.foliage.EcotonesFoliagePlacers;
import supercoder79.ecotones.world.gen.BiomeGenData;
import supercoder79.ecotones.world.gen.EcotonesBiomeSource;
import supercoder79.ecotones.world.gen.EcotonesChunkGenerator;
import supercoder79.ecotones.world.structure.EcotonesConfiguredStructures;
import supercoder79.ecotones.world.structure.EcotonesStructurePieces;
import supercoder79.ecotones.world.structure.EcotonesStructures;
import supercoder79.ecotones.world.structure.EcotonesStructuresConfig;
import supercoder79.ecotones.world.surface.EcotonesSurfaces;
import supercoder79.ecotones.world.tree.trait.EcotonesTreeTraits;
import supercoder79.ecotones.world.treedecorator.EcotonesTreeDecorators;

import java.util.List;

public final class Ecotones implements ModInitializer {
	private static final boolean RUN_DATA_GEN = "true".equals(System.getProperty("ECOTONES_RUN_DATAGEN", null));

	// TODO: split out into it's own class
	public static final Identifier WORLD_TYPE = new Identifier("ecotones", "world_type");

	public static final Logger LOGGER = LogManager.getLogger("ecotones");

	// Dynamic registry
	public static Registry<Biome> REGISTRY;
	private static EcotonesWorldType worldType;
	public static boolean isServerEcotones = false;

	@Override
	public void onInitialize() {
		long start = System.currentTimeMillis();

		OreVeins.init();

		EcotonesFoliagePlacers.init();
		EcotonesTreeDecorators.init();

		EcotonesTreeTraits.init();

		EcotonesDecorators.init();
		EcotonesFeatures.init();
		EcotonesSurfaces.init();

		EcotonesStructurePieces.init();
		EcotonesStructures.init();
		EcotonesConfiguredStructures.init();


		EcotonesBiomes.init();

		EcotonesEdgeDecorations.init();

		EcotonesData.init();

		CampfireLogHelper.initVanilla();
		BlockDecorations.init();

		if (RUN_DATA_GEN) {
			DataGen.run();
		}

		AiLog.init();
		AiLog.log("[System] Starting AI log");

		// Biome count summary and biome finalization
		int ecotonesBiomes = 0;
		for (Identifier id : BuiltinRegistries.BIOME.getIds()) {
			if (id.getNamespace().contains("ecotones")) {
				Biome biome = BuiltinRegistries.BIOME.get(id);
				BiomeGenData data = EcotonesBiomeBuilder.OBJ2DATA.get(biome);
				List<ConfiguredStructureFeature<?, ?>> structures = EcotonesBiomeBuilder.BIOME_STRUCTURES.get(biome);

				RegistryKey<Biome> key = BuiltinRegistries.BIOME.getKey(biome).get();
				EcotonesStructuresConfig.STRUCTURE_DATA.put(key, structures);
				BiomeGenData.LOOKUP.put(key, data);
				if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
					BiomeChecker.check(biome);
				}

				ecotonesBiomes++;
			}
		}

		VanillaBiomeData.init();

		log("Registering " + ecotonesBiomes + " ecotones biomes!");
		RegistryReport.report(ecotonesBiomes);

		// register chunk generator and world type
		Registry.register(Registry.BIOME_SOURCE, new Identifier("ecotones", "ecotones"), EcotonesBiomeSource.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, new Identifier("ecotones", "ecotones"), EcotonesChunkGenerator.CODEC);

		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			worldType = new EcotonesWorldType();
		}

		// Store if this server is in ecotones or not
//		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
//			isServerEcotones = server.getOverworld().getChunkManager().getChunkGenerator() instanceof EcotonesChunkGenerator;
//		});

		log("Ecotones init took " + (System.currentTimeMillis() - start) + "ms!");
	}

	public static Identifier id(String name) {
		return new Identifier("ecotones", name);
	}

	public static void log(String str) {
		LOGGER.info("[ecotones] " + str);
	}

	private static boolean isModLoaded(String modid) {
		return FabricLoader.getInstance().isModLoaded(modid);
	}
}
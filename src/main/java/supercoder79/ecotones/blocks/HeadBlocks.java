package supercoder79.ecotones.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HeadBlocks {
    //Assigning
    public static final Block SMALL_CACTUS = createSmallCactus("eyJ0aW1lc3RhbXAiOjE1NjY3NDcyNzg5NzEsInByb2ZpbGVJZCI6ImZkNjBmMzZmNTg2MTRmMTJiM2NkNDdjMmQ4NTUyOTlhIiwicHJvZmlsZU5hbWUiOiJSZWFkIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81NWIwYjg2YWFhNGJlOWVjYmY3YzJiY2VjNGFmZGQ0OGJjMDljOWZkYzA5ODA2NzBiMmJmZjQxYzExNGU4MTVhIn19fQ==");

    //Creation
    public static Block createSmallCactus(String texture) {
        return new HeadBlock(FabricBlockSettings.of(Material.CACTUS).strength(0.4f,0.4f), texture);
    }

    //Registering
    public static <T extends Block> void init() {
        Registry.register(Registry.BLOCK, new Identifier("small_cactus"), (T) HeadBlocks.SMALL_CACTUS);
    }
}

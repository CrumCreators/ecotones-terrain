package supercoder79.ecotones.blocks;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.List;

public class NestBlock extends Block {
    private static final List<Block> VALID_BLOCKS = ImmutableList.of(Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL);
    public NestBlock(Settings settings) {
        super(settings);
    }
    public static boolean isValid(Block block) {
        return VALID_BLOCKS.contains(block);
    }
}

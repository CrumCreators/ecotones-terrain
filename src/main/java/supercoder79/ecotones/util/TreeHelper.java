package supercoder79.ecotones.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

public class TreeHelper {
    public static boolean canLogReplace(WorldAccess world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.isAir() || !state.getMaterial().isSolid()) {
            return true;
        }

        return state.isIn(BlockTags.LEAVES) || state.isIn(BlockTags.LOGS) || state.isIn(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH);
    }
}

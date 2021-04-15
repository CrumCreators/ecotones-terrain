package supercoder79.ecotones.blocks.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import supercoder79.ecotones.blocks.TreetapBlock;
import supercoder79.ecotones.client.particle.EcotonesParticles;

public class TreetapBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
    // 0 .. 8,000
    private int sapAmount = 0;
    private Direction direction;

    public TreetapBlockEntity(BlockPos pos, BlockState state) {
        super(EcotonesBlockEntities.TREETAP, pos, state);
        this.direction = state.get(TreetapBlock.FACING);
    }

    public static void tick(World world, BlockPos pos, BlockState state, TreetapBlockEntity blockEntity) {
        if (world.getBlockState(pos.offset(state.get(TreetapBlock.FACING))).isOf(Blocks.OAK_LOG)) {
            blockEntity.sapAmount++;
            blockEntity.markDirty();
            blockEntity.sync();

            // Spawn drip particles every second
            if (world.getTime() % 40 == 0 && world.getRandom().nextInt(2) == 0) {
                ((ServerWorld)world).spawnParticles(EcotonesParticles.SAP_DRIP, pos.getX() + tapX(blockEntity.getDirection()), pos.getY() + 0.8, pos.getZ() + tapZ(blockEntity.getDirection()), 1, 0.0D, 0.0D, 0.0D, 1);
            }
        }
    }

    public boolean canDropSap() {
        return this.sapAmount >= 1000;
    }

    public void dropSap() {
        if (canDropSap()) {
            this.sapAmount -= 1000;

            markDirty();
            sync();
        }
    }

    public int getSapAmount() {
        return this.sapAmount;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        this.sapAmount = tag.getInt("sap_amount");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putInt("sap_amount", this.sapAmount);
        return tag;
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        this.sapAmount = tag.getInt("sap_amount");
        this.direction = Direction.fromHorizontal(tag.getInt("direction"));
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        tag.putInt("sap_amount", this.sapAmount);
        tag.putInt("direction", this.direction.getHorizontal());
        return tag;
    }

    // TODO: these values are slightly wrong
    private static double tapX(Direction direction) {
        switch (direction) {
            case NORTH:
                return 7 / 16.0;
            case EAST:
                return 9 / 16.0;
            case SOUTH:
                return 8 / 16.0;
            case WEST:
                return 4 / 16.0;
            default:
                throw new RuntimeException("We've got a sticky situation here!");
        }
    }

    private static double tapZ(Direction direction) {
        switch (direction) {
            case NORTH:
                return 6 / 16.0;
            case EAST:
                return 8 / 16.0;
            case SOUTH:
                return 10 / 16.0;
            case WEST:
                return 8 / 16.0;
            default:
                throw new RuntimeException("We've got a sticky situation here!");
        }
    }
}

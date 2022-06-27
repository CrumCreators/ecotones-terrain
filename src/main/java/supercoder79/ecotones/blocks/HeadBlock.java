package supercoder79.ecotones.blocks;

import eu.pb4.polymer.api.block.PolymerHeadBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import java.util.ArrayList;
import java.util.List;

public class HeadBlock extends Block implements PolymerHeadBlock {

    public static final List<HeadBlock> HEADS = new ArrayList<>();
    private final String texture;


    public HeadBlock(Settings settings, String texture) {
        super(settings);
        this.texture = texture;

        HEADS.add(this);
    }

    @Override
    public String getPolymerSkinValue(BlockState state) {
        return this.texture;
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return PolymerHeadBlock.super.getPolymerBlock();
    }
}

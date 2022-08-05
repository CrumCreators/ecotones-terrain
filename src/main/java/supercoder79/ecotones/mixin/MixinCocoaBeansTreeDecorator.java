package supercoder79.ecotones.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.CocoaBeansTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(CocoaBeansTreeDecorator.class)
public class MixinCocoaBeansTreeDecorator {
    @Inject(method = "generate", at = @At(value = "INVOKE", target = "Ljava/util/List;get(I)Ljava/lang/Object;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void fixCrash(TreeDecorator.Generator generator, CallbackInfo ci, Random random, List<BlockPos> list) {
        if (list.size() == 0) {
            ci.cancel(); // Not sure how this happens but it does
        }
    }
}

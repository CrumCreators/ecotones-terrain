package supercoder79.ecotones.recipe.memyimi;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.emi.emi.EmiRenderHelper;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import supercoder79.ecotones.blocks.EcotonesBlocks;
import supercoder79.ecotones.items.EcotonesItemBlocks;
import supercoder79.ecotones.items.EcotonesItems;
import supercoder79.ecotones.recipe.EcotonesSapDistilleryRecipes;
import supercoder79.ecotones.recipe.SapDistilleryRecipe;

import java.util.Map;

public class EcotonesEmiPlugin implements EmiPlugin {
    public static final EmiRecipeCategory SAP_DISTILLERY = new EmiRecipeCategory(new Identifier("ecotones:sap_distillery"),
            EmiStack.of(EcotonesItemBlocks.get(EcotonesBlocks.SAP_DISTILLERY)), simplifiedRenderer(240, 240));

    @Override
    public void register(EmiRegistry registry) {

        registry.addCategory(SAP_DISTILLERY);

        registry.addWorkstation(SAP_DISTILLERY, EmiStack.of(EcotonesBlocks.SAP_DISTILLERY));

        for (Map.Entry<Identifier, SapDistilleryRecipe> e : EcotonesSapDistilleryRecipes.RECIPES.entrySet()) {
            registry.addRecipe(new SapDistilleryEmiRecipe(e.getKey(), e.getValue()));
        }

        registry.removeEmiStacks(EmiStack.of(EcotonesItems.ECOTONES_BOOK));
    }

    private static EmiRecipeCategory.Renderer simplifiedRenderer(int u, int v) {
        return (matrices, x, y, delta) -> {
            RenderSystem.setShaderTexture(0, EmiRenderHelper.WIDGETS);
            DrawableHelper.drawTexture(matrices, x, y, u, v, 16, 16, 256, 256);
        };
    }
}

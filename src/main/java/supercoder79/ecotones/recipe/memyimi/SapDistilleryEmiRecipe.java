package supercoder79.ecotones.recipe.memyimi;

import dev.emi.emi.EmiRenderHelper;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.stack.ItemEmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import supercoder79.ecotones.recipe.EcotonesSapDistilleryRecipes;
import supercoder79.ecotones.recipe.SapDistilleryRecipe;

import java.util.List;

public record SapDistilleryEmiRecipe(Identifier id, SapDistilleryRecipe backing) implements EmiRecipe {
    @Override
    public EmiRecipeCategory getCategory() {
        return EcotonesEmiPlugin.SAP_DISTILLERY;
    }

    @Override
    public @Nullable Identifier getId() {
        return this.id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(EmiStack.of(new ItemStack(backing.in(), 8)));
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(EmiStack.of(new ItemStack(backing.out(), 1)));
    }

    @Override
    public int getDisplayWidth() {
        return 240;
    }

    @Override
    public int getDisplayHeight() {
        return 160;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(EmiRenderHelper.WIDGETS, 27, 3, 13, 13, 82, 0);
        widgets.addTexture(EmiRenderHelper.WIDGETS, 75, 1, 24, 17, 44, 0);
        widgets.addSlot(EmiStack.of(new ItemStack(backing.in(), 8)), 10, 10);
        widgets.addSlot(EmiStack.of(new ItemStack(backing.out(), 1)), 56, 0).output(true).recipeContext(this);
    }
}

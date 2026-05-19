package net.vercte.craftingmats.util.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.vercte.craftingmats.CraftingMats;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class StandardRecipeProvider extends RecipeProvider {
    public StandardRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, CraftingMats.CRAFTING_MAT_ITEM.get(), 4)
                .requires(Tags.Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.CRAFTING_TABLE)
                .requires(Tags.Items.LEATHER)
                .unlockedBy("has_paper", has(Items.PAPER))
                .unlockedBy("has_leather", has(Tags.Items.LEATHER))
                .save(consumer);
    }
}

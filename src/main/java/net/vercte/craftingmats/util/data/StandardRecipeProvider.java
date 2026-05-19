package net.vercte.craftingmats.util.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.vercte.craftingmats.CraftingMats;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class StandardRecipeProvider extends FabricRecipeProvider {
    public StandardRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, CraftingMats.CRAFTING_MAT_ITEM, 4)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.CRAFTING_TABLE)
                .requires(Items.LEATHER)
                .unlockedBy("has_paper", has(Items.PAPER))
                .unlockedBy("has_leather", has(Items.LEATHER))
                .save(consumer);
    }
}

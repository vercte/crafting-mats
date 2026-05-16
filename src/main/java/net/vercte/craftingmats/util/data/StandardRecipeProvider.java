package net.vercte.craftingmats.util.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.vercte.craftingmats.CraftingMats;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class StandardRecipeProvider extends RecipeProvider {
    public StandardRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> recipes) {
        super(output, recipes);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, CraftingMats.CRAFTING_MAT_ITEM.get())
                .requires(Tags.Items.STRINGS)
                .requires(Items.PAPER)
                .requires(Tags.Items.PLAYER_WORKSTATIONS_CRAFTING_TABLES)
                .requires(Tags.Items.LEATHERS)
                .unlockedBy("has_paper", has(Items.PAPER))
                .unlockedBy("has_leather", has(Tags.Items.LEATHERS))
                .save(output);
    }
}

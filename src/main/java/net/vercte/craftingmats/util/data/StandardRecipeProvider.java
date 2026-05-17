package net.vercte.craftingmats.util.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.vercte.craftingmats.CraftingMats;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class StandardRecipeProvider extends FabricRecipeProvider {
    public StandardRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> recipes) {
        super(output, recipes);
    }

    @Override
    public void buildRecipes(@NotNull RecipeOutput output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, CraftingMats.CRAFTING_MAT_ITEM)
                .requires(ConventionalItemTags.STRINGS)
                .requires(Items.PAPER)
                .requires(ConventionalItemTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES)
                .requires(ConventionalItemTags.LEATHERS)
                .unlockedBy("has_paper", has(Items.PAPER))
                .unlockedBy("has_leather", has(ConventionalItemTags.LEATHERS))
                .save(output);
    }
}

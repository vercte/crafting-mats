package net.vercte.craftingmats.util.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.vercte.craftingmats.CraftingMats;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends FabricTagProvider.ItemTagProvider {
    public ItemTagGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        getOrCreateTagBuilder(ItemTags.DYEABLE)
                .add(CraftingMats.CRAFTING_MAT_ITEM);
    }
}

package net.vercte.craftingmats.util.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.vercte.craftingmats.CraftingMats;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends ItemTagsProvider {
    public ItemTagGen(PackOutput arg, CompletableFuture<HolderLookup.Provider> holderLookup) {
        super(arg, holderLookup, CompletableFuture.supplyAsync(() -> null));
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(ItemTags.DYEABLE)
                .add(CraftingMats.CRAFTING_MAT_ITEM.get());
    }
}

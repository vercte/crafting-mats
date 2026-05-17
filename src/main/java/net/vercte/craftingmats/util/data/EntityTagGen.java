package net.vercte.craftingmats.util.data;

import dev.ryanhcode.sable.index.SableTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.vercte.craftingmats.CraftingMats;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class EntityTagGen extends FabricTagProvider.EntityTypeTagProvider {
    public EntityTagGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        getOrCreateTagBuilder(SableTags.RETAIN_IN_SUB_LEVEL)
                .add(CraftingMats.CRAFTING_MAT);
    }
}

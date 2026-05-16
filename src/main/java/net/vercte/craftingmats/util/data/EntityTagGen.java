package net.vercte.craftingmats.util.data;

import dev.ryanhcode.sable.index.SableTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.vercte.craftingmats.CraftingMats;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class EntityTagGen extends EntityTypeTagsProvider {
    public EntityTagGen(PackOutput arg, CompletableFuture<HolderLookup.Provider> holderLookup, ExistingFileHelper existingFileHelper) {
        super(arg, holderLookup, CraftingMats.ID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(SableTags.RETAIN_IN_SUB_LEVEL)
                .add(CraftingMats.CRAFTING_MAT.get());
    }
}

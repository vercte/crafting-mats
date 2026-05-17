package net.vercte.craftingmats.util.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.vercte.craftingmats.CraftingMatTags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class BlockTagGen extends FabricTagProvider.BlockTagProvider {
    public BlockTagGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        getOrCreateTagBuilder(CraftingMatTags.SUPPORTS_MAT)
                .forceAddTag(BlockTags.BEDS)
                .forceAddTag(BlockTags.WOOL_CARPETS)
                .forceAddTag(ConventionalBlockTags.FENCES);
    }
}

package net.vercte.craftingmats.util.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.vercte.craftingmats.CraftingMatTags;
import net.vercte.craftingmats.CraftingMats;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class BlockTagGen extends BlockTagsProvider {
    public BlockTagGen(PackOutput arg, CompletableFuture<HolderLookup.Provider> holderLookup, ExistingFileHelper existingFileHelper) {
        super(arg, holderLookup, CraftingMats.ID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(CraftingMatTags.SUPPORTS_MAT)
                .addTag(BlockTags.BEDS)
                .addTag(BlockTags.WOOL_CARPETS)
                .addTag(Tags.Blocks.FENCES);
    }
}

package net.vercte.craftingmats.util.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.vercte.craftingmats.CraftingMats;

import java.util.concurrent.CompletableFuture;

public class LangGen extends FabricLanguageProvider {
    public LangGen(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(CraftingMats.CRAFTING_MAT_ITEM, "Crafting Mat");
        translationBuilder.add(CraftingMats.CRAFTING_MAT, "Crafting Mat");
    }
}

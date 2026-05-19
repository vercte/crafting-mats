package net.vercte.craftingmats.util.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.vercte.craftingmats.CraftingMats;

public class LangGen extends FabricLanguageProvider {
    public LangGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(CraftingMats.CRAFTING_MAT_ITEM, "Crafting Mat");
        translationBuilder.add(CraftingMats.CRAFTING_MAT, "Crafting Mat");
    }
}

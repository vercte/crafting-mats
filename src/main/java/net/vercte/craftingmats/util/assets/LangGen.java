package net.vercte.craftingmats.util.assets;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.vercte.craftingmats.CraftingMats;

public class LangGen extends LanguageProvider {
    public LangGen(PackOutput output) {
        super(output, CraftingMats.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(CraftingMats.CRAFTING_MAT_ITEM, "Crafting Mat");
        addEntityType(CraftingMats.CRAFTING_MAT, "Crafting Mat");
    }
}

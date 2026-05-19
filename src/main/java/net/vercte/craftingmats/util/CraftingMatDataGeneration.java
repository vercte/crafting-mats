package net.vercte.craftingmats.util;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;
import net.vercte.craftingmats.util.assets.ModelGen;
import net.vercte.craftingmats.util.assets.LangGen;
import net.vercte.craftingmats.util.data.BlockTagGen;
import net.vercte.craftingmats.util.data.StandardRecipeProvider;

public class CraftingMatDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        Pack pack = generator.createPack();

        pack.addProvider(ModelGen::new);
        pack.addProvider(LangGen::new);

        pack.addProvider(BlockTagGen::new);
        pack.addProvider(StandardRecipeProvider::new);
    }
}

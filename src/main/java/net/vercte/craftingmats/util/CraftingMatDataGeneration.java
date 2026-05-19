package net.vercte.craftingmats.util;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.vercte.craftingmats.util.assets.ItemModelGen;
import net.vercte.craftingmats.util.assets.LangGen;
import net.vercte.craftingmats.util.data.BlockTagGen;
import net.vercte.craftingmats.util.data.StandardRecipeProvider;

import java.util.concurrent.CompletableFuture;

public class CraftingMatDataGeneration {
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if(event.includeClient()) {
            generator.addProvider(true, new ItemModelGen(output, existingFileHelper));
            generator.addProvider(true, new LangGen(output));
        }

        if(event.includeServer()) {
            generator.addProvider(true, new BlockTagGen(output, lookupProvider, existingFileHelper));
            generator.addProvider(true, new StandardRecipeProvider(output));
        }
    }
}

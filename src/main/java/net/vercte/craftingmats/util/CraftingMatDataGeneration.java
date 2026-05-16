package net.vercte.craftingmats.util;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.vercte.craftingmats.util.assets.ItemModelGen;
import net.vercte.craftingmats.util.assets.LangGen;
import net.vercte.craftingmats.util.data.BlockTagGen;
import net.vercte.craftingmats.util.data.EntityTagGen;
import net.vercte.craftingmats.util.data.ItemTagGen;
import net.vercte.craftingmats.util.data.StandardRecipeProvider;

import java.util.concurrent.CompletableFuture;

public class CraftingMatDataGeneration {
    public static void gatherData(final GatherDataEvent event) {
        PackOutput output = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if(event.includeClient()) {
            event.addProvider(new ItemModelGen(output, existingFileHelper));
            event.addProvider(new LangGen(output));
        }

        if(event.includeServer()) {
            event.addProvider(new EntityTagGen(output, lookupProvider, existingFileHelper));
            event.addProvider(new BlockTagGen(output, lookupProvider, existingFileHelper));
            event.addProvider(new ItemTagGen(output, lookupProvider));
            event.addProvider(new StandardRecipeProvider(output, lookupProvider));
        }
    }
}

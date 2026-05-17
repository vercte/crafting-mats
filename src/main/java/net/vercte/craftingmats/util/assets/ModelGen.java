package net.vercte.craftingmats.util.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.vercte.craftingmats.CraftingMats;

public class ModelGen extends FabricModelProvider {
    public ModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        ResourceLocation matLocation = BuiltInRegistries.ITEM.getResourceKey(CraftingMats.CRAFTING_MAT_ITEM)
                .orElseThrow()
                .location()
                .withPrefix("item/");
        itemModelGenerator.generateLayeredItem(
                matLocation,
                matLocation.withSuffix("_paper"),
                matLocation.withSuffix("_grid"),
                matLocation
        );
    }
}

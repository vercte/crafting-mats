package net.vercte.craftingmats.util.assets;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.vercte.craftingmats.CraftingMats;

public class ItemModelGen extends ItemModelProvider {
    public ItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CraftingMats.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModelFile item_generated = new ModelFile.UncheckedModelFile("item/generated");

        ResourceLocation crafting_mat = CraftingMats.at("item/crafting_mat");

        ItemModelBuilder heldModel = nested().parent(item_generated)
                .texture("layer0", crafting_mat.withSuffix("_paper"))
                .texture("layer1", crafting_mat.withSuffix("_grid"))
                .texture("layer2", crafting_mat);

        ItemModelBuilder entityModel = nested().parent(getExistingFile(CraftingMats.at("block/crafting_mat")));

        withExistingParent(crafting_mat.toString(), "item/generated")
                .texture("layer0", crafting_mat.withSuffix("_paper"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(heldModel)
                .perspective(ItemDisplayContext.valueOf("CRAFTING_MATS_CRAFTING_MAT"), entityModel);
    }
}

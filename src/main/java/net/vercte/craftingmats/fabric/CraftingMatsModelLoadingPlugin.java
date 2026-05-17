package net.vercte.craftingmats.fabric;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.resources.ResourceLocation;
import net.vercte.craftingmats.CraftingMats;

public class CraftingMatsModelLoadingPlugin implements ModelLoadingPlugin {
    public static final ResourceLocation CRAFTING_MAT_WORLD_MODEL = CraftingMats.at("block/crafting_mat");

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.addModels(CRAFTING_MAT_WORLD_MODEL);
    }
}

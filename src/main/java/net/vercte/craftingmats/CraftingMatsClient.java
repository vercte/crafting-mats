package net.vercte.craftingmats;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.vercte.craftingmats.fabric.CraftingMatsModelLoadingPlugin;
import net.vercte.craftingmats.mat.CraftingMat;
import net.vercte.craftingmats.mat.CraftingMatRenderer;

public class CraftingMatsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(CraftingMats.CRAFTING_MAT, CraftingMatRenderer::new);

        ModelLoadingPlugin.register(new CraftingMatsModelLoadingPlugin());

        ColorProviderRegistry.ITEM.register((stack, layer) -> {
            if(stack.is(CraftingMats.CRAFTING_MAT_ITEM)) {
                if(layer == 0) return CraftingMat.calculateColor(stack, false);
                if(layer == 1) return CraftingMat.calculateColor(stack, true);
            }
            return 0xffffffff;
        }, CraftingMats.CRAFTING_MAT_ITEM);
    }
}

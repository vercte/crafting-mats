package net.vercte.craftingmats;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.vercte.craftingmats.mat.CraftingMat;
import net.vercte.craftingmats.mat.CraftingMatRenderer;

@Mod(value = CraftingMats.ID, dist = Dist.CLIENT)
public class CraftingMatsClient {
    public CraftingMatsClient(IEventBus bus) {
        bus.addListener(this::registerEntityRenderers);
        bus.addListener(this::registerItemColorHandlers);
    }

    private void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        EntityRenderers.register(CraftingMats.CRAFTING_MAT.get(), CraftingMatRenderer<CraftingMat>::new);
    }

    public void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((stack, layer) -> {
            if(stack.is(CraftingMats.CRAFTING_MAT_ITEM)) {
                if(layer == 0) return CraftingMat.calculateColor(stack, false);
                if(layer == 1) return CraftingMat.calculateColor(stack, true);
            }
            return 0xffffffff;
        }, CraftingMats.CRAFTING_MAT_ITEM.get());
    }
}

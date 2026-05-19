package net.vercte.craftingmats;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vercte.craftingmats.mat.CraftingMat;
import net.vercte.craftingmats.mat.CraftingMatRenderer;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = CraftingMats.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CraftingMatsClient {
    @SubscribeEvent
    public static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CraftingMats.CRAFTING_MAT.get(), CraftingMatRenderer<CraftingMat>::new);
    }

    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((stack, layer) -> {
            if(stack.is(CraftingMats.CRAFTING_MAT_ITEM.get())) {
                if(layer == 0) return CraftingMat.calculateColor(stack, false);
                if(layer == 1) return CraftingMat.calculateColor(stack, true);
            }
            return 0xffffffff;
        }, CraftingMats.CRAFTING_MAT_ITEM.get());
    }
}

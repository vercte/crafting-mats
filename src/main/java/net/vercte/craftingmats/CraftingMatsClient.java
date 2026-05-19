package net.vercte.craftingmats;

import com.mojang.logging.LogUtils;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.vercte.craftingmats.mat.CraftingMat;
import net.vercte.craftingmats.mat.CraftingMatRenderer;

public class CraftingMatsClient {
    public static void init(final FMLClientSetupEvent event) {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(CraftingMatsClient::registerEntityRenderers);
        bus.addListener(CraftingMatsClient::registerItemColorHandlers);
    }

    private static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        LogUtils.getLogger().info("I sure hope this gets called!");
        event.registerEntityRenderer(CraftingMats.CRAFTING_MAT.get(), CraftingMatRenderer<CraftingMat>::new);
    }

    private static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((stack, layer) -> {
            if(stack.is(CraftingMats.CRAFTING_MAT_ITEM.get())) {
                if(layer == 0) return CraftingMat.calculateColor(stack, false);
                if(layer == 1) return CraftingMat.calculateColor(stack, true);
            }
            return 0xffffffff;
        }, CraftingMats.CRAFTING_MAT_ITEM.get());
    }
}

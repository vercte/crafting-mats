package net.vercte.craftingmats;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.vercte.craftingmats.mat.CraftingMat;
import net.vercte.craftingmats.mat.CraftingMatItem;
import net.vercte.craftingmats.util.CraftingMatDataGeneration;

@Mod(CraftingMats.ID)
public class CraftingMats {
    public static final String ID = "crafting_mats";

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ID);

    public static final DeferredItem<CraftingMatItem> CRAFTING_MAT_ITEM = ITEMS.registerItem(
            "crafting_mat", CraftingMatItem::new,
            new Item.Properties().stacksTo(1)
    );

    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ID);

    public static final DeferredHolder<EntityType<?>, EntityType<CraftingMat>> CRAFTING_MAT = ENTITIES.register(
            "crafting_mat",
            () -> EntityType.Builder.<CraftingMat>of(CraftingMat::new, MobCategory.MISC)
                    .sized(1.05f, 1.05f)
                    .eyeHeight(0)
                    .clientTrackingRange(10)
                    .updateInterval(Integer.MAX_VALUE)
                    .build("crafting_mats:crafting_mat")
    );

    public CraftingMats(IEventBus bus) {
        ITEMS.register(bus);
        ENTITIES.register(bus);

        bus.addListener(this::initExtra);
        bus.addListener(this::creativeTabBuild);
        bus.addListener(CraftingMatDataGeneration::gatherData);
    }

    private void initExtra(final FMLCommonSetupEvent event) {
        CauldronInteraction.WATER.map().put(CRAFTING_MAT_ITEM.get(), CauldronInteraction.DYED_ITEM);
    }

    private void creativeTabBuild(final BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.insertAfter(new ItemStack(Items.MAP), CRAFTING_MAT_ITEM.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    public static ResourceLocation at(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}

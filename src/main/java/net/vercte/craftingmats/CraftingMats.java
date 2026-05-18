package net.vercte.craftingmats;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.vercte.craftingmats.mat.CraftingMat;
import net.vercte.craftingmats.mat.CraftingMatItem;

public class CraftingMats implements ModInitializer {
    public static final String ID = "crafting_mats";

    public static final CraftingMatItem CRAFTING_MAT_ITEM = Registry.register(
            BuiltInRegistries.ITEM,
            at("crafting_mat"),
            new CraftingMatItem(
                    new Item.Properties().stacksTo(1)
            )
    );

    public static final EntityType<CraftingMat> CRAFTING_MAT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            at("crafting_mat"),
            EntityType.Builder.<CraftingMat>of(CraftingMat::new, MobCategory.MISC)
                    .sized(1.05f, 1.05f)
                    .eyeHeight(0)
                    .clientTrackingRange(10)
                    .updateInterval(Integer.MAX_VALUE)
                    .build("crafting_mats:crafting_mat")
    );

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(this::creativeTabBuild);

        initExtra();
    }

    private void creativeTabBuild(FabricItemGroupEntries entries) {
        entries.addAfter(
                new ItemStack(Items.MAP),
                CRAFTING_MAT_ITEM.getDefaultInstance()
        );
    }

    public void initExtra() {
        CauldronInteraction.WATER.map().put(CRAFTING_MAT_ITEM, CauldronInteraction.DYED_ITEM);
    }

    public static ResourceLocation at(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}

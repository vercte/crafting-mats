package net.vercte.craftingmats;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.vercte.craftingmats.mat.CraftingMat;
import net.vercte.craftingmats.mat.CraftingMatItem;

public class CraftingMats implements ModInitializer {
    public static final String ID = "crafting_mats";

    public static final CraftingMatItem CRAFTING_MAT_ITEM = Registry.register(
            BuiltInRegistries.ITEM,
            at("crafting_mat"),
            new CraftingMatItem(new Item.Properties())
    );

    public static final EntityType<CraftingMat> CRAFTING_MAT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            at("crafting_mat"),
            EntityType.Builder.<CraftingMat>of(CraftingMat::new, MobCategory.MISC)
                    .sized(1.05f, 1.05f)
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

    private void initExtra() {
        CauldronInteraction.WATER.put(CRAFTING_MAT_ITEM, (state, level, pos, player, hand, stack) -> {
            if (!stack.is(CRAFTING_MAT_ITEM)) {
                return InteractionResult.PASS;
            } else if (!CRAFTING_MAT_ITEM.hasCustomColor(stack)) {
                return InteractionResult.PASS;
            } else {
                if (!level.isClientSide) {
                    CRAFTING_MAT_ITEM.clearColor(stack);
                    player.awardStat(Stats.CLEAN_ARMOR);
                    LayeredCauldronBlock.lowerFillLevel(state, level, pos);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        });
    }

    public static ResourceLocation at(String path) {
        return new ResourceLocation(ID, path);
    }
}

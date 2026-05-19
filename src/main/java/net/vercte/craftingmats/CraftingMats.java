package net.vercte.craftingmats;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.vercte.craftingmats.mat.CraftingMat;
import net.vercte.craftingmats.mat.CraftingMatItem;
import net.vercte.craftingmats.util.CraftingMatDataGeneration;

import java.util.function.Supplier;

@Mod(CraftingMats.ID)
public class CraftingMats {
    public static final String ID = "crafting_mats";

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ID);

    public static final Supplier<CraftingMatItem> CRAFTING_MAT_ITEM = ITEMS.register(
            "crafting_mat",
            () -> new CraftingMatItem(new Item.Properties().stacksTo(1))
    );

    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ID);

    public static final Supplier<EntityType<CraftingMat>> CRAFTING_MAT = ENTITIES.register(
            "crafting_mat",
            () -> EntityType.Builder.<CraftingMat>of(CraftingMat::new, MobCategory.MISC)
                    .sized(1.05f, 1.05f)
                    .clientTrackingRange(10)
                    .updateInterval(Integer.MAX_VALUE)
                    .build("crafting_mats:crafting_mat")
    );

    public CraftingMats() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ITEMS.register(bus);
        ENTITIES.register(bus);

        ItemDisplayContext.create("CRAFTING_MATS_CRAFTING_MAT", at("crafting_mat"), ItemDisplayContext.HEAD);

        bus.addListener(this::initExtra);
        bus.addListener(this::creativeTabBuild);
        bus.addListener(CraftingMatDataGeneration::gatherData);
    }

    private void initExtra(final FMLCommonSetupEvent event) {
        CauldronInteraction.WATER.put(CRAFTING_MAT_ITEM.get(), (state, level, pos, player, hand, stack) -> {
            CraftingMatItem item = CraftingMats.CRAFTING_MAT_ITEM.get();
            if (!stack.is(item)) {
                return InteractionResult.PASS;
            } else if (!item.hasCustomColor(stack)) {
                return InteractionResult.PASS;
            } else {
                if (!level.isClientSide) {
                    item.clearColor(stack);
                    player.awardStat(Stats.CLEAN_ARMOR);
                    LayeredCauldronBlock.lowerFillLevel(state, level, pos);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        });
    }

    private void creativeTabBuild(final BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.getEntries().putAfter(new ItemStack(Items.MAP), CRAFTING_MAT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    public static ResourceLocation at(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}

package net.vercte.craftingmats.mixin;

import net.minecraft.world.item.ItemDisplayContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinIntrinsics;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemDisplayContext.class)
public enum ItemDisplayContextMixin {
    @SuppressWarnings("AddedEnumConstantsNamePattern")
    CRAFTING_MATS_CRAFTING_MAT(MixinIntrinsics.currentEnumOrdinal(), "crafting_mats:crafting_mat");

    @Shadow
    ItemDisplayContextMixin(int string, String i) {
    }
}

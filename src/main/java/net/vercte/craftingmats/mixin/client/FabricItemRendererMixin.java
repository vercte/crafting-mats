package net.vercte.craftingmats.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.vercte.craftingmats.CraftingMats;
import net.vercte.craftingmats.fabric.CraftingMatsModelLoadingPlugin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class FabricItemRendererMixin {
    @Shadow
    @Final
    private ItemModelShaper itemModelShaper;

    @ModifyVariable(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/BakedModel;getTransforms()Lnet/minecraft/client/renderer/block/model/ItemTransforms;"), argsOnly = true, name = "bakedModel")
    public BakedModel modifyMatModel(BakedModel bakedModel, @Local(argsOnly = true, name = "itemStack") ItemStack itemStack, @Local(argsOnly = true, name = "itemDisplayContext") ItemDisplayContext itemDisplayContext) {
        if(itemStack.is(CraftingMats.CRAFTING_MAT_ITEM) && itemDisplayContext == ItemDisplayContext.valueOf("CRAFTING_MATS_CRAFTING_MAT")) {
            return this.itemModelShaper.getModelManager().getModel(
                    CraftingMatsModelLoadingPlugin.CRAFTING_MAT_WORLD_MODEL
            );
        }

        return bakedModel;
    }
}

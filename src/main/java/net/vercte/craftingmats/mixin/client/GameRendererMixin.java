package net.vercte.craftingmats.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.vercte.craftingmats.mat.CraftingMat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @WrapOperation(method = "lambda$pick$57", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isPickable()Z"))
    private static boolean dontPickMatsWhenCrouching(Entity entity, Operation<Boolean> original) {
        if(!original.call(entity)) return false;
        if(!(entity instanceof CraftingMat)) return true;

        Player player = Minecraft.getInstance().player;
        assert player != null;

        ItemStack mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack offHand = player.getItemInHand(InteractionHand.OFF_HAND);
        boolean mainBlock = mainHand.getItem() instanceof BlockItem;
        boolean offBlock = offHand.getItem() instanceof BlockItem;

        return !(
                (mainBlock || offBlock) && player.isCrouching()
        );
    }
}

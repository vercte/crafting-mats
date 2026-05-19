package net.vercte.craftingmats.mat;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.vercte.craftingmats.CraftingMatTags;
import org.jetbrains.annotations.NotNull;

public class CraftingMatItem extends Item implements DyeableLeatherItem {
    public CraftingMatItem(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public InteractionResult useOn(@NotNull UseOnContext context) {
        boolean canPlace = canPlaceOn(context);
        if(!canPlace) return InteractionResult.PASS;

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if(!level.isClientSide) {
            CraftingMat mat = new CraftingMat(context.getItemInHand().copy(), level, pos);
            level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, pos);
            level.addFreshEntity(mat);
        }

        context.getItemInHand().shrink(1);

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    private boolean canPlaceOn(@NotNull UseOnContext context) {
        if(context.getClickedFace() != Direction.UP) return false;
        if(context.isInside()) return false;

        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        VoxelShape shape = state.getShape(level, pos);

        if(level.isOutsideBuildHeight(pos)) return false;
        if(player != null && !player.mayUseItemAt(pos, context.getClickedFace(), context.getItemInHand())) return false;

        if(state.is(CraftingMatTags.SUPPORTS_MAT)) return true;
        if(state.getBlock() instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) return true;
        if(state.getBlock() instanceof TrapDoorBlock && state.getValue(TrapDoorBlock.HALF) == Half.BOTTOM && !state.getValue(TrapDoorBlock.OPEN)) return true;

        return Block.isFaceFull(shape, Direction.UP);
    }

    public int getColor(ItemStack stack) {
        CompoundTag compoundtag = stack.getTagElement("display");
        return compoundtag != null && compoundtag.contains("color", Tag.TAG_ANY_NUMERIC) ? compoundtag.getInt("color") : CraftingMat.DEFAULT_PAPER_COLOR;
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack stack, @NotNull ItemStack other, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        if(stack.isEmpty() || other.isEmpty()) return false;
        if(!other.is(this)) return false;
        if(hasDisplay(stack) || hasDisplay(other)) return false;

        int maxMoved = stack.getMaxStackSize() - stack.getCount();
        if(maxMoved == 0) return false;

        int moved = action == ClickAction.SECONDARY ? 1 : Math.min(maxMoved, other.getCount());

        other.shrink(moved);
        stack.grow(moved);
        return true;
    }

    @SuppressWarnings("DataFlowIssue")
    private static boolean hasDisplay(ItemStack stack) {
        return stack.hasTag() &&
                stack.getTag().contains("display") &&
                !stack.getTagElement("display").isEmpty();
    }
}

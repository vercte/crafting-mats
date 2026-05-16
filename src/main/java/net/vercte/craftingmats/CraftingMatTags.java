package net.vercte.craftingmats;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CraftingMatTags {
    public static final TagKey<Block> SUPPORTS_MAT = TagKey.create(Registries.BLOCK, CraftingMats.at("supports_block"));
}

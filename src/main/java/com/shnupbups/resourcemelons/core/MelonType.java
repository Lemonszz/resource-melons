package com.shnupbups.resourcemelons.core;

import com.shnupbups.resourcemelons.ResourceMelons;
import com.shnupbups.resourcemelons.block.ResourceAttachedStemBlock;
import com.shnupbups.resourcemelons.block.ResourceMelonBlock;
import com.shnupbups.resourcemelons.block.ResourceStemBlock;
import com.shnupbups.resourcemelons.item.ResourceMelonSliceItem;
import com.shnupbups.resourcemelons.misc.MelonDispenserBehavior;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public record MelonType(Identifier id, ResourceStemBlock stem, ResourceAttachedStemBlock attachedStem, ResourceMelonBlock melon, Item seeds, ResourceMelonSliceItem slice, Catalyst catalyst, int colour) {
    public void register() {
        Registry.register(Registry.BLOCK, new Identifier(id().getNamespace(), id().getPath() + "_melon"), melon);
        createBlockItem(melon, new Identifier(id().getNamespace(), id().getPath() + "_melon"));

        Registry.register(Registry.BLOCK, new Identifier(id().getNamespace(), id().getPath() + "_stem"), stem);
        Registry.register(Registry.BLOCK, new Identifier(id().getNamespace(), id().getPath() + "_attached_stem"), attachedStem);

        Registry.register(Registry.ITEM, new Identifier(id().getNamespace(), id().getPath() + "_seeds"), seeds);
        Registry.register(Registry.ITEM, new Identifier(id().getNamespace(), id().getPath() + "_slice"), slice);

        DispenserBlock.registerBehavior(slice, new MelonDispenserBehavior());
    }

    private static void createBlockItem(Block block, Identifier id) {
        BlockItem item = new BlockItem(block, new Item.Settings().group(ResourceMelons.group));
        item.appendBlocks(Item.BLOCK_ITEMS, item);

        Registry.register(Registry.ITEM, new Identifier(id.getNamespace(), id.getPath()), item);
    }
}

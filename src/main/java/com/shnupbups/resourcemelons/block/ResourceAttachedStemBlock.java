package com.shnupbups.resourcemelons.block;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.GourdBlock;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class ResourceAttachedStemBlock extends AttachedStemBlock {
    public ResourceAttachedStemBlock(GourdBlock melon, Supplier<Item> pickBlockItem, Settings settings) {
        super(melon, pickBlockItem, settings);
    }
}

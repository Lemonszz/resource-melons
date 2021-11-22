package com.shnupbups.resourcemelons.block;

import java.util.function.Supplier;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.GourdBlock;
import net.minecraft.item.Item;

public class ResourceAttachedStemBlock extends AttachedStemBlock {
	public ResourceAttachedStemBlock(GourdBlock melon, Supplier<Item> pickBlockItem, Settings settings) {
		super(melon, pickBlockItem, settings);
	}
}

package com.shnupbups.resourcemelons.core;

import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.block.ResourceMelonAttachedStemBlock;
import com.shnupbups.resourcemelons.block.ResourceMelonBlock;
import com.shnupbups.resourcemelons.block.ResourceMelonStemBlock;
import com.shnupbups.resourcemelons.item.ResourceMelonSliceItem;
import com.shnupbups.resourcemelons.misc.ResourceMelonSliceDispenserBehavior;

public record MelonType(Identifier id, Item resource, Block resourceBlock, ResourceMelonStemBlock stem,
						ResourceMelonAttachedStemBlock attachedStem,
						ResourceMelonBlock melon, Item seeds, ResourceMelonSliceItem slice, Tag<Block> catalyst,
						int colour, float resourceChanceMultiplier, float seedsChanceMultiplier, int miningLevel) {
	private static void createBlockItem(Block block, Identifier id) {
		BlockItem item = new BlockItem(block, new Item.Settings().group(RMCommon.getMelonBlockGroup()));
		item.appendBlocks(Item.BLOCK_ITEMS, item);

		Registry.register(Registry.ITEM, new Identifier(id.getNamespace(), id.getPath()), item);
	}

	public void register() {
		String path = id().getPath() + "_melon";

		Registry.register(Registry.BLOCK, new Identifier(id().getNamespace(), path), melon);
		createBlockItem(melon, new Identifier(id().getNamespace(), path));

		Registry.register(Registry.BLOCK, new Identifier(id().getNamespace(), path + "_stem"), stem);
		Registry.register(Registry.BLOCK, new Identifier(id().getNamespace(), path + "_attached_stem"), attachedStem);

		Registry.register(Registry.ITEM, new Identifier(id().getNamespace(), path + "_seeds"), seeds);
		Registry.register(Registry.ITEM, new Identifier(id().getNamespace(), path + "_slice"), slice);

		DispenserBlock.registerBehavior(slice, new ResourceMelonSliceDispenserBehavior());
	}
}

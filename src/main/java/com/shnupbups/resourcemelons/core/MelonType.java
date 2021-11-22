package com.shnupbups.resourcemelons.core;

import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import com.shnupbups.resourcemelons.ResourceMelons;
import com.shnupbups.resourcemelons.block.ResourceAttachedStemBlock;
import com.shnupbups.resourcemelons.block.ResourceMelonBlock;
import com.shnupbups.resourcemelons.block.ResourceStemBlock;
import com.shnupbups.resourcemelons.item.ResourceMelonSliceItem;
import com.shnupbups.resourcemelons.misc.MelonDispenserBehavior;

public record MelonType(Identifier id, ResourceStemBlock stem, ResourceAttachedStemBlock attachedStem,
						ResourceMelonBlock melon, Item seeds, ResourceMelonSliceItem slice, Catalyst catalyst,
						int colour) {
	private static void createBlockItem(Block block, Identifier id) {
		BlockItem item = new BlockItem(block, new Item.Settings().group(ResourceMelons.getMelonBlockGroup()));
		item.appendBlocks(Item.BLOCK_ITEMS, item);

		Registry.register(Registry.ITEM, new Identifier(id.getNamespace(), id.getPath()), item);
	}

	public void register() {
		String path = id().getPath();

		Registry.register(Registry.BLOCK, new Identifier(id().getNamespace(), path + "_melon"), melon);
		createBlockItem(melon, new Identifier(id().getNamespace(), path + "_melon"));

		Registry.register(Registry.BLOCK, new Identifier(id().getNamespace(), path + "_stem"), stem);
		Registry.register(Registry.BLOCK, new Identifier(id().getNamespace(), path + "_attached_stem"), attachedStem);

		Registry.register(Registry.ITEM, new Identifier(id().getNamespace(), path + "_seeds"), seeds);
		Registry.register(Registry.ITEM, new Identifier(id().getNamespace(), path + "_slice"), slice);

		DispenserBlock.registerBehavior(slice, new MelonDispenserBehavior());
	}
}

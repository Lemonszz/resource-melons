package com.shnupbups.resourcemelons.core;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import net.fabricmc.yarn.constants.MiningLevels;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.misc.RMTags;

public enum DefaultMelonTypes implements MelonTypeBuilder {
	DIAMOND("diamond", Items.DIAMOND, Blocks.DIAMOND_BLOCK, 0x4AEDD9, RMTags.BlockTags.DIAMOND_MELON_CATALYSTS, MiningLevels.IRON),
	GOLD("gold", Items.GOLD_INGOT, Blocks.GOLD_BLOCK, 0xFDF55F, RMTags.BlockTags.GOLD_MELON_CATALYSTS, MiningLevels.IRON),
	EMERALD("emerald", Items.EMERALD, Blocks.EMERALD_BLOCK, 0x41F384, RMTags.BlockTags.EMERALD_MELON_CATALYSTS, MiningLevels.IRON),
	IRON("iron", Items.IRON_INGOT, Blocks.IRON_BLOCK, 0xFEDEC8, RMTags.BlockTags.IRON_MELON_CATALYSTS, MiningLevels.STONE),
	LAPIS("lapis", Items.LAPIS_LAZULI, Blocks.LAPIS_BLOCK, 0x7497EA, RMTags.BlockTags.LAPIS_MELON_CATALYSTS, MiningLevels.STONE),
	REDSTONE("redstone", Items.REDSTONE, Blocks.REDSTONE_BLOCK, 0xFF0000, RMTags.BlockTags.REDSTONE_MELON_CATALYSTS, MiningLevels.IRON),
	COAL("coal", Items.COAL, Blocks.COAL_BLOCK, 0x2E2E2E, RMTags.BlockTags.COAL_MELON_CATALYSTS),
	QUARTZ("quartz", Items.QUARTZ, Blocks.QUARTZ_BLOCK, 0xEAE5DE, RMTags.BlockTags.QUARTZ_MELON_CATALYSTS),
	GLOWSTONE("glowstone", Items.GLOWSTONE_DUST, Blocks.GLOWSTONE, 0xFFEC8E, RMTags.BlockTags.GLOWSTONE_MELON_CATALYSTS),
	COPPER("copper", Items.COPPER_INGOT, Blocks.COPPER_BLOCK, 0xE77C56, RMTags.BlockTags.COPPER_MELON_CATALYSTS, MiningLevels.STONE),
	AMETHYST("amethyst", Items.AMETHYST_SHARD, Blocks.AMETHYST_BLOCK, 0xB38EF3, RMTags.BlockTags.AMETHYST_MELON_CATALYSTS),
	NETHERITE("netherite", Items.NETHERITE_SCRAP, Blocks.NETHERITE_BLOCK, 0x654740, RMTags.BlockTags.NETHERITE_MELON_CATALYSTS, MiningLevels.DIAMOND);

	final Info info;
	MelonType type;

	DefaultMelonTypes(String id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst) {
		this(RMCommon.id(id), resource, resourceBlock, colour, catalyst, 0);
	}

	DefaultMelonTypes(String id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst, float chanceMultiplier) {
		this(RMCommon.id(id), resource, resourceBlock, colour, catalyst, chanceMultiplier, chanceMultiplier, 0);
	}

	DefaultMelonTypes(String id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst, float resourceChanceMultiplier, float seedsChanceMultiplier) {
		this(RMCommon.id(id), resource, resourceBlock, colour, catalyst, resourceChanceMultiplier, seedsChanceMultiplier, 0);
	}

	DefaultMelonTypes(Identifier id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst) {
		this(id, resource, resourceBlock, colour, catalyst, 1.0f, 0);
	}

	DefaultMelonTypes(Identifier id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst, float chanceMultiplier) {
		this(id, resource, resourceBlock, colour, catalyst, chanceMultiplier, chanceMultiplier, 0);
	}

	DefaultMelonTypes(String id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst, int miningLevel) {
		this(RMCommon.id(id), resource, resourceBlock, colour, catalyst, miningLevel);
	}

	DefaultMelonTypes(String id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst, float chanceMultiplier, int miningLevel) {
		this(RMCommon.id(id), resource, resourceBlock, colour, catalyst, chanceMultiplier, chanceMultiplier, miningLevel);
	}

	DefaultMelonTypes(String id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst, float resourceChanceMultiplier, float seedsChanceMultiplier, int miningLevel) {
		this(RMCommon.id(id), resource, resourceBlock, colour, catalyst, resourceChanceMultiplier, seedsChanceMultiplier, miningLevel);
	}

	DefaultMelonTypes(Identifier id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst, int miningLevel) {
		this(id, resource, resourceBlock, colour, catalyst, 1.0f, miningLevel);
	}

	DefaultMelonTypes(Identifier id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst, float chanceMultiplier, int miningLevel) {
		this(id, resource, resourceBlock, colour, catalyst, chanceMultiplier, chanceMultiplier, miningLevel);
	}

	DefaultMelonTypes(Identifier id, Item resource, Block resourceBlock, int colour, Tag<Block> catalyst, float resourceChanceMultiplier, float seedsChanceMultiplier, int miningLevel) {
		this.info = new Info(id, resource, resourceBlock, catalyst, colour, resourceChanceMultiplier, seedsChanceMultiplier, miningLevel);
	}

	@Override
	public Info getInfo() {
		return info;
	}

	@Override
	public void setType(MelonType type) {
		this.type = type;
	}

	@Override
	public MelonType getTypeRaw() {
		return type;
	}
}
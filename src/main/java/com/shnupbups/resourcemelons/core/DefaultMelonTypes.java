package com.shnupbups.resourcemelons.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import com.shnupbups.resourcemelons.ResourceMelons;
import com.shnupbups.resourcemelons.misc.ResourceMelonsTags;

public enum DefaultMelonTypes implements MelonTypeBuilder {
	DIAMOND("diamond", Items.DIAMOND, 0x4AEDD9, ResourceMelonsTags.BlockTags.DIAMOND_MELON_CATALYSTS),
	GOLD("gold", Items.GOLD_INGOT, 0xFDF55F, ResourceMelonsTags.BlockTags.GOLD_MELON_CATALYSTS),
	EMERALD("emerald", Items.EMERALD, 0x41F384, ResourceMelonsTags.BlockTags.EMERALD_MELON_CATALYSTS),
	IRON("iron", Items.IRON_INGOT, 0xD8D8D8, ResourceMelonsTags.BlockTags.IRON_MELON_CATALYSTS),
	LAPIS("lapis", Items.LAPIS_LAZULI, 0x7497EA, ResourceMelonsTags.BlockTags.LAPIS_MELON_CATALYSTS),
	REDSTONE("redstone", Items.REDSTONE, 0xFF0000, ResourceMelonsTags.BlockTags.REDSTONE_MELON_CATALYSTS),
	COAL("coal", Items.COAL, 0x2E2E2E, ResourceMelonsTags.BlockTags.COAL_MELON_CATALYSTS),
	QUARTZ("quartz", Items.QUARTZ, 0xEEE6DE, ResourceMelonsTags.BlockTags.QUARTZ_MELON_CATALYSTS),
	GLOWSTONE("glowstone", Items.GLOWSTONE_DUST, 0xFFEC8E, ResourceMelonsTags.BlockTags.GLOWSTONE_MELON_CATALYSTS),
	COPPER("copper", Items.COPPER_INGOT, 0xE77C56, ResourceMelonsTags.BlockTags.COPPER_MELON_CATALYSTS),
	AMETHYST("amethyst", Items.AMETHYST_SHARD, 0xB38EF3, ResourceMelonsTags.BlockTags.AMETHYST_MELON_CATALYSTS),
	NETHERITE("netherite", Items.NETHERITE_SCRAP, 0x654740, ResourceMelonsTags.BlockTags.NETHERITE_MELON_CATALYSTS);

	Info info;
	MelonType type;

	DefaultMelonTypes(String id, Item resource, int colour, Block... catalyst) {
		this(ResourceMelons.id(id), resource, colour, catalyst);
	}

	DefaultMelonTypes(Identifier id, Item resource, int colour, Block... catalyst) {
		this(id, resource, colour, new Catalyst(catalyst));
	}

	DefaultMelonTypes(String id, Item resource, int colour, Tag<Block> catalyst) {
		this(ResourceMelons.id(id), resource, colour, catalyst);
	}

	DefaultMelonTypes(Identifier id, Item resource, int colour, Tag<Block> catalyst) {
		this(id, resource, colour, new Catalyst(catalyst));
	}

	DefaultMelonTypes(String id, Item resource, int colour, Catalyst catalyst) {
		this(ResourceMelons.id(id), resource, colour, catalyst);
	}

	DefaultMelonTypes(Identifier id, Item resource, int colour, Catalyst catalyst) {
		this.info = new Info(id, resource, catalyst, colour);
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
package com.shnupbups.resourcemelons.misc;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import net.fabricmc.fabric.api.tag.TagFactory;

import com.shnupbups.resourcemelons.ResourceMelons;

public class ResourceMelonsTags {
	public static class BlockTags {
		public static final Tag.Identified<Block> DIAMOND_MELON_CATALYSTS = create("diamond_melon_catalysts");
		public static final Tag.Identified<Block> GOLD_MELON_CATALYSTS = create("gold_melon_catalysts");
		public static final Tag.Identified<Block> EMERALD_MELON_CATALYSTS = create("emerald_melon_catalysts");
		public static final Tag.Identified<Block> IRON_MELON_CATALYSTS = create("iron_melon_catalysts");
		public static final Tag.Identified<Block> LAPIS_MELON_CATALYSTS = create("lapis_melon_catalysts");
		public static final Tag.Identified<Block> REDSTONE_MELON_CATALYSTS = create("redstone_melon_catalysts");
		public static final Tag.Identified<Block> COAL_MELON_CATALYSTS = create("coal_melon_catalysts");
		public static final Tag.Identified<Block> QUARTZ_MELON_CATALYSTS = create("quartz_melon_catalysts");
		public static final Tag.Identified<Block> GLOWSTONE_MELON_CATALYSTS = create("redstone_melon_catalysts");
		public static final Tag.Identified<Block> COPPER_MELON_CATALYSTS = create("copper_melon_catalysts");
		public static final Tag.Identified<Block> AMETHYST_MELON_CATALYSTS = create("amethyst_melon_catalysts");
		public static final Tag.Identified<Block> NETHERITE_MELON_CATALYSTS = create("netherite_melon_catalysts");

		public static void init() {

		}

		public static Tag.Identified<Block> create(String id) {
			return TagFactory.BLOCK.create(ResourceMelons.id(id));
		}
	}

	public static class ItemTags {
		public static final Tag.Identified<Item> RESOURCE_MELON_SLICES = create("resource_melon_slices");

		public static void init() {

		}

		public static Tag.Identified<Item> create(String id) {
			return TagFactory.ITEM.create(ResourceMelons.id(id));
		}
	}
}

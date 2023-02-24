package com.shnupbups.resourcemelons.misc;

import com.shnupbups.resourcemelons.RMCommon;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class RMTags {
	public static class BlockTags {
		public static final TagKey<Block> UNWAXED_COPPER_BLOCKS = create("unwaxed_copper_blocks");
		public static final TagKey<Block> WAXED_COPPER_BLOCKS = create("waxed_copper_blocks");
		public static final TagKey<Block> COPPER_BLOCKS = create("copper_blocks");
		public static final TagKey<Block> UNWAXED_CUT_COPPER = create("unwaxed_cut_copper");
		public static final TagKey<Block> WAXED_CUT_COPPER = create("waxed_cut_copper");
		public static final TagKey<Block> CUT_COPPER = create("cut_copper");
		public static final TagKey<Block> QUARTZ_BLOCKS = create("quartz_blocks");

		public static final TagKey<Block> RESOURCE_MELONS = create("resource_melons");
		public static final TagKey<Block> RESOURCE_MELON_ATTACHED_STEMS = create("resource_melon_attached_stems");
		public static final TagKey<Block> RESOURCE_MELON_UNATTACHED_STEMS = create("resource_melon_unattached_stems");
		public static final TagKey<Block> RESOURCE_MELON_STEMS = create("resource_melon_stems");

		public static final TagKey<Block> DIAMOND_MELON_CATALYSTS = create("diamond_melon_catalysts");
		public static final TagKey<Block> GOLD_MELON_CATALYSTS = create("gold_melon_catalysts");
		public static final TagKey<Block> EMERALD_MELON_CATALYSTS = create("emerald_melon_catalysts");
		public static final TagKey<Block> IRON_MELON_CATALYSTS = create("iron_melon_catalysts");
		public static final TagKey<Block> LAPIS_MELON_CATALYSTS = create("lapis_melon_catalysts");
		public static final TagKey<Block> REDSTONE_MELON_CATALYSTS = create("redstone_melon_catalysts");
		public static final TagKey<Block> COAL_MELON_CATALYSTS = create("coal_melon_catalysts");
		public static final TagKey<Block> QUARTZ_MELON_CATALYSTS = create("quartz_melon_catalysts");
		public static final TagKey<Block> GLOWSTONE_MELON_CATALYSTS = create("redstone_melon_catalysts");
		public static final TagKey<Block> COPPER_MELON_CATALYSTS = create("copper_melon_catalysts");
		public static final TagKey<Block> AMETHYST_MELON_CATALYSTS = create("amethyst_melon_catalysts");
		public static final TagKey<Block> NETHERITE_MELON_CATALYSTS = create("netherite_melon_catalysts");

		public static void init() {

		}

		public static TagKey<Block> create(String id) {
			return TagKey.of(Registry.BLOCK_KEY, RMCommon.id(id));
		}
	}

	public static class ItemTags {
		public static final TagKey<Item> RESOURCE_MELON_SLICES = create("resource_melon_slices");
		public static final TagKey<Item> RESOURCE_MELON_SEEDS = create("resource_melon_seeds");
		public static final TagKey<Item> RESOURCE_MELONS = create("resource_melons");

		public static void init() {

		}

		public static TagKey<Item> create(String id) {
			return TagKey.of(Registry.ITEM_KEY, RMCommon.id(id));
		}
	}
}

package com.shnupbups.resourcemelons.datagen.provider;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.TagFactory;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.core.MelonType;
import com.shnupbups.resourcemelons.misc.RMTags;

public class RMBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
	public RMBlockTagsProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	public static Tag.Identified<Block> getMiningLevelTag(int miningLevel) {
		if (miningLevel <= 0) return null;
		return switch (miningLevel) {
			case 1 -> BlockTags.NEEDS_STONE_TOOL;
			case 2 -> BlockTags.NEEDS_IRON_TOOL;
			case 3 -> BlockTags.NEEDS_DIAMOND_TOOL;
			default -> TagFactory.BLOCK.create(new Identifier("fabric", "needs_tool_level_" + miningLevel));
		};
	}

	@Override
	protected void generateTags() {
		RMCommon.LOGGER.info("Generating block tags...");

		FabricTagBuilder<Block> resourceMelons = getOrCreateTagBuilder(RMTags.BlockTags.RESOURCE_MELONS);
		FabricTagBuilder<Block> resourceMelonUnattachedStems = getOrCreateTagBuilder(RMTags.BlockTags.RESOURCE_MELON_UNATTACHED_STEMS);
		FabricTagBuilder<Block> resourceMelonAttachedStems = getOrCreateTagBuilder(RMTags.BlockTags.RESOURCE_MELON_ATTACHED_STEMS);

		for (MelonType melonType : RMCommon.MELONS) {
			RMCommon.LOGGER.info("Generating for " + melonType.id());

			resourceMelons.add(melonType.melon());
			resourceMelonUnattachedStems.add(melonType.stem());
			resourceMelonAttachedStems.add(melonType.attachedStem());
			Tag.Identified<Block> miningLevelTag = getMiningLevelTag(melonType.miningLevel());
			if (miningLevelTag != null) {
				getOrCreateTagBuilder(miningLevelTag).add(melonType.stem(), melonType.attachedStem(), melonType.melon());
			}
		}

		getOrCreateTagBuilder(RMTags.BlockTags.RESOURCE_MELON_STEMS).addTag(RMTags.BlockTags.RESOURCE_MELON_UNATTACHED_STEMS).addTag(RMTags.BlockTags.RESOURCE_MELON_ATTACHED_STEMS);

		getOrCreateTagBuilder(RMTags.BlockTags.UNWAXED_COPPER_BLOCKS).add(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER);
		getOrCreateTagBuilder(RMTags.BlockTags.WAXED_COPPER_BLOCKS).add(Blocks.WAXED_COPPER_BLOCK, Blocks.WAXED_EXPOSED_COPPER, Blocks.WAXED_WEATHERED_COPPER, Blocks.WAXED_OXIDIZED_COPPER);
		getOrCreateTagBuilder(RMTags.BlockTags.COPPER_BLOCKS).addTag(RMTags.BlockTags.UNWAXED_COPPER_BLOCKS).addTag(RMTags.BlockTags.WAXED_COPPER_BLOCKS);

		getOrCreateTagBuilder(RMTags.BlockTags.UNWAXED_CUT_COPPER).add(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER);
		getOrCreateTagBuilder(RMTags.BlockTags.WAXED_CUT_COPPER).add(Blocks.WAXED_CUT_COPPER, Blocks.WAXED_EXPOSED_CUT_COPPER, Blocks.WAXED_WEATHERED_CUT_COPPER, Blocks.WAXED_OXIDIZED_CUT_COPPER);
		getOrCreateTagBuilder(RMTags.BlockTags.CUT_COPPER).addTag(RMTags.BlockTags.UNWAXED_CUT_COPPER).addTag(RMTags.BlockTags.WAXED_CUT_COPPER);

		getOrCreateTagBuilder(RMTags.BlockTags.QUARTZ_BLOCKS).add(Blocks.QUARTZ_BLOCK, Blocks.CHISELED_QUARTZ_BLOCK, Blocks.QUARTZ_PILLAR, Blocks.QUARTZ_BRICKS);

		RMCommon.LOGGER.info("Generating catalysts");

		getOrCreateTagBuilder(RMTags.BlockTags.DIAMOND_MELON_CATALYSTS).add(Blocks.DIAMOND_BLOCK);
		getOrCreateTagBuilder(RMTags.BlockTags.GOLD_MELON_CATALYSTS).add(Blocks.GOLD_BLOCK, Blocks.RAW_GOLD_BLOCK);
		getOrCreateTagBuilder(RMTags.BlockTags.EMERALD_MELON_CATALYSTS).add(Blocks.EMERALD_BLOCK);
		getOrCreateTagBuilder(RMTags.BlockTags.IRON_MELON_CATALYSTS).add(Blocks.IRON_BLOCK, Blocks.RAW_IRON_BLOCK);
		getOrCreateTagBuilder(RMTags.BlockTags.LAPIS_MELON_CATALYSTS).add(Blocks.LAPIS_BLOCK);
		getOrCreateTagBuilder(RMTags.BlockTags.REDSTONE_MELON_CATALYSTS).add(Blocks.REDSTONE_BLOCK);
		getOrCreateTagBuilder(RMTags.BlockTags.COAL_MELON_CATALYSTS).add(Blocks.COAL_BLOCK);
		getOrCreateTagBuilder(RMTags.BlockTags.QUARTZ_MELON_CATALYSTS).addTag(RMTags.BlockTags.QUARTZ_BLOCKS);
		getOrCreateTagBuilder(RMTags.BlockTags.GLOWSTONE_MELON_CATALYSTS).add(Blocks.GLOWSTONE);
		getOrCreateTagBuilder(RMTags.BlockTags.COPPER_MELON_CATALYSTS).addTag(RMTags.BlockTags.COPPER_BLOCKS).addTag(RMTags.BlockTags.CUT_COPPER).add(Blocks.RAW_COPPER_BLOCK);
		getOrCreateTagBuilder(RMTags.BlockTags.AMETHYST_MELON_CATALYSTS).add(Blocks.AMETHYST_BLOCK).add(Blocks.BUDDING_AMETHYST);
		getOrCreateTagBuilder(RMTags.BlockTags.NETHERITE_MELON_CATALYSTS).add(Blocks.NETHERITE_BLOCK);

		RMCommon.LOGGER.info("Generating mineables");

		getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).addTag(RMTags.BlockTags.RESOURCE_MELONS).addTag(RMTags.BlockTags.RESOURCE_MELON_STEMS);

		RMCommon.LOGGER.info("Finished generating block tags!");
	}
}

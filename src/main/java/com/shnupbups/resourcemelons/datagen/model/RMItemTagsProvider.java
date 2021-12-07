package com.shnupbups.resourcemelons.datagen.model;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.core.MelonType;
import com.shnupbups.resourcemelons.misc.RMTags;

public class RMItemTagsProvider extends FabricTagProvider.ItemTagProvider {
	public RMItemTagsProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void generateTags() {
		RMCommon.LOGGER.info("Generating item tags...");

		FabricTagBuilder<Item> resourceMelons = getOrCreateTagBuilder(RMTags.ItemTags.RESOURCE_MELONS);
		FabricTagBuilder<Item> resourceMelonSeeds = getOrCreateTagBuilder(RMTags.ItemTags.RESOURCE_MELON_SEEDS);
		FabricTagBuilder<Item> resourceMelonSlices = getOrCreateTagBuilder(RMTags.ItemTags.RESOURCE_MELON_SLICES);

		for (MelonType melonType : RMCommon.MELONS) {
			RMCommon.LOGGER.info("Generating for " + melonType.id());

			resourceMelons.add(melonType.melon().asItem());
			resourceMelonSeeds.add(melonType.seeds());
			resourceMelonSlices.add(melonType.slice());
		}

		RMCommon.LOGGER.info("Finished generating item tags!");
	}
}

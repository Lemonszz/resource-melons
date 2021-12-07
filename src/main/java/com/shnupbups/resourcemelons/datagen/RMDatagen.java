package com.shnupbups.resourcemelons.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.datagen.provider.RMBlockLootTablesProvider;
import com.shnupbups.resourcemelons.datagen.provider.RMBlockTagsProvider;
import com.shnupbups.resourcemelons.datagen.provider.RMItemTagsProvider;
import com.shnupbups.resourcemelons.datagen.provider.RMModelsProvider;
import com.shnupbups.resourcemelons.datagen.provider.RMRecipesProvider;

public class RMDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		RMCommon.LOGGER.info("Starting Resource Melons Datagen...");

		dataGenerator.addProvider(RMModelsProvider::new);
		dataGenerator.addProvider(RMRecipesProvider::new);
		dataGenerator.addProvider(RMBlockTagsProvider::new);
		dataGenerator.addProvider(RMItemTagsProvider::new);
		dataGenerator.addProvider(RMBlockLootTablesProvider::new);
	}
}

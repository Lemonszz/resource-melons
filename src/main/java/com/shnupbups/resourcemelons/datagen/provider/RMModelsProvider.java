package com.shnupbups.resourcemelons.datagen.provider;

import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockStateDefinitionProvider;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.core.MelonType;

public class RMModelsProvider extends FabricBlockStateDefinitionProvider {
	public RMModelsProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		RMCommon.LOGGER.info("Generating block state models...");

		for (MelonType melonType : RMCommon.MELONS) {
			RMCommon.LOGGER.info("Generating for " + melonType.id());

			registerResourceMelonBlockStateModels(blockStateModelGenerator, melonType);
		}

		RMCommon.LOGGER.info("Finished generating block state models!");
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		RMCommon.LOGGER.info("Generating item models...");

		for (MelonType melonType : RMCommon.MELONS) {
			RMCommon.LOGGER.info("Generating for " + melonType.id());

			itemModelGenerator.register(melonType.slice(), Models.GENERATED);
		}

		RMCommon.LOGGER.info("Finished generating item models!");
	}

	@Override
	public String getName() {
		return "Resource Melons BlockState Definition Provider";
	}

	public static void registerResourceMelonBlockStateModels(BlockStateModelGenerator blockStateModelGenerator, MelonType melonType) {
		blockStateModelGenerator.registerSingleton(melonType.melon(), TexturedModel.CUBE_COLUMN);
		blockStateModelGenerator.registerParentedItemModel(melonType.melon(), ModelIds.getBlockModelId(melonType.melon()));
		blockStateModelGenerator.registerItemModel(melonType.stem().asItem());
		TextureMap texture = TextureMap.stem(Blocks.MELON_STEM);
		TextureMap texture2 = TextureMap.stemAndUpper(Blocks.MELON_STEM, Blocks.ATTACHED_MELON_STEM);
		Identifier identifier = Models.STEM_FRUIT.upload(melonType.attachedStem(), texture2, blockStateModelGenerator.modelCollector);
		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(melonType.attachedStem(), BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING).register(Direction.WEST, BlockStateVariant.create()).register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270)).register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90)).register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180))));
		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(melonType.stem()).coordinate(BlockStateVariantMap.create(Properties.AGE_7).register(integer -> BlockStateVariant.create().put(VariantSettings.MODEL, Models.STEM_GROWTH_STAGES[integer].upload(melonType.stem(), texture, blockStateModelGenerator.modelCollector)))));
	}
}

package com.shnupbups.resourcemelons.datagen.provider;

import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTablesProvider;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.core.MelonType;

public class RMBlockLootTablesProvider extends FabricBlockLootTablesProvider {
	public RMBlockLootTablesProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void generateBlockLootTables() {
		RMCommon.LOGGER.info("Generating block loot tables...");

		for (MelonType melonType : RMCommon.MELONS) {
			RMCommon.LOGGER.info("Generating for " + melonType.id());

			addDrop(melonType.stem(), (block) -> BlockLootTableGenerator.cropStemDrops(block, melonType.seeds()));
			addDrop(melonType.attachedStem(), (block) -> BlockLootTableGenerator.attachedCropStemDrops(block, melonType.seeds()));
			addDrop(melonType.melon(), (block) -> BlockLootTableGenerator.dropsWithSilkTouch(block, (LootPoolEntry.Builder) BlockLootTableGenerator.applyExplosionDecay(block, ((LeafEntry.Builder) ItemEntry.builder(melonType.slice()).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 7.0f)))).apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE)).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.createMax(9))))));
		}

		RMCommon.LOGGER.info("Finished generating block loot tables!");
	}
}

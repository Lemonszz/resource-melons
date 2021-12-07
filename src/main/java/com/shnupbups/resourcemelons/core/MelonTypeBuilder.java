package com.shnupbups.resourcemelons.core;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.block.ResourceMelonAttachedStemBlock;
import com.shnupbups.resourcemelons.block.ResourceMelonBlock;
import com.shnupbups.resourcemelons.block.ResourceMelonStemBlock;
import com.shnupbups.resourcemelons.item.ResourceMelonSliceItem;

public interface MelonTypeBuilder {
	static FabricBlockSettings getMelonSettings() {
		return FabricBlockSettings.of(Material.GOURD).strength(1.0F).sounds(BlockSoundGroup.STONE).requiresTool();
	}

	static FabricBlockSettings getStemSettings() {
		return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.STEM).requiresTool();
	}

	static FabricBlockSettings getAttachedStemSettings() {
		return FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.STONE).requiresTool();
	}

	Info getInfo();

	default MelonType build() {
		ResourceMelonBlock melon = new ResourceMelonBlock(getMelonSettings());
		ResourceMelonStemBlock stem = new ResourceMelonStemBlock(melon, melon::getSeeds, getInfo().catalyst(), getStemSettings());
		ResourceMelonAttachedStemBlock attachedStem = new ResourceMelonAttachedStemBlock(melon, melon::getSeeds, getAttachedStemSettings());

		AliasedBlockItem seeds = new AliasedBlockItem(stem, new FabricItemSettings().group(RMCommon.getSeedsGroup()));
		ResourceMelonSliceItem slice = new ResourceMelonSliceItem(new Item.Settings().group(RMCommon.getMelonSliceGroup()).food(RMCommon.getMelonFoodComponent()));

		MelonType type = new MelonType(getInfo().id(), getInfo().resource(), getInfo().resourceBlock(), stem, attachedStem, melon, seeds, slice, getInfo().catalyst(), getInfo().colour(), getInfo().resourceChanceMultiplier(), getInfo().seedsChanceMultiplier(), getInfo().miningLevel());

		melon.setType(type);
		slice.setType(type);
		this.setType(type);
		return type;
	}

	MelonType getTypeRaw();

	default MelonType getType() {
		if (getTypeRaw() == null) return build();
		else return getTypeRaw();
	}

	void setType(MelonType type);

	record Info(Identifier id, Item resource, Block resourceBlock, Tag<Block> catalyst, int colour,
				float resourceChanceMultiplier, float seedsChanceMultiplier, int miningLevel) {
	}
}

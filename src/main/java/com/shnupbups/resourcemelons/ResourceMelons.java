package com.shnupbups.resourcemelons;

import java.util.ArrayList;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import com.shnupbups.resourcemelons.config.ResourceMelonsConfig;
import com.shnupbups.resourcemelons.core.DefaultMelonTypes;
import com.shnupbups.resourcemelons.core.MelonType;
import com.shnupbups.resourcemelons.item.DebugItem;

public class ResourceMelons implements ModInitializer {
	public static final String MOD_ID = "resourcemelons";
	public static ArrayList<MelonType> MELONS = new ArrayList<>();
	public static FoodComponent melonFoodComponent;

	private static ResourceMelonsConfig config;
	private static ItemGroup melonSliceGroup = ItemGroup.FOOD;
	private static ItemGroup melonBlockGroup = ItemGroup.BUILDING_BLOCKS;
	private static ItemGroup seedsGroup = ItemGroup.MISC;

	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}

	public static ItemGroup getMelonSliceGroup() {
		return melonSliceGroup;
	}

	public static ItemGroup getMelonBlockGroup() {
		return melonBlockGroup;
	}

	public static ItemGroup getSeedsGroup() {
		return seedsGroup;
	}

	public static ResourceMelonsConfig getConfig() {
		return config;
	}

	@Override
	public void onInitialize() {
		config = AutoConfig.register(ResourceMelonsConfig.class, GsonConfigSerializer::new).getConfig();

		if (getConfig().misc().creativeTabEnabled()) {
			melonSliceGroup = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(DefaultMelonTypes.DIAMOND.getType().slice()));
			melonBlockGroup = melonSliceGroup;
			seedsGroup = melonSliceGroup;
		}

		FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder();
		if (getConfig().consumption().foodComponent().alwaysEdible()) foodComponentBuilder.alwaysEdible();
		foodComponentBuilder.hunger(getConfig().consumption().foodComponent().hunger());
		foodComponentBuilder.saturationModifier(getConfig().consumption().foodComponent().saturationModifier());
		if (getConfig().consumption().foodComponent().snack()) foodComponentBuilder.snack();
		melonFoodComponent = foodComponentBuilder.build();

		for (DefaultMelonTypes melonBuilder : DefaultMelonTypes.values()) {
			MelonType type = melonBuilder.build();
			type.register();
			MELONS.add(type);
		}

		if (getConfig().misc().debugItemEnabled()) {
			Registry.register(Registry.ITEM, id("debug"), new DebugItem(new FabricItemSettings()));
		}
	}
}

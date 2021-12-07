package com.shnupbups.resourcemelons;

import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import com.shnupbups.resourcemelons.config.RMConfig;
import com.shnupbups.resourcemelons.core.DefaultMelonTypes;
import com.shnupbups.resourcemelons.core.MelonType;
import com.shnupbups.resourcemelons.item.DebugItem;

public class RMCommon implements ModInitializer {
	public static final String MOD_ID = "resourcemelons";

	public static final Logger LOGGER = LogManager.getLogger();

	public static final int CONFIG_VERSION = 1;

	public static ArrayList<MelonType> MELONS = new ArrayList<>();

	private static FoodComponent melonFoodComponent;
	private static RMConfig config;
	private static ItemGroup group;
	private static ItemGroup melonSliceGroup;
	private static ItemGroup melonBlockGroup;
	private static ItemGroup seedsGroup;

	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}

	public static ItemGroup getGroup() {
		if(group == null) {
			group = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(DefaultMelonTypes.DIAMOND.getType().slice()));
		}
		return group;
	}

	public static ItemGroup getMelonSliceGroup() {
		if(!isConfigLoaded()) loadConfig();
		if (melonSliceGroup == null) {
			if(getConfig().misc().creativeTabEnabled()) {
				melonSliceGroup = getGroup();
			} else {
				melonSliceGroup = ItemGroup.FOOD;
			}
		}
		return melonSliceGroup;
	}

	public static ItemGroup getMelonBlockGroup() {
		if(!isConfigLoaded()) loadConfig();
		if (melonBlockGroup == null) {
			if(getConfig().misc().creativeTabEnabled()) {
				melonBlockGroup = getGroup();
			} else {
				melonBlockGroup = ItemGroup.BUILDING_BLOCKS;
			}
		}
		return melonBlockGroup;
	}

	public static ItemGroup getSeedsGroup() {
		if(!isConfigLoaded()) loadConfig();
		if (seedsGroup == null) {
			if(getConfig().misc().creativeTabEnabled()) {
				seedsGroup = getGroup();
			} else {
				seedsGroup = ItemGroup.MISC;
			}
		}
		return seedsGroup;
	}

	public static FoodComponent getMelonFoodComponent() {
		if(!isConfigLoaded()) loadConfig();
		if (melonFoodComponent == null) {
			FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder();
			if (getConfig().consumption().foodComponent().alwaysEdible()) foodComponentBuilder.alwaysEdible();
			foodComponentBuilder.hunger(getConfig().consumption().foodComponent().hunger());
			foodComponentBuilder.saturationModifier(getConfig().consumption().foodComponent().saturationModifier());
			if (getConfig().consumption().foodComponent().snack()) foodComponentBuilder.snack();
			melonFoodComponent = foodComponentBuilder.build();
		}
		return melonFoodComponent;
	}

	public static boolean isConfigLoaded() {
		return config != null;
	}

	public static RMConfig getConfig() {
		if(!isConfigLoaded()) loadConfig();
		return config;
	}

	public static void loadConfig() {
		ConfigHolder<RMConfig> configHolder = AutoConfig.register(RMConfig.class, GsonConfigSerializer::new);
		config = configHolder.getConfig();

		LOGGER.info("Resource Melons config version " + getConfig().misc().configVersion());

		if (config.isConfigOutdated()) {
			LOGGER.info("Resource Melons config outdated! Attempting to update...");
			//configHolder.
		}
	}

	public static boolean randomWithPercentage(Random random, float percentage) {
		if (percentage <= 0) return false;
		else if (percentage >= 100) return true;
		else return random.nextFloat(100) <= percentage;
	}

	@Override
	public void onInitialize() {
		loadConfig();

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

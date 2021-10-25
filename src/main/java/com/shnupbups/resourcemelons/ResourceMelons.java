package com.shnupbups.resourcemelons;

import com.shnupbups.resourcemelons.core.DefaultMelonTypes;
import com.shnupbups.resourcemelons.core.MelonType;
import com.shnupbups.resourcemelons.item.DebugItem;
import com.shnupbups.resourcemelons.misc.ResourceMelonsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class ResourceMelons implements ModInitializer {
	public static final String MOD_ID = "resourcemelons";
	public static ArrayList<MelonType> MELONS = new ArrayList<>();
	public static FoodComponent melonFoodComponent;

	public static ResourceMelonsConfig config;
	public static ItemGroup group;
	
	@Override
	public void onInitialize() {
		config = AutoConfig.register(ResourceMelonsConfig.class, GsonConfigSerializer::new).getConfig();

		FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder();
		if(config.consumption.foodComponent.alwaysEdible) foodComponentBuilder.alwaysEdible();
		foodComponentBuilder.hunger(config.consumption.foodComponent.hunger);
		foodComponentBuilder.saturationModifier(config.consumption.foodComponent.saturationModifier);
		if(config.consumption.foodComponent.snack) foodComponentBuilder.snack();
		melonFoodComponent = foodComponentBuilder.build();

		group = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(DefaultMelonTypes.DIAMOND.getType().slice()));

		for(DefaultMelonTypes melonBuilder:DefaultMelonTypes.values()) {
			MelonType type = melonBuilder.build();
			type.register();
			MELONS.add(type);
		}

		if(config.misc.debugItemEnabled) {
			Registry.register(Registry.ITEM, id("debug"), new DebugItem(new FabricItemSettings()));
		}
	}

	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}
}

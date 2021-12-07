package com.shnupbups.resourcemelons.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.rei.display.ResourceMelonConsumptionDisplay;

public class RMPlugin implements REIServerPlugin {
	public static final CategoryIdentifier<ResourceMelonConsumptionDisplay> MELON_CONSUMPTION = CategoryIdentifier.of(RMCommon.MOD_ID, "plugins/melon_consumption");

	@Override
	public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
		registry.register(MELON_CONSUMPTION, ResourceMelonConsumptionDisplay.serializer());
	}
}

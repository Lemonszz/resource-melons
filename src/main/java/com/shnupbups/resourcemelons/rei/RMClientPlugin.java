package com.shnupbups.resourcemelons.rei;

import net.minecraft.block.Blocks;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.core.MelonType;
import com.shnupbups.resourcemelons.misc.ResourceMelonSliceDispenserBehavior;
import com.shnupbups.resourcemelons.rei.category.ResourceMelonConsumptionCategory;
import com.shnupbups.resourcemelons.rei.display.ResourceMelonConsumptionDisplay;

public class RMClientPlugin implements REIClientPlugin {
	@Override
	public void registerCategories(CategoryRegistry registry) {
		registry.add(new ResourceMelonConsumptionCategory());
		registry.removePlusButton(RMPlugin.MELON_CONSUMPTION);
		if (ResourceMelonSliceDispenserBehavior.isEnabled()) {
			registry.addWorkstations(RMPlugin.MELON_CONSUMPTION, EntryIngredients.of(Blocks.DISPENSER));
		}
	}

	@Override
	public void registerDisplays(DisplayRegistry registry) {
		for (MelonType melonType : RMCommon.MELONS) {
			registry.add(new ResourceMelonConsumptionDisplay(melonType));
		}
	}
}

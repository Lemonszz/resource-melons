package com.shnupbups.resourcemelons.rei.display;

import java.util.Collections;
import java.util.List;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import com.shnupbups.resourcemelons.core.MelonType;
import com.shnupbups.resourcemelons.rei.RMPlugin;

public class ResourceMelonConsumptionDisplay extends BasicDisplay {
	public MelonType melonType;

	public ResourceMelonConsumptionDisplay(MelonType melonType) {
		super(Collections.singletonList(EntryIngredients.of(melonType.slice())), List.of(EntryIngredients.of(melonType.slice().getResourceStack()), EntryIngredients.of(melonType.slice().getSeedsStack())));
		this.melonType = melonType;
	}

	public ResourceMelonConsumptionDisplay(List<EntryIngredient> input, List<EntryIngredient> output) {
		super(input, output);
	}

	public static BasicDisplay.Serializer<ResourceMelonConsumptionDisplay> serializer() {
		return BasicDisplay.Serializer.ofSimpleRecipeLess(ResourceMelonConsumptionDisplay::new);
	}

	public EntryIngredient getSlice() {
		return getInputEntries().get(0);
	}

	public EntryIngredient getResource() {
		return getOutputEntries().get(0);
	}

	public EntryIngredient getSeed() {
		return getOutputEntries().get(1);
	}

	public MelonType getMelonType() {
		return melonType;
	}

	@Override
	public CategoryIdentifier<?> getCategoryIdentifier() {
		return RMPlugin.MELON_CONSUMPTION;
	}
}

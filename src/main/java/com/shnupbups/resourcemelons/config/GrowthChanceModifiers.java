package com.shnupbups.resourcemelons.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class GrowthChanceModifiers {
	@ConfigEntry.Gui.Tooltip(count = 2)
	@ConfigEntry.Gui.CollapsibleObject
	public ToggleableValueConfigEntry secondaryCatalysts = new ToggleableValueConfigEntry(0.75f);
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.Gui.CollapsibleObject
	public ToggleableValueConfigEntry moisture = new ToggleableValueConfigEntry(0.1f);
	@ConfigEntry.Gui.Tooltip(count = 2)
	@ConfigEntry.Gui.CollapsibleObject
	public ToggleableMultiplierConfigEntry light = new ToggleableMultiplierConfigEntry(0.1f);
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.Gui.CollapsibleObject
	public ToggleableMultiplierConfigEntry skyLight = new ToggleableMultiplierConfigEntry(0.1f);
	@ConfigEntry.Gui.Tooltip(count = 2)
	@ConfigEntry.Gui.CollapsibleObject
	public ToggleableMultiplierConfigEntry allSecondaryCatalysts = new ToggleableMultiplierConfigEntry(1.5f);

	public ToggleableValueConfigEntry secondaryCatalysts() {
		return secondaryCatalysts;
	}

	public ToggleableValueConfigEntry moisture() {
		return moisture;
	}

	public ToggleableMultiplierConfigEntry light() {
		return light;
	}

	public ToggleableMultiplierConfigEntry skyLight() {
		return skyLight;
	}

	public ToggleableMultiplierConfigEntry allSecondaryCatalysts() {
		return allSecondaryCatalysts;
	}
}

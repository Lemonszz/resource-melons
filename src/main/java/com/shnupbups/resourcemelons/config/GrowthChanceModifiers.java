package com.shnupbups.resourcemelons.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class GrowthChanceModifiers {
	@ConfigEntry.Gui.Tooltip(count = 2)
	@ConfigEntry.Gui.CollapsibleObject
	public ToggleableFloatConfigEntry secondaryCatalysts = new ToggleableFloatConfigEntry(0.75f);
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.Gui.CollapsibleObject
	public ToggleableFloatConfigEntry moisture = new ToggleableFloatConfigEntry(0.1f);
	@ConfigEntry.Gui.Tooltip(count = 2)
	@ConfigEntry.Gui.CollapsibleObject
	public ToggleableFloatConfigEntry light = new ToggleableFloatConfigEntry(0.1f);
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.Gui.CollapsibleObject
	public ToggleableFloatConfigEntry skyLight = new ToggleableFloatConfigEntry(0.1f);
	@ConfigEntry.Gui.Tooltip(count = 2)
	@ConfigEntry.Gui.CollapsibleObject
	public ToggleableFloatConfigEntry allSecondaryCatalysts = new ToggleableFloatConfigEntry(1.5f);

	public ToggleableFloatConfigEntry secondaryCatalysts() {
		return secondaryCatalysts;
	}

	public ToggleableFloatConfigEntry moisture() {
		return moisture;
	}

	public ToggleableFloatConfigEntry light() {
		return light;
	}

	public ToggleableFloatConfigEntry skyLight() {
		return skyLight;
	}

	public ToggleableFloatConfigEntry allSecondaryCatalysts() {
		return allSecondaryCatalysts;
	}
}

package com.shnupbups.resourcemelons.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class ToggleableMultiplierConfigEntry implements ToggleableConfigEntry {
	@ConfigEntry.Gui.Tooltip
	boolean enabled;
	@ConfigEntry.Gui.Tooltip
	float multiplier;

	public ToggleableMultiplierConfigEntry(boolean enabled, float multiplier) {
		this.enabled = enabled;
		this.multiplier = multiplier;
	}

	public ToggleableMultiplierConfigEntry(float multiplier) {
		this(true, multiplier);
	}

	@Override
	public boolean enabled() {
		return enabled;
	}

	public float multiplier() {
		return multiplier;
	}
}

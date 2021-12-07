package com.shnupbups.resourcemelons.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class ToggleablePercentageConfigEntry implements ToggleableConfigEntry {
	@ConfigEntry.Gui.Tooltip
	boolean enabled;
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 100)
	float percentage;

	public ToggleablePercentageConfigEntry(boolean enabled, float percentage) {
		this.enabled = enabled;
		this.percentage = percentage;
	}

	public ToggleablePercentageConfigEntry(float percentage) {
		this(true, percentage);
	}

	@Override
	public boolean enabled() {
		return enabled;
	}

	public float percentage() {
		return percentage;
	}
}

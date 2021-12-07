package com.shnupbups.resourcemelons.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class ToggleableValueConfigEntry implements ToggleableConfigEntry {
	@ConfigEntry.Gui.Tooltip
	boolean enabled;
	@ConfigEntry.Gui.Tooltip
	float value;

	public ToggleableValueConfigEntry(boolean enabled, float value) {
		this.enabled = enabled;
		this.value = value;
	}

	public ToggleableValueConfigEntry(float value) {
		this(true, value);
	}

	@Override
	public boolean enabled() {
		return enabled;
	}

	public float value() {
		return value;
	}
}

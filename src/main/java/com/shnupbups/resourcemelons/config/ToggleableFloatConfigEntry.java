package com.shnupbups.resourcemelons.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class ToggleableFloatConfigEntry implements ToggleableConfigEntry {
	@ConfigEntry.Gui.Tooltip
	boolean enabled;
	@ConfigEntry.Gui.Tooltip
	float value;

	public ToggleableFloatConfigEntry(boolean enabled, float value) {
		this.enabled = enabled;
		this.value = value;
	}

	public ToggleableFloatConfigEntry(float value) {
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

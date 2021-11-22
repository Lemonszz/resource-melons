package com.shnupbups.resourcemelons.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class ToggleableIntConfigEntry implements ToggleableConfigEntry {
	@ConfigEntry.Gui.Tooltip
	boolean enabled;
	@ConfigEntry.Gui.Tooltip
	int value;

	public ToggleableIntConfigEntry(boolean enabled, int value) {
		this.enabled = enabled;
		this.value = value;
	}

	public ToggleableIntConfigEntry(int value) {
		this(true, value);
	}

	@Override
	public boolean enabled() {
		return enabled;
	}

	public int value() {
		return value;
	}
}

package com.shnupbups.resourcemelons.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import com.shnupbups.resourcemelons.RMCommon;

@Config(name = "resourcemelons")
public class RMConfig implements ConfigData {
	@ConfigEntry.Category("growth")
	@ConfigEntry.Gui.TransitiveObject
	private Growth growth = new Growth();

	@ConfigEntry.Category("consumption")
	@ConfigEntry.Gui.TransitiveObject
	private Consumption consumption = new Consumption();

	@ConfigEntry.Category("misc")
	@ConfigEntry.Gui.TransitiveObject
	private Misc misc = new Misc();

	public boolean isConfigOutdated() {
		return misc().configVersion() < RMCommon.CONFIG_VERSION;
	}

	public Growth growth() {
		return growth;
	}

	public Consumption consumption() {
		return consumption;
	}

	public Misc misc() {
		return misc;
	}

	public static class Growth {
		@ConfigEntry.Gui.Tooltip
		private boolean requireCatalyst = true;
		@ConfigEntry.Gui.Tooltip
		private boolean requireNoSky = true;
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.BoundedDiscrete(max = 100)
		private float baseGrowthPercent = 2f;
		@ConfigEntry.Gui.TransitiveObject
		private ToggleablePercentageConfigEntry revert = new ToggleablePercentageConfigEntry(10);
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.Gui.CollapsibleObject
		private GrowthChanceModifiers growthChanceModifiers = new GrowthChanceModifiers();
		@ConfigEntry.Gui.Tooltip
		private boolean fertilizable = true;
		@ConfigEntry.Gui.Tooltip
		private boolean fertilizerOverrideRequirements = true;

		public boolean requireCatalyst() {
			return requireCatalyst;
		}

		public boolean requireNoSky() {
			return requireNoSky;
		}

		public float baseGrowthPercent() {
			return baseGrowthPercent;
		}

		public ToggleablePercentageConfigEntry revert() {
			return revert;
		}

		public GrowthChanceModifiers growthChanceModifiers() {
			return growthChanceModifiers;
		}

		public boolean fertilizable() {
			return fertilizable;
		}

		public boolean fertilizerOverrideRequirements() {
			return fertilizerOverrideRequirements;
		}
	}

	public static class Consumption {
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.BoundedDiscrete(max = 100)
		private float resourcePercent = 100f;
		@ConfigEntry.Gui.TransitiveObject
		private ToggleablePercentageConfigEntry dropSeeds = new ToggleablePercentageConfigEntry(2f);
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.Gui.CollapsibleObject
		private MelonFoodComponent foodComponent = new MelonFoodComponent();
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
		private Dispensers dispensers = new Dispensers();

		public float resourcePercent() {
			return resourcePercent;
		}

		public ToggleablePercentageConfigEntry dropSeeds() {
			return dropSeeds;
		}

		public MelonFoodComponent foodComponent() {
			return foodComponent;
		}

		public Dispensers dispensers() {
			return dispensers;
		}

		public static class MelonFoodComponent {
			@ConfigEntry.Gui.Tooltip
			@ConfigEntry.Gui.RequiresRestart
			private boolean alwaysEdible = true;
			@ConfigEntry.Gui.Tooltip
			@ConfigEntry.Gui.RequiresRestart
			private int hunger = 2;
			@ConfigEntry.Gui.Tooltip
			@ConfigEntry.Gui.RequiresRestart
			private float saturationModifier = 0.3f;
			@ConfigEntry.Gui.Tooltip
			@ConfigEntry.Gui.RequiresRestart
			private boolean snack = false;

			public boolean alwaysEdible() {
				return alwaysEdible;
			}

			public int hunger() {
				return hunger;
			}

			public float saturationModifier() {
				return saturationModifier;
			}

			public boolean snack() {
				return snack;
			}
		}

		public static class Dispensers {
			@ConfigEntry.Gui.TransitiveObject
			private ToggleableMultiplierConfigEntry eating = new ToggleableMultiplierConfigEntry(1f);
			@ConfigEntry.Gui.TransitiveObject
			private ToggleableMultiplierConfigEntry dropSeeds = new ToggleableMultiplierConfigEntry(false, 0.1f);

			public ToggleableMultiplierConfigEntry eating() {
				return eating;
			}

			public ToggleableMultiplierConfigEntry dropSeeds() {
				return dropSeeds;
			}
		}
	}

	public static class Misc {
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.Gui.RequiresRestart
		private boolean debugItemEnabled = false;
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.Gui.RequiresRestart
		private boolean creativeTabEnabled = true;
		@ConfigEntry.Gui.Excluded
		private int configVersion = 1;

		public boolean debugItemEnabled() {
			return debugItemEnabled;
		}

		public boolean creativeTabEnabled() {
			return creativeTabEnabled;
		}

		public int configVersion() {
			return configVersion;
		}
	}
}

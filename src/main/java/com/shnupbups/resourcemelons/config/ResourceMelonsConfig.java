package com.shnupbups.resourcemelons.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "resourcemelons")
public class ResourceMelonsConfig implements ConfigData {
	@ConfigEntry.Category("growth")
	@ConfigEntry.Gui.TransitiveObject
	private Growth growth = new Growth();

	@ConfigEntry.Category("consumption")
	@ConfigEntry.Gui.TransitiveObject
	private Consumption consumption = new Consumption();

	@ConfigEntry.Category("misc")
	@ConfigEntry.Gui.TransitiveObject
	private Misc misc = new Misc();

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
		private float baseGrowthChance = 50f;
		@ConfigEntry.Gui.TransitiveObject
		private ToggleableIntConfigEntry revert = new ToggleableIntConfigEntry(8);
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

		public float baseGrowthChance() {
			return baseGrowthChance;
		}

		public ToggleableIntConfigEntry revert() {
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
		private int resourceChance = 1;
		@ConfigEntry.Gui.TransitiveObject
		private ToggleableIntConfigEntry dropSeeds = new ToggleableIntConfigEntry(50);
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.Gui.CollapsibleObject
		private MelonFoodComponent foodComponent = new MelonFoodComponent();
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.Gui.CollapsibleObject
		private Dispensers dispensers = new Dispensers();

		public int resourceChance() {
			return resourceChance;
		}

		public ToggleableIntConfigEntry dropSeeds() {
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
			private ToggleableFloatConfigEntry eating = new ToggleableFloatConfigEntry(1.0f);
			@ConfigEntry.Gui.TransitiveObject
			private ToggleableFloatConfigEntry dropSeeds = new ToggleableFloatConfigEntry(false, 10f);

			public ToggleableFloatConfigEntry eating() {
				return eating;
			}

			public ToggleableFloatConfigEntry dropSeeds() {
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

		public boolean debugItemEnabled() {
			return debugItemEnabled;
		}

		public boolean creativeTabEnabled() {
			return creativeTabEnabled;
		}
	}
}

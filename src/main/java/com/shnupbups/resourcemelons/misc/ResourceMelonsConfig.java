package com.shnupbups.resourcemelons.misc;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "resourcemelons")
public class ResourceMelonsConfig implements ConfigData {
    @ConfigEntry.Category("growth")
    @ConfigEntry.Gui.TransitiveObject
    public Growth growth = new Growth();

    @ConfigEntry.Category("consumption")
    @ConfigEntry.Gui.TransitiveObject
    public Consumption consumption = new Consumption();

    @ConfigEntry.Category("misc")
    @ConfigEntry.Gui.TransitiveObject
    public Misc misc = new Misc();

    public static class Growth {
        @ConfigEntry.Gui.Tooltip
        public boolean requireCatalyst = true;
        @ConfigEntry.Gui.Tooltip
        public boolean requireNoSky = true;
        @ConfigEntry.Gui.Tooltip
        public float baseGrowthChance = 50f;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.CollapsibleObject
        public GrowthChanceModifiers growthChanceModifiers = new GrowthChanceModifiers();

        public static class GrowthChanceModifiers {
            @ConfigEntry.Gui.Tooltip(count = 2)
            @ConfigEntry.Gui.CollapsibleObject
            public GrowthChanceModifier secondaryCatalysts = new GrowthChanceModifier(0.75f);
            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.Gui.CollapsibleObject
            public GrowthChanceModifier moisture = new GrowthChanceModifier(0.1f);
            @ConfigEntry.Gui.Tooltip(count = 2)
            @ConfigEntry.Gui.CollapsibleObject
            public GrowthChanceModifier light = new GrowthChanceModifier(0.1f);
            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.Gui.CollapsibleObject
            public GrowthChanceModifier skyLight = new GrowthChanceModifier(0.1f);
            @ConfigEntry.Gui.Tooltip(count = 2)
            @ConfigEntry.Gui.CollapsibleObject
            public GrowthChanceModifier allCatalysts = new GrowthChanceModifier(1.5f);

            public static class GrowthChanceModifier {
                public boolean enabled = true;
                public float value;

                public GrowthChanceModifier(float value) {
                    this.value = value;
                }
            }
        }
    }

    public static class Consumption {
        @ConfigEntry.Gui.Tooltip
        public int resourceChance = 1;
        @ConfigEntry.Gui.Tooltip
        public boolean canDropSeeds = true;
        @ConfigEntry.Gui.Tooltip
        public int seedChance = 50;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.CollapsibleObject
        public MelonFoodComponent foodComponent = new MelonFoodComponent();
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.CollapsibleObject
        public Dispensers dispensers = new Dispensers();

        public static class MelonFoodComponent {
            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.Gui.RequiresRestart
            public boolean alwaysEdible = true;
            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.Gui.RequiresRestart
            public int hunger = 2;
            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.Gui.RequiresRestart
            public float saturationModifier = 0.3f;
            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.Gui.RequiresRestart
            public boolean snack = false;
        }

        public static class Dispensers {
            @ConfigEntry.Gui.Tooltip
            public boolean canDispensersEat = true;
            @ConfigEntry.Gui.Tooltip
            public float dispenserResourceChanceModifier = 1;
            @ConfigEntry.Gui.Tooltip
            public boolean canDispensersDropSeeds = false;
            @ConfigEntry.Gui.Tooltip
            public float dispenserSeedChanceModifier = 1;
        }
    }

    public static class Misc {
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.RequiresRestart
        public boolean debugItemEnabled = false;
    }
}

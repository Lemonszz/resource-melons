package com.shnupbups.resourcemelons.rei.category;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Label;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;

import com.shnupbups.resourcemelons.core.DefaultMelonTypes;
import com.shnupbups.resourcemelons.core.MelonType;
import com.shnupbups.resourcemelons.item.ResourceMelonSliceItem;
import com.shnupbups.resourcemelons.misc.ResourceMelonSliceDispenserBehavior;
import com.shnupbups.resourcemelons.rei.RMPlugin;
import com.shnupbups.resourcemelons.rei.display.ResourceMelonConsumptionDisplay;

public class ResourceMelonConsumptionCategory implements DisplayCategory<ResourceMelonConsumptionDisplay> {
	public static boolean shouldLabelExist(MelonType type) {
		return shouldLabelShowNormalResourceChance(type) || shouldLabelShowNormalSeedsChance(type) || shouldLabelShowDispenserResourceChance(type) || shouldLabelShowDispenserSeedsChance(type);
	}

	public static boolean shouldLabelBeSimple(MelonType type) {
		return !shouldLabelShowNormalSeedsChance(type) && !shouldLabelShowDispenserResourceChance(type) && !shouldLabelShowDispenserSeedsChance(type);
	}

	public static boolean shouldLabelShowDispenserChances(MelonType type) {
		if (!canDispensersEat()) return false;
		else return shouldLabelShowDispenserResourceChance(type) || shouldLabelShowDispenserSeedsChance(type);
	}

	public static boolean shouldLabelShowNormalResourceChance(MelonType type) {
		return getNormalSeedsChance(type) != 100;
	}

	public static boolean shouldLabelShowDispenserResourceChance(MelonType type) {
		if (!canDispensersEat()) return false;
		else return !areResourceChancesEqual(type);
	}

	public static boolean shouldLabelShowNormalSeedsChance(MelonType type) {
		if (!canDropSeeds()) return false;
		else return getNormalSeedsChance(type) != 100;
	}

	public static boolean shouldLabelShowDispenserSeedsChance(MelonType type) {
		if (!canDispensersEat()) return false;
		else if (canNormallyDropSeeds() && !canDispensersDropSeeds()) return true;
		else if (areSeedChancesEqual(type)) return false;
		else if (shouldLabelShowNormalSeedsChance(type)) return true;
		else return getDispenserSeedsChance(type) != 100;
	}

	public static boolean areResourceChancesEqual(MelonType type) {
		return getDispenserResourceChance(type) == getNormalResourceChance(type);
	}

	public static boolean areSeedChancesEqual(MelonType type) {
		return getDispenserSeedsChance(type) == getNormalSeedsChance(type);
	}

	public static boolean canNormallyDropSeeds() {
		return ResourceMelonSliceItem.canDropSeeds();
	}

	public static boolean canDispensersEat() {
		return ResourceMelonSliceDispenserBehavior.isEnabled();
	}

	public static boolean canDispensersDropSeeds() {
		return canDispensersEat() && ResourceMelonSliceDispenserBehavior.canDropSeeds();
	}

	public static boolean canDropSeeds() {
		return canNormallyDropSeeds() || canDispensersDropSeeds();
	}

	public static float getNormalResourceChance(MelonType type) {
		return type.slice().getResourceChance();
	}

	public static float getDispenserResourceChance(MelonType type) {
		return canDispensersEat() ? ResourceMelonSliceDispenserBehavior.getResourceChance(type.slice()) : 0;
	}

	public static float getNormalSeedsChance(MelonType type) {
		return canNormallyDropSeeds() ? type.slice().getSeedsChance() : 0;
	}

	public static float getDispenserSeedsChance(MelonType type) {
		return canDispensersEat() && canDispensersDropSeeds() ? ResourceMelonSliceDispenserBehavior.getSeedsChance(type.slice()) : 0;
	}

	@Override
	public Renderer getIcon() {
		return EntryStacks.of(DefaultMelonTypes.DIAMOND.getType().slice());
	}

	@Override
	public Text getTitle() {
		return new TranslatableText("category.resourcemelons.melon_consumption");
	}

	@Override
	public CategoryIdentifier<? extends ResourceMelonConsumptionDisplay> getCategoryIdentifier() {
		return RMPlugin.MELON_CONSUMPTION;
	}

	@Override
	public List<Widget> setupDisplay(ResourceMelonConsumptionDisplay display, Rectangle bounds) {
		Point startPoint = new Point(bounds.getCenterX() - 41, bounds.getCenterY() - 13);
		List<Widget> widgets = Lists.newArrayList();
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createArrow(new Point(startPoint.x + 27, startPoint.y - 1)));
		widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61, startPoint.y)));
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 4, startPoint.y)).entries(display.getSlice()).markInput());
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 61, startPoint.y)).entries(display.getResource()).disableBackground().markOutput());

		MelonType type = display.getMelonType();

		boolean dropSeeds = canDropSeeds();
		float resourceChance = getNormalResourceChance(type);
		float dispenserResourceChance = getDispenserResourceChance(type);
		float seedsChance = getNormalSeedsChance(type);
		float dispenserSeedsChance = getDispenserSeedsChance(type);

		if (dropSeeds) {
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 83, startPoint.y)).entries(display.getSeed()).markOutput());
		}

		if (shouldLabelExist(type)) {
			Label label;

			if (shouldLabelBeSimple(type)) {
				label = Widgets.createLabel(new Point(startPoint.x + 60, startPoint.y + 25), new TranslatableText("text.rei.resourcemelons.drop_chance", getNormalResourceChance(type)));
			} else {
				label = Widgets.createLabel(new Point(startPoint.x + 40, startPoint.y + 25), new TranslatableText("text.rei.resourcemelons.drop_chances"));
				List<Text> tooltip = Lists.newArrayList();
				if (shouldLabelShowNormalResourceChance(type)) {
					tooltip.add(new TranslatableText("text.rei.resourcemelons.drop_chances.resource", resourceChance));
				}
				if (shouldLabelShowNormalSeedsChance(type)) {
					tooltip.add(new TranslatableText("text.rei.resourcemelons.drop_chances.seeds", seedsChance));
				}
				if (shouldLabelShowDispenserChances(type)) {
					tooltip.add(new LiteralText(" "));
					if (shouldLabelShowDispenserResourceChance(type)) {
						tooltip.add(new TranslatableText("text.rei.resourcemelons.drop_chances.resource.dispenser", dispenserResourceChance));
					}
					if (shouldLabelShowDispenserSeedsChance(type)) {
						tooltip.add(new TranslatableText("text.rei.resourcemelons.drop_chances.seeds.dispenser", dispenserSeedsChance));
					}
				}

				label.tooltip(tooltip.toArray(new Text[0]));
			}
			widgets.add(label);
		}

		return widgets;
	}

	@Override
	public int getDisplayHeight() {
		return 52;
	}
}

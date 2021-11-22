package com.shnupbups.resourcemelons.misc;

import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;

import com.shnupbups.resourcemelons.ResourceMelons;
import com.shnupbups.resourcemelons.item.ResourceMelonSliceItem;

public class MelonDispenserBehavior extends ItemDispenserBehavior {

	public static boolean isEnabled() {
		return ResourceMelons.getConfig().consumption().dispensers().eating().enabled();
	}

	public static float getResourceChanceModifier() {
		return ResourceMelons.getConfig().consumption().dispensers().eating().value();
	}

	public static int getResourceChance() {
		return (int) (ResourceMelons.getConfig().consumption().resourceChance() * getResourceChanceModifier());
	}

	public static boolean canDropSeeds() {
		return ResourceMelons.getConfig().consumption().dispensers().dropSeeds().enabled();
	}

	public static float getSeedChanceModifier() {
		return ResourceMelons.getConfig().consumption().dispensers().dropSeeds().value();
	}

	public static int getSeedChance() {
		return (int) (ResourceMelons.getConfig().consumption().dropSeeds().value() * getSeedChanceModifier());
	}

	@Override
	protected ItemStack dispenseSilently(BlockPointer blockPointer, ItemStack stack) {
		if (isEnabled() && stack.isIn(ResourceMelonsTags.ItemTags.RESOURCE_MELON_SLICES) && stack.getItem() instanceof ResourceMelonSliceItem melon) {
			if (blockPointer.getWorld().getRandom().nextInt(getResourceChance()) == 0)
				super.dispenseSilently(blockPointer, melon.getResourceStack());
			if (canDropSeeds()) {
				if (blockPointer.getWorld().getRandom().nextInt(getSeedChance()) == 0)
					super.dispenseSilently(blockPointer, melon.getSeedStack());
			}
			stack.decrement(1);
			return stack;
		}
		return super.dispenseSilently(blockPointer, stack);
	}
}

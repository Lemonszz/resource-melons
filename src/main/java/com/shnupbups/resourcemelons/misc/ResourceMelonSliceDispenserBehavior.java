package com.shnupbups.resourcemelons.misc;

import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.MathHelper;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.item.ResourceMelonSliceItem;

public class ResourceMelonSliceDispenserBehavior extends ItemDispenserBehavior {

	public static boolean isEnabled() {
		return RMCommon.getConfig().consumption().dispensers().eating().enabled();
	}

	public static float getResourceChanceModifier() {
		return RMCommon.getConfig().consumption().dispensers().eating().multiplier();
	}

	public static float getResourceChance(ResourceMelonSliceItem slice) {
		return MathHelper.clamp(slice.getResourceChance() * getResourceChanceModifier(), 0, 100);
	}

	public static boolean canDropSeeds() {
		return RMCommon.getConfig().consumption().dispensers().dropSeeds().enabled();
	}

	public static float getSeedsChanceModifier() {
		return RMCommon.getConfig().consumption().dispensers().dropSeeds().multiplier();
	}

	public static float getSeedsChance(ResourceMelonSliceItem slice) {
		return MathHelper.clamp(slice.getSeedsChance() * getSeedsChanceModifier(), 0, 100);
	}

	@Override
	protected ItemStack dispenseSilently(BlockPointer blockPointer, ItemStack stack) {
		if (isEnabled() && stack.isIn(RMTags.ItemTags.RESOURCE_MELON_SLICES) && stack.getItem() instanceof ResourceMelonSliceItem slice) {
			if (RMCommon.randomWithPercentage(blockPointer.getWorld().getRandom(), getResourceChance(slice)))
				super.dispenseSilently(blockPointer, slice.getResourceStack());
			if (canDropSeeds()) {
				if (RMCommon.randomWithPercentage(blockPointer.getWorld().getRandom(), getSeedsChance(slice)))
					super.dispenseSilently(blockPointer, slice.getSeedsStack());
			}
			stack.decrement(1);
			return stack;
		}
		return super.dispenseSilently(blockPointer, stack);
	}
}

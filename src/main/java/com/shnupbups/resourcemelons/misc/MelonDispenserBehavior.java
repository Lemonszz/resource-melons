package com.shnupbups.resourcemelons.misc;

import com.shnupbups.resourcemelons.ResourceMelons;
import com.shnupbups.resourcemelons.item.ResourceMelonSliceItem;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;

public class MelonDispenserBehavior extends ItemDispenserBehavior {
	
	@Override
	protected ItemStack dispenseSilently(BlockPointer blockPointer, ItemStack stack) {
		if (ResourceMelons.config.consumption.dispensers.canDispensersEat && stack.getItem() instanceof ResourceMelonSliceItem melon) {
			int resourceChance = (int)(ResourceMelons.config.consumption.resourceChance * ResourceMelons.config.consumption.dispensers.dispenserResourceChanceModifier);
			if(blockPointer.getWorld().getRandom().nextInt(resourceChance) == 0)
				super.dispenseSilently(blockPointer, melon.getResourceStack());
			int seedChance = (int)(ResourceMelons.config.consumption.seedChance * ResourceMelons.config.consumption.dispensers.dispenserSeedChanceModifier);
			if(ResourceMelons.config.consumption.dispensers.canDispensersDropSeeds && blockPointer.getWorld().getRandom().nextInt(seedChance) == 0)
				super.dispenseSilently(blockPointer, melon.getSeedStack());
			stack.decrement(1);
			return stack;
		}
		return super.dispenseSilently(blockPointer, stack);
	}
}

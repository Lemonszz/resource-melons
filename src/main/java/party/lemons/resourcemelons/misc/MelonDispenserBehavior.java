package party.lemons.resourcemelons.misc;

import party.lemons.resourcemelons.item.ResourceMelonSliceItem;

import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;

public class MelonDispenserBehavior extends ItemDispenserBehavior {
	
	@Override
	protected ItemStack dispenseSilently(BlockPointer blockPointer, ItemStack stack) {
		if (stack.getItem() instanceof ResourceMelonSliceItem) {
			super.dispenseSilently(blockPointer, ((ResourceMelonSliceItem) stack.getItem()).getOutputStack(stack, blockPointer.getWorld(), null));
			stack.decrement(1);
			return stack;
		}
		return super.dispenseSilently(blockPointer, stack);
	}
}

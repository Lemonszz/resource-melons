package party.lemons.resourcemelons.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ResourceMelonSliceItem extends Item {
	private final Item output;

	public ResourceMelonSliceItem(Item output, Settings settings) {
		super(settings);
		this.output = output;
	}

	@Override
	public ItemStack finishUsing(ItemStack itemStack_1, World world_1, LivingEntity livingEntity_1) {
		world_1.spawnEntity(new ItemEntity(world_1, livingEntity_1.getBlockPos().getX(), livingEntity_1.getBlockPos().getY(), livingEntity_1.getBlockPos().getZ(), new ItemStack(output)));
		return super.finishUsing(itemStack_1, world_1, livingEntity_1);
	}
}

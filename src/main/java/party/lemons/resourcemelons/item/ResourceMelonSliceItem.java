package party.lemons.resourcemelons.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ResourceMelonSliceItem extends Item {
	private final Item resource;
	
	public ResourceMelonSliceItem(Item resource, Settings settings) {
		super(settings);
		this.resource = resource;
	}
	
	public ItemStack getOutputStack(ItemStack stack, World world, LivingEntity livingEntity) {
		return new ItemStack(resource);
	}
	
	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity livingEntity) {
		world.spawnEntity(new ItemEntity(world, livingEntity.getBlockPos().getX(), livingEntity.getBlockPos().getY(), livingEntity.getBlockPos().getZ(), getOutputStack(stack, world, livingEntity)));
		return super.finishUsing(stack, world, livingEntity);
	}
}

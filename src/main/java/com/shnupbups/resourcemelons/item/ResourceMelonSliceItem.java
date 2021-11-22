package com.shnupbups.resourcemelons.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.shnupbups.resourcemelons.ResourceMelons;

public class ResourceMelonSliceItem extends Item {
	private final Item resource;
	private final Item seed;

	public ResourceMelonSliceItem(Item resource, Item seed, Settings settings) {
		super(settings);
		this.resource = resource;
		this.seed = seed;
	}

	public static int getResourceChance() {
		return ResourceMelons.getConfig().consumption().resourceChance();
	}

	public static boolean canDropSeeds() {
		return ResourceMelons.getConfig().consumption().dropSeeds().enabled();
	}

	public static int getSeedDropChance() {
		return ResourceMelons.getConfig().consumption().dropSeeds().value();
	}

	public ItemStack getResourceStack() {
		return new ItemStack(resource);
	}

	public ItemStack getSeedStack() {
		return new ItemStack(seed);
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity livingEntity) {
		if (world.getRandom().nextInt(getResourceChance()) == 0)
			spawnStack(world, getResourceStack(), livingEntity.getBlockPos());
		if (canDropSeeds() && world.getRandom().nextInt(getSeedDropChance()) == 0)
			spawnStack(world, getSeedStack(), livingEntity.getBlockPos());
		return super.finishUsing(stack, world, livingEntity);
	}

	public void spawnStack(World world, ItemStack stack, BlockPos pos) {
		world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack));
	}
}

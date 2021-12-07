package com.shnupbups.resourcemelons.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.core.MelonType;

public class ResourceMelonSliceItem extends Item {
	private MelonType type;

	public ResourceMelonSliceItem(Settings settings) {
		super(settings);
	}

	public static float getBaseResourceChance() {
		return RMCommon.getConfig().consumption().resourcePercent();
	}

	public static boolean canDropSeeds() {
		return RMCommon.getConfig().consumption().dropSeeds().enabled();
	}

	public static float getBaseSeedsChance() {
		return RMCommon.getConfig().consumption().dropSeeds().percentage();
	}

	public float getResourceChance() {
		return getBaseResourceChance() * getType().resourceChanceMultiplier();
	}

	public float getSeedsChance() {
		return getBaseSeedsChance() * getType().seedsChanceMultiplier();
	}

	public ItemStack getResourceStack() {
		return new ItemStack(getType().resource());
	}

	public ItemStack getSeedsStack() {
		return new ItemStack(getType().seeds());
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity livingEntity) {
		if (RMCommon.randomWithPercentage(world.getRandom(), getResourceChance()))
			spawnStack(world, getResourceStack(), livingEntity.getBlockPos());
		if (canDropSeeds() && RMCommon.randomWithPercentage(world.getRandom(), getSeedsChance()))
			spawnStack(world, getSeedsStack(), livingEntity.getBlockPos());
		return super.finishUsing(stack, world, livingEntity);
	}

	public void spawnStack(World world, ItemStack stack, BlockPos pos) {
		world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack));
	}

	public MelonType getType() {
		return type;
	}

	public void setType(MelonType type) {
		this.type = type;
	}
}

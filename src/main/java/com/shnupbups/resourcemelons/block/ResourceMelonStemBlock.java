package com.shnupbups.resourcemelons.block;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.config.GrowthChanceModifiers;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class ResourceMelonStemBlock extends StemBlock {

	private final TagKey<Block> catalyst;

	public ResourceMelonStemBlock(GourdBlock melon, Supplier<Item> pickBlockItem, TagKey<Block> catalyst, Settings settings) {
		super(melon, pickBlockItem, settings);
		this.catalyst = catalyst;
	}

	public static boolean isSkyVisible(World world, BlockPos pos) {
		if (requiresNoSky())
			return world.isSkyVisible(pos.up());
		else return false;
	}

	public static int getMoisture(World world, BlockPos pos) {
		BlockPos soilPos = pos.down();
		BlockState soil = world.getBlockState(soilPos);
		Optional<Integer> moisture = soil.getOrEmpty(FarmlandBlock.MOISTURE);
		return moisture.orElse(0);
	}

	public static float getMoistureGrowthModifier(World world, BlockPos pos) {
		if (getGrowthChanceModifiers().moisture().enabled())
			return getMoisture(world, pos) * getGrowthChanceModifiers().moisture().value();
		else return 0f;
	}

	public static float getLightGrowthModifier(World world, BlockPos pos) {
		if (getGrowthChanceModifiers().light().enabled())
			return (15 - world.getLightLevel(pos)) * getGrowthChanceModifiers().light().multiplier();
		else return 1f;
	}

	public static float getSkyLightGrowthModifier(World world, BlockPos pos) {
		if (getGrowthChanceModifiers().skyLight().enabled())
			return Math.min(1f, (15 - world.getLightLevel(LightType.SKY, pos)) * getGrowthChanceModifiers().skyLight().multiplier());
		else return 1f;
	}

	public static float getBaseGrowthChance() {
		return RMCommon.getConfig().growth().baseGrowthPercent();
	}

	public static boolean requiresCatalyst() {
		return RMCommon.getConfig().growth().requireCatalyst();
	}

	public static boolean requiresNoSky() {
		return RMCommon.getConfig().growth().requireNoSky();
	}

	public static boolean canRevert() {
		return RMCommon.getConfig().growth().revert().enabled();
	}

	public static float getRevertChance() {
		return RMCommon.getConfig().growth().revert().percentage();
	}

	public static GrowthChanceModifiers getGrowthChanceModifiers() {
		return RMCommon.getConfig().growth().growthChanceModifiers();
	}

	public static boolean canFertilize() {
		return RMCommon.getConfig().growth().fertilizable();
	}

	public static boolean doesFertilizerOverrideRequirements() {
		return RMCommon.getConfig().growth().fertilizerOverrideRequirements();
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (meetsGrowthRequirements(world, pos)) {
			if (RMCommon.randomWithPercentage(world.getRandom(), getGrowthChance(world, pos))) {
				int growthStage = state.get(AGE);
				if (growthStage < 7) {
					state = state.with(AGE, growthStage + 1);
					world.setBlockState(pos, state, Block.NOTIFY_LISTENERS);
				} else {
					Direction direction = Direction.Type.HORIZONTAL.random(random);
					BlockPos gourdPos = pos.offset(direction);
					BlockState gourdSoilState = world.getBlockState(gourdPos.down());
					if (world.getBlockState(gourdPos).isAir() && (gourdSoilState.isOf(Blocks.FARMLAND) || gourdSoilState.isIn(BlockTags.DIRT) || this.isCatalyst(gourdSoilState))) {
						world.setBlockState(gourdPos, this.getGourdBlock().getDefaultState());
						if (canRevert() && RMCommon.randomWithPercentage(world.getRandom(), getRevertChance()))
							world.setBlockState(pos, this.getDefaultState());
						else
							world.setBlockState(pos, this.getGourdBlock().getAttachedStem().getDefaultState().with(HorizontalFacingBlock.FACING, direction));
					}
				}
			}
		}
	}

	public boolean isCatalyst(BlockState state) {
		return state.isIn(catalyst);
	}

	public float getGrowthChance(World world, BlockPos pos) {
		return getBaseGrowthChance() / this.getTotalGrowthModifier(world, pos) + 1;
	}

	public float getTotalGrowthModifier(World world, BlockPos pos) {
		float growthModifier = 1.0f;
		growthModifier += getMoistureGrowthModifier(world, pos);
		growthModifier += this.getSecondaryCatalystGrowthModifier(world, pos);
		growthModifier *= getLightGrowthModifier(world, pos);
		growthModifier *= getSkyLightGrowthModifier(world, pos);
		growthModifier *= this.getAllSecondaryCatalystsGrowthModifier(world, pos);
		if (growthModifier == 0) return 0.001f;
		else return growthModifier;
	}

	public int getSecondaryCatalystCount(World world, BlockPos pos) {
		int count = 0;
		BlockPos soilPos = pos.down();
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				BlockPos secondaryCatalystPos = soilPos.add(new Vec3i(x, 0, z));
				if (!secondaryCatalystPos.equals(soilPos) && isCatalyst(world.getBlockState(secondaryCatalystPos))) {
					count++;
				}
			}
		}
		return count;
	}

	public float getSecondaryCatalystGrowthModifier(World world, BlockPos pos) {
		if (getGrowthChanceModifiers().secondaryCatalysts().enabled())
			return getSecondaryCatalystCount(world, pos) * getGrowthChanceModifiers().secondaryCatalysts().value();
		else return 0f;
	}

	public boolean hasPrimaryCatalyst(World world, BlockPos pos) {
		if (requiresCatalyst())
			return this.isCatalyst(world.getBlockState(pos.down(2)));
		else return true;
	}

	public boolean hasAllSecondaryCatalysts(World world, BlockPos pos) {
		return getSecondaryCatalystCount(world, pos) == 8;
	}

	public float getAllSecondaryCatalystsGrowthModifier(World world, BlockPos pos) {
		if (getGrowthChanceModifiers().allSecondaryCatalysts().enabled() && hasAllSecondaryCatalysts(world, pos))
			return getGrowthChanceModifiers().allSecondaryCatalysts().multiplier();
		else return 1f;
	}

	public boolean meetsGrowthRequirements(World world, BlockPos pos) {
		return hasPrimaryCatalyst(world, pos) && !isSkyVisible(world, pos);
	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		return doesFertilizerOverrideRequirements() || meetsGrowthRequirements(world, pos);
	}

	@Override
	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
		return super.isFertilizable(world, pos, state, isClient) && canFertilize();
	}
}

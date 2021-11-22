package com.shnupbups.resourcemelons.block;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import com.shnupbups.resourcemelons.ResourceMelons;
import com.shnupbups.resourcemelons.config.GrowthChanceModifiers;
import com.shnupbups.resourcemelons.core.Catalyst;

public class ResourceStemBlock extends StemBlock {

	private final Catalyst catalyst;

	public ResourceStemBlock(GourdBlock melon, Supplier<Item> pickBlockItem, Catalyst catalyst, Settings settings) {
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
			return (15 - world.getLightLevel(pos)) * getGrowthChanceModifiers().light().value();
		else return 1f;
	}

	public static float getSkyLightGrowthModifier(World world, BlockPos pos) {
		if (getGrowthChanceModifiers().skyLight().enabled())
			return Math.min(1f, (15 - world.getLightLevel(LightType.SKY, pos)) * getGrowthChanceModifiers().skyLight().value());
		else return 1f;
	}

	public static float getBaseGrowthChance() {
		return ResourceMelons.getConfig().growth().baseGrowthChance();
	}

	public static boolean requiresCatalyst() {
		return ResourceMelons.getConfig().growth().requireCatalyst();
	}

	public static boolean requiresNoSky() {
		return ResourceMelons.getConfig().growth().requireNoSky();
	}

	public static boolean canRevert() {
		return ResourceMelons.getConfig().growth().revert().enabled();
	}

	public static int getRevertChance() {
		return ResourceMelons.getConfig().growth().revert().value();
	}

	public static GrowthChanceModifiers getGrowthChanceModifiers() {
		return ResourceMelons.getConfig().growth().growthChanceModifiers();
	}

	public static boolean canFertilize() {
		return ResourceMelons.getConfig().growth().fertilizable();
	}

	public static boolean doesFertilizerOverrideRequirements() {
		return ResourceMelons.getConfig().growth().fertilizerOverrideRequirements();
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (meetsGrowthRequirements(world, pos)) {
			if (random.nextInt(getGrowthChance(world, pos)) == 0) {
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
						if (canRevert() && random.nextInt(getRevertChance()) == 0)
							world.setBlockState(pos, this.getDefaultState());
						else
							world.setBlockState(pos, this.getGourdBlock().getAttachedStem().getDefaultState().with(HorizontalFacingBlock.FACING, direction));
					}
				}
			}
		}
	}

	public boolean isCatalyst(BlockState state) {
		return catalyst.isValid(state);
	}

	public int getGrowthChance(World world, BlockPos pos) {
		return (int) (getBaseGrowthChance() / this.getTotalGrowthModifier(world, pos)) + 1;
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
			return getGrowthChanceModifiers().allSecondaryCatalysts().value();
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

package com.shnupbups.resourcemelons.block;

import com.shnupbups.resourcemelons.ResourceMelons;
import com.shnupbups.resourcemelons.core.Catalyst;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class ResourceStemBlock extends StemBlock {
	
	private final Catalyst catalyst;
	
	public ResourceStemBlock(GourdBlock melon, Supplier<Item> pickBlockItem, Catalyst catalyst, Settings settings) {
		super(melon, pickBlockItem, settings);
		this.catalyst = catalyst;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!this.isSkyVisible(world, pos) && this.hasPrimaryCatalyst(world, pos)) {
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
						if(ResourceMelons.config.growth.canRevert && random.nextInt(ResourceMelons.config.growth.revertChance) == 0)
							world.setBlockState(pos, this.getDefaultState());
						else
							world.setBlockState(pos, this.getGourdBlock().getAttachedStem().getDefaultState().with(HorizontalFacingBlock.FACING, direction));
					}
				}
			}
		}
	}

	public boolean isSkyVisible(World world, BlockPos pos) {
		if(ResourceMelons.config.growth.requireNoSky)
			return world.isSkyVisible(pos.up());
		else return false;
	}
	
	public boolean isCatalyst(BlockState state) {
		return catalyst.isValid(state);
	}

	public int getGrowthChance(World world, BlockPos pos) {
		return (int)(ResourceMelons.config.growth.baseGrowthChance / this.getTotalGrowthModifier(world, pos)) + 1;
	}
	
	public float getTotalGrowthModifier(World world, BlockPos pos) {
		float growthModifier = 1.0f;
		growthModifier += this.getMoistureGrowthModifier(world, pos);
		growthModifier += this.getSecondaryCatalystGrowthModifier(world, pos);
		growthModifier *= this.getLightGrowthModifier(world, pos);
		growthModifier *= this.getSkyLightGrowthModifier(world, pos);
		growthModifier *= this.getAllCatalystsGrowthModifier(world, pos);
		if(growthModifier == 0) return 0.001f;
		else return growthModifier;
	}

	public int getMoisture(World world, BlockPos pos) {
		BlockPos soilPos = pos.down();
		BlockState soil = world.getBlockState(soilPos);
		Optional<Integer> moisture = soil.getOrEmpty(FarmlandBlock.MOISTURE);
		return moisture.orElse(0);
	}

	public float getMoistureGrowthModifier(World world, BlockPos pos) {
		if(ResourceMelons.config.growth.growthChanceModifiers.moisture.enabled)
			return getMoisture(world, pos) * ResourceMelons.config.growth.growthChanceModifiers.moisture.value;
		else return 0f;
	}

	public int getSecondaryCatalystCount(World world, BlockPos pos) {
		int count = 0;
		BlockPos soilPos = pos.down();
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				BlockPos secondaryCatalystPos = soilPos.add(new Vec3i(x, 0, z));
				if(!secondaryCatalystPos.equals(soilPos) && isCatalyst(world.getBlockState(secondaryCatalystPos))) {
					count++;
				}
			}
		}
		return count;
	}

	public float getSecondaryCatalystGrowthModifier(World world, BlockPos pos) {
		if(ResourceMelons.config.growth.growthChanceModifiers.secondaryCatalysts.enabled)
			return getSecondaryCatalystCount(world, pos) * ResourceMelons.config.growth.growthChanceModifiers.secondaryCatalysts.value;
		else return 0f;
	}

	public boolean hasPrimaryCatalyst(World world, BlockPos pos) {
		if(ResourceMelons.config.growth.requireCatalyst)
			return this.isCatalyst(world.getBlockState(pos.down(2)));
		else return true;
	}

	public boolean hasAllCatalysts(World world, BlockPos pos) {
		return getSecondaryCatalystCount(world, pos) == 8;
	}

	public float getAllCatalystsGrowthModifier(World world, BlockPos pos) {
		if(ResourceMelons.config.growth.growthChanceModifiers.allCatalysts.enabled && hasAllCatalysts(world, pos))
			return ResourceMelons.config.growth.growthChanceModifiers.allCatalysts.value;
		else return 1f;
	}

	public float getLightGrowthModifier(World world, BlockPos pos) {
		if(ResourceMelons.config.growth.growthChanceModifiers.light.enabled)
			return (15 - world.getLightLevel(pos)) * ResourceMelons.config.growth.growthChanceModifiers.light.value;
		else return 1f;
	}

	public float getSkyLightGrowthModifier(World world, BlockPos pos) {
		if(ResourceMelons.config.growth.growthChanceModifiers.skyLight.enabled)
			return Math.min(1f, (15 - world.getLightLevel(LightType.SKY, pos)) * ResourceMelons.config.growth.growthChanceModifiers.skyLight.value);
		else return 1f;
	}
}

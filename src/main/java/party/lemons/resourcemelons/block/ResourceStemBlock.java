package party.lemons.resourcemelons.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Random;

public class ResourceStemBlock extends StemBlock {
	private static final float BASE_GROW_CHANCE = 25F;

	private Block soil;
	private Item seeds;

	public ResourceStemBlock(GourdBlock melonBlock) {
		super(melonBlock, FabricBlockSettings.of(Material.PLANT).collidable(false).ticksRandomly().hardness(0).sounds(BlockSoundGroup.WOOD).build());
	}

	public ResourceStemBlock setSeeds(Item seeds) {
		this.seeds = seeds;
		return this;
	}

	public ResourceStemBlock setSoil(Block soil) {
		this.soil = soil;
		return this;
	}

	@Environment(EnvType.CLIENT)
	@Override
	protected Item getPickItem() {
		return seeds;
	}

	@Override
	public void onScheduledTick(BlockState state, World world, BlockPos pos, Random random) {
		if (!world.isSkyVisible(pos.up())) {
			float growFactor = getGrowthFactor(world, pos);
			if (random.nextInt((int) (BASE_GROW_CHANCE / growFactor) + 1) == 0) {
				int growthLevel = state.get(AGE);
				if (growthLevel < 7) {
					state = state.with(AGE, growthLevel + 1);
					world.setBlockState(pos, state, 2);
				} else {
					Direction facing = Direction.Type.HORIZONTAL.random(random);
					BlockPos melonPos = pos.offset(facing);
					Block melonSoilBlock = world.getBlockState(melonPos.down()).getBlock();
					if (world.getBlockState(melonPos).isAir() && isGoodSoil(melonSoilBlock)) {
						world.setBlockState(melonPos, getGourdBlock().getDefaultState());
						if (world.random.nextInt(7) == 0) {
							world.setBlockState(pos, Blocks.AIR.getDefaultState());
						} else {
							world.setBlockState(pos, getGourdBlock().getAttachedStem().getDefaultState().with(HorizontalFacingBlock.FACING, facing));
						}
					}
				}
			}

		}
	}

	public boolean isGoodSoil(Block block) {
		return block.equals(soil);
	}

	public float getGrowthFactor(World world, BlockPos pos) {
		float factor = 1.0F;

		float lightFactor = (15.0F - world.getLightLevel(LightType.SKY, pos.up())) / 10.0F;

		factor += lightFactor;

		BlockPos downPos = pos.down();

		float posFactor = -0.5F;

		for (int x = -1; x <= 1; ++x) {
			for (int z = -1; z <= 1; ++z) {
				BlockState posState = world.getBlockState(downPos.add(x, 0, z));
				if (isGoodSoil(posState.getBlock())) {
					posFactor += 0.5F;
				}
			}
		}

		factor += posFactor;

		return factor;
	}

	/*@Override
	public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
		System.out.println(getGrowthFactor(world_1,blockPos_1));
		return super.activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
	}*/
}

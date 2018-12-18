package party.lemons.resourcemelons.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class ResourceStemBlock extends StemBlock
{
	private static final int MAX_LIGHT_LEVEL = 7;
	private static final float BASE_GROW_CHANCE = 25F;

	private Item seed;

	public ResourceStemBlock(GourdBlock melonBlock)
	{
		super(melonBlock, FabricBlockSettings.of(Material.PLANT).collidable(false).ticksRandomly().hardness(0).setSoundGroup(BlockSoundGroup.WOOD).build());
	}

	public ResourceStemBlock setSeed(Item seed)
	{
		this.seed = seed;
		return this;
	}

	@Environment(EnvType.CLIENT)
	//middle click block
	protected Item method_10695()
	{
		return seed;
	}

	public void scheduledTick(BlockState state, World world, BlockPos pos, Random random)
	{
		if (world.method_8624(pos.up(), 0) <= MAX_LIGHT_LEVEL)    //If light level < 7
		{
			float growFactor = getGrowthFactor(this, world, pos);
			if (random.nextInt((int)(BASE_GROW_CHANCE / growFactor) + 1) == 0)
			{
				int growthLevel = state.get(field_11584);
				if (growthLevel < 7)
				{
					state = state.with(field_11584, growthLevel + 1);
					world.setBlockState(pos, state, 2);
				}
				else
				{
					Direction facing = Direction.class_2353.HORIZONTAL.random(random);
					BlockPos melonPos = pos.offset(facing);
					Block melonSoilBlock = world.getBlockState(melonPos.down()).getBlock();
					if (world.getBlockState(melonPos).isAir() && isValidStone(melonSoilBlock))
					{
						world.setBlockState(melonPos, getMelonBlock().getDefaultState());

						if(world.random.nextInt(4) == 0)
						{
							world.setBlockState(pos, Blocks.AIR.getDefaultState());
						}
						else
						{
							world.setBlockState(pos, getMelonBlock().method_10680().getDefaultState().with(HorizontalFacingBlock.field_11177, facing));
						}
					}
				}
			}

		}
	}

	public static boolean isValidStone(Block block)
	{
		return  block == Blocks.STONE ||
				block == Blocks.DIORITE ||
				block == Blocks.ANDESITE ||
				block == Blocks.GRANITE;
	}

	public static float getGrowthFactor(Block block, BlockView world, BlockPos pos)
	{
		float factor = 1.0F;
		BlockPos downPos = pos.down();

		for(int x = -1; x <= 1; ++x)
		{
			for(int z = -1; z <= 1; ++z)
			{
				float posFactor = 0.0F;
				BlockState posState = world.getBlockState(downPos.add(x, 0, z));
				if (isValidStone(posState.getBlock()))
				{
					posFactor = 2.0F;
				}

				if (x != 0 || z != 0)
				{
					posFactor /= 4.0F;
				}

				factor += posFactor;
			}
		}

		BlockPos var12 = pos.north();
		BlockPos var13 = pos.south();
		BlockPos var15 = pos.west();
		BlockPos var14 = pos.east();
		boolean blocksEW = block == world.getBlockState(var15).getBlock() || block == world.getBlockState(var14).getBlock();
		boolean blocksNS = block == world.getBlockState(var12).getBlock() || block == world.getBlockState(var13).getBlock();
		if (blocksEW && blocksNS)
		{
			factor /= 2.0F;
		}
		else
		{
			boolean isSurrounded = block == world.getBlockState(var15.north()).getBlock() || block == world.getBlockState(var14.north()).getBlock() || block == world.getBlockState(var14.south()).getBlock() || block == world.getBlockState(var15.south()).getBlock();
			if (isSurrounded)
			{
				factor /= 2.0F;
			}
		}

		return factor;
	}

	public GourdBlock getMelonBlock()
	{
		return this.method_10694();
	}
}

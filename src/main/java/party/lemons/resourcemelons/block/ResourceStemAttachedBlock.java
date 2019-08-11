package party.lemons.resourcemelons.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.GourdBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class ResourceStemAttachedBlock extends AttachedStemBlock {
	private final ResourceStemBlock stem;
	
	public ResourceStemAttachedBlock(GourdBlock var1, ResourceStemBlock stem) {
		super(var1, FabricBlockSettings.of(Material.PLANT).collidable(false).ticksRandomly().hardness(0).dropsLike(stem).sounds(BlockSoundGroup.WOOD).build());
		this.stem = stem;
	}

	/*@Override
	public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
		System.out.println(stem.getGrowthFactor(world_1,blockPos_1));
		return super.activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
	}*/
}

package party.lemons.resourcemelons.block;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.GourdBlock;
import net.minecraft.block.Material;
import net.minecraft.block.StemAttachedBlock;
import net.minecraft.sound.BlockSoundGroup;

public class ResourceStemAttachedBlock extends StemAttachedBlock
{
	public ResourceStemAttachedBlock(GourdBlock var1, ResourceStemBlock stem)
	{
		super(var1, FabricBlockSettings.of(Material.PLANT).collidable(false).ticksRandomly().hardness(0).copyDropTable(stem).setSoundGroup(BlockSoundGroup.WOOD).build());
	}
}

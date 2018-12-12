package party.lemons.resourcemelons.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class MelonInfo
{
	private final Block stem, melon;
	private final Item seed, slice;

	public MelonInfo(Block stem, Block melon, Item seed, Item slice)
	{
		this.seed = seed;
		this.slice = slice;
		this.stem = stem;
		this.melon = melon;
	}

	public Block getStem()
	{
		return stem;
	}

	public Block getMelon()
	{
		return melon;
	}

	public Item getSeed()
	{
		return seed;
	}

	public Item getSlice()
	{
		return slice;
	}
}

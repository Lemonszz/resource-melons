package party.lemons.resourcemelons.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class MelonInfo {
	private final Block stem, melon, soil, aStem;
	private final Item seeds, slice;
	private final int colour;

	public MelonInfo(Block stem, Block aStem, Block melon, Item seeds, Item slice, Block soil, int colour) {
		this.seeds = seeds;
		this.slice = slice;
		this.stem = stem;
		this.melon = melon;
		this.soil = soil;
		this.colour = colour;
		this.aStem = aStem;
	}

	public Block getStem() {
		return stem;
	}

	public Block getAttachedStem() {
		return aStem;
	}

	public Block getMelon() {
		return melon;
	}

	public Block getSoil() {
		return soil;
	}

	public Item getSeeds() {
		return seeds;
	}

	public Item getSlice() {
		return slice;
	}

	public int getColour() {
		return colour;
	}
}

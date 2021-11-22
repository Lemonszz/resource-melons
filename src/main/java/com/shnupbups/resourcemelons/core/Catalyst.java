package com.shnupbups.resourcemelons.core;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tag.Tag;

public class Catalyst {
	private List<Block> blocks;
	private Tag<Block> tag;

	public Catalyst(Block... blocks) {
		this.blocks = Arrays.asList(blocks);
	}

	public Catalyst(Tag<Block> blocks) {
		this.tag = blocks;
	}

	public List<Block> getBlocks() {
		if (tag != null) return tag.values();
		else return blocks;
	}

	public boolean isValid(BlockState state) {
		if (tag != null) return state.isIn(tag);
		else return blocks.contains(state.getBlock());
	}
}

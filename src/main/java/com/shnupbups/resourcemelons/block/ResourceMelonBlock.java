package com.shnupbups.resourcemelons.block;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.GourdBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.item.Item;

import com.shnupbups.resourcemelons.core.MelonType;

public class ResourceMelonBlock extends GourdBlock {
	private MelonType type;

	public ResourceMelonBlock(Settings settings) {
		super(settings);
	}

	@Override
	public StemBlock getStem() {
		return type.stem();
	}

	@Override
	public AttachedStemBlock getAttachedStem() {
		return type.attachedStem();
	}

	public Item getSeeds() {
		return type.seeds();
	}

	public void setType(MelonType type) {
		this.type = type;
	}
}

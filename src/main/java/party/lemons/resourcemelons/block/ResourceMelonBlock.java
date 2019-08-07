package party.lemons.resourcemelons.block;

import net.minecraft.block.*;

public class ResourceMelonBlock extends GourdBlock {
	private ResourceStemBlock stem;
	private ResourceStemAttachedBlock stem_attached;

	public ResourceMelonBlock() {
		super(Block.Settings.of(Material.STONE, MaterialColor.STONE).strength(1F, 0F));
	}

	@Override
	public StemBlock getStem() {
		return stem;
	}

	@Override
	public AttachedStemBlock getAttachedStem() {
		return stem_attached;
	}

	public void setStem(ResourceStemBlock stem, ResourceStemAttachedBlock stem_attached) {
		this.stem = stem;
		this.stem_attached = stem_attached;
	}
}

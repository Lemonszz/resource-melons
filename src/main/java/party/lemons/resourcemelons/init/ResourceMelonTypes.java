package party.lemons.resourcemelons.init;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import party.lemons.resourcemelons.ResourceMelons;
import party.lemons.resourcemelons.block.ResourceMelonBlock;
import party.lemons.resourcemelons.block.ResourceStemAttachedBlock;
import party.lemons.resourcemelons.block.ResourceStemBlock;
import party.lemons.resourcemelons.item.ResourceMelonSliceItem;

public class ResourceMelonTypes
{
	public static MelonInfo DIAMOND_MELON, GOLD_MELON, EMERALD_MELON, IRON_MELON, LAPIS_MELON, REDSTONE_MELON, COAL_MELON;

	public static void init()
	{
		DIAMOND_MELON = createMelon("diamond", Items.DIAMOND);
		GOLD_MELON = createMelon("gold", Items.GOLD_INGOT);
		EMERALD_MELON = createMelon("emerald", Items.EMERALD);
		IRON_MELON = createMelon("iron", Items.IRON_INGOT);
		LAPIS_MELON = createMelon("lapis", Items.LAPIS_LAZULI);
		REDSTONE_MELON = createMelon("redstone", Items.REDSTONE);
		COAL_MELON = createMelon("coal", Items.COAL);
	}

	public static MelonInfo createMelon(String name, Item output)
	{
		ResourceMelonBlock melon = new ResourceMelonBlock();
		ResourceStemBlock stem = new ResourceStemBlock(melon);
		ResourceStemAttachedBlock stem_attached = new ResourceStemAttachedBlock(melon, stem);
		melon.setStem(stem, stem_attached);

		SeedsItem seed = new SeedsItem(stem, (new Item.Settings()).itemGroup(ItemGroup.MATERIALS));
		stem.setSeed(seed);
		ResourceMelonSliceItem slice = new ResourceMelonSliceItem(2, 0.3F, false, output, (new Item.Settings()).itemGroup(ItemGroup.FOOD));

		MelonInfo info = new MelonInfo(stem, melon, seed, slice);

		Registry.register(Registry.BLOCK, new Identifier(ResourceMelons.MODID, name + "_melon"), melon);
		createBlockItem(melon, name + "_melon", ItemGroup.FOOD);

		Registry.register(Registry.BLOCK, new Identifier(ResourceMelons.MODID, name + "_stem"), stem);
		Registry.register(Registry.BLOCK, new Identifier(ResourceMelons.MODID, name + "_stem_attached"), stem_attached);

		Registry.register(Registry.ITEM, new Identifier(ResourceMelons.MODID, name + "_seed"), seed);
		Registry.register(Registry.ITEM, new Identifier(ResourceMelons.MODID, name + "_slice"), slice);

		return info;
	}

	private static void createBlockItem(Block block, String name, ItemGroup group)
	{
		BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(group));
		item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);

		Registry.register(Registry.ITEM, new Identifier(ResourceMelons.MODID, name), item);
	}
}

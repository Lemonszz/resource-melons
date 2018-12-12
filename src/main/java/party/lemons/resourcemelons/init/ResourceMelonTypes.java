package party.lemons.resourcemelons.init;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.item.block.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import party.lemons.resourcemelons.ResourceMelons;
import party.lemons.resourcemelons.block.ResourceMelonBlock;
import party.lemons.resourcemelons.block.ResourceStemAttachedBlock;
import party.lemons.resourcemelons.block.ResourceStemBlock;

public class ResourceMelonTypes
{
	public static MelonInfo DIAMOND_MELON, GOLD_MELON, EMERALD_MELON, IRON_MELON, LAPIS_MELON, REDSTONE_MELON, COAL_MELON;

	public static void init()
	{
		DIAMOND_MELON = createMelon("diamond");
		GOLD_MELON = createMelon("gold");
		EMERALD_MELON = createMelon("emerald");
		IRON_MELON = createMelon("iron");
		LAPIS_MELON = createMelon("lapis");
		REDSTONE_MELON = createMelon("redstone");
		COAL_MELON = createMelon("coal");
	}

	public static MelonInfo createMelon(String name)
	{
		ResourceMelonBlock melon = new ResourceMelonBlock();
		ResourceStemBlock stem = new ResourceStemBlock(melon);
		ResourceStemAttachedBlock stem_attached = new ResourceStemAttachedBlock(melon, stem);
		melon.setStem(stem, stem_attached);

		SeedsItem seed = new SeedsItem(stem, (new Item.Settings()).itemGroup(ItemGroup.MATERIALS));
		stem.setSeed(seed);
		FoodItem slice = new FoodItem(2, 0.3F, false, (new Item.Settings()).itemGroup(ItemGroup.FOOD));

		MelonInfo info = new MelonInfo(stem, melon, seed, slice);

		Registry.register(Registry.BLOCKS, new Identifier(ResourceMelons.MODID, name + "_melon"), melon);
		createBlockItem(melon, name + "_melon", ItemGroup.FOOD);

		Registry.register(Registry.BLOCKS, new Identifier(ResourceMelons.MODID, name + "_stem"), stem);
		Registry.register(Registry.BLOCKS, new Identifier(ResourceMelons.MODID, name + "_stem_attached"), stem_attached);

		Registry.register(Registry.ITEMS, new Identifier(ResourceMelons.MODID, name + "_seed"), seed);
		Registry.register(Registry.ITEMS, new Identifier(ResourceMelons.MODID, name + "_slice"), slice);

		return info;
	}

	private static void createBlockItem(Block block, String name, ItemGroup group)
	{
		BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(group));
		item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);

		Registry.register(Registry.ITEMS, new Identifier(ResourceMelons.MODID, name), item);
	}
}

package party.lemons.resourcemelons.init;

import party.lemons.resourcemelons.ResourceMelons;
import party.lemons.resourcemelons.block.ResourceMelonBlock;
import party.lemons.resourcemelons.block.ResourceStemAttachedBlock;
import party.lemons.resourcemelons.block.ResourceStemBlock;
import party.lemons.resourcemelons.item.ResourceMelonSliceItem;
import party.lemons.resourcemelons.misc.MelonDispenserBehavior;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class ResourceMelonTypes {
	public static final FoodComponent MELON;
	public static MelonInfo DIAMOND_MELON, GOLD_MELON, EMERALD_MELON, IRON_MELON, LAPIS_MELON, REDSTONE_MELON, COAL_MELON, QUARTZ_MELON, GLOWSTONE_MELON;
	public static ArrayList<MelonInfo> MELONS = new ArrayList<>();
	
	static {
		FoodComponent.Builder builder = new FoodComponent.Builder();
		builder.alwaysEdible();
		builder.hunger(2);
		builder.saturationModifier(0.3f);
		MELON = builder.build();
	}
	
	public static void init() {
		DIAMOND_MELON = createMelon("diamond", Items.DIAMOND, Blocks.DIAMOND_BLOCK, 0x4AEDD9);
		GOLD_MELON = createMelon("gold", Items.GOLD_INGOT, Blocks.GOLD_BLOCK, 0xFDF55F);
		EMERALD_MELON = createMelon("emerald", Items.EMERALD, Blocks.EMERALD_BLOCK, 0x41F384);
		IRON_MELON = createMelon("iron", Items.IRON_INGOT, Blocks.IRON_BLOCK, 0xD8D8D8);
		LAPIS_MELON = createMelon("lapis", Items.LAPIS_LAZULI, Blocks.LAPIS_BLOCK, 0x7497EA);
		REDSTONE_MELON = createMelon("redstone", Items.REDSTONE, Blocks.REDSTONE_BLOCK, 0xFF0000);
		COAL_MELON = createMelon("coal", Items.COAL, Blocks.COAL_BLOCK, 0x2E2E2E);
		QUARTZ_MELON = createMelon("quartz", Items.QUARTZ, Blocks.QUARTZ_BLOCK, 0xEEE6DE);
		GLOWSTONE_MELON = createMelon("glowstone", Items.GLOWSTONE_DUST, Blocks.GLOWSTONE, 0xFFEC8E);
	}
	
	public static MelonInfo createMelon(String name, Item output, Block soil, int colour) {
		return createMelon(new Identifier(ResourceMelons.MODID, name), output, soil, colour);
	}
	
	public static MelonInfo createMelon(Identifier id, Item output, Block soil, int colour) {
		ResourceMelonBlock melon = new ResourceMelonBlock();
		ResourceStemBlock stem = new ResourceStemBlock(melon);
		ResourceStemAttachedBlock aStem = new ResourceStemAttachedBlock(melon, stem);
		melon.setStem(stem, aStem);
		
		AliasedBlockItem seeds = new AliasedBlockItem(stem, (new Item.Settings()).group(ResourceMelons.group));
		stem.setSeeds(seeds);
		stem.setSoil(soil);
		ResourceMelonSliceItem slice = new ResourceMelonSliceItem(output, (new Item.Settings()).group(ResourceMelons.group).food(MELON));
		
		MelonInfo info = new MelonInfo(stem, aStem, melon, seeds, slice, soil, colour);
		
		Registry.register(Registry.BLOCK, new Identifier(id.getNamespace(), id.getPath() + "_melon"), melon);
		createBlockItem(melon, new Identifier(id.getNamespace(), id.getPath() + "_melon"));
		
		Registry.register(Registry.BLOCK, new Identifier(id.getNamespace(), id.getPath() + "_stem"), stem);
		Registry.register(Registry.BLOCK, new Identifier(id.getNamespace(), id.getPath() + "_stem_attached"), aStem);
		
		Registry.register(Registry.ITEM, new Identifier(id.getNamespace(), id.getPath() + "_seeds"), seeds);
		Registry.register(Registry.ITEM, new Identifier(id.getNamespace(), id.getPath() + "_slice"), slice);
		
		DispenserBlock.registerBehavior(slice, new MelonDispenserBehavior());
		
		MELONS.add(info);
		return info;
	}
	
	private static void createBlockItem(Block block, Identifier id) {
		BlockItem item = new BlockItem(block, new Item.Settings().group(ResourceMelons.group));
		item.appendBlocks(Item.BLOCK_ITEMS, item);
		
		Registry.register(Registry.ITEM, new Identifier(id.getNamespace(), id.getPath()), item);
	}
}

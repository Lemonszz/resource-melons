package party.lemons.resourcemelons;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.world.loot.BinomialLootTableRange;
import net.minecraft.world.loot.ConstantLootTableRange;
import net.minecraft.world.loot.LootTableRange;
import net.minecraft.world.loot.UniformLootTableRange;
import net.minecraft.world.loot.entry.ItemEntry;
import net.minecraft.world.loot.entry.LootTableEntry;
import party.lemons.resourcemelons.init.ResourceMelonTypes;

public class ResourceMelons implements ModInitializer {
	public static final String MODID = "resourcemelons";

	public static ItemGroup group;

	@Override
	public void onInitialize() {
		group = FabricItemGroupBuilder.build(new Identifier(MODID, "group"), () -> new ItemStack(ResourceMelonTypes.DIAMOND_MELON.getSlice()));
		ResourceMelonTypes.init();

		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
			if (new Identifier("chests/abandoned_mineshaft").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f,2.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID,"chests/abandoned_mineshaft_inject")));
				supplier.withPool(poolBuilder);
				System.out.println("inject mineshaft");
			} else if (new Identifier("chests/buried_treasure").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f,2.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID,"chests/buried_treasure_inject")));
				supplier.withPool(poolBuilder);
				System.out.println("inject buried");
			} else if (new Identifier("chests/end_city_treasure").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f,5.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID,"chests/end_city_treasure_inject")));
				supplier.withPool(poolBuilder);
				System.out.println("inject end");
			} else if (new Identifier("chests/jungle_temple").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f,2.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID,"chests/jungle_temple_inject")));
				supplier.withPool(poolBuilder);
				System.out.println("inject jungle");
			} else if (new Identifier("chests/nether_bridge").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f,4.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID,"chests/nether_bridge_inject")));
				supplier.withPool(poolBuilder);
				System.out.println("inject nether");
			} else if (new Identifier("chests/shipwreck_treasure").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f,2.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID,"chests/shipwreck_treasure_inject")));
				supplier.withPool(poolBuilder);
				System.out.println("inject shipwreck");
			} else if (new Identifier("chests/simple_dungeon").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f,2.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID,"chests/simple_dungeon_inject")));
				supplier.withPool(poolBuilder);
				System.out.println("inject dungeon");
			} else if (new Identifier("chests/stronghold_corridor").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f,3.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID,"chests/stronghold_corridor_inject")));
				supplier.withPool(poolBuilder);
				System.out.println("inject stronghold");
			}
		});
	}
}

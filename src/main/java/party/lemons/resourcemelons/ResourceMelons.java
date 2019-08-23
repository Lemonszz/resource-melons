package party.lemons.resourcemelons;

import party.lemons.resourcemelons.init.ResourceMelonTypes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.loot.UniformLootTableRange;
import net.minecraft.world.loot.entry.LootTableEntry;

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
						.withRolls(UniformLootTableRange.between(0.0f, 1.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID, "chests/abandoned_mineshaft_inject")));
				supplier.withPool(poolBuilder);
			} else if (new Identifier("chests/buried_treasure").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f, 1.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID, "chests/buried_treasure_inject")));
				supplier.withPool(poolBuilder);
			} else if (new Identifier("chests/end_city_treasure").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f, 1.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID, "chests/end_city_treasure_inject")));
				supplier.withPool(poolBuilder);
			} else if (new Identifier("chests/jungle_temple").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f, 1.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID, "chests/jungle_temple_inject")));
				supplier.withPool(poolBuilder);
			} else if (new Identifier("chests/nether_bridge").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f, 1.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID, "chests/nether_bridge_inject")));
				supplier.withPool(poolBuilder);
			} else if (new Identifier("chests/shipwreck_treasure").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f, 1.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID, "chests/shipwreck_treasure_inject")));
				supplier.withPool(poolBuilder);
			} else if (new Identifier("chests/simple_dungeon").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f, 1.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID, "chests/simple_dungeon_inject")));
				supplier.withPool(poolBuilder);
			} else if (new Identifier("chests/stronghold_corridor").equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0.0f, 1.0f))
						.withEntry(LootTableEntry.builder(new Identifier(MODID, "chests/stronghold_corridor_inject")));
				supplier.withPool(poolBuilder);
			}
		});
	}
}

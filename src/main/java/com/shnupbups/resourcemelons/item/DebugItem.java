package com.shnupbups.resourcemelons.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import com.shnupbups.resourcemelons.block.ResourceMelonStemBlock;

public class DebugItem extends Item {
	public DebugItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		if (!world.isClient() && world.getBlockState(pos).getBlock() instanceof ResourceMelonStemBlock stem) {
			PlayerEntity player = context.getPlayer();
			if (world.isSkyVisible(pos.up())) {
				player.sendMessage(new LiteralText("Sky visible, can't grow!"), false);
			} else if (!stem.isCatalyst(world.getBlockState(pos.down(2)))) {
				player.sendMessage(new LiteralText("No catalyst, can't grow!"), false);
			} else {
				int moisture = ResourceMelonStemBlock.getMoisture(world, pos);
				float moistureGrowthModifier = ResourceMelonStemBlock.getMoistureGrowthModifier(world, pos);
				boolean moistureGrowthModifierEnabled = ResourceMelonStemBlock.getGrowthChanceModifiers().moisture().enabled();
				int secondaryCatalystCount = stem.getSecondaryCatalystCount(world, pos);
				float secondaryCatalystGrowthModifier = stem.getSecondaryCatalystGrowthModifier(world, pos);
				boolean secondaryCatalystGrowthModifierEnabled = ResourceMelonStemBlock.getGrowthChanceModifiers().secondaryCatalysts().enabled();
				int light = world.getLightLevel(pos);
				float lightGrowthModifier = ResourceMelonStemBlock.getLightGrowthModifier(world, pos);
				boolean lightGrowthModifierEnabled = ResourceMelonStemBlock.getGrowthChanceModifiers().light().enabled();
				int skyLight = world.getLightLevel(LightType.SKY, pos);
				float skyLightGrowthModifier = ResourceMelonStemBlock.getSkyLightGrowthModifier(world, pos);
				boolean skyLightGrowthModifierEnabled = ResourceMelonStemBlock.getGrowthChanceModifiers().skyLight().enabled();
				boolean allCatalysts = stem.hasAllSecondaryCatalysts(world, pos);
				float allCatalystsGrowthModifier = stem.getAllSecondaryCatalystsGrowthModifier(world, pos);
				boolean allCatalystsGrowthModifierEnabled = ResourceMelonStemBlock.getGrowthChanceModifiers().allSecondaryCatalysts().enabled();
				float totalGrowthModifier = stem.getTotalGrowthModifier(world, pos);
				float baseGrowthChance = ResourceMelonStemBlock.getBaseGrowthChance();
				float totalGrowthChance = stem.getGrowthChance(world, pos);
				if (moistureGrowthModifierEnabled)
					player.sendMessage(new LiteralText("Moisture: " + moisture + " Modifier: " + moistureGrowthModifier), false);
				if (secondaryCatalystGrowthModifierEnabled)
					player.sendMessage(new LiteralText("Secondary Catalysts: " + secondaryCatalystCount + " Modifier: " + secondaryCatalystGrowthModifier), false);
				if (lightGrowthModifierEnabled)
					player.sendMessage(new LiteralText("Light: " + light + " Modifier: " + lightGrowthModifier), false);
				if (skyLightGrowthModifierEnabled)
					player.sendMessage(new LiteralText("Sky Light: " + skyLight + " Modifier: " + skyLightGrowthModifier), false);
				if (allCatalystsGrowthModifierEnabled)
					player.sendMessage(new LiteralText("All Catalysts: " + allCatalysts + " Modifier: " + allCatalystsGrowthModifier), false);
				player.sendMessage(new LiteralText("Total Growth Modifier: " + totalGrowthModifier), false);
				player.sendMessage(new LiteralText(
						"Growth Modifier Calculation: 1.0" +
								(moistureGrowthModifierEnabled ? " + " + moistureGrowthModifier : "") +
								(secondaryCatalystGrowthModifierEnabled ? " + " + secondaryCatalystGrowthModifier : "") +
								(lightGrowthModifierEnabled ? " * " + lightGrowthModifier : "") +
								(skyLightGrowthModifierEnabled ? " * " + skyLightGrowthModifier : "") +
								(allCatalystsGrowthModifierEnabled ? " * " + allCatalystsGrowthModifier : "") +
								" = " + totalGrowthModifier
				), false);
				player.sendMessage(new LiteralText("Base Growth Chance: 1 in " + baseGrowthChance), false);
				player.sendMessage(new LiteralText("Total Growth Chance: 1 in " + totalGrowthChance), false);
				player.sendMessage(new LiteralText(
						"Growth Chance Calculation: (int) 1.0" +
								" + " + baseGrowthChance +
								" / " + totalGrowthModifier +
								" = " + totalGrowthChance
				), false);
			}
			return ActionResult.CONSUME;
		} else return ActionResult.PASS;
	}
}

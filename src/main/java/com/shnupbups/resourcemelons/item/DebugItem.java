package com.shnupbups.resourcemelons.item;

import com.shnupbups.resourcemelons.block.ResourceStemBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class DebugItem extends Item {
    public DebugItem(Settings settings) {
        super(settings);
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        if(!world.isClient() && world.getBlockState(pos).getBlock() instanceof ResourceStemBlock stem) {
            PlayerEntity player = context.getPlayer();
            if(world.isSkyVisible(pos.up())) {
                player.sendMessage(new LiteralText("Sky visible, can't grow!"), false);
            } else if(!stem.isCatalyst(world.getBlockState(pos.down(2)))) {
                player.sendMessage(new LiteralText("No catalyst, can't grow!"), false);
            } else {
                int moisture = stem.getMoisture(world, pos);
                float moistureGrowthModifier = stem.getMoistureGrowthModifier(world, pos);
                int secondaryCatalystCount = stem.getSecondaryCatalystCount(world, pos);
                float secondaryCatalystGrowthModifier = stem.getSecondaryCatalystGrowthModifier(world, pos);
                int light = world.getLightLevel(pos);
                float lightGrowthModifier = stem.getLightGrowthModifier(world, pos);
                int skyLight = world.getLightLevel(LightType.SKY, pos);
                float skyLightGrowthModifier = stem.getSkyLightGrowthModifier(world, pos);
                boolean allCatalysts = stem.hasAllCatalysts(world, pos);
                float allCatalystsGrowthModifier = stem.getAllCatalystsGrowthModifier(world, pos);
                float totalGrowthModifier = stem.getTotalGrowthModifier(world, pos);
                float growthChance = stem.getGrowthChance(world, pos);
                player.sendMessage(new LiteralText("Moisture: "+moisture+" Modifier: "+moistureGrowthModifier), false);
                player.sendMessage(new LiteralText("Secondary Catalysts: "+secondaryCatalystCount+" Modifier: "+secondaryCatalystGrowthModifier), false);
                player.sendMessage(new LiteralText("Light: "+light+" Modifier: "+lightGrowthModifier), false);
                player.sendMessage(new LiteralText("Sky Light: "+skyLight+" Modifier: "+skyLightGrowthModifier), false);
                player.sendMessage(new LiteralText("All Catalysts: "+allCatalysts+" Modifier: "+allCatalystsGrowthModifier), false);
                player.sendMessage(new LiteralText("Total Growth Modifier: "+totalGrowthModifier), false);
                player.sendMessage(new LiteralText(
                        "Calculation: 1.0"+
                                " + "+moistureGrowthModifier+
                                " + "+secondaryCatalystGrowthModifier+
                                " * "+lightGrowthModifier+
                                " * "+skyLightGrowthModifier+
                                " * "+allCatalystsGrowthModifier+
                                " = "+totalGrowthModifier
                ), false);
                player.sendMessage(new LiteralText("Growth Chance: 1 in "+growthChance), false);
            }
            return ActionResult.CONSUME;
        } else return ActionResult.PASS;
    }
}

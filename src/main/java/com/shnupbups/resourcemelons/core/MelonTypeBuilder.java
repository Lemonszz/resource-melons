package com.shnupbups.resourcemelons.core;

import com.shnupbups.resourcemelons.ResourceMelons;
import com.shnupbups.resourcemelons.block.ResourceAttachedStemBlock;
import com.shnupbups.resourcemelons.block.ResourceMelonBlock;
import com.shnupbups.resourcemelons.block.ResourceStemBlock;
import com.shnupbups.resourcemelons.item.ResourceMelonSliceItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public interface MelonTypeBuilder {
    Info getInfo();
    record Info(Identifier id, Item resource, Catalyst catalyst, int colour) {}
    default MelonType build() {
        ResourceMelonBlock melon = new ResourceMelonBlock(getMelonSettings());
        ResourceStemBlock stem = new ResourceStemBlock(melon, melon::getSeeds, getInfo().catalyst(), getStemSettings());
        ResourceAttachedStemBlock attachedStem = new ResourceAttachedStemBlock(melon, melon::getSeeds, getAttachedStemSettings());

        AliasedBlockItem seeds = new AliasedBlockItem(stem, new FabricItemSettings().group(ResourceMelons.group));
        ResourceMelonSliceItem slice = new ResourceMelonSliceItem(getInfo().resource(), seeds, new Item.Settings().group(ResourceMelons.group).food(ResourceMelons.melonFoodComponent));

        MelonType type = new MelonType(getInfo().id(), stem, attachedStem, melon, seeds, slice, getInfo().catalyst(), getInfo().colour());

        melon.setType(type);
        this.setType(type);
        return type;
    }

    void setType(MelonType type);

    MelonType getTypeRaw();

    default MelonType getType() {
        if(getTypeRaw() == null) return build();
        else return getTypeRaw();
    }

    static FabricBlockSettings getMelonSettings() {
        return FabricBlockSettings.of(Material.GOURD).strength(1.0F).sounds(BlockSoundGroup.STONE).requiresTool();
    }

    static FabricBlockSettings getStemSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.STEM).requiresTool();
    }

    static FabricBlockSettings getAttachedStemSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.STONE).requiresTool();
    }
}

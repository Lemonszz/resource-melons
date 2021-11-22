package com.shnupbups.resourcemelons;

import net.minecraft.client.render.RenderLayer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import com.shnupbups.resourcemelons.core.MelonType;

@SuppressWarnings("unused")
public class ResourceMelonsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		for (MelonType melon : ResourceMelons.MELONS) {
			ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> melon.colour(), melon.stem(), melon.attachedStem());
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), melon.stem(), melon.attachedStem());
		}
	}
}

package com.shnupbups.resourcemelons;

import com.shnupbups.resourcemelons.core.MelonType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;

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

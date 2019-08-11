package party.lemons.resourcemelons;

import party.lemons.resourcemelons.init.MelonInfo;
import party.lemons.resourcemelons.init.ResourceMelonTypes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;

@SuppressWarnings("unused")
public class ResourceMelonsClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		for (MelonInfo melon : ResourceMelonTypes.MELONS) {
			ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> {
				return melon.getColour();
			}, melon.getStem(), melon.getAttachedStem());
		}
	}
}

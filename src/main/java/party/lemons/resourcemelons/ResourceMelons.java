package party.lemons.resourcemelons;

import net.fabricmc.api.ModInitializer;
import party.lemons.resourcemelons.init.ResourceMelonTypes;

public class ResourceMelons implements ModInitializer
{
	public static final String MODID = "resourcemelons";

	@Override
	public void onInitialize()
	{
		ResourceMelonTypes.init();
	}
}

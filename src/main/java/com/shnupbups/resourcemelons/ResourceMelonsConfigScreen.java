package com.shnupbups.resourcemelons;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.autoconfig.AutoConfig;

import com.shnupbups.resourcemelons.config.ResourceMelonsConfig;

public class ResourceMelonsConfigScreen implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> AutoConfig.getConfigScreen(ResourceMelonsConfig.class, parent).get();
	}
}
